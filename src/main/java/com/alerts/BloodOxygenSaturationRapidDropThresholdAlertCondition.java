package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.List;

import static java.lang.System.currentTimeMillis;

public class BloodOxygenSaturationRapidDropThresholdAlertCondition implements AlertCondition{

  @Override
  public String getAlertDescription() {
    return "Blood oxygen saturation level drops by 5% or more within a 10-minute interval";
  }

  @Override
  public boolean isAlertConditionMet(Patient patient) {
    List<PatientRecord> lastRecords = patient.getRecords(currentTimeMillis() - 60_000,
      currentTimeMillis(), "Saturation");

    if(lastRecords.size() < 2) {
      return false;
    }

    double minValue = getMinRecordValue(lastRecords);
    double maxValue = getMaxRecordValue(lastRecords);

    double dropPercentage = maxValue - minValue;
    return dropPercentage >= 5;
  }

  private static double getMinRecordValue(List<PatientRecord> records){
    double min = records.getFirst().getMeasurementValue();
    for (PatientRecord record : records) {
      if(min > record.getMeasurementValue())
      {
        min = record.getMeasurementValue();
      }
    }
    return min;
  }

  private static double getMaxRecordValue(List<PatientRecord> records){
    double max = records.getFirst().getMeasurementValue();
    for (PatientRecord record : records) {
      if(max < record.getMeasurementValue())
      {
        max = record.getMeasurementValue();
      }
    }
    return max;
  }

}
