package com.alerts;

/**
 * Checks and re-checks alert conditions over a set interval.
 */
public class RepeatedAlertDecorator extends AlertDecorator{
  private final int repeatCount;

  public RepeatedAlertDecorator(Alert alert, int repeatCount) {
    super(alert);
    this.repeatCount = repeatCount;
  }

  @Override
  public String getCondition() {
    return super.getCondition() + " was repeated: " + repeatCount + " times";
  }

}
