import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class random {
    public static void main(String[] args) {
        System.out.println(isPalindrome(1121));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int n = x;
        int length = 0;

        while (n != 0) {
            n /= 10;
            length++;
        }

        if (length == 0 || length == 1) {
            return true;
        }

        int midpoint = length / 2;

        ArrayList<Integer> stack = new ArrayList<>();
        int index = 0;

        n = x;

        while (n != 0) {
            int i = n % 10;
            
            if (index < midpoint) {
                stack.add(i);
            } else if (index == midpoint) {
                if (length % 2 == 0) {
                    index++;
                    continue;
                }
            } else {
                if (stack.get(stack.size() - 1) != i) {
                    return false;
                }
                stack.remove(stack.size() - 1);
            }
            
            index++;
            n /= 10;
        }

        return true;
    }


    public static boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();

        boolean first_match = (!s.isEmpty() && p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');

        if (p.length() >= 2 && p.charAt(1) == '*'){
            return (isMatch(s, p.substring(2)) ||
                    (first_match && isMatch(s.substring(1), p)));
        } else {
            return first_match && isMatch(s.substring(1), p.substring(1));
        }

    }

    public static long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int length = nums.size();
        long count = 0;

        Map<Integer, Integer> table = new HashMap<>();

        for (int i = 0; i < length; i++) {
            if (nums.get(i) % modulo == k) {
                table.put(i * length + i, 1);
            } else {
                table.put(i * length + i, 0);
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {

                if (i != j) {
                    table.put(i * length + j, table.get(j * length + j) + table.get(i * length + (j - 1)));
                }

                if (table.get(i * length + j) % modulo == k) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int countSymmetricIntegers(int low, int high) {
        int count = 0;

        for (int i = low; i <= high; i++) {
            if (checkIfSymmetric(i)) {
                count++;
            }
        }

        return count;
    }

    private static boolean checkIfSymmetric(int x) {
        int digits = 0;
        int n = x;

        while (n != 0) {
            n /= 10;
            digits++;
        }

        if (digits % 2 != 0) {
            return false;
        }

        n = x;

        int half = digits / 2;

        int sumFirst = 0;

        int sumSecond = 0;

        for (int i = 0; i < half; i++) {
            sumFirst += n % 10;
            n /= 10;
        }
        for (int i = 0; i < half; i++) {
            sumSecond += n % 10;
            n /= 10;
        }

        if (sumFirst != sumSecond) {
            return false;
        }
        return true;
    }

    /**
     * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.
     * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
     * @param n
     * @return
     */

    public static int reverse(int n) {
        int nRev = 0;
        
        while (n != 0) {
            int pop = n % 10;
            n = n / 10;
            if (nRev > Integer.MAX_VALUE / 10 || (nRev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (nRev < Integer.MIN_VALUE / 10 || (nRev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            nRev = nRev * 10 + pop;
        }

        return nRev;
    }

    /**
     * You're given a string consisting solely of (, ), and *. * can represent either a (, ), or an empty string. Determine whether the parentheses are balanced.
     * For example, (()* and (*) are balanced. )*( is not balanced.
     * 
     * @param s
     * @return 
     */

    public static boolean isBalanced(String s) {
        return isBalancedRec(s, 0, 0);
    }

    private static boolean isBalancedRec(String s, int index, int stack) {
        if (s.length() == index) {
            if (stack == 0) {
                return true;
            } else {
                return false;
            }
        }

        if (stack < 0) {
            return false;
        }
        if (s.charAt(index) == '(') {
            return isBalancedRec(s, index + 1, stack + 1);

        } else if (s.charAt(index) == ')') {
            return isBalancedRec(s, index + 1, stack - 1);
        } else {
            String s1 = s.substring(0, index) + "(" + s.substring(index + 1);
            String s2 = s.substring(0, index) + ")" + s.substring(index + 1);
            String s3 = s.substring(0, index) + s.substring(index + 1);
            return isBalancedRec(s1, index, stack) || isBalancedRec(s2, index, stack) || isBalancedRec(s3, index, stack); 
        }
    }

    public static String convert(String s, int numRows) {
        String returnStr = "";
        int interval = (numRows - 1) * 2;

        if (numRows == 1) {
            return s;
        }

        for (int i = 0; i < numRows; i++) {
            boolean first = true;
            int firstJump = interval - (i * 2);
            int secondJump = i * 2;
            int j = i;
            while (j < s.length()) {
                System.out.println(j);
                if (first) {
                    if (firstJump != 0) {
                        returnStr = returnStr + s.charAt(j);
                        j = j + firstJump;
                    }
                    first = false;
                } else {
                    if (secondJump != 0) {
                        returnStr = returnStr + s.charAt(j);
                        j = j + secondJump;
                    }
                    first = true;
                }
            }
        }
        return returnStr;
    }
}