package com.alerts;

public class BloodOxygenAlertFactory extends AlertFactory{
  @Override
  public GenericAlert createAlert(String patientId, String condition, long timestamp) {
    return new BloodOxygenAlert(patientId, condition, timestamp);
  }
}
