package calculator.parser;

import calculator.DelimiterConfiguration;
import calculator.DelimiterUtils;

import java.util.*;

public class DelimitedNumberParser {
    private final String delimiterRegex;

    private DelimitedNumberParser(String delimiterRegex) {
        this.delimiterRegex = delimiterRegex;
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
        return Arrays.stream(text.split(delimiterRegex))
                .map(Integer::parseInt)
                .toList();
    }

    public static class Builder {
        private final Set<Character> delimiters;

        public Builder() {
            this.delimiters = new HashSet<>(DelimiterConfiguration.DEFAULT_DELIMITERS);
        }

        public Builder addDelimiter(char customDelimiter) {
            validateDelimiter(customDelimiter);
            this.delimiters.add(customDelimiter);
            return this;
        }

        public DelimitedNumberParser build() {
            String delimitersAsString = DelimiterUtils.getDelimitersAsString(delimiters);
            return new DelimitedNumberParser(buildDelimiterRegex(delimitersAsString));
        }

        private void validateDelimiter(char delimiter) {
            if (Character.isDigit(delimiter)) {
                throw new IllegalArgumentException("숫자는 구분자로 사용할 수 없습니다: " + delimiter);
            }
        }

        private String buildDelimiterRegex(String delimiters) {
            return "[" + delimiters + "]";
        }
    }
}
