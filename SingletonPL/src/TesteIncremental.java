public class TesteIncremental {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Singleton inc = Singleton.getInstance();
            System.out.println(inc);
        }
    }
}
