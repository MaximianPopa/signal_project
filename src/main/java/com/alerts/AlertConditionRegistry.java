package com.alerts;

import java.util.List;

public class AlertConditionRegistry {

  private static final List<AlertStrategy> ALERT_CONDITIONS =
    List.of(
      new SystolicBloodPressureDecreaseTrendAlertStrategy(),
      new SystolicBloodPressureIncreaseTrendAlertStrategy(),
      new DiastolicBloodPressureDecreaseTrendAlertStrategy(),
      new DiastolicBloodPressureIncreaseTrendAlertStrategy(),
      new SystolicPressureBelowThresholdAlertStrategy(),
      new SystolicPressureAboveThresholdAlertStrategy(),
      new DiastolicPressureBelowThresholdAlertStrategy(),
      new DiastolicPressureAboveThresholdAlertStrategy(),
      new BloodOxygenSaturationBelowThresholdAlertStrategy(),
      new BloodOxygenSaturationRapidDropThresholdAlertStrategy(),
      new HypotensiveHypoxemiaAlertStrategy(),
      new ECGAbnormalDataAlertStrategy(),
      new TriggeredAlertAlertStrategy()
    );

  public static List<AlertStrategy> getAlertConditions(){

    return ALERT_CONDITIONS;
  }

}
