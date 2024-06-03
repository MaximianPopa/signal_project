package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.List;

import static com.alerts.TrendUtilities.*;

public class DiastolicBloodPressureIncreaseTrendAlertCondition implements AlertCondition{

  @Override
  public String getAlertDescription() {
    return "Diastolic blood pressure increase by more than 10 mmHg";
  }

  @Override
  public boolean isAlertConditionMet(Patient patient) {
    List<PatientRecord> lastRecords = patient.getLastNRecords("DiastolicPressure", NUMBER_OF_RECORDS);
    return isIncreasingTrend(lastRecords);
  }
}