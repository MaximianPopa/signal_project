package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.List;

import static com.alerts.TrendUtilities.*;

public class DiastolicBloodPressureDecreaseTrendAlertCondition implements AlertCondition{

  @Override
  public String getAlertDescription() {
    return "Diastolic blood pressure decrease by more than 10 mmHg";
  }

  @Override
  public boolean isAlertConditionMet(Patient patient) {
    List<PatientRecord> lastRecords = patient.getLastNRecords("DiastolicPressure", NUMBER_OF_RECORDS);
    return isDecreasingTrend(lastRecords);
  }

  @Override
  public AlertFactory getAlertFactory() {
    return new BloodPressureAlertFactory();
  }
}
