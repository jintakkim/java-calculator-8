package calculator.parser;

import calculator.CustomDelimiterParsedInputContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomDelimiterParserTest {
    @Test
    void 커스텀_딜리미터가_있다면_파싱되어야_한다() {
        CustomDelimiterParsedInputContext context = CustomDelimiterParser.parse("//;\\n1;2");
        Assertions.assertThat(context.customDelimiter())
                .isNotNull()
                .isEqualTo(';');
    }

    @Test
    void 커스텀_딜리미터가_없다면_context에_null로_전달한다() {
        CustomDelimiterParsedInputContext context = CustomDelimiterParser.parse("1;2");
        Assertions.assertThat(context.customDelimiter()).isNull();
    }
}
