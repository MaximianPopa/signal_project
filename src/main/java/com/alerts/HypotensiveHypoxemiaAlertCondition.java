package com.alerts;

import com.data_management.Patient;

public class HypotensiveHypoxemiaAlertCondition implements AlertCondition{

  @Override
  public String getAlertDescription() {
    return "Systolic blood pressure is below 90 mmHg and blood oxygen saturation falls below 92%";
  }

  @Override
  public boolean isAlertConditionMet(Patient patient) {
    //TODO: implement method
    return false;
  }
}
