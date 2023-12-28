package com.ll;

public class Calc {
  public static boolean recursionDebug = true; // 내가 디버그 모드를 켜겠다 할때는 true로 변경

  public static int runCallCount = 0;

  public static int run(String exp) {
    runCallCount++;

    exp = exp.trim(); // exp 의 좌우 공백 제거
    exp = stripOuterBracket(exp); //필요없는 괄호를 제거하는 함수 실행

    if (recursionDebug) {
      System.out.printf("exp(%d) : %s\n", runCallCount, exp);
    }


    if (!exp.contains(" ")) return Integer.parseInt(exp); //연산기호가 없으면 string 타입의 exp를 정수로 변환하여 리턴

    boolean needToMultiply = exp.contains(" * ");
    boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");
    // 다항식에서 나올 수 있는 연산기호에 따라 구분해주기 위해 포함하는의미의 contains()로 구분, 더하기는 +(-)로 나타낼
    // 수 있기때문에 or 을 사용하여 한꺼번에 처리
    boolean needToCompound = needToMultiply && needToPlus;
    boolean needToSplit = exp.contains("(") || exp.contains(")");
    // +, *의 연산기호가 동시에 들어가있는 식, 괄호로 감싸 연산의 순서가 있는 식을 처리하기 위한 구분
    if (needToSplit) {  // 괄호로 감싸 연산의 순서가 있는 식을 처리하기 위한 내용

      int splitPointIndex = findSplitPointIndex(exp); //괄호 안의 식을 처리하고 그 밖에 연산기호를 통해 식을 처리해야하기 때문에
      // 괄호 밖의 연산기호의 index를 찾는 함수 실행

      String firstExp = exp.substring(0, splitPointIndex);
      String secondExp = exp.substring(splitPointIndex + 1);
      //연산기호의 index를 기준으로 firstExp와 secondExp로 문자열을 나눔
      char operator = exp.charAt(splitPointIndex);
      //연산기호를 기준으로 문자열을 나누었기때문에 그 사이에 빠진 연산기호를 char타입의 operator변수에 저장
      exp = Calc.run(firstExp) + " " + operator + " " + Calc.run(secondExp);
      //재귀함수를 사용하여 firstExp와 secondExp의 return된 값을 받고 저장해둔 연산기호로 두 수를 연산하는 식을 담은 문자열을 변수 exp에 초기화
      return Calc.run(exp);
    } else if (needToCompound) { // +, *의 연산기호가 동시에 들어가있는 식을 처리하기 위한 내용
      String[] bits = exp.split(" \\+ ");

      return Integer.parseInt(bits[0]) + Calc.run(bits[1]); // TODO
    }
    // +를 기준으로 식을 쪼개고, *가 들어간 식을 재귀함수를 통해 따로 실행하여 그 값을 얻어내고, + 왼쪽의 수를 합하여 결과 도출
    // + 왼쪽에 곱하는 식이 있거나 곱으로 이루어진 식 두개를 +로 합하는 식 등은 구현할 수없는 내용
    if (needToPlus) { // + 또는 -로만 이루어진 식을 처리하기 위한 내용
      exp = exp.replaceAll("\\- ", "\\+ \\-");

      String[] bits = exp.split(" \\+ ");

      int sum = 0;

      for (int i = 0; i < bits.length; i++) {
        sum += Integer.parseInt(bits[i]);
      }

      return sum;
    } else if (needToMultiply) { // *으로만 이루어진 식을 처리하기 위한 내용
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

  private static String stripOuterBracket(String exp) { // string exp의 필요없는 괄호들을 삭제하는 함수 생성
    int outerBracketCount = 0;

    while (exp.charAt(outerBracketCount) == '(' && exp.charAt(exp.length() - 1 - outerBracketCount) == ')') {
      outerBracketCount++;
    }

    if (outerBracketCount == 0) return exp;


    return exp.substring(outerBracketCount, exp.length() - outerBracketCount);
  }
}
