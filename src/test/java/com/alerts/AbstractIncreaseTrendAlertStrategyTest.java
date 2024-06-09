package com.alerts;

import com.data_management.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractIncreaseTrendAlertStrategyTest {

  private final String recordType;
  private final AlertStrategy alertStrategy;

  public AbstractIncreaseTrendAlertStrategyTest(String recordType, AlertStrategy alertStrategy) {
    this.recordType = recordType;
    this.alertStrategy = alertStrategy;
  }

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
    Patient patient = new Patient(1);
    boolean actualAlertConditionMet = alertStrategy.checkAlert(patient);
    assertFalse(actualAlertConditionMet);
  }

  private boolean testAlertCondition(int value1, int value2, int value3) {
    Patient patient = new Patient(1);
    patient.addRecord(value1, recordType, System.currentTimeMillis() - 10);
    patient.addRecord(value2, recordType, System.currentTimeMillis() - 5);
    patient.addRecord(value3, recordType, System.currentTimeMillis());
    return alertStrategy.checkAlert(patient);
  }

}
