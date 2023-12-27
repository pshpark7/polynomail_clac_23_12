package com.ll;

public class Calc {
  public static int run(String exp) {

    exp = exp.replaceAll("\\- ", "\\+ \\-");

    String[] bits = exp.split(" \\+ ");

    int sum = 0;

    for (int i = 0; i < bits.length; i++) {
      sum += Integer.parseInt(bits[i]);
    }

    return sum;

//    throw new RuntimeException("처리할 수 있는 계산식이 아닙니다");
  }
}