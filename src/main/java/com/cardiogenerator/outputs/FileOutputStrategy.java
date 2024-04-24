// Java style: reformatted the whole file to use 2 space indentation

// Java style: fixed package name (no underscores)
package com.cardiogenerator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implements the OutputStrategy interface and provides a file-based output strategy for storing data.
 */
// Java style: fixed class name capitalisation (class names must start with upper-case)
public class FileOutputStrategy implements OutputStrategy {

  // Java style: fixed field name capitalisation (field names must start with lower-case)
  private String baseDirectory;

  // Java style: fixed field name (field names must be written in lowerCamelCase style)
  public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>();

  /**
   * Creates a new FileOutputStrategy instance with the specified base directory.
   *
   * @param baseDirectory Base directory for storing output files.
   */
  public FileOutputStrategy(String baseDirectory) {

    this.baseDirectory = baseDirectory;
  }

  /**
   * Outputs the given data to a file, using specified label.
   *
   * @param patientId ID of a patient used for outputting data.
   * @param timestamp Time stamp used for data output.
   * @param label The label associated with the output data.
   * @param data The data that needs to be outputted.
   */
  @Override
  public void output(int patientId, long timestamp, String label, String data) {
    try {
      // Create the directory
      Files.createDirectories(Paths.get(baseDirectory));
    } catch (IOException e) {
      System.err.println("Error creating base directory: " + e.getMessage());
      return;
    }
    // Java style: fixed variable name capitalisation (must start with lower-case)
    // Set the filePath variable
    String filePath = fileMap.computeIfAbsent(label, k -> Paths.get(baseDirectory, label + ".txt").toString());

    // Write the data to the file
    try (PrintWriter out = new PrintWriter(
      Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
      out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timestamp, label, data);
    } catch (Exception e) {
      System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
    }
  }
}