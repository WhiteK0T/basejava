package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainStreams {

    public static void main(String[] args) {
        //System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 3}));
        //System.out.println(minValue(new int[]{9, 8}));
        System.out.println(oddOrEven(Arrays.asList(9, 8, 1)));
        System.out.println(oddOrEven(Arrays.asList(1, 2)));
    }

    /*
        Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число, составленное из
        этих уникальных цифр. Не использовать преобразование в строку и обратно. Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89

     */
    public static int minValue(int[] values) {
        return Arrays.stream(values)
                .sorted()
                .distinct()
                .reduce((left, right) -> left * 10 + right)
                .getAsInt();
    }

    /*
        Если сумма всех чисел нечетная - удалить все нечетные, если четная - удалить все четные.
        Сложность алгоритма должна быть O(N). Optional - решение в один стрим.
    */
    public static List<Integer> oddOrEven(List<Integer> integers) {
        ArrayList<Integer> odd = new ArrayList<>();
        ArrayList<Integer> even = new ArrayList<>();
        final int sum = integers.stream()
                .reduce(0, (a, b) -> {
                    if (b % 2 == 0) {
                        odd.add(b);
                    } else {
                        even.add(b);
                    }
                    return a + b;
                });
        return sum % 2 == 0 ? odd : even;
    }
}
