public class Count {

  int c;

  public Count() {
    CounterThread c1 = new CounterThread(this);
    CounterThread c2 = new CounterThread(this);
    CounterThread c3 = new CounterThread(this);
    c1.start();
    c2.start();
    c3.start();
  }

  public void inc() {
    synchronized (this) {
      c++;
    }
  }

  public static void main(String args[]) {
    new Count();
  }
}

class CounterThread extends Thread {

  Count count;

  public CounterThread(Count c) {
    count = c;
  }

  public void run() {
    for (int i = 0; i < 10000; i++) {
      count.inc();
      System.out.println(getId() + ":" + count.c);
    }
  }
}
