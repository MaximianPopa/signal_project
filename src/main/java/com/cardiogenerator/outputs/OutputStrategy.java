package com.cardiogenerator.outputs;

/**
 * Output strategy.
 */
public interface OutputStrategy {
    /**
     * Outputs the results into the specific output strategy (console, file, etc.).
     *
     * @param patientId Patient ID used for outputting data.
     * @param timestamp Time stamp used for data output.
     * @param label The label associated with the output data.
     * @param data The data that needs to be outputted.
     */
    void output(int patientId, long timestamp, String label, String data);
}
