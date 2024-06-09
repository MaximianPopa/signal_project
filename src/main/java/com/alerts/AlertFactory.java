package com.alerts;

public class AlertFactory {

  public GenericAlert createAlert(String patientId, String condition, long timestamp) {
    return new GenericAlert(patientId, condition, timestamp);
  }

}