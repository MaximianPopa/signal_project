package com.alerts;

/**
 * Adds prioritization tagging to alerts needing urgent attention.
 */
public class PriorityAlertDecorator extends AlertDecorator{

  private final int priority;


  public PriorityAlertDecorator(Alert alert, int priority) {
    super(alert);
    this.priority = priority;
  }

  @Override
  public String getCondition() {
    return super.getCondition() + " has priority: " + priority;
  }

}
