package com.alerts;

public class SystolicBloodPressureIncreaseTrendAlertConditionTest
  extends AbstractIncreaseTrendAlertConditionTest {

  public SystolicBloodPressureIncreaseTrendAlertConditionTest() {
    super("SystolicPressure",
      new SystolicBloodPressureIncreaseTrendAlertCondition());
  }

}
