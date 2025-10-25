package calculator;

import calculator.parser.CustomDelimiterParser;
import calculator.parser.DelimitedNumberParser;
import calculator.validator.NumbersTextValidator;
import camp.nextstep.edu.missionutils.Console;

import java.util.List;

public class CliBasedRunner {
    public static void run() {
        ConsoleWriter.writeStartMessage();
        String text = Console.readLine();
        CustomDelimiterParsedInputContext context = CustomDelimiterParser.parse(text);
        NumbersTextValidator numbersTextValidator;
        numbersTextValidator = NumbersTextValidator.create(context.customDelimiter());
        numbersTextValidator.validate(context.numbersText());
        DelimitedNumberParser.Builder numberParserBuilder = DelimitedNumberParser.builder();
        if(context.customDelimiter() != null) {
            numberParserBuilder.addDelimiter(context.customDelimiter());
        }
        DelimitedNumberParser numberDelimitedNumberParser = numberParserBuilder.build();
        List<Integer> numbers = numberDelimitedNumberParser.parse(context.numbersText());
        Calculator calculator = new Calculator();
        calculator.add(numbers);
        ConsoleWriter.writeCalResult(calculator.getSum());
        Console.close();
    }
}
