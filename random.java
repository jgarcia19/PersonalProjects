public class random {
    public static void main(String[] args) {
        String test = "(*)";

        System.out.println(isBalanced(test));
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