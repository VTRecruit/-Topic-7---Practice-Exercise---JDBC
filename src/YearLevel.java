/*
Name: Umandap, Sam Matthew B.
Section: BSIT 2-2
File Name: YearLevel.java
*/

package com.example;

public enum YearLevel {

    FIRST_YEAR,
    SECOND_YEAR,
    THIRD_YEAR,
    FOURTH_YEAR;

    @Override
    public String toString() {

        switch (this) {

            case FIRST_YEAR:
                return "1st Year";

            case SECOND_YEAR:
                return "2nd Year";

            case THIRD_YEAR:
                return "3rd Year";

            case FOURTH_YEAR:
                return "4th Year";

            default:
                return "";
        }
    }
}
