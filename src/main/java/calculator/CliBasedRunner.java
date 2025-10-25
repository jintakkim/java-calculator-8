package calculator;

import calculator.parser.CustomDelimiterParser;
import calculator.parser.DelimitedNumberParser;
import camp.nextstep.edu.missionutils.Console;

import java.util.List;

public class CliBasedRunner {
    public static void run() {
        Calculator calculator = new Calculator();
        ConsoleWriter.writeStartMessage();
        String text = Console.readLine();
        CustomDelimiterParsedInputContext context = CustomDelimiterParser.parse(text);
        DelimitedNumberParser.Builder numberParserBuilder = DelimitedNumberParser.builder();
        if(context.customDelimiter() != null) {
            numberParserBuilder.addDelimiter(context.customDelimiter());
        }
        DelimitedNumberParser numberDelimitedNumberParser = numberParserBuilder.build();
        List<Integer> numbers = numberDelimitedNumberParser.parse(context.numbersText());
        calculator.add(numbers);
        ConsoleWriter.writeCalResult(calculator.getSum());
        Console.close();
    }
}
