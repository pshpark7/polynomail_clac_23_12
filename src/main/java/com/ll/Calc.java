package com.ll;

public class Calc {
  public static int run(String exp) { // 10 + (10 + 5)
    exp = exp.trim();
    exp = stripOuterBracket(exp);

    // 연산기호가 없으면 바로 리턴
    if (!exp.contains(" ")) return Integer.parseInt(exp);

    boolean needToMultiply = exp.contains(" * ");
    boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");

    boolean needToCompound = needToMultiply && needToPlus;
    boolean needToSplit = exp.contains("(") || exp.contains(")");

    if (needToSplit) {  // 800 + (10 + 5)

      int splitPointIndex = findSplitPointIndex(exp);

      String firstExp = exp.substring(0, splitPointIndex);
      String secondExp = exp.substring(splitPointIndex + 1);

      char operator = exp.charAt(splitPointIndex);

      exp = Calc.run(firstExp) + " " + operator + " " + Calc.run(secondExp);

      return Calc.run(exp);

    } else if (needToCompound) {
      String[] bits = exp.split(" \\+ ");

      return Integer.parseInt(bits[0]) + Calc.run(bits[1]); // TODO
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
      }
      return rs;
    }

    throw new RuntimeException("처리할 수 있는 계산식이 아닙니다");
  }

  private static int findSplitPointIndexBy(String exp, char findChar) {
    int bracketCount = 0;

    for (int i = 0; i < exp.length(); i++) {
      char c = exp.charAt(i);

      if (c == '(') {
        bracketCount++;
      } else if (c == ')') {
        bracketCount--;
      } else if (c == findChar) {
        if (bracketCount == 0) return i;
      }
    }
    return -1;
  }

  private static int findSplitPointIndex(String exp) {
    int index = findSplitPointIndexBy(exp, '+');

    if (index >= 0) return index;

    return findSplitPointIndexBy(exp, '*');
  }

  private static String stripOuterBracket(String exp) {
    int outerBracketCount = 0;

    while (exp.charAt(outerBracketCount) == '(' && exp.charAt(exp.length() - 1 - outerBracketCount) == ')') {
      outerBracketCount++;
    }

    if (outerBracketCount == 0) return exp;


    return exp.substring(outerBracketCount, exp.length() - outerBracketCount);
  }
}