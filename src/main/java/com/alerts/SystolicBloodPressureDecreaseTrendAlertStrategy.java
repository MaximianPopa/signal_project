package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.List;

import static com.alerts.TrendUtilities.*;

public class SystolicBloodPressureDecreaseTrendAlertStrategy implements AlertStrategy {

  @Override
  public String getAlertDescription() {
    return "Systolic blood pressure decrease by more than 10 mmHg";
  }

  @Override
  public boolean checkAlert(Patient patient) {
    List<PatientRecord> lastRecords = patient.getLastNRecords("SystolicPressure", NUMBER_OF_RECORDS);
    return isDecreasingTrend(lastRecords);
  }

  @Override
  public AlertFactory getAlertFactory() {
    return new BloodPressureAlertFactory();
  }
}
