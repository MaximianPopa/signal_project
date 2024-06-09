package com.alerts;

public class ConsoleAlertDeliverer implements AlertDeliverer {


  @Override
  public void deliverAlert(GenericAlert alert) {
    System.out.println("Sending alert to staff members: " + alert);
  }
}
