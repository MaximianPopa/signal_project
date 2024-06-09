package com.alerts;

import com.data_management.Patient;

public interface AlertStrategy {

  String getAlertDescription();

  boolean checkAlert(Patient patient);

  AlertFactory getAlertFactory();

}
