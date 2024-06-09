package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;

public class DiastolicPressureAboveThresholdAlertStrategy implements AlertStrategy {

  @Override
  public String getAlertDescription() {
    return "Diastolic blood pressure above 120 mmHg";
  }

  @Override
  public boolean checkAlert(Patient patient) {
    PatientRecord lastRecord = patient.getLastRecord("DiastolicPressure");
    if(lastRecord == null)
    {
      // We have no data from monitors of this type in the last 30 sec
      return false;
    }

    return lastRecord.getMeasurementValue() > 120;
  }

  @Override
  public AlertFactory getAlertFactory() {
    return new BloodPressureAlertFactory();
  }

}
