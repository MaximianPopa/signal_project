package com.alerts;

import com.data_management.Patient;

public class TriggeredAlertAlertCondition implements AlertCondition{

  @Override
  public String getAlertDescription() {
    return "The alert button was triggered";
  }

  @Override
  public boolean isAlertConditionMet(Patient patient) {
    //TODO: implement method
    return false;
  }
}
