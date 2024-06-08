package com.cardiogenerator;

import com.data_management.DataStorage;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws Exception {
    if (args.length > 0 && args[0].equals("DataStorage")) {
      DataStorage.main(tail(args));
    } else {
      HealthDataSimulator.main(tail(args));
    }
  }

  private static String[] tail(String[] args){
    if (args.length == 0)
    {
      return args;
    }

    int length = args.length - 1;
    String[] tail = new String[length];

    for(int i = 0; i < length; i++)
    {
      tail[i] = args[i + 1];
    }
    return tail;
  }


}