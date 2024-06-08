package com.data_management;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class WebSocketDataReader implements DataReader {

  private final int portNumber;

  public WebSocketDataReader(int portNumber) {
    this.portNumber = portNumber;
  }

  @Override
  public void readData(DataStorage dataStorage) throws IOException {
    try {
      WebSocketClient client = new SimpleWebSocketClient(new URI("ws://localhost:" + portNumber), dataStorage);
      client.connect();
    } catch (URISyntaxException e) {
      throw new IOException(e);
    }
  }


  class SimpleWebSocketClient extends WebSocketClient {

    private final DataStorage dataStorage;

    public SimpleWebSocketClient(URI serverUri, DataStorage dataStorage) {
      super(serverUri);
      this.dataStorage = dataStorage;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
      System.out.println("Opened connection");
    }

    @Override
    public void onMessage(String message) {
      //System.out.println("Received message from server: " + message);
      PatientRecord patientRecord = PatientRecordParser.parseLine(message);
      dataStorage.addPatientData(patientRecord.getPatientId(), patientRecord.getMeasurementValue(),
        patientRecord.getRecordType(), patientRecord.getTimestamp());
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
      System.out.println("Closed connection");
    }

    @Override
    public void onError(Exception ex) {
      ex.printStackTrace();
    }

  }

}
