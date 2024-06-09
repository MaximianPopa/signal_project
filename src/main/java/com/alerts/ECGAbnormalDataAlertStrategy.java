package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.List;

import static java.lang.System.currentTimeMillis;

public class ECGAbnormalDataAlertStrategy implements AlertStrategy {

  private static final long MOVING_WINDOW_DURATION_MILLIS = 180_000;

  @Override
  public String getAlertDescription() {
    return "Peaks above certain values happen";
  }

  @Override
  public boolean checkAlert(Patient patient) {
    List<PatientRecord> lastRecords = patient.getRecords(currentTimeMillis() - MOVING_WINDOW_DURATION_MILLIS,
      currentTimeMillis(), "ECG");

    if(lastRecords.size() < 2)
    {
      return false;
    }

    // We don't want to include the last value in avg calc
    double averageOfSlidingWindow = getAverageValue(lastRecords.subList(0, lastRecords.size() - 1));
    double lastValue = lastRecords.getLast().getMeasurementValue();

    return lastValue > 2*averageOfSlidingWindow;
  }

  @Override
  public AlertFactory getAlertFactory() {
    return new ECGAlertFactory();
  }

  private double getAverageValue(List<PatientRecord> records){
    double sum = 0;
    for (PatientRecord record : records) {
      sum += record.getMeasurementValue();
    }
    return sum/records.size();
  }

}
