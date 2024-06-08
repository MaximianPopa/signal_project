package com.data_management;

import com.alerts.Alert;
import com.alerts.AlertDeliverer;
import com.alerts.AlertGenerator;
import com.cardiogenerator.outputs.WebSocketOutputStrategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EndToEndTest {

  @Test
  void testValidMessageHandling() throws InterruptedException {
    DataStorage dataStorage = new DataStorage();
    ArrayList<Alert> generatedAlerts = new ArrayList<>();
    AlertGenerator alertGenerator = new AlertGenerator(dataStorage, new AlertDeliverer() {
      @Override
      public void deliverAlert(Alert alert) {
        generatedAlerts.add(alert);
      }
    });
    WebSocketDataReader reader = new WebSocketDataReader(7777);
    WebSocketOutputStrategy output = new WebSocketOutputStrategy(7777);
    reader.readData(dataStorage);
    //sleep for one second, let the client connect to server
    Thread.sleep(1_000);
    long recordTimestamp = System.currentTimeMillis();
    output.output(1, recordTimestamp, "Alert", "triggered");
    //sleep for one second, let the client add data to data storage
    Thread.sleep(1_000);
    List<PatientRecord> patientRecords = dataStorage.getRecords(1, recordTimestamp - 1, recordTimestamp);
    PatientRecord expected = new PatientRecord(1, 1, "Alert", recordTimestamp);
    List<PatientRecord> expectedPatientRecords = List.of(expected);
    assertEquals(expectedPatientRecords, patientRecords);

    for (Patient patient : dataStorage.getAllPatients()) {
      alertGenerator.evaluateData(patient);
    }
    assertEquals(1, generatedAlerts.size());
    Alert expectedAlert = new Alert("1", "The alert button was triggered", recordTimestamp);
    Alert actualAlert = generatedAlerts.getFirst();
    assertEquals(expectedAlert.getPatientId(), actualAlert.getPatientId());
    assertEquals(expectedAlert.getCondition(), actualAlert.getCondition());
  }

}
