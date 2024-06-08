package com.data_management;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicBoolean;


public class WebSocketDataReader implements DataReader {

  private final int portNumber;
  private final AtomicBoolean isReconnecting = new AtomicBoolean(false);

  public WebSocketDataReader(int portNumber) {
    this.portNumber = portNumber;
  }

  @Override
  public void readData(DataStorage dataStorage) throws IOException {
    startMessageConsumption(dataStorage);
  }

  private void startMessageConsumption(DataStorage dataStorage) {
    try {
      WebSocketClient client = new SimpleWebSocketClient(new URI("ws://localhost:" + portNumber), dataStorage);
      client.connect();
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }


  private void reconnect(DataStorage dataStorage) {
    if (!isReconnecting.compareAndSet(false, true)) {
      System.out.println("Already reconnecting...");
      return;
    }
    System.out.println("Attempting to reconnect...");

    // Create a new WebSocketClient instance and attempt to connect
    startMessageConsumption(dataStorage);
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
      isReconnecting.set(false);
    }

    @Override
    public void onMessage(String message) {
      //System.out.println("Received message from server: " + message);
      try {
        PatientRecord patientRecord = PatientRecordParser.parseLine(message);
        dataStorage.addPatientData(patientRecord.getPatientId(), patientRecord.getMeasurementValue(),
          patientRecord.getRecordType(), patientRecord.getTimestamp());
      } catch (PatientRecordFormatException e) {
        System.out.println("Skipping invalid line: " + message);
      }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
      System.out.println("Closed connection");
      if (code!= 1000 || remote) {
        WebSocketDataReader.this.reconnect(dataStorage); // Attempt to reconnect if disconnected unexpectedly
      }
    }

    @Override
    public void onError(Exception ex) {
      ex.printStackTrace();
      WebSocketDataReader.this.reconnect(dataStorage);
    }

  }

}
