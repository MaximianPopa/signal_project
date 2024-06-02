package com.alerts;

import com.data_management.Patient;

public class DiastolicPressureAboveThresholdAlertCondition implements AlertCondition{

  @Override
  public String getAlertDescription() {
    return "Diastolic blood pressure above 120 mmHg";
  }

  @Override
  public boolean isAlertConditionMet(Patient patient) {
    //TODO: implement method
    return false;
  }
}
