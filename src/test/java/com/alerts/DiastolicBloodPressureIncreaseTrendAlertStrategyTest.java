package com.alerts;

public class DiastolicBloodPressureIncreaseTrendAlertStrategyTest
  extends AbstractIncreaseTrendAlertStrategyTest {

  public DiastolicBloodPressureIncreaseTrendAlertStrategyTest() {
    super("DiastolicPressure",
      new DiastolicBloodPressureIncreaseTrendAlertStrategy());
  }

}
