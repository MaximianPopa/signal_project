package com.alerts;

import com.data_management.Patient;
import org.junit.jupiter.api.Test;

import static java.lang.System.currentTimeMillis;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BloodOxygenSaturationRapidDropThresholdAlertStrategyTest {
  
  @Test
  void testIsAlertConditionFalse() {
    boolean actualAlertConditionMet = testAlertCondition(currentTimeMillis() - 5, 160,
      currentTimeMillis(), 161);
    assertFalse(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionTrue(){
    boolean actualAlertConditionMet = testAlertCondition(currentTimeMillis() - 5, 150,
      currentTimeMillis(), 160);
    assertTrue(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionFalseWhenOutsideTenMinWindow(){
    boolean actualAlertConditionMet = testAlertCondition(currentTimeMillis() - 600_001, 150,
      currentTimeMillis(), 160);
    assertFalse(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionFalseWhenNoData(){
    var alertCondition = new BloodOxygenSaturationRapidDropThresholdAlertStrategy();
    Patient patient = new Patient(1);
    boolean actualAlertConditionMet = alertCondition.checkAlert(patient);
    assertFalse(actualAlertConditionMet);
  }

  @Test
  void testIsAlertConditionFalseWhenSingleDataPoint(){
    var alertCondition = new BloodOxygenSaturationRapidDropThresholdAlertStrategy();
    Patient patient = new Patient(1);
    patient.addRecord(10, "Saturation", currentTimeMillis());
    boolean actualAlertConditionMet = alertCondition.checkAlert(patient);
    assertFalse(actualAlertConditionMet);
  }

  private static boolean testAlertCondition(long timestamp1, double measurement1,
                                            long timestamp2, double measurement2) {
    var alertCondition = new BloodOxygenSaturationRapidDropThresholdAlertStrategy();
    Patient patient = new Patient(1);
    patient.addRecord(measurement1, "Saturation", timestamp1);
    patient.addRecord(measurement2, "Saturation", timestamp2);
    return alertCondition.checkAlert(patient);
  }

}
