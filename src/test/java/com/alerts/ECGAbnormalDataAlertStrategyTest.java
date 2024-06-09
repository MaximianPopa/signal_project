package com.alerts;

import com.data_management.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ECGAbnormalDataAlertStrategyTest {

  @Test
  void testIsAlertConditionMetFalse() {
    var alertCondition = new ECGAbnormalDataAlertStrategy();
    Patient patient = new Patient(1);
    patient.addRecord(0.2, "ECG", System.currentTimeMillis() - 120_000);
    patient.addRecord(0.4, "ECG", System.currentTimeMillis() - 60_000);
    patient.addRecord(0.3, "ECG", System.currentTimeMillis());
    boolean actualAlertConditionMet = alertCondition.checkAlert(patient);
    assertFalse(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionMetTrue(){
    var alertCondition = new ECGAbnormalDataAlertStrategy();
    Patient patient = new Patient(1);
    patient.addRecord(0.2, "ECG", System.currentTimeMillis() - 120_000);
    patient.addRecord(0.4, "ECG", System.currentTimeMillis() - 60_000);
    patient.addRecord(100, "ECG", System.currentTimeMillis());
    boolean actualAlertConditionMet = alertCondition.checkAlert(patient);
    assertTrue(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionFalseWhenNotEnoughData(){
    var alertCondition = new ECGAbnormalDataAlertStrategy();
    Patient patient = new Patient(1);
    patient.addRecord(0.2, "ECG", System.currentTimeMillis() - 120_000);
    patient.addRecord(0.4, "ECG", System.currentTimeMillis() - 60_000);
    boolean actualAlertConditionMet = alertCondition.checkAlert(patient);
    assertFalse(actualAlertConditionMet);
  }

}
