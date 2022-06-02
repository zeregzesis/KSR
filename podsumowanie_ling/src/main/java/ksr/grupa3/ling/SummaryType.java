package ksr.grupa3.ling;

public enum SummaryType {
    SINGLE_SUBJECT(0),
    FIRST_FORM(1),
    SECOND_FORM(2),
    THIRD_FORM(3),
    FOURTH_FORM(4);

    private int value;

    SummaryType(int value) {
        this.value = value;
    }
}
