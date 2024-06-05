package com.alerts;

import com.data_management.PatientRecord;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrendUtilitiesTest {

  @Test
  void testEmptyListDecreasingTrendFalse(){
    assertFalse(TrendUtilities.isDecreasingTrend(List.of()));
  }

  @Test
  void testEmptyListIncreasingTrendFalse(){
    assertFalse(TrendUtilities.isIncreasingTrend(List.of()));
  }

  @Test
  void testOneElementListDecreasingTrendFalse(){
    PatientRecord record = newRecord(1, 1);
    assertFalse(TrendUtilities.isDecreasingTrend(List.of(record)));
  }

  @Test
  void testOneElementListIncreasingTrendFalse(){
    PatientRecord record = newRecord(1, 1);
    assertFalse(TrendUtilities.isIncreasingTrend(List.of(record)));
  }

  @Test
  void testTwoElementListDecreasingTrendFalse(){
    PatientRecord record1 = newRecord(1, 1);
    PatientRecord record2 = newRecord(1, 2);
    assertFalse(TrendUtilities.isDecreasingTrend(List.of(record1, record2)));
  }

  @Test
  void testTwoElementListIncreasingTrendFalse(){
    PatientRecord record1 = newRecord(1, 1);
    PatientRecord record2 = newRecord(1, 2);
    assertFalse(TrendUtilities.isIncreasingTrend(List.of(record1, record2)));
  }

  @Test
  void testThreeElementListDecreasingTrendFalse(){
    PatientRecord record1 = newRecord(1, 1);
    PatientRecord record2 = newRecord(1, 2);
    PatientRecord record3 = newRecord(1, 3);
    assertFalse(TrendUtilities.isDecreasingTrend(List.of(record1, record2, record3)));
  }

  @Test
  void testThreeElementListDecreasingTrendTrue(){
    PatientRecord record1 = newRecord(30, 1);
    PatientRecord record2 = newRecord(19, 2);
    PatientRecord record3 = newRecord(8, 3);
    assertTrue(TrendUtilities.isDecreasingTrend(List.of(record1, record2, record3)));
  }

  @Test
  void testThreeElementListIncreasingTrendFalse(){
    PatientRecord record1 = newRecord(1, 1);
    PatientRecord record2 = newRecord(1, 2);
    PatientRecord record3 = newRecord(1, 3);
    assertFalse(TrendUtilities.isIncreasingTrend(List.of(record1, record2, record3)));
  }

  @Test
  void testThreeElementListIncreasingTrendTrue(){
    PatientRecord record1 = newRecord(8, 1);
    PatientRecord record2 = newRecord(19, 2);
    PatientRecord record3 = newRecord(30, 3);
    assertTrue(TrendUtilities.isIncreasingTrend(List.of(record1, record2, record3)));
  }

  private static PatientRecord newRecord(int measurementValue, int timestamp) {
    return new PatientRecord(1, measurementValue, "A", timestamp);
  }

}
