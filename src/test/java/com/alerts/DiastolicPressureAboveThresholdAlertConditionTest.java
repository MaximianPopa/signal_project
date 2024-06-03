package com.alerts;

import com.data_management.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiastolicPressureAboveThresholdAlertConditionTest {
  //TODO: change the conditions, correct the tests
  @Test
  void testIsAlertConditionFalse() {
    boolean actualAlertConditionMet = testAlertCondition(200);
    assertTrue(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionTrue(){
    boolean actualAlertConditionMet = testAlertCondition(160);
    assertFalse(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionFalseWhenNoData(){
    var alertCondition = new SystolicPressureAboveThresholdAlertCondition();
    Patient patient = new Patient(1);
    boolean actualAlertConditionMet = alertCondition.isAlertConditionMet(patient);
    assertFalse(actualAlertConditionMet);
  }

  private static boolean testAlertCondition(int measurementValue) {
    var alertCondition = new SystolicPressureAboveThresholdAlertCondition();
    Patient patient = new Patient(1);
    patient.addRecord(measurementValue, "SystolicPressure", System.currentTimeMillis());
    return alertCondition.isAlertConditionMet(patient);
  }

}
