package calculator;

public record CustomDelimiterParsedInputContext(
        Character customDelimiter, //nullable
        String numbersText
) {
}
