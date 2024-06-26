package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;

public class TriggeredAlertAlertStrategy implements AlertStrategy {

  private static final double ALERT_UNTRIGGERED = 0;

  @Override
  public String getAlertDescription() {
    return "The alert button was triggered";
  }

  @Override
  public boolean checkAlert(Patient patient) {
    PatientRecord lastAlertRecord = patient.getLastRecord("Alert");

    if(lastAlertRecord == null)
    {
      return false;
    }
    else
    {
      double value = lastAlertRecord.getMeasurementValue();
      return value != ALERT_UNTRIGGERED;
    }

  }

  @Override
  public AlertFactory getAlertFactory() {
    return new AlertFactory();
  }


}
