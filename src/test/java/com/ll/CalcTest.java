package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
}
