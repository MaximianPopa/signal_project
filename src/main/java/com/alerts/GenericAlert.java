package com.alerts;

// Represents an alert
public class GenericAlert implements Alert{
    private String patientId;
    private String condition;
    private long timestamp;

    public GenericAlert(String patientId, String condition, long timestamp) {
        this.patientId = patientId;
        this.condition = condition;
        this.timestamp = timestamp;
    }

    public GenericAlert(int patientId, String condition) {
        this(String.valueOf(patientId), condition, System.currentTimeMillis());
    }

    @Override
    public String getPatientId() {
        return patientId;
    }
    @Override
    public String getCondition() {
        return condition;
    }
    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Alert{" +
          "patientId='" + patientId + '\'' +
          ", condition='" + condition + '\'' +
          ", timestamp=" + timestamp +
          '}';
    }
}
