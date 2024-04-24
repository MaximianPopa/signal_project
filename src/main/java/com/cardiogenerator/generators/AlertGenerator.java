// Java style: reformatted the whole file to use 2 space indentation

package com.cardiogenerator.generators;

import java.util.Random;

import com.cardiogenerator.outputs.OutputStrategy;

/**
 * Implements the PatientDataGenerator interface and generates an alert for each patient.
 * When an alert is triggered, it is marked as "triggered" and has a 90% chance of being resolved.
 */
public class AlertGenerator implements PatientDataGenerator {

  // Java style: fixed constant name (must be written in UPPER_SNAKE_CASE style)
  public static final Random RANDOM_GENERATOR = new Random();
  // Java style: fixed field name capitalisation (field names must start with lower-case)
  private boolean[] alertStates; // false = resolved, true = pressed

  /**
   * Initializes the alert states for the specified number of patients.
   *
   * @param patientCount The amount of patients for generating alerts.
   */
  public AlertGenerator(int patientCount) {
    alertStates = new boolean[patientCount + 1];
  }

  /**
   * Generates an alert for a given patient based on the status probability (90% resolving),
   * outputs the alert status using the given output style.
   *
   * @param patientId Patient ID used for generating data.
   * @param outputStrategy Output strategy for the type of output.
   */
  @Override
  public void generate(int patientId, OutputStrategy outputStrategy) {
    try {
      if (alertStates[patientId]) {
        if (RANDOM_GENERATOR.nextDouble() < 0.9) { // 90% chance to resolve
          alertStates[patientId] = false;
          // Output the alert
          outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
        }
      } else {
        // Java style: fixed variable name capitalisation (must start with lower-case)
        double lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency
        double p = -Math.expm1(-lambda); // Probability of at least one alert in the period
        boolean alertTriggered = RANDOM_GENERATOR.nextDouble() < p;

        if (alertTriggered) {
          alertStates[patientId] = true;
          // Output the alert
          outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
        }
      }
    } catch (Exception e) {
      System.err.println("An error occurred while generating alert data for patient " + patientId);
      e.printStackTrace();
    }
  }
}
