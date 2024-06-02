package com.data_management;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;


public class FileDataReader implements DataReader{

  private final String dataDirectory;

  public FileDataReader(String dataDirectory) {
    if(dataDirectory == null)
    {
      throw new IllegalArgumentException("Path is null");
    }
    this.dataDirectory = dataDirectory;
  }

  @Override
  public void readData(DataStorage dataStorage) throws IOException {
    List<String> fileNames = getFileNames();
    for (String fileName : fileNames) {
      readDataFromFile(dataStorage, fileName);
    }
  }

  private List<String> getFileNames() throws IOException {
    File directory = new File(dataDirectory);
    File[] files = directory.listFiles();

    List<String> filenames = new ArrayList<>();
    for (File file : files) {
      if (file.isFile() && !file.getName().equalsIgnoreCase("Alert.txt")) {
        filenames.add(file.getName());
      }
    }
    return filenames;
  }

  private void readDataFromFile(DataStorage dataStorage, String fileName) throws IOException {
    String filePath = dataDirectory + "\\" + fileName;
    List<String> allLines = Files.readAllLines(Paths.get((filePath)));

    for (String line : allLines) {
      int patientId = getPatientId(line);
      long timestamp = getTimestamp(line);
      String label = getLabel(line);
      Double data = getData(line);
      dataStorage.addPatientData(patientId, data, label, timestamp);
    }
  }

  private static String getField(String fileLine, String fieldName){
    String fieldPrefix = fieldName + ": ";
    int startOfFieldValue = fileLine.indexOf(fieldPrefix) + fieldPrefix.length();
    int endOfFieldValue = fileLine.indexOf(",", startOfFieldValue);
    if(endOfFieldValue == -1)
    {
      endOfFieldValue = fileLine.length();
    }
    return fileLine.substring(startOfFieldValue, endOfFieldValue);
  }

  private static int getPatientId(String fileLine){
    String patientIdPrefix = "Patient ID";
    return Integer.parseInt(getField(fileLine, patientIdPrefix));
  }
  private static long getTimestamp(String fileLine){
    String timestampPrefix = "Timestamp";
    return Long.parseLong(getField(fileLine, timestampPrefix));
  }
  private static String getLabel(String fileLine){
    String labelPrefix = "Label";
    return getField(fileLine, labelPrefix);
  }
  private static Double getData(String fileLine){
    String labelPrefix = "Data";
    String fieldValue = getField(fileLine, labelPrefix).replace("%", "");
    return Double.parseDouble(fieldValue);
  }

//  public static void main(String[] args) throws IOException {
//    FileDataReader reader = new FileDataReader("D:\\tmp");
//    DataStorage storage = new DataStorage();
//    reader.readData(storage);
//
//  }

}
