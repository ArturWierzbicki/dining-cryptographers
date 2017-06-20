package utils;

import java.util.List;

public final class CollectionUtils {

    private CollectionUtils() {}

    public static int xor(List<Integer> numbers) {
        return numbers.stream().reduce((x,y) -> x^y).get();
    }
}
