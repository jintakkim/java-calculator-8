package calculator;

import camp.nextstep.edu.missionutils.Console;

public class CliBasedRunner {
    public static void run() {
        ConsoleWriter.writeStartMessage();
        String text = Console.readLine();
        Console.close();
        int result = StringCalculator.add(text);
        ConsoleWriter.writeCalResult(result);
    }
}
