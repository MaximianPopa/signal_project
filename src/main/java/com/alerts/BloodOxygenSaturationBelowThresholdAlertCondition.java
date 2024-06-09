package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;

public class BloodOxygenSaturationBelowThresholdAlertCondition implements AlertCondition{

  @Override
  public String getAlertDescription() {
    return "Blood oxygen saturation level falls below 92%";
  }

  @Override
  public boolean isAlertConditionMet(Patient patient) {
    PatientRecord lastRecord = patient.getLastRecord("Saturation");
    if(lastRecord == null)
    {
      // We have no data from monitors of this type in the last 30 sec
      return false;
    }

    return lastRecord.getMeasurementValue() < 92;
  }

  @Override
  public AlertFactory getAlertFactory() {
    return new BloodOxygenAlertFactory();
  }

}
