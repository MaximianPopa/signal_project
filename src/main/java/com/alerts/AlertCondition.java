package com.alerts;

import com.data_management.Patient;

public interface AlertCondition {

  String getAlertDescription();

  boolean isAlertConditionMet(Patient patient);

  AlertFactory getAlertFactory();

}
