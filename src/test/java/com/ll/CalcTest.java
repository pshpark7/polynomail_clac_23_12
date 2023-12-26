package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcTest {
  @Test
  @DisplayName("1 + 1 == 2")
  void t1() {
    assertThat(Calc.run("1 + 1")).isEqualTo(2);
  }

  @Test
  @DisplayName("2 + 1 == 3")
  void t2() {
    assertThat(Calc.run("2 + 1")).isEqualTo(3);
  }

  @Test
  @DisplayName("2 + 2 == 4")
  void t3() {
    assertThat(Calc.run("2 + 2")).isEqualTo(4);
  }

  @Test
  @DisplayName("123 + 456 == 579")
  void t4() {
    assertThat(Calc.run("123 + 456")).isEqualTo(579);
  }

  @Test
  @DisplayName("1000 + 200 == 1200")
  void t5() {
    assertThat(Calc.run("1000 + 200")).isEqualTo(1200);
  }

  // 구현
  @Test
  @DisplayName("50 - 30 == 20")
  void t6() {
    assertThat(Calc.run("50 - 30")).isEqualTo(20);
  }
}