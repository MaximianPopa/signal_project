package com.cardiogenerator.generators;

import com.cardiogenerator.outputs.OutputStrategy;

/**
 * Patient data generator.
 */
public interface PatientDataGenerator {
    /**
     * Generates data for the given patient ID and outputs with the given output strategy (console, file, etc.)
     *
     * @param patientId Patient ID used for generating data.
     * @param outputStrategy Output strategy for the type of output.
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
