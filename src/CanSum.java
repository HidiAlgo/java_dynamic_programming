import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CanSum {
    private static BiFunction<Integer, List<Integer>,Boolean> canSum1; // Time O(n^m) Space O(m) where TargetSum = m and the list of numbers = n

    //This is a kind of currying function
    private static BiFunction<Integer, List<Integer>,
            Function<HashMap<Integer, Boolean>, Boolean>> canSum2;// Time O(n*m)    Space O(m) where TargetSum = m and the list of numbers = n.

    public static void main(String[] args) {
        canSum1 = (targetSum, n) -> {
            if(targetSum == 0) return true;
            if(targetSum<0) return false;
            for(Integer num: n){
                var remainder = targetSum - num;
                if(canSum1.apply(remainder, n)) return true;
            }
            return false;
        };

        canSum2 = (targetSum, n) -> (memo) -> {
            if(memo.containsKey(targetSum)) return memo.get(targetSum);
            if(targetSum == 0) return true;
            if(targetSum<0) return false;

            for(Integer num: n){
                var remainder = targetSum - num;
                if(canSum2.apply(remainder,n).apply(memo)) {
                    memo.put(targetSum, true);
                    return true;
                }
            }
            memo.put(targetSum, false);
            return false;
        };

        System.out.println(canSum2.apply(7, Arrays.asList(7, 14)).apply(new HashMap<>()));
        System.out.println(canSum2_method(7, Arrays.asList(7, 14), new HashMap<>()));
    }

    //If you make your self difficult to understand the can_sum2 lambda expression, then try out this method
    //They both do the same.
    public static Boolean canSum2_method(Integer targetSum, List<Integer> n, HashMap<Integer, Boolean> memo){
        if(memo.containsKey(targetSum)) return memo.get(targetSum);
        if(targetSum == 0) return true;
        if(targetSum<0) return false;

        for(Integer num: n){
            var remainder = targetSum - num;
            if(canSum2_method(remainder, n, memo)) {
                memo.put(targetSum, true);
                return true;
            }
        }
        memo.put(targetSum, false);
        return false;
    }
}
