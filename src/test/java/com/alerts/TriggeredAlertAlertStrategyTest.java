package com.alerts;

import com.data_management.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TriggeredAlertAlertStrategyTest {

  @Test
  void testIsAlertConditionTrue() {
    boolean actualAlertConditionMet = testAlertCondition(1);
    assertTrue(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionFalse(){
    boolean actualAlertConditionMet = testAlertCondition(0);
    assertFalse(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionFalseWhenNoData(){
    var alertCondition = new TriggeredAlertAlertStrategy();
    Patient patient = new Patient(1);
    boolean actualAlertConditionMet = alertCondition.checkAlert(patient);
    assertFalse(actualAlertConditionMet);
  }

  private static boolean testAlertCondition(int measurementValue) {
    var alertCondition = new TriggeredAlertAlertStrategy();
    Patient patient = new Patient(1);
    patient.addRecord(measurementValue, "Alert", System.currentTimeMillis());
    return alertCondition.checkAlert(patient);
  }

}
