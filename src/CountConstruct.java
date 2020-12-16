import java.util.Arrays;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.List;
import java.util.function.Function;

public class CountConstruct {
    //TIme O(n^m * m)   Space(m^2)
    private static BiFunction<String, List<String>, Integer> countConstruct1;

    //TIme O(n * m^2)   Space(m^2)
    private static BiFunction<String, List<String>,
            Function<HashMap<String,Integer>,Integer>> countConstruct2;

    public static void main(String[] args) {
        countConstruct1 = (target,wordBank) -> {
            if (target.equals("")) return 1;

            Integer total = 0;

            for(String word: wordBank){
                if(target.indexOf(word) == 0){
                    String suffix = target.substring(word.length());
                    Integer numOfWays = countConstruct1.apply(suffix,wordBank);
                    total += numOfWays;
                }
            }
            return total;
        };
        //This is a kind of currying function
        countConstruct2 = (target,wordBank) -> (memo) ->{
            if (memo.containsKey(target)) return memo.get(target);
            if (target.equals("")) return 1;

            Integer total = 0;

            for(String word: wordBank){
                if(target.indexOf(word) == 0){
                    String suffix = target.substring(word.length());
                    Integer numOfWays = countConstruct2.apply(suffix,wordBank).apply(memo);
                    total += numOfWays;
                }
            }
            memo.put(target, total);
            return total;
        };
        System.out.println(countConstruct1.apply("purple", Arrays.asList("purp","p","ur","le")));

        System.out.println(countConstruct2.apply("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                Arrays.asList("ee", "eeee", "eeeee", "eeeeee", "eeeeeee")).apply(new HashMap<>()));

        System.out.println(countConstruct2_method("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                Arrays.asList("ee", "eeee", "eeeee", "eeeeee", "eeeeeee"),new HashMap<>()));


    }

    //If you make your self difficult to understand the countConstruct2 lambda expression, then try out this method
    //They both do the same.
    public static Integer countConstruct2_method(String target, List<String> wordBank, HashMap<String, Integer> memo){
        if (memo.containsKey(target)) return memo.get(target);
        if (target.equals("")) return 1;

        Integer total = 0;

        for(String word: wordBank){
            if(target.indexOf(word) == 0){
                String suffix = target.substring(word.length());
                Integer numOfWays = countConstruct2_method(suffix, wordBank,memo);
                total += numOfWays;
            }
        }
        memo.put(target, total);
        return total;
    }
}
