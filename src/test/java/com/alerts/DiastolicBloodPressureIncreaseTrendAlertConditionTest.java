package com.alerts;

import com.data_management.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiastolicBloodPressureIncreaseTrendAlertConditionTest {

  @Test
  void testIsAlertConditionTrue() {
    boolean actualAlertConditionMet = testAlertCondition(8, 19, 30);
    assertTrue(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionFalse(){
    boolean actualAlertConditionMet = testAlertCondition(31, 20, 35);
    assertFalse(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionFalseWhenNoData(){
    var alertCondition = new DiastolicPressureAboveThresholdAlertCondition();
    Patient patient = new Patient(1);
    boolean actualAlertConditionMet = alertCondition.isAlertConditionMet(patient);
    assertFalse(actualAlertConditionMet);
  }

  private static boolean testAlertCondition(int value1, int value2, int value3) {
    var alertCondition = new DiastolicBloodPressureIncreaseTrendAlertCondition();
    Patient patient = new Patient(1);
    patient.addRecord(value1, "DiastolicPressure", System.currentTimeMillis() - 10);
    patient.addRecord(value2, "DiastolicPressure", System.currentTimeMillis() - 5);
    patient.addRecord(value3, "DiastolicPressure", System.currentTimeMillis());
    return alertCondition.isAlertConditionMet(patient);
  }

}
