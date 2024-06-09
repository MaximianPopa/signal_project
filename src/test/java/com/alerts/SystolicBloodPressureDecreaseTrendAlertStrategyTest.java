package com.alerts;

public class SystolicBloodPressureDecreaseTrendAlertStrategyTest
  extends AbstractDecreaseTrendAlertStrategyTest {

  public SystolicBloodPressureDecreaseTrendAlertStrategyTest() {
    super("SystolicPressure",
      new SystolicBloodPressureDecreaseTrendAlertStrategy());
  }

}
