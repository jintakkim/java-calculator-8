package calculator.validator;

import calculator.CustomDelimiterParsedInputContext;
import calculator.DelimiterConfiguration;
import calculator.DelimiterUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumbersTextValidator {
    private final String invalidCharsRegex;

    private NumbersTextValidator(String invalidCharsRegex) {
        this.invalidCharsRegex = invalidCharsRegex;
    }

    public static NumbersTextValidator create(Character customDelimiter) {
        Set<Character> delimiters = new HashSet<>(DelimiterConfiguration.DEFAULT_DELIMITERS);
        if(customDelimiter != null) delimiters.add(customDelimiter);
        String inValidCharsRegex = buildInvalidCharsRegex(DelimiterUtils.getDelimitersAsString(delimiters));
        return new NumbersTextValidator(inValidCharsRegex);
    }

    public void validate(String numbersText) {
        Matcher matcher = Pattern.compile(invalidCharsRegex).matcher(numbersText);
        if (matcher.find()) {
            throw new IllegalArgumentException("허용되지 않은 문자가 포함되어 있습니다: " + matcher.group());
        }
    }

    private static String buildInvalidCharsRegex(String delimiters) {
        return "[^\\d" + delimiters + "-]";
    }
}
