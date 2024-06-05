package com.alerts;

public class DiastolicBloodPressureIncreaseTrendAlertConditionTest
  extends AbstractIncreaseTrendAlertConditionTest {

  public DiastolicBloodPressureIncreaseTrendAlertConditionTest() {
    super("DiastolicPressure",
      new DiastolicBloodPressureIncreaseTrendAlertCondition());
  }

}
