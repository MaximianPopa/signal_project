package com.data_management;

/**
 * Indicates an issue occurred while parsing a patient record due to incorrect formatting or content
 */
public class PatientRecordFormatException extends RuntimeException {
    /**
     * Catches the error and throws an exception whenever the patient record format is incorrect
     *
     * @param message The message thrown in case of exception
     * @param cause The cause of exception
     */
    public PatientRecordFormatException(String message, Exception cause) {
        super(message, cause);
    }
}
