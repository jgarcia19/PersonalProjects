public class random {
    public static void main(String[] args) {

        try {
            new int[]{1,2,3};

        } catch (Exception e) {
            System.out.println("Exception was caught!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index Exception was caught!");
        }
    }
}