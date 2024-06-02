package com.alerts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovingAverage {
  private double[] window;
  private int n, insert;
  private long sum;

  public MovingAverage(int size) {
    window = new double[size];
    insert = 0;
    sum = 0;
  }

  public double next(double val) {
    if (n < window.length) {
      n++;
    }
    sum -= window[insert];
    sum += val;
    window[insert] = val;
    insert = (insert + 1) % window.length;
    return (double) sum / n;
  }

  public List<Double> getLastValues(){
    List<Double> lastValues = new ArrayList<>();
    if (n < window.length) {
      for (int i = 0; i < n; i++) {
        double element = window[i];
        lastValues.add(element);
      }
    }
    else{
      int startIndex = insert;
      for (int i = 0; i < n; i++) {
        double element = window[(startIndex + i)%n];
        lastValues.add(element);
      }
    }
    return lastValues;
  }

  @Override
  public String toString() {
    return "MovingAverage{" +
      "window=" + Arrays.toString(window) +
      ", n=" + n +
      ", insert=" + insert +
      ", sum=" + sum +
      ", average=" + sum/n +
      '}';
  }

//  public static void main(String[] args) {
//    MovingAverage movingAverage = new MovingAverage(3);
//    movingAverage.next(3.1);
//    movingAverage.next(10.2);
//    movingAverage.next(5.4);
//    movingAverage.next(6.7);
//    movingAverage.next(9.8);
//    System.out.println("movingAverage = " + movingAverage);
//    System.out.println(movingAverage.getLastValues());
//  }

}
