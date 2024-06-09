package com.data_management;

import com.alerts.GenericAlert;
import com.alerts.AlertDeliverer;
import com.alerts.AlertGenerator;
import com.cardiogenerator.outputs.WebSocketOutputStrategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EndToEndTest {

  public static final int PORT_NUMBER = 7777;

  @Test
  void testValidMessageHandling() throws InterruptedException {
    DataStorage dataStorage = new DataStorage();
    ArrayList<GenericAlert> generatedAlerts = new ArrayList<>();
    AlertGenerator alertGenerator = new AlertGenerator(dataStorage, new AlertDeliverer() {
      @Override
      public void deliverAlert(GenericAlert alert) {
        generatedAlerts.add(alert);
      }
    });
    WebSocketDataReader reader = new WebSocketDataReader(PORT_NUMBER);
    WebSocketOutputStrategy output = new WebSocketOutputStrategy(PORT_NUMBER);
    reader.readData(dataStorage);
    //sleep for one second, let the client connect to server
    Thread.sleep(1_000);
    long recordTimestamp = System.currentTimeMillis();
    int patientId = 7;
    output.output(patientId, recordTimestamp, "Alert", "triggered");
    //sleep for one second, let the client add data to data storage
    Thread.sleep(1_000);
    List<PatientRecord> patientRecords = dataStorage.getRecords(patientId, recordTimestamp - 1, recordTimestamp);
    PatientRecord expected = new PatientRecord(patientId, 1, "Alert", recordTimestamp);
    List<PatientRecord> expectedPatientRecords = List.of(expected);
    assertEquals(expectedPatientRecords, patientRecords);

    for (Patient patient : dataStorage.getAllPatients()) {
      alertGenerator.evaluateData(patient);
    }
    assertEquals(1, generatedAlerts.size());
    GenericAlert expectedAlert = new GenericAlert(String.valueOf(patientId), "The alert button was triggered", recordTimestamp);
    GenericAlert actualAlert = generatedAlerts.getFirst();
    assertEquals(expectedAlert.getPatientId(), actualAlert.getPatientId());
    assertEquals(expectedAlert.getCondition(), actualAlert.getCondition());
    reader.stop();
    output.stop();
  }

}
