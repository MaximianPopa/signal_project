package com.data_management;

public class PatientRecordFormatException extends RuntimeException {
    public PatientRecordFormatException(String message, Exception cause) {
        super(message, cause);
    }
}
