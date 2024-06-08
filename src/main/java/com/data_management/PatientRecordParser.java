package com.data_management;

public class PatientRecordParser {

    private static final String ALERT = "Alert";

    public static PatientRecord parseLine(String line) {

        int patientId = getPatientId(line);
        long timestamp = getTimestamp(line);
        String label = getLabel(line);
        Double data = getData(line, label);

        return new PatientRecord(patientId, data, label, timestamp);
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
    private static Double getData(String fileLine, String label){
        String labelPrefix = "Data";
        String fieldValue = getField(fileLine, labelPrefix).replace("%", "");
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

}
