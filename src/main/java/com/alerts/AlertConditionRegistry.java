package com.alerts;

import java.util.List;

public class AlertConditionRegistry {

  private static final List<AlertCondition> ALERT_CONDITIONS =
    List.of(
      new SystolicBloodPressureDecreaseTrendAlertCondition(),
      new SystolicBloodPressureIncreaseTrendAlertCondition(),
      new DiastolicBloodPressureDecreaseTrendAlertCondition(),
      new DiastolicBloodPressureIncreaseTrendAlertCondition(),
      new SystolicPressureBelowThresholdAlertCondition(),
      new SystolicPressureAboveThresholdAlertCondition(),
      new DiastolicPressureBelowThresholdAlertCondition(),
      new DiastolicPressureAboveThresholdAlertCondition(),
      new BloodOxygenSaturationBelowThresholdAlertCondition(),
      new BloodOxygenSaturationRapidDropThresholdAlertCondition(),
      new HypotensiveHypoxemiaAlertCondition(),
      new ECGAbnormalDataAlertCondition(),
      new TriggeredAlertAlertCondition()
    );

  public static List<AlertCondition> getAlertConditions(){

    return ALERT_CONDITIONS;
  }

}
