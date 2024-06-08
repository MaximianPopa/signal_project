package com.data_management;

public class WebSocketDataReader implements DataReader {

  private final int portNumber;

  public WebSocketDataReader(int portNumber) {
    this.portNumber = portNumber;
  }

  @Override
  public void readData(DataStorage dataStorage) {
    ReconnectingWebSocketClient client =
      new ReconnectingWebSocketClient("ws://localhost:" + portNumber, new MessageHandler() {
      @Override
      public void handle(String message) {
        try {
          PatientRecord patientRecord = PatientRecordParser.parseLine(message);
          dataStorage.addPatientData(patientRecord.getPatientId(), patientRecord.getMeasurementValue(),
            patientRecord.getRecordType(), patientRecord.getTimestamp());
        } catch (PatientRecordFormatException e) {
          System.out.println("Skipping invalid message: " + message);
        }
      }
    });
    client.startWebSocketClient();
  }

}
