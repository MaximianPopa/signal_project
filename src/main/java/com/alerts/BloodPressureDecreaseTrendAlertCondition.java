package com.alerts;

import com.data_management.Patient;

public class BloodPressureDecreaseTrendAlertCondition implements AlertCondition{

  @Override
  public String getAlertDescription() {
    return "Blood pressure decrease by more than 10 mmHg";
  }

  @Override
  public boolean isAlertConditionMet(Patient patient) {
    // TODO: implement method
    return false;
  }
}
