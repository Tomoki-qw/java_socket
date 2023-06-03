public class Binary {

  public static void print_binary(int n) {
    if (n == 0) return;
    print_binary(n / 2);
    System.out.print("" + n % 2);
  }

  public static void main(String args[]) {
    print_binary(Integer.parseInt(args[0]));
  }
}
