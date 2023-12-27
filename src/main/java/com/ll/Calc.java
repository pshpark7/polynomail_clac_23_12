package com.ll;

public class Calc {
  public static int run(String exp) {

    exp = stripOuterBracket(exp);

    boolean needToMultiply = exp.contains(" * ");
    boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");

    boolean needToCompound = needToMultiply && needToPlus;

    if (needToCompound) {
      String[] bits = exp.split(" \\+ ");

      return Integer.parseInt(bits[0]) + run(bits[1]);
    }
    if (needToPlus) {
      exp = exp.replaceAll("\\- ", "\\+ \\-");

      String[] bits = exp.split(" \\+ ");

      int sum = 0;

      for (int i = 0; i < bits.length; i++) {
        sum += Integer.parseInt(bits[i]);
      }

      return sum;
    } else if (needToMultiply) {
      String[] bits = exp.split(" \\* ");

      int rs = 1;

      for (int i = 0; i < bits.length; i++) {
        rs *= Integer.parseInt(bits[i]);
      }g
      return rs;
    }

    throw new RuntimeException("처리할 수 있는 계산식이 아닙니다");
  }

  private static String stripOuterBracket(String exp) {
    int outerBracketCount = 0;

    while (exp.charAt(outerBracketCount) == '(' && exp.charAt(exp.length() - 1 - outerBracketCount) == ')') {
      outerBracketCount++;
    }

    if (outerBracketCount == 0) {
      return exp;
    }

    ;
    return exp.substring(outerBracketCount, exp.length() - outerBracketCount);
  }
}