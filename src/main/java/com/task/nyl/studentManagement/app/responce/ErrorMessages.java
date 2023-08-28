package com.task.nyl.studentManagement.app.responce;

public enum ErrorMessages {

    MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
    RECORD_ALREADY_EXISTS("Record already exists"),
    EMAIL_ALREADY_EXISTS("Record already exists"),
    UNABLE_TO_FIND_USER("Unable to find this student"),
    INTERNAL_SERVER_ERROR("Internal server error"),
    NO_RECORD_FOUND("Record with provided id is not found"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified"),
    UNABLE_TO_FIND_ADDRESS("Unable to find address for this student"),
    UNABLE_TO_FIND_COURSES("Unable to find courses  for this student"),
    UNABLE_TO_FIND_STUDENT_BY_EMAIL("Unable to find student by this email ID");


    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
