package com.data_management;

/**
 * Parses the patient records
 */
public class PatientRecordParser {

    private static final String ALERT = "Alert";

    /**
     * Parses PatientRecord from a line
     *
     * @param line encoded PatientRecord
     * @return parsed PatientRecord object
     */
    public static PatientRecord parseLine(String line) {
        try {
            int patientId = getPatientId(line);
            long timestamp = getTimestamp(line);
            String label = getLabel(line);
            Double data = getData(line, label);

            return new PatientRecord(patientId, data, label, timestamp);
        } catch (Exception e) {
            throw new PatientRecordFormatException("Illegal patient record line: " + line + ", with error: " + e, e);
        }
    }

    /**
     * Extracts the patient ID from the line
     *
     * @param line Line containing the patient record
     * @return Extracted patient ID of type integer
     */
    private static int getPatientId(String line){
        String patientIdPrefix = "Patient ID";
        return Integer.parseInt(getField(line, patientIdPrefix));
    }

    /**
     * Extracts the timestamp from the line
     *
     * @param line Line containing the patient record
     * @return Extracted timestamp of type long
     */
    private static long getTimestamp(String line){
        String timestampPrefix = "Timestamp";
        return Long.parseLong(getField(line, timestampPrefix));
    }

    /**
     * Extracts the label from the line
     *
     * @param line Line containing the patient record
     * @return Extracted label of type string
     */
    private static String getLabel(String line){
        String labelPrefix = "Label";
        return getField(line, labelPrefix);
    }

    /**
     * Extracts the data associated with the label from the line
     *
     * @param line Line containing the patient record
     * @param label Label associated with the data
     * @return Extracted data of type Double
     */
    private static Double getData(String line, String label){
        String labelPrefix = "Data";
        String fieldValue = getField(line, labelPrefix).replace("%", "");
        if(label.equalsIgnoreCase(ALERT))
        {
            if(fieldValue.equalsIgnoreCase("resolved"))
            {
                return 0.;
            }
            else {
                return 1.;
            }
        }
        else {
            return Double.parseDouble(fieldValue);
        }
    }

    /**
     * Extracts a field value from the line based on its prefix
     *
     * @param line Line containing the patient record
     * @param fieldName Prefix identifying the field to extract
     * @return Extracted field value as a string
     */
    private static String getField(String line, String fieldName){
        String fieldPrefix = fieldName + ": ";
        int startOfFieldValue = line.indexOf(fieldPrefix) + fieldPrefix.length();
        int endOfFieldValue = line.indexOf(",", startOfFieldValue);
        if(endOfFieldValue == -1)
        {
            endOfFieldValue = line.length();
        }
        return line.substring(startOfFieldValue, endOfFieldValue);
    }

}
