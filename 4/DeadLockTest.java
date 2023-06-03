public class DeadLockTest {

  int fork = 0;
  int knife = 0;

  synchronized void getFork(int n) {
    if (fork != 0) {
      return;
    }
    fork = n;
    System.out.println(n + " get Fork");
  }

  synchronized void getKnife(int n) {
    if (knife != 0) {
      return;
    }
    knife = n;
    System.out.println(n + " get Knife");
  }

  synchronized void reset1(int n) {
    if (fork == n) {
      fork = 0;
      System.out.println(n + " リセットフォーク");
    }
    if (knife == n) {
      knife = 0;
      System.out.println(n + " リセットナイフ");
    }
    /* System.out.println(n + " リセット1"); */
  }

  synchronized void reset2(int n) {
    if (fork == n) fork = 0;
    if (knife == n) knife = 0;
    System.out.println(n + " リセット2");
  }

  synchronized boolean canEat(int n) {
    return ((fork == n) && (knife == n));
  }

  public static void main(String args[]) {
    DeadLockTest dlt = new DeadLockTest();
    for (int i = 1; i < 9; i++) {
      new HumanThread(dlt, i).start();
    }
  }
}

class HumanThread extends Thread {

  DeadLockTest dlt;
  int id;

  public HumanThread(DeadLockTest d, int n) {
    id = n;
    dlt = d;
  }

  public void run() {
    for (int i = 0; i < 10; i++) {
      while (!dlt.canEat(id)) {
        dlt.reset1(id);
        dlt.getFork(id);
        dlt.getKnife(id);
      }
      System.out.println(id + " ate a meal.");
      dlt.reset2(id);
    }
  }
}
