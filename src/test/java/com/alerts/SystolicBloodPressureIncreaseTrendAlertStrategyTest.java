package com.alerts;

public class SystolicBloodPressureIncreaseTrendAlertStrategyTest
  extends AbstractIncreaseTrendAlertStrategyTest {

  public SystolicBloodPressureIncreaseTrendAlertStrategyTest() {
    super("SystolicPressure",
      new SystolicBloodPressureIncreaseTrendAlertStrategy());
  }

}
