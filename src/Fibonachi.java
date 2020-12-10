import java.util.HashMap;
import java.util.function.Function;

public class Fibonachi {
    private static Function<Integer, Long> fib1; // Time O(2^n) Space O(n)
    private static Function<Integer, Long> fib2; // Time O(n) Space O(n)

    private static HashMap<Integer, Long> memo = new HashMap<>();

    public static void main(String[] args) {

        fib1 = n -> {
            if (n <= 2) return (long) 1;
            return fib1.apply(n-1) + fib1.apply(n-2);
        };

        fib2 = n -> {
            if (memo.containsKey(n)) return memo.get(n);
            if (n<=2) return (long) 1;
            Long result = fib2.apply(n-1) + fib2.apply(n-2);
            memo.put(n, result);
            return result;
        };


        System.out.println(fib2.apply(6));
        System.out.println(fib2.apply(50));
        System.out.println(fib2.apply(80));
    }
}
