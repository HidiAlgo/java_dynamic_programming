import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class GridTraveller {
    private static BiFunction<Integer, Integer, Long> grid_traveller1;

    //This is a kind of currying function
    private static BiFunction<Integer, Integer,
            Function<HashMap<String, Long>,Long>> grid_traveller2;

    public static void main(String[] args) {

        //Time O(2^n+m)     Space O(n+m)
        grid_traveller1 = (m, n) -> {
            if (m == 1 && n == 1) return (long) 1;
            if (m == 0 || n == 0) return (long) 0;
            return grid_traveller1.apply(m - 1, n) + grid_traveller1.apply(m, n-1);
        };

        //Time O(n*m)     Space O(n+m)
        grid_traveller2 = (m, n) -> (memo) -> {
            String key = m + ","+n;
            if(memo.containsKey(key)) return memo.get(key);
            if (m == 1 && n == 1) return (long) 1;
            if (m == 0 || n == 0) return (long) 0;

            Long result = grid_traveller2.apply(m-1,n).apply(memo) + grid_traveller2.apply(m,n-1).apply(memo);
            memo.put(key, result);

            return result;
        };

//        System.out.println(grid_traveller1.apply(18,18));
        System.out.println(grid_traveller2.apply(18,18).apply(new HashMap<>()));
        System.out.println(grid_traveller2_method(18, 18, new HashMap<>()));
    }

    //If you make your self difficult to understand the grid_traveller2 lambda expression, then try out this method
    //They both do the same.
    private static Long grid_traveller2_method(Integer m, Integer n, HashMap<String, Long> memo){
        String key = m + ","+n;
        if(memo.containsKey(key)) return memo.get(key);
        if (m == 1 && n == 1) return (long) 1;
        if (m == 0 || n == 0) return (long) 0;

        Long result = grid_traveller2.apply(m-1,n).apply(memo) + grid_traveller2.apply(m,n-1).apply(memo);
        memo.put(key, result);

        return result;
    }
}
