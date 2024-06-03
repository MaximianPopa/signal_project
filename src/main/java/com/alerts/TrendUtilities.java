package com.alerts;

import com.data_management.PatientRecord;

import java.util.List;

public class TrendUtilities {

  public static final int NUMBER_OF_RECORDS = 3;

  public static boolean isDecreasingTrend(List<PatientRecord> lastRecords){
    if(lastRecords.size() < NUMBER_OF_RECORDS)
    {
      //Not enough data to determine trend
      return false;
    }

    for(int i = 1; i < lastRecords.size(); i++)
    {
      if(lastRecords.get(i-1).getMeasurementValue() <= lastRecords.get(i).getMeasurementValue() + 10)
      {
        return false;
      }
    }
    return true;
  }

  public static boolean isIncreasingTrend(List<PatientRecord> lastRecords){
    if(lastRecords.size() < NUMBER_OF_RECORDS)
    {
      //Not enough data to determine trend
      return false;
    }

    for(int i = 1; i < lastRecords.size(); i++)
    {
      if(lastRecords.get(i-1).getMeasurementValue() >= lastRecords.get(i).getMeasurementValue() - 10)
      {
        return false;
      }
    }
    return true;
  }

}
