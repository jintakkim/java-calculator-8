package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자를_구분하는_문자열이_잘못된_경우_예외가_발생한다() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("/;\n1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 음수가_입력되면_그에_맞는_예외_메시지가_출력되어야_한다() {
        String expectedErrorMessage = "음수값은 허용되지 않습니다.";
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(expectedErrorMessage)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
