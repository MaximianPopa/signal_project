package com.alerts;

import com.data_management.Patient;

public class SystolicPressureAboveThresholdAlertCondition implements AlertCondition{

  @Override
  public String getAlertDescription() {
    return "SSystolic blood pressure above 180 mmHg";
  }

  @Override
  public boolean isAlertConditionMet(Patient patient) {
    //TODO: implement method
    return false;
  }
}
