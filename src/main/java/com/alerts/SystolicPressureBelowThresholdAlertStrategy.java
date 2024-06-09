package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;

public class SystolicPressureBelowThresholdAlertStrategy implements AlertStrategy {

  @Override
  public String getAlertDescription() {
    return "Systolic blood pressure below 90 mmHg";
  }

  @Override
  public boolean checkAlert(Patient patient) {
    PatientRecord lastRecord = patient.getLastRecord("SystolicPressure");
    if(lastRecord == null)
    {
      // We have no data from monitors of this type in the last 30 sec
      return false;
    }

    return lastRecord.getMeasurementValue() < 90;
  }

  @Override
  public AlertFactory getAlertFactory() {
    return new BloodPressureAlertFactory();
  }
}
