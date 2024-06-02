package com.alerts;

import com.data_management.Patient;

public class BloodPressureIncreaseTrendAlertCondition implements AlertCondition{

  @Override
  public String getAlertDescription() {
    return "Blood pressure increase by more than 10 mmHg";
  }

  @Override
  public boolean isAlertConditionMet(Patient patient) {
    //TODO: implement method
    return false;
  }
}
