package com.alerts;

import static org.junit.jupiter.api.Assertions.*;

import com.data_management.Patient;
import org.junit.jupiter.api.Test;

public class SystolicPressureBelowThresholdAlertConditionTest {

  @Test
  void testIsAlertConditionFalse() {
    boolean actualAlertConditionMet = testAlertCondition(200);
    assertFalse(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionTrue(){
    boolean actualAlertConditionMet = testAlertCondition(80);
    assertTrue(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionFalseWhenNoData(){
    var alertCondition = new SystolicPressureBelowThresholdAlertCondition();
    Patient patient = new Patient(1);
    boolean actualAlertConditionMet = alertCondition.isAlertConditionMet(patient);
    assertFalse(actualAlertConditionMet);
  }

  private static boolean testAlertCondition(int measurementValue) {
    var alertCondition = new SystolicPressureBelowThresholdAlertCondition();
    Patient patient = new Patient(1);
    patient.addRecord(measurementValue, "SystolicPressure", System.currentTimeMillis());
    return alertCondition.isAlertConditionMet(patient);
  }

}
