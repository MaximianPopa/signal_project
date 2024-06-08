package com.alerts;

public class ConsoleAlertDeliverer implements AlertDeliverer {


  @Override
  public void deliverAlert(Alert alert) {
    System.out.println("Sending alert to staff members: " + alert);
  }
}
