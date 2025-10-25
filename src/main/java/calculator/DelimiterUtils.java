package calculator;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class DelimiterUtils {
    private DelimiterUtils() {}

    public static String getDelimitersAsString(Set<Character> delimiters) {
        return delimiters.stream()
                .map(String::valueOf)
                .map(Pattern::quote)
                .collect(Collectors.joining());
    }
}
