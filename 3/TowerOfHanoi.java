public class TowerOfHanoi {

  TowerOfHanoi(int n) {
    System.out.println("Move " + n + " Disks from A to C");
    System.out.println("... Move Sequence ...");
    hanoi(n, 'A', 'B', 'C');
  }

  void hanoi(int n, char origin, char using, char target) {
    if (n > 0) {
      hanoi(n - 1, origin, target, using);
      System.out.println(
        "Move Disk " + n + " from " + origin + " to " + target
      );
      hanoi(n - 1, using, origin, target);
    }
  }

  public static void main(String args[]) {
    int noOfDisk = Integer.parseInt(args[0]);
    TowerOfHanoi hanoi = new TowerOfHanoi(noOfDisk);
  }
}
