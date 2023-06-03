public class Euclid {

  static int euclid(int i, int j) {
    if (i < j) {
      int tmp = i;
      i = j;
      j = tmp;
    }

    if (j == 0) {
      return i;
    } else {
      return euclid(j, i % j);
    }
  }

  public static void main(String args[]) {
    System.out.println(
      "" + euclid(Integer.parseInt(args[0]), Integer.parseInt(args[1]))
    );
  }
}
