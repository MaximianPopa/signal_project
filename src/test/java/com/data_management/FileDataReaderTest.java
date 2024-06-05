package com.data_management;

import static org.junit.jupiter.api.Assertions.*;

import com.data_management.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileDataReaderTest {

  @Test
  void testReadData() throws IOException {
    DataReader reader = new FileDataReader("src/test/resources/test_data");
    DataStorage storage = new DataStorage();
    reader.readData(storage);
    assertEquals(2, storage.getAllPatients().size());

    Set<Integer> patientIds = getPatientIds(storage);
    Set<Integer> expectedPatientIds = Set.of(1, 2);
    assertEquals(expectedPatientIds, patientIds);

    List<PatientRecord> nonExistingPatientRecords = storage.getRecords(3, 0, System.currentTimeMillis());
    assertEquals(List.of(), nonExistingPatientRecords);

    List<PatientRecord> patientOneRecords = storage.getRecords(1, 0, System.currentTimeMillis());
    List<PatientRecord> expectedPatientOneRecords = List.of(
      new PatientRecord(1, 1, "Alert", 1717334874818L),
      new PatientRecord(1, 0, "Alert", 1717334894822L),
      new PatientRecord(1, 161.70918450722624, "Cholesterol", 1717334874792L),
      new PatientRecord(1, 72.0, "DiastolicPressure", 1717334874859L),
      new PatientRecord(1, 0.675279111554288, "ECG", 1717334880809L),
      new PatientRecord(1, 5.083978065257663, "RedBloodCells", 1717334874867L),
      new PatientRecord(1, 4.572522753700594, "RedBloodCells", 1717334874868L),
      new PatientRecord(1, 91, "Saturation", 1717334885803L),
      new PatientRecord(1, 116, "SystolicPressure", 1717334874835L),
      new PatientRecord(1, 123, "SystolicPressure", 1717334874806L),
      new PatientRecord(1, 10.254734706879129, "WhiteBloodCells", 1717334874859L),
      new PatientRecord(1, 5.505940701617645, "WhiteBloodCells", 1717336182135L)
    );

    assertPatientRecordsEqual(expectedPatientOneRecords, patientOneRecords);

  }

  private static void assertPatientRecordsEqual(List<PatientRecord> expectedRecords,
                                                List<PatientRecord> actualRecords) {
    assertEquals(expectedRecords.size(), actualRecords.size());
    for (int i = 0; i < expectedRecords.size(); i++) {
      PatientRecord expectedRecord = expectedRecords.get(i);
      PatientRecord actualRecord = actualRecords.get(i);
      assertEquals(expectedRecord, actualRecord);
    }
  }

  private Set<Integer> getPatientIds(DataStorage storage)
  {
    Set<Integer> patientIds = new HashSet<>();
    List<Patient> patients = storage.getAllPatients();

    for (Patient patient : patients) {
      patientIds.add(patient.getPatientId());
    }

    return patientIds;
  }


}
