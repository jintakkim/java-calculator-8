package calculator.parser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DelimitedNumberParserTest {
    @Test
    void 커스텀_구분자_등록을_하지않았다면_기본_구분자를_이용하여_파싱한다() {
        List<Integer> result = DelimitedNumberParser.builder().build().parse("1:2,4");
        Assertions.assertThat(result).containsExactly(1, 2, 4);
    }

    @Test
    void 커스텀_구분자를_등록했다면_커스텀_구분자도_포함하여_파싱한다() {
        List<Integer> result = DelimitedNumberParser.builder()
                .addDelimiter(';')
                .build()
                .parse("1:2;4");
        Assertions.assertThat(result).containsExactly(1, 2, 4);
    }

    @Nested
    class 잘못된_입력값_테스트 {
        @Test
        void 커스텀_구분자가_숫자로_들어오는_경우_예외가_발생한다() {
            Assertions.assertThatThrownBy(() -> DelimitedNumberParser.builder().addDelimiter('2'))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 등록되지_않은_구분자를_사용시_예외가_발생한다() {
            DelimitedNumberParser delimitedNumberParser = DelimitedNumberParser.builder().build();
            Assertions.assertThatThrownBy(() -> delimitedNumberParser.parse("4;3"))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
