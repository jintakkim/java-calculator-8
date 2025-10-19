package calculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CalculatorTest {
    private Calculator calculator = new Calculator();

    @Test
    void 음수_덧셈은_예외가_발생한다() {
        Assertions.assertThatThrownBy(() -> calculator.add(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 여러개의_숫자를_더할_수_있다() {
        calculator.add(List.of(1,2,3));
        Assertions.assertThat(calculator.getSum()).isEqualTo(6);
    }
}
