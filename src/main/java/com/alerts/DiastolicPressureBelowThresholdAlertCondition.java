package com.alerts;

import com.data_management.Patient;

public class DiastolicPressureBelowThresholdAlertCondition implements AlertCondition{

  @Override
  public String getAlertDescription() {
    return "Diastolic blood pressure below 60 mmHg";
  }

  @Override
  public boolean isAlertConditionMet(Patient patient) {
    //TODO: implement method
    return false;
  }
}
