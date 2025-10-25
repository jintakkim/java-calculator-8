package calculator.parser;

import calculator.CustomDelimiterParsedInputContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CustomDelimiterParser {
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("^//(?<delimiter>.)\\\\n(?<numbers>.*)$");

    private CustomDelimiterParser() {}

    public static CustomDelimiterParsedInputContext parse(String text) {
        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(text);
        if (matcher.matches()) {
            char customDelimiter = matcher.group("delimiter").charAt(0);
            String numbersText = matcher.group("numbers");
            return new CustomDelimiterParsedInputContext(customDelimiter, numbersText);
        }
        return new CustomDelimiterParsedInputContext(null, text);
    }
}
