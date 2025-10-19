package calculator;

public class ConsoleWriter {
    private static final String START_MESSAGE = "덧셈할 문자열을 입력해 주세요.";
    private static final String RESULT_MESSAGE_TEMPLATE = "결과 : %d";

    public static void writeStartMessage() {
        write(START_MESSAGE);
    }

    public static void writeCalResult(int result) {
        write(String.format(RESULT_MESSAGE_TEMPLATE, result));
    }

    public static void write(String message) {
        System.out.println(message);
    }
}
