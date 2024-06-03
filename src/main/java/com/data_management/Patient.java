package com.data_management;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

/**
 * Represents a patient and manages their medical records.
 * This class stores patient-specific data, allowing for the addition and
 * retrieval
 * of medical records based on specified criteria.
 */
public class Patient {
    private final static int DATA_GENERATION_PERIOD_MILlIS = 30_000;
    private int patientId;
    private List<PatientRecord> patientRecords;

    public int getPatientId() {
        return patientId;
    }

    /**
     * Constructs a new Patient with a specified ID.
     * Initializes an empty list of patient records.
     *
     * @param patientId the unique identifier for the patient
     */
    public Patient(int patientId) {
        this.patientId = patientId;
        this.patientRecords = new ArrayList<>();
    }

    /**
     * Adds a new record to this patient's list of medical records.
     * The record is created with the specified measurement value, record type, and
     * timestamp.
     *
     * @param measurementValue the measurement value to store in the record
     * @param recordType       the type of record, e.g., "HeartRate",
     *                         "BloodPressure"
     * @param timestamp        the time at which the measurement was taken, in
     *                         milliseconds since UNIX epoch
     */
    public void addRecord(double measurementValue, String recordType, long timestamp) {
        PatientRecord record = new PatientRecord(this.patientId, measurementValue, recordType, timestamp);
        this.patientRecords.add(record);
    }

    /**
     * Retrieves a list of PatientRecord objects for this patient that fall within a
     * specified time range.
     * The method filters records based on the start and end times provided.
     *
     * @param startTime the start of the time range, in milliseconds since UNIX
     *                  epoch
     * @param endTime   the end of the time range, in milliseconds since UNIX epoch
     * @return a list of PatientRecord objects that fall within the specified time
     *         range
     */
    public List<PatientRecord> getRecords(long startTime, long endTime) {

        List<PatientRecord> result = new ArrayList<>();

        for (PatientRecord patientRecord : patientRecords) {
            if(patientRecord.getTimestamp() >= startTime && patientRecord.getTimestamp() <= endTime)
            {
                result.add(patientRecord);
            }
        }
        return result;
    }

    public PatientRecord getLastRecord(String recordType){
        List<PatientRecord> patientRecords = getRecords(currentTimeMillis() -
          DATA_GENERATION_PERIOD_MILlIS, currentTimeMillis());
        for (int i = patientRecords.size() - 1; i >= 0; i--){
            PatientRecord patientRecord = patientRecords.get(i);
            if(patientRecord.getRecordType().equals(recordType)){
                return patientRecord;
            }
        }
        return null;
    }

    public List<PatientRecord> getLastNRecords(String recordType, int n){
        List<PatientRecord> patientRecords = getRecords(currentTimeMillis() -
          n*DATA_GENERATION_PERIOD_MILlIS, currentTimeMillis());
        List<PatientRecord> result = new ArrayList<>();
        for (int i = patientRecords.size() - 1; i >= 0; i--){
            PatientRecord patientRecord = patientRecords.get(i);
            if(patientRecord.getRecordType().equals(recordType)){
                result.add(patientRecord);
                if(result.size() == n){
                    break;
                }
            }
        }
        return result.reversed();
    }

}
