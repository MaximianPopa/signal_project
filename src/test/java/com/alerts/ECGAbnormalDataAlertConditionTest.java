package com.alerts;

import com.data_management.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ECGAbnormalDataAlertConditionTest {

  @Test
  void testIsAlertConditionMetFalse() {
    var alertCondition = new ECGAbnormalDataAlertCondition();
    Patient patient = new Patient(1);
    patient.addRecord(0.2, "ECG", System.currentTimeMillis() - 120_000);
    patient.addRecord(0.4, "ECG", System.currentTimeMillis() - 60_000);
    patient.addRecord(0.3, "ECG", System.currentTimeMillis());
    boolean actualAlertConditionMet = alertCondition.isAlertConditionMet(patient);
    assertFalse(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionMetTrue(){
    var alertCondition = new ECGAbnormalDataAlertCondition();
    Patient patient = new Patient(1);
    patient.addRecord(0.2, "ECG", System.currentTimeMillis() - 120_000);
    patient.addRecord(0.4, "ECG", System.currentTimeMillis() - 60_000);
    patient.addRecord(100, "ECG", System.currentTimeMillis());
    boolean actualAlertConditionMet = alertCondition.isAlertConditionMet(patient);
    assertTrue(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionFalseWhenNotEnoughData(){
    var alertCondition = new ECGAbnormalDataAlertCondition();
    Patient patient = new Patient(1);
    patient.addRecord(0.2, "ECG", System.currentTimeMillis() - 120_000);
    patient.addRecord(0.4, "ECG", System.currentTimeMillis() - 60_000);
    boolean actualAlertConditionMet = alertCondition.isAlertConditionMet(patient);
    assertFalse(actualAlertConditionMet);
  }

  private static boolean testAlertCondition(int measurementValue) {
    var alertCondition = new ECGAbnormalDataAlertCondition();
    Patient patient = new Patient(1);
    patient.addRecord(measurementValue, "ECG", System.currentTimeMillis());
    return alertCondition.isAlertConditionMet(patient);
  }

}
