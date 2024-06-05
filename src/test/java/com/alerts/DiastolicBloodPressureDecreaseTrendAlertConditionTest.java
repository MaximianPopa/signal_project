package com.alerts;

public class DiastolicBloodPressureDecreaseTrendAlertConditionTest
  extends AbstractDecreaseTrendAlertConditionTest {

  public DiastolicBloodPressureDecreaseTrendAlertConditionTest() {
    super("DiastolicPressure",
      new DiastolicBloodPressureDecreaseTrendAlertCondition());
  }

}
