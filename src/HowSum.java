import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class HowSum {
    private static BiFunction<Integer, List<Integer>, List<Integer>> howSum1;
    //Time O(n^m * m)   Space O(m) where n = length of the numbers array, m = targetSum

    private static BiFunction<Integer, List<Integer>,
            Function<HashMap<Integer, List<Integer>>, List<Integer>>> howSum2;
    //Time O(n*m^2)     Space O(m*m) where n = length of the numbers array, m = targetSum

    public static void main(String []args){

        howSum1 = (targetSum, n) -> {
            if(targetSum == 0) return new ArrayList<>();
            if(targetSum<0) return null;

            for(Integer num: n){
                Integer remainder = targetSum - num;
                List<Integer> remainderResult = howSum1.apply(remainder,n);
                if(remainderResult != null) {
                    remainderResult.add(num);
                    return remainderResult;
                }
            }
            return null;
        };

        //This is a kind of currying function
        howSum2 = (targetSum,n)-> (memo) -> {
            if(memo.containsKey(targetSum)) return memo.get(targetSum);
            if(targetSum == 0) return new ArrayList<>();
            if(targetSum<0) return null;

            for(Integer num: n){
                Integer remainder = targetSum - num;
                List<Integer> remainderResult = howSum2.apply(remainder, n).apply(memo);
                if(remainderResult != null){
                    List<Integer> out = new ArrayList<>(remainderResult);
                    out.add(num);
                    memo.put(targetSum,out);
                    return out;
                }
            }
            memo.put(targetSum, null);
            return null;
        };

//        System.out.println(howSum1.apply(300, Arrays.asList(7,14)));
        System.out.println(howSum2.apply(100, Arrays.asList(7,14,5)).apply(new HashMap<>()));
        System.out.println(howSum2_method(100, Arrays.asList(7,14,5),new HashMap<>()));
    }

    //If you make your self difficult to understand the howSum2_method lambda expression, then try out this method
    //They both do the same.
    public static List<Integer> howSum2_method(Integer targetSum, List<Integer> n, HashMap<Integer, List<Integer>> memo){
        if(memo.containsKey(targetSum)) return memo.get(targetSum);
        if(targetSum == 0) return new ArrayList<>();
        if(targetSum<0) return null;

        for(Integer num: n){
            Integer remainder = targetSum - num;
            List<Integer> remainderResult = howSum2_method(remainder, n, memo);
            if(remainderResult != null){
                List<Integer> out = new ArrayList<>(remainderResult);
                out.add(num);
                memo.put(targetSum,out);
                return out;
            }
        }
        memo.put(targetSum, null);
        return null;
    }
}