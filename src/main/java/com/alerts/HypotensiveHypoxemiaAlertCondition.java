package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;

public class HypotensiveHypoxemiaAlertCondition implements AlertCondition{

  @Override
  public String getAlertDescription() {
    return "Systolic blood pressure is below 90 mmHg and blood oxygen saturation falls below 92%";
  }

  @Override
  public boolean isAlertConditionMet(Patient patient) {
    PatientRecord systolicLastRecord = patient.getLastRecord("SystolicPressure");
    PatientRecord bloodOxygenLastRecord = patient.getLastRecord("Saturation");
    if(systolicLastRecord == null || bloodOxygenLastRecord == null)
    {
      // We have no data from monitors of this type in the last 30 sec
      return false;
    }

    return systolicLastRecord.getMeasurementValue() < 90 &&
      bloodOxygenLastRecord.getMeasurementValue() < 92;
  }
}
