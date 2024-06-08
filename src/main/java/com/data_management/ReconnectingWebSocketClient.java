package com.data_management;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReconnectingWebSocketClient {

  private static final AtomicBoolean isReconnecting = new AtomicBoolean(false);
  private WebSocketClient websocketClient;
  private final URI serverUri;
  private final MessageHandler messageHandler;

  public ReconnectingWebSocketClient(String url, MessageHandler messageHandler) {
    this.messageHandler = messageHandler;
    try {
      serverUri = new URI(url);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

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

  private void reconnectToServer() {
    if (!isReconnecting.compareAndSet(false, true)) {
      System.out.println("Already reconnecting...");
      return;
    }
    System.out.println("Attempting to reconnect...");

    // Create a new WebSocketClient instance and attempt to connect
    startWebSocketClient();
  }
  
}
