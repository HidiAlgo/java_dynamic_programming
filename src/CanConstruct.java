import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CanConstruct {
    //TIme O(n^m * m)   Space(m^2)
    private static BiFunction<String, List<String>, Boolean> canConstruct1;

    //TIme O(n * m^2)   Space(m^2)
    private static BiFunction<String, List<String>, Function<HashMap<String, Boolean>, Boolean>> canConstruct2;
    public static void main(String[] args) {
        canConstruct1 = (m, n) -> {
          if (m.equals("")) return true;

          for(String word: n){
              if(m.indexOf(word) == 0){
                  String suffix = m.substring(word.length());
                  if(canConstruct1.apply(suffix, n)){
                      return true;
                  }
              }
          }
          return false;
        };

        canConstruct2 = (m, n) -> (memo) -> {
            if (memo.containsKey(m)) return memo.get(m);
            if (m.equals("")) return true;

            for(String word: n){
                if(m.indexOf(word) == 0){
                    String suffix = m.substring(word.length());
                    if(canConstruct2.apply(suffix, n).apply(memo)){
                        memo.put(m,true);
                        return true;
                    }
                }
            }
            memo.put(m, false);
            return false;
        };
        System.out.println(canConstruct2.apply("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                Arrays.asList("ee", "eeee", "eeeee", "eeeeee", "eeeeeee")).apply(new HashMap<>()));
        System.out.println(canConstruct2_method("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                Arrays.asList("ee", "eeee", "eeeee", "eeeeee", "eeeeeee"),new HashMap<>()));
    }

    public static Boolean canConstruct2_method(String m, List<String> n, HashMap<String, Boolean> memo){
        if (memo.containsKey(m)) return memo.get(m);
        if (m.equals("")) return true;

        for(String word: n){
            if(m.indexOf(word) == 0){
                String suffix = m.substring(word.length());
                if(canConstruct2_method(suffix,n,memo)){
                    memo.put(m,true);
                    return true;
                }
            }
        }
        memo.put(m, false);
        return false;
    }
}
