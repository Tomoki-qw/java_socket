public class Search {

  int Xmax, Ymax;

  public static void main(String arg[]) {
    new Search(5, 4);
  }

  public Search(int x, int y) {
    Xmax = x;
    Ymax = y;
    System.out.println("route = " + search(0, 0));
  }

  int search(int x, int y) {
    if ((x > Xmax) || (y > Ymax)) {
      return 0;
    }
    if ((x == Xmax) && (y == Ymax)) {
      return 1;
    }
    return search(x + 1, y) + search(x, y + 1);
  }
}
