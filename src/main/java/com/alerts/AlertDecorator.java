package com.alerts;

public class AlertDecorator implements Alert{

  private final Alert alert;

  public AlertDecorator(Alert alert) {
    this.alert = alert;
  }

  @Override
  public String getPatientId() {
    return alert.getPatientId();
  }

  @Override
  public String getCondition() {
    return alert.getCondition();
  }

  @Override
  public long getTimestamp() {
    return System.currentTimeMillis();
  }

}
