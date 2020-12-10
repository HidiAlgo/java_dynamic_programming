import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Fibonachi {
    private static Function<Integer, Long> fib1; // Time O(2^n) Space O(n)
    private static BiFunction<Integer, HashMap<Integer, Long>, Long> fib2; // Time O(n) Space O(n)


    public static void main(String[] args) {

        fib1 = n -> {
            if (n <= 2) return (long) 1;
            return fib1.apply(n-1) + fib1.apply(n-2);
        };

        fib2 = (n, memo) -> {
            if (memo.containsKey(n)) return memo.get(n);
            if (n<=2) return (long) 1;
            Long result = fib2.apply(n-1, memo) + fib2.apply(n-2, memo);
            memo.put(n, result);
            return result;
        };


        System.out.println(fib2.apply(50, new HashMap<>()));
    }
}
