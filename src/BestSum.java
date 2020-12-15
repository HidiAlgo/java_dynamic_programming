import java.util.*;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BestSum {
    //Time O(n^m * m)   Space O(m^2)
    private static BiFunction<Integer, List<Integer>, List<Integer>> bestSum1;

    //Time O(n * m^2)   Space O(m^2)
    private static BiFunction<Integer, List<Integer>,
            Function<HashMap<Integer, List<Integer>>, List<Integer>>> bestSum2;

    public static void main(String[] args) {
        bestSum1 = (targetSum, n) -> {
            if(targetSum == 0) return new ArrayList<>();
            if(targetSum<0) return null;

            List<Integer> shortestPath = null;

            for(Integer num: n){
                Integer remainder = targetSum - num;
                List<Integer> remainderResult = bestSum1.apply(remainder,n);
                if(remainderResult != null){
                    remainderResult.add(num);
                    if(shortestPath == null || shortestPath.size() > remainderResult.size()){
                        shortestPath = remainderResult;
                    }
                }
            }
            return shortestPath;
        };

        //This is a kind of currying function
        bestSum2 = (targetSum, n) -> (memo) -> {
            if(memo.containsKey(targetSum)) return memo.get(targetSum);
            if(targetSum == 0) return new ArrayList<>();
            if(targetSum<0) return null;

            List<Integer> shortestPath = null;

            for(Integer num: n){
                Integer remainder = targetSum - num;
                List<Integer> remainderResult = bestSum2.apply(remainder, n).apply(memo);

                if(remainderResult != null){
                    List<Integer> out = new ArrayList<>(remainderResult);
                    out.add(num);
                    if(shortestPath == null || shortestPath.size() > remainderResult.size()){
                        shortestPath = out;
                    }
                }

            }
            memo.put(targetSum,shortestPath);
            return shortestPath;
        };
//        System.out.println(bestSum1.apply(7, Arrays.asList(1,2,5,25)));
        System.out.println(bestSum2.apply(7, Arrays.asList(1,2,5,25)).apply(new HashMap<>()));
        System.out.println(bestSum2_method(7, Arrays.asList(1,2,5,25), new HashMap<>()));
    }

    //If you make your self difficult to understand the bestSum2 lambda expression, then try out this method
    //They both do the same.
    public static List<Integer> bestSum2_method(Integer targetSum, List<Integer> n, HashMap<Integer, List<Integer>> memo){
        if(memo.containsKey(targetSum)) return memo.get(targetSum);
        if(targetSum == 0) return new ArrayList<>();
        if(targetSum<0) return null;

        List<Integer> shortestPath = null;

        for(Integer num: n){
            Integer remainder = targetSum - num;
            List<Integer> remainderResult = bestSum2_method(remainder, n, memo);

            if(remainderResult != null){
                List<Integer> out = new ArrayList<>(remainderResult);
                out.add(num);
                if(shortestPath == null || shortestPath.size() > remainderResult.size()){
                    shortestPath = out;
                }
            }

        }
        memo.put(targetSum,shortestPath);
        return shortestPath;
    }
}
