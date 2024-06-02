package com.alerts;

import com.data_management.Patient;

public class BloodOxygenSaturationLevelBelowThresholdAlertCondition implements AlertCondition{

  @Override
  public String getAlertDescription() {
    return "Blood oxygen saturation level falls below 92%";
  }

  @Override
  public boolean isAlertConditionMet(Patient patient) {
    //TODO: implement method
    return false;
  }
}
