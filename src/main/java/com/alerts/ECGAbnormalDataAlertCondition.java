package com.alerts;

import com.data_management.Patient;

public class ECGAbnormalDataAlertCondition implements AlertCondition{

  @Override
  public String getAlertDescription() {
    return "Peaks above certain values happen";
  }

  @Override
  public boolean isAlertConditionMet(Patient patient) {
    //TODO: implement method
    return false;
  }
}
