package calculator.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumbersTextValidatorTest {
    @Test
    void 커스텀_딜리미터가_추가된_경우_커스텀_딜리미터도_허용된_문자열로_추가된다() {
        NumbersTextValidator numbersTextValidator = NumbersTextValidator.create(';');
        numbersTextValidator.validate("4;5:2");
    }

    @ParameterizedTest
    @ValueSource(strings = {"4;5:2", "4dd:2", "43:v"})
    void 딜리미터와_숫자를_제외한_나머지_문자는_허용되지_않는다() {
        NumbersTextValidator numbersTextValidator = NumbersTextValidator.create(null);
        Assertions.assertThatThrownBy(() -> numbersTextValidator.validate("4;5:2"))
                .isInstanceOf(IllegalArgumentException.class);
    }
    
    /**
     * 비지니스 로직상 음수를 잡는 책임을 가진 객체는 Calculator이다.
     */
    @Test
    void 음수도_허용된다() {
        NumbersTextValidator numbersTextValidator = NumbersTextValidator.create(null);
        numbersTextValidator.validate("-4:5:2");
    }
}
