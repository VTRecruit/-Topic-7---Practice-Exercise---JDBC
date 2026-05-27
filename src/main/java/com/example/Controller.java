/*
Name: Umandap, Sam Matthew B.
Section: BSIT 2-2
File Name: Controller.java
*/

package com.example;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.*;

public class Controller {

    @FXML private TextField txtName;
    @FXML private TextField txtCourse;
    @FXML private ChoiceBox<YearLevel> cbYear;

    @FXML private TableView<Student> table;

    @FXML private TableColumn<Student, Integer> colId;
    @FXML private TableColumn<Student, String> colName;
    @FXML private TableColumn<Student, String> colCourse;
    @FXML private TableColumn<Student, String> colYear;

    private ObservableList<Student> list =
            FXCollections.observableArrayList();

    private Connection conn;

    private int selectedId = -1;

    @FXML
    public void initialize() {

        conn = DBConnection.connect();

        cbYear.getItems().setAll(YearLevel.values());

        colId.setCellValueFactory(
                data -> data.getValue().idProperty().asObject()
        );

        colName.setCellValueFactory(
                data -> data.getValue().nameProperty()
        );

        colCourse.setCellValueFactory(
                data -> data.getValue().courseProperty()
        );

        colYear.setCellValueFactory(
                data -> data.getValue().yearLevelProperty()
        );

        loadData();

        table.setOnMouseClicked(e -> {

            Student s =
                    table.getSelectionModel().getSelectedItem();

            if (s != null) {

                selectedId = s.getId();

                txtName.setText(s.getName());
                txtCourse.setText(s.getCourse());

                for (YearLevel y : YearLevel.values()) {

                    if (y.toString().equals(s.getYearLevel())) {

                        cbYear.setValue(y);
                    }
                }
            }
        });
    }

    private void loadData() {

        list.clear();

        try {

            String query = "SELECT * FROM students";

            ResultSet rs =
                    conn.createStatement().executeQuery(query);

            while (rs.next()) {

                list.add(
                        new Student(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("course"),
                                rs.getString("year_level")
                        )
                );
            }

            table.setItems(list);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private boolean validateInput() {

        if (txtName.getText().isEmpty()
                || txtCourse.getText().isEmpty()
                || cbYear.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Validation Error");

            alert.setHeaderText(null);

            alert.setContentText(
                    "Please fill in all fields."
            );

            alert.showAndWait();

            return false;
        }

        return true;
    }

    @FXML
    private void addStudent() {

        if (!validateInput()) {
            return;
        }

        try {

            String query =
                    "INSERT INTO students(name, course, year_level) VALUES (?, ?, ?)";

            PreparedStatement pst =
                    conn.prepareStatement(query);

            pst.setString(1, txtName.getText());
            pst.setString(2, txtCourse.getText());
            pst.setString(3, cbYear.getValue().toString());

            pst.executeUpdate();

            loadData();
            clearFields();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void updateStudent() {

        if (selectedId == -1) {

            Alert alert =
                    new Alert(Alert.AlertType.WARNING);

            alert.setContentText(
                    "Select a student first."
            );

            alert.showAndWait();

            return;
        }

        if (!validateInput()) {
            return;
        }

        try {

            String query =
                    "UPDATE students SET name=?, course=?, year_level=? WHERE id=?";

            PreparedStatement pst =
                    conn.prepareStatement(query);

            pst.setString(1, txtName.getText());
            pst.setString(2, txtCourse.getText());
            pst.setString(3, cbYear.getValue().toString());
            pst.setInt(4, selectedId);

            pst.executeUpdate();

            loadData();
            clearFields();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void deleteStudent() {

        if (selectedId == -1) {

            Alert alert =
                    new Alert(Alert.AlertType.WARNING);

            alert.setContentText(
                    "Select a student first."
            );

            alert.showAndWait();

            return;
        }

        try {

            String query =
                    "DELETE FROM students WHERE id=?";

            PreparedStatement pst =
                    conn.prepareStatement(query);

            pst.setInt(1, selectedId);

            pst.executeUpdate();

            loadData();
            clearFields();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void clearFields() {

        txtName.clear();
        txtCourse.clear();
        cbYear.setValue(null);

        selectedId = -1;
    }
}