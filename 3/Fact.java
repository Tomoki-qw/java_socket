public class Fact {

  static int fact(int n) {
    int answer = 1;
    while (n > 0) {
      answer *= n;
      n--;
    }
    return answer;
  }

  public static void main(String args[]) {
    System.out.println("" + fact(Integer.parseInt(args[0])));
  }
}
