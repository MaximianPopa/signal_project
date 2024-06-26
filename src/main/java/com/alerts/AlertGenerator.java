package com.alerts;

import com.data_management.DataStorage;
import com.data_management.Patient;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data
 * and generating alerts when certain predefined conditions are met. This class
 * relies on a {@link DataStorage} instance to access patient data and evaluate
 * it against specific health criteria.
 */
public class AlertGenerator {
    private DataStorage dataStorage;
    private final AlertDeliverer alertDeliverer;

    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * The {@code DataStorage} is used to retrieve patient data that this class
     * will monitor and evaluate.
     *
     * @param dataStorage the data storage system that provides access to patient
     *                    data
     */
    public AlertGenerator(DataStorage dataStorage) {
        this(dataStorage, new ConsoleAlertDeliverer());
    }

    public AlertGenerator(DataStorage dataStorage, AlertDeliverer alertDeliverer) {
        this.dataStorage = dataStorage;
      this.alertDeliverer = alertDeliverer;
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the
     * {@link #triggerAlert}
     * method. This method should define the specific conditions under which an
     * alert
     * will be triggered.
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient) {
        for (AlertStrategy alertStrategy : AlertConditionRegistry.getAlertConditions()) {
            if(alertStrategy.checkAlert(patient)){
//                Alert alert = new Alert(patient.getPatientId(),
//                  alertCondition.getAlertDescription());
                AlertFactory alertFactory = alertStrategy.getAlertFactory();
                GenericAlert alert = alertFactory.createAlert(String.valueOf(patient.getPatientId()),
                  alertStrategy.getAlertDescription(), System.currentTimeMillis());
                triggerAlert(alert);
            }
        }
    }

    /**
     * Triggers an alert for the monitoring system. This method can be extended to
     * notify medical staff, log the alert, or perform other actions. The method
     * currently assumes that the alert information is fully formed when passed as
     * an argument.
     *
     * @param alert the alert object containing details about the alert condition
     */
    private void triggerAlert(GenericAlert alert) {
        // Implementation might involve logging the alert or notifying staff
        alertDeliverer.deliverAlert(alert);
    }
}
