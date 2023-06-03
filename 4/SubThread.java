public class SubThread extends Thread {

  public SubThread() {
    this.start();
    while (true) {
      System.out.println("AAA");
      try {
        sleep(100);
      } catch (InterruptedException e) {
        System.exit(0);
      }
    }
  }

  public void run() {
    while (true) {
      System.out.println("BBB");
      try {
        sleep(100);
      } catch (InterruptedException e) {
        System.exit(0);
      }
    }
  }

  public static void main(String args[]) {
    new SubThread();
  }
}
