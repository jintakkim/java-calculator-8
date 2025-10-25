package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CustomDelimiterParser {
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("^//(.)\\\\n(.*)$");

    private CustomDelimiterParser() {}

    public static CustomDelimiterParsedInputContext parse(String text) {
        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(text);
        Parser.Builder parserBuilder = Parser.builder();
        if (matcher.matches()) {
            char customDelimiter = matcher.group(1).charAt(0);
            parserBuilder.addDelimiter(customDelimiter);
            String numbersText = matcher.group(2);
            return new CustomDelimiterParsedInputContext(customDelimiter, numbersText);
        }
        return new CustomDelimiterParsedInputContext(null, text);
    }
}
