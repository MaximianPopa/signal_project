package com.alerts;

import com.data_management.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractDecreaseTrendAlertConditionTest {

  private final String recordType;
  private final AlertCondition alertCondition;

  public AbstractDecreaseTrendAlertConditionTest(String recordType, AlertCondition alertCondition) {
    this.recordType = recordType;
    this.alertCondition = alertCondition;
  }

  @Test
  void testIsAlertConditionTrue() {
    boolean actualAlertConditionMet = testAlertCondition(30, 19, 8);
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
    boolean actualAlertConditionMet = alertCondition.isAlertConditionMet(patient);
    assertFalse(actualAlertConditionMet);
  }

  private boolean testAlertCondition(int value1, int value2, int value3) {
    Patient patient = new Patient(1);
    patient.addRecord(value1, recordType, System.currentTimeMillis() - 10);
    patient.addRecord(value2, recordType, System.currentTimeMillis() - 5);
    patient.addRecord(value3, recordType, System.currentTimeMillis());
    return alertCondition.isAlertConditionMet(patient);
  }

}
