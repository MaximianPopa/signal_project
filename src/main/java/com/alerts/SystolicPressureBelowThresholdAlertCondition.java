package com.alerts;

import com.data_management.Patient;

public class SystolicPressureBelowThresholdAlertCondition implements AlertCondition{

  @Override
  public String getAlertDescription() {
    return "Systolic blood pressure below 90 mmHg";
  }

  @Override
  public boolean isAlertConditionMet(Patient patient) {
    //TODO: implement method
    return false;
  }
}
