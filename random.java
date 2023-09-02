public class random {
    public static void main(String[] args) {
        String s = "A";
        int numRows = 1;

        String result = convert(s, numRows);
    }

    public static String convert(String s, int numRows) {
        String returnStr = "";
        int interval = (numRows - 1) * 2;

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