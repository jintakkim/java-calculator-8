package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;

public class CliBasedRunner {
    public static void run() {
        Calculator calculator = new Calculator();
        ConsoleWriter.writeStartMessage();
        String text = Console.readLine();
        CustomDelimiterParsedInputContext context = CustomDelimiterParser.parse(text);
        Parser.Builder numberParserBuilder = Parser.builder();
        if(context.customDelimiter() != null) {
            numberParserBuilder.addDelimiter(context.customDelimiter());
        }
        Parser numberParser = numberParserBuilder.build();
        List<Integer> numbers = numberParser.parse(context.numbersText());
        calculator.add(numbers);
        ConsoleWriter.writeCalResult(calculator.getSum());
        Console.close();
    }
}
