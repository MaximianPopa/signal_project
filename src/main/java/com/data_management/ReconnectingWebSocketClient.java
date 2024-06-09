package com.data_management;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a WebSocket client capable of reconnecting to the server upon disconnection or error.
 * The client maintains a state to track whether it is currently attempting to reconnect.
 */
public class ReconnectingWebSocketClient {

  private static final AtomicBoolean isReconnecting = new AtomicBoolean(false);
  private WebSocketClient websocketClient;
  private final URI serverUri;
  private final MessageHandler messageHandler;

  /**
   * Constructs a new ReconnectingWebSocketClient with the specified URL and message handler
   *
   * @param url The WebSocket server URL
   * @param messageHandler The handler for incoming WebSocket server messages
   */
  public ReconnectingWebSocketClient(String url, MessageHandler messageHandler) {
    this.messageHandler = messageHandler;
    try {
      serverUri = new URI(url);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Stops the connection to the WebSocket server
   */
  public void stop(){
    if(websocketClient != null)
    {
      websocketClient.close();
    }
  }

  /**
   * Starts the WebSocket client and initiates the connection to the server.
   * Sets up event handlers for connection events such as opening, receiving messages,
   * closing, and encountering errors.
   */
  public void startWebSocketClient() {
    websocketClient = new WebSocketClient(serverUri) {
      @Override
      public void onOpen(ServerHandshake handshake) {
        System.out.println("Connected");
        isReconnecting.set(false); // Reset reconnection flag
      }

      @Override
      public void onMessage(String message) {
        // Handle incoming messages
        messageHandler.handle(message);
      }

      @Override
      public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected");
        if (code!= 1000 || remote) {
          reconnectToServer(); // Attempt to reconnect if disconnected unexpectedly
        }
      }

      @Override
      public void onError(Exception ex) {
        System.out.println("Error: " + ex.getMessage());
        reconnectToServer(); // Attempt to reconnect on error
      }
    };
    websocketClient.connect();
  }

  /**
   * Attempts to reconnect to the WebSocket server, if not already in the process of reconnecting
   */

  public void reconnectToServer() {
    if (!isReconnecting.compareAndSet(false, true)) {
      System.out.println("Already reconnecting...");
      return;
    }
    System.out.println("Attempting to reconnect...");

    // Create a new WebSocketClient instance and attempt to connect
    startWebSocketClient();
  }
  
}
