import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PrintAllPermutation {
    public static void main(String ags[]) {
        char[] charArray = {'A', 'B', 'C', 'D', 'E'};
        int wordLength = 4;
        List<String> allPermutations = new ArrayList<>();
        // generateUniquePermutation(charArray, allPermutations, "", 0, wordLength);

        allPermutations = generatePermutations(new String(charArray),wordLength);
       // permute(charArray, 0, charArray.length, wordLength, allPermutations, new StringBuilder());
        System.out.println(allPermutations);
    }

    private static boolean isValidPermutation(String perm) {
        //System.out.println("perm = "+perm);
        for (int i = 0; i < perm.length() - 1; i++) {
            if (isConsonant(perm.charAt(i)) && isConsonant(perm.charAt(i + 1))) {
                //System.out.println("returning false ");
                return false;
            }
        }
        //System.out.println("returning true ");
        return true;
    }

    private static boolean isConsonant(char c) {
        return "aeiouAEIOU".indexOf(c) == -1;
    }

    private static List<String> generatePermutations(String input, int k) {
        List<String> result = new ArrayList<>();
        permute("", input, k, result);
        return result;
    }

    private static void permute(String prefix, String remaining, int k, List<String> result) {
        System.out.println("prefix = "+prefix+", remaining = "+remaining);
        if (prefix.length() == k) {
            System.out.println("Before validation prefix = "+prefix);
            if (isValidPermutation(prefix)) {
                result.add(prefix);
                System.out.println("After validation result = "+result);
            }
            return;
        }

        /*for (int i = 0; i < remaining.length(); i++) {
            char current = remaining.charAt(i);
            System.out.println("i= "+i+", current = "+current);
            if (prefix.length() == 0 || !isConsonant(prefix.charAt(prefix.length() - 1)) || !isConsonant(current)) {
                System.out.println("Before call, prefix + current = "+(prefix + current)+", remaining.substring(0, i) + remaining.substring(i + 1) = "+(remaining.substring(0, i) + remaining.substring(i + 1)));
                permute(prefix + current, remaining.substring(0, i) + remaining.substring(i + 1), k, result);
            }
        }*/
        IntStream.range(0, remaining.length())
                .forEach(i -> {
                    char current = remaining.charAt(i);
                    if (prefix.length() == 0 || !isConsonant(prefix.charAt(prefix.length() - 1)) || !isConsonant(current)) {
                        permute(prefix + current, remaining.substring(0, i) + remaining.substring(i + 1), k, result);
                    }
                });
    }
}
