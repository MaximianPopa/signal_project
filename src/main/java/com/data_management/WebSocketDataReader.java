package com.data_management;

/**
 * Reads patient data from a WebSocket server and stores it in a DataStorage object.
 * The class connects to a WebSocket server, listens for incoming messages.
 * It takes part in managing the connection lifecycle, including reconnections.
 */
public class WebSocketDataReader implements DataReader {

  private final int portNumber;
  private ReconnectingWebSocketClient client;

  /**
   * Constructor method for WebSocketDataReader
   *
   * @param portNumber The port number of the WebSocket server
   */
  public WebSocketDataReader(int portNumber) {
    this.portNumber = portNumber;
  }

  /**
   * Starts reading data from the WebSocket server and storing it in the provided DataStorage
   *
   * @param dataStorage the storage where data will be stored
   */
  @Override
  public void readData(DataStorage dataStorage) {
    client = new ReconnectingWebSocketClient("ws://localhost:" + portNumber, new MessageHandler() {
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

  /**
   * Stops the WebSocket server
   */
  public void stop(){
    if(client != null)
    {
      client.stop();
    }
  }

  /**
   * Reconnects the WebSocket server
   */
  public void reconnectToServer(){
    client.reconnectToServer();
  }


}
