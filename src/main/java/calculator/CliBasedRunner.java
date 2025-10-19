package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CliBasedRunner {
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("^//(.)\\\\n(.*)$");

    public static void run() {
        ConsoleWriter.writeStartMessage();
        String text = Console.readLine();

        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(text);
        Parser.Builder parserBuilder = Parser.builder();
        String numbersText = text;
        if (matcher.matches()) {
            char customDelimiter = matcher.group(1).charAt(0);
            parserBuilder.addDelimiter(customDelimiter);
            numbersText = matcher.group(2);
        }
        Parser parser = parserBuilder.build();
        Calculator calculator = new Calculator();
        calculator.add(parser.parse(numbersText));
        ConsoleWriter.writeCalResult(calculator.getSum());
        Console.close();
    }
}
