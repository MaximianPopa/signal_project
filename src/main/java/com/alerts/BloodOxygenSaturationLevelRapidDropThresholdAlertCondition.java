package com.alerts;

import com.data_management.Patient;

public class BloodOxygenSaturationLevelRapidDropThresholdAlertCondition implements AlertCondition{

  @Override
  public String getAlertDescription() {
    return "Blood oxygen saturation level drops by 5% or more within a 10-minute interval";
  }

  @Override
  public boolean isAlertConditionMet(Patient patient) {
    //TODO: implement method
    return false;
  }
}
