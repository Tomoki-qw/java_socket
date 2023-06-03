public class JoinTest extends Thread {

  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("" + i);
    }
  }

  public static void main(String args[]) {
    JoinTest jt = new JoinTest();
    jt.start();

    try {
      jt.join();
    } catch (InterruptedException e) {}

    System.out.println("END");
  }
}
