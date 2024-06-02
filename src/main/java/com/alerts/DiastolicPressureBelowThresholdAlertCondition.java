package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;

public class DiastolicPressureBelowThresholdAlertCondition implements AlertCondition{

  @Override
  public String getAlertDescription() {
    return "Diastolic blood pressure below 60 mmHg";
  }

  @Override
  public boolean isAlertConditionMet(Patient patient) {
    PatientRecord lastRecord = patient.getLastRecord("DiastolicPressure");
    if(lastRecord == null)
    {
      // We have no data from monitors of this type in the last 30 sec
      return false;
    }

    return lastRecord.getMeasurementValue() < 60;
  }
}
