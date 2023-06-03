public class Knapsack {

  final int MAX_WEIGHT = 10;
  int max_value = 0;
  String item_name = "";
  int flag[] = new int[8];
  int items[][] = {
    { 3, 1000 },
    { 4, 800 },
    { 2, 850 },
    { 4, 1200 },
    { 3, 1100 },
    { 4, 2200 },
    { 2, 400 },
    { 2, 1100 },
  };

  public Knapsack() {
    for (int i = 0; i < 8; i++) {
      flag[i] = 0;
    }
    knap("", 0);
    System.out.println(item_name + " " + max_value + "円");
  }

  void knap(String str, int n) {
    if (n == 8) {
      int weight = 0;
      int value = 0;
      for (int i = 0; i < 8; i++) {
        weight += flag[i] * items[i][0];
        value += flag[i] * items[i][1];
      }

      if (weight > MAX_WEIGHT) return;

      if (value > max_value) {
        max_value = value;
        item_name = str;
      }
      return;
    }

    flag[n] = 0;
    knap(str, n + 1);
    flag[n] = 1;
    str += n + " ";
    knap(str, n + 1);
  }

  public static void main(String args[]) {
    new Knapsack();
  }
}
