package calculator;

import calculator.parser.CustomDelimiterParser;
import calculator.parser.DelimitedNumberParser;
import calculator.validator.NumbersTextValidator;

import java.util.List;

public class StringCalculator {

    public static int add(String text) {
        CustomDelimiterParsedInputContext context = CustomDelimiterParser.parse(text);
        validateNumbersText(context.numbersText(), context.customDelimiter());
        List<Integer> numbers = parseNumbers(context.numbersText(), context.customDelimiter());
        Calculator calculator = new Calculator();
        calculator.add(numbers);
        return calculator.getSum();
    }

    private static void validateNumbersText(String numbersText, Character customDelimiter) {
        NumbersTextValidator validator = NumbersTextValidator.create(customDelimiter);
        validator.validate(numbersText);
    }

    private static List<Integer> parseNumbers(String numbersText, Character customDelimiter) {
        DelimitedNumberParser.Builder parserBuilder = DelimitedNumberParser.builder();
        if (customDelimiter != null) {
            parserBuilder.addDelimiter(customDelimiter);
        }
        DelimitedNumberParser parser = parserBuilder.build();
        return parser.parse(numbersText);
    }
}
