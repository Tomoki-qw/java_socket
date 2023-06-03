public class WaitNotifyTest {

  int n = 0;

  synchronized void inc() {
    try {
      if (n == 10) {
        System.out.println("nが10になっているため加算を待機");
        wait();
      }
    } catch (InterruptedException e) {}
    n++;
    System.out.println("Inc:" + n);
    notify();
  }

  synchronized void dec() {
    try {
      if (n == 0) {
        System.out.println("nが0になっているため減算を待機");
        wait();
      }
    } catch (InterruptedException e) {}
    n--;
    System.out.println("Dec:" + n);
    notify();
  }

  public static void main(String args[]) {
    WaitNotifyTest wt = new WaitNotifyTest();
    (new IncThread(wt)).start();
    (new DecThread(wt)).start();
  }
}

class IncThread extends Thread {

  WaitNotifyTest wt;

  public IncThread(WaitNotifyTest w) {
    wt = w;
  }

  public void run() {
    for (int i = 0; i < 1000; i++) {
      wt.inc();
    }
  }
}

class DecThread extends Thread {

  WaitNotifyTest wt;

  public DecThread(WaitNotifyTest w) {
    wt = w;
  }

  public void run() {
    for (int i = 0; i < 1000; i++) {
      wt.dec();
    }
  }
}
