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
      if (file.isFile()) {
        filenames.add(file.getName());
      }
    }
    return filenames;
  }

  private void readDataFromFile(DataStorage dataStorage, String fileName) throws IOException {
    String filePath = dataDirectory + "/" + fileName;
    List<String> allLines = Files.readAllLines(Paths.get((filePath)));

    for (String line : allLines) {
      PatientRecord patientRecord = PatientRecordParser.parseLine(line);
      dataStorage.addPatientData(patientRecord.getPatientId(), patientRecord.getMeasurementValue(),
              patientRecord.getRecordType(), patientRecord.getTimestamp());
    }
  }

}
