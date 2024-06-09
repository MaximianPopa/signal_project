package com.data_management;

import com.cardiogenerator.outputs.WebSocketOutputStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NetworkErrorTest {

  public static final int PORT_NUMBER = 8888;

  @Test
  void testValidMessageHandling() throws InterruptedException {

    DataStorage dataStorage = new DataStorage();

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

    reader.stop();
    reader.reconnectToServer();
    Thread.sleep(2_000);
    output.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
    Thread.sleep(1_000);
    List<PatientRecord> newPatientRecords = dataStorage.getRecords(patientId, recordTimestamp - 1, System.currentTimeMillis());
    assertEquals(2, newPatientRecords.size());
    reader.stop();
    output.stop();
  }

}
