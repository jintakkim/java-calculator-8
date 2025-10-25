package calculator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DelimitedNumberParser {
    private final String delimiterRegex;
    private final String invalidCharsRegex;

    private DelimitedNumberParser(String delimiterRegex, String invalidCharsRegex) {
        this.delimiterRegex = delimiterRegex;
        this.invalidCharsRegex = invalidCharsRegex;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * @param text 단순 숫자와 구분자로 이루어진 문자열 ex) 1,4:6
     * @return 파싱된 숫자 리스트
     */
    public List<Integer> parse(String text) {
        if (text.isEmpty()) return Collections.emptyList();
        validateAllowedCharacters(text);
        return Arrays.stream(text.split(delimiterRegex))
                .map(Integer::parseInt)
                .toList();
    }

    private void validateAllowedCharacters(String text) {
        Matcher matcher = Pattern.compile(invalidCharsRegex).matcher(text);
        if (matcher.find()) {
            throw new IllegalArgumentException("허용되지 않은 문자가 포함되어 있습니다: " + matcher.group());
        }
    }

    public static class Builder {
        private static final Set<Character> DEFAULT_DELIMITERS = Set.of(':', ',');
        private final Set<Character> delimiters;

        public Builder() {
            this.delimiters = new HashSet<>(DEFAULT_DELIMITERS);
        }

        public Builder addDelimiter(char customDelimiter) {
            validateDelimiter(customDelimiter);
            this.delimiters.add(customDelimiter);
            return this;
        }

        public DelimitedNumberParser build() {
            String delimitersAsString = getDelimitersAsString(delimiters);
            return new DelimitedNumberParser(buildDelimiterRegex(delimitersAsString), buildInvalidCharsRegex(delimitersAsString));
        }

        private void validateDelimiter(char delimiter) {
            if (Character.isDigit(delimiter)) {
                throw new IllegalArgumentException("숫자는 구분자로 사용할 수 없습니다: " + delimiter);
            }
        }

        private String getDelimitersAsString(Set<Character> delimiters) {
            return delimiters.stream()
                    .map(String::valueOf)
                    .map(Pattern::quote)
                    .collect(Collectors.joining());
        }

        private String buildDelimiterRegex(String delimiters) {
            return "[" + delimiters + "]";
        }

        private String buildInvalidCharsRegex(String delimiters) {
            return "[^\\d" + delimiters + "]";
        }
    }
}
