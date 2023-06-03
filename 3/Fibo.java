public class Fibo {

  public static int fibo(int n) {
    if (n == 0 || n == 1) return 1;
    return fibo(n - 1) + fibo(n - 2);
  }

  public static void main(String args[]) {
    System.out.println("" + fibo(Integer.parseInt(args[0])));
  }
}
