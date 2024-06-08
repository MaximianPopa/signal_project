package com.data_management;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatientRecordParserTest {

    @Test
    void testPatientRecord(){
        PatientRecord expectedRecord = new PatientRecord(1, 5,
                "A", 1717334874867L);
        PatientRecord actualRecord = PatientRecordParser.parseLine(
                "Patient ID: 1, Timestamp: 1717334874867, Label: A, Data: 5");
        PatientRecord actualRecord2 = PatientRecordParser.parseLine(
                "Label: A, Patient ID: 1, Data: 5, Timestamp: 1717334874867");
        assertEquals(expectedRecord, actualRecord);
        assertEquals(expectedRecord, actualRecord2);
    }

    @Test
    void testAlertParsing(){
        PatientRecord expectedResolvedAlert = new PatientRecord(1, 0,
                "Alert", 1717334874867L);
        PatientRecord expectedTriggeredAlert = new PatientRecord(1, 1,
                "Alert", 1717334874867L);
        PatientRecord actualResolvedAlert = PatientRecordParser.parseLine(
                "Patient ID: 1, Timestamp: 1717334874867, Label: Alert, Data: resolved");
        PatientRecord actualTriggeredAlert = PatientRecordParser.parseLine(
                "Patient ID: 1, Timestamp: 1717334874867, Label: Alert, Data: triggered");
        assertEquals(expectedResolvedAlert, actualResolvedAlert);
        assertEquals(expectedTriggeredAlert, actualTriggeredAlert);
    }

    @Test
    void testIllegalArgumentException(){
        assertIllegalArgumentException("blah");
        assertIllegalArgumentException("");
        assertIllegalArgumentException("Patient ID: 1, Timestamp: a, Label: Alert, Data: resolved");
        assertIllegalArgumentException("Patient ID: 1, Timestamp: a, Data: resolved");
        assertIllegalArgumentException("Patient ID: 1, Timestamp: a, Label: Alert, Data: r");
    }

    private static void assertIllegalArgumentException(String line) {
        try {
            PatientRecordParser.parseLine(line);
            fail("Should have failed for line: " + line);
        } catch (PatientRecordFormatException e) {
            System.out.println("Got expected exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
