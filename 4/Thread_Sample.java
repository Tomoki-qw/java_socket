public class Thread_Sample extends Thread {

  static int count = 0;

  public Thread_Sample(int i) {
    setName("Thread-" + i);
    start();
  }

  public void run() {
    while (true) {
      System.out.println(getName() + " ID:" + getId() + " count=" + count);
      count++;
      yield();
    }
  }

  public static void main(String args[]) {
    for (int i = 0; i < 10; i++) {
      new Thread_Sample(i);
    }
  }
}
