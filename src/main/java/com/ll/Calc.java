package com.ll;

public class Calc {
  public static int run(String exp) {

    String[] bits = exp.split(" \\+ ");

    int a = Integer.parseInt(bits[0]);
    int b = Integer.parseInt(bits[1]);

    return a + b;
  }
}