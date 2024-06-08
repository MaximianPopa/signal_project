package com.data_management;

import com.cardiogenerator.outputs.WebSocketOutputStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReconnectingWebSocketClientTest {

  @Test
  void testValidMessageHandling() throws InterruptedException {
    DataStorage dataStorage = new DataStorage();
    WebSocketDataReader reader = new WebSocketDataReader(7777);
    WebSocketOutputStrategy output = new WebSocketOutputStrategy(7777);
    reader.readData(dataStorage);
    Thread.sleep(1_000);
    output.output(1, 100, "Alert", "triggered");
    Thread.sleep(1_000);
    List<PatientRecord> patientRecords = dataStorage.getRecords(1, 100, 101);
    PatientRecord expected = new PatientRecord(1, 1, "Alert", 100);
    List<PatientRecord> expectedPatientRecords = List.of(expected);
    assertEquals(expectedPatientRecords, patientRecords);
  }

}
