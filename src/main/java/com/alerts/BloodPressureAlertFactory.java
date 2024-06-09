package com.alerts;

public class BloodPressureAlertFactory extends AlertFactory{
  @Override
  public GenericAlert createAlert(String patientId, String condition, long timestamp) {
    return new BloodPressureAlert(patientId, condition, timestamp);
  }
}
