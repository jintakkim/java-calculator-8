package calculator;

import java.util.List;

public class Calculator {
    private int sum = 0;

    public void add(int value) {
        if(value < 0) {
            throw new IllegalArgumentException("음수값은 허용되지 않습니다.");
        }
        sum += value;
    }

    public void add(List<Integer> values) {
        values.forEach(this::add);
    }

    public int getSum() {
        return sum;
    }
}
