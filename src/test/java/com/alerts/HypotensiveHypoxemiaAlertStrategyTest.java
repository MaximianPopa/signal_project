package com.alerts;

import com.data_management.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HypotensiveHypoxemiaAlertStrategyTest {

  @Test
  void testIsAlertConditionFalseBothThresholdExceeded() {
    boolean actualAlertConditionMet = testAlertCondition(91, 92);
    assertFalse(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionFalseSystolicThresholdExceeded() {
    boolean actualAlertConditionMet = testAlertCondition(91, 91);
    assertFalse(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionFalseSaturationThresholdExceeded() {
    boolean actualAlertConditionMet = testAlertCondition(89, 92);
    assertFalse(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionTrue(){
    boolean actualAlertConditionMet = testAlertCondition(89, 91);
    assertTrue(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionFalseWhenNoData(){
    var alertCondition = new HypotensiveHypoxemiaAlertStrategy();
    Patient patient = new Patient(1);
    boolean actualAlertConditionMet = alertCondition.checkAlert(patient);
    assertFalse(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionFalseWhenNoSystolicData(){
    var alertCondition = new HypotensiveHypoxemiaAlertStrategy();
    Patient patient = new Patient(1);
    patient.addRecord(100, "SystolicPressure", System.currentTimeMillis());
    boolean actualAlertConditionMet = alertCondition.checkAlert(patient);
    assertFalse(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionFalseWhenNoSaturationData(){
    var alertCondition = new HypotensiveHypoxemiaAlertStrategy();
    Patient patient = new Patient(1);
    patient.addRecord(100, "Saturation", System.currentTimeMillis());
    boolean actualAlertConditionMet = alertCondition.checkAlert(patient);
    assertFalse(actualAlertConditionMet);
  }

  private static boolean testAlertCondition(double systolicPressure, double saturation) {
    var alertCondition = new HypotensiveHypoxemiaAlertStrategy();
    Patient patient = new Patient(1);
    patient.addRecord(systolicPressure, "SystolicPressure", System.currentTimeMillis());
    patient.addRecord(saturation, "Saturation", System.currentTimeMillis());
    return alertCondition.checkAlert(patient);
  }

}
