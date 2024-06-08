package com.data_management;

public class PatientRecordParser {

    private static final String ALERT = "Alert";

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

    private static int getPatientId(String line){
        String patientIdPrefix = "Patient ID";
        return Integer.parseInt(getField(line, patientIdPrefix));
    }
    private static long getTimestamp(String line){
        String timestampPrefix = "Timestamp";
        return Long.parseLong(getField(line, timestampPrefix));
    }
    private static String getLabel(String line){
        String labelPrefix = "Label";
        return getField(line, labelPrefix);
    }
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
