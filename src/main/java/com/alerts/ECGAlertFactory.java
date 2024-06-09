package com.alerts;

public class ECGAlertFactory extends AlertFactory{
  @Override
  public GenericAlert createAlert(String patientId, String condition, long timestamp) {
    return new ECGAlert(patientId, condition, timestamp);
  }
}
