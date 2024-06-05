package com.alerts;

public class SystolicBloodPressureDecreaseTrendAlertConditionTest
  extends AbstractDecreaseTrendAlertConditionTest {

  public SystolicBloodPressureDecreaseTrendAlertConditionTest() {
    super("SystolicPressure",
      new SystolicBloodPressureDecreaseTrendAlertCondition());
  }

}
