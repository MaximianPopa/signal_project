package com.alerts;

public class DiastolicBloodPressureDecreaseTrendAlertStrategyTest
  extends AbstractDecreaseTrendAlertStrategyTest {

  public DiastolicBloodPressureDecreaseTrendAlertStrategyTest() {
    super("DiastolicPressure",
      new DiastolicBloodPressureDecreaseTrendAlertStrategy());
  }

}
