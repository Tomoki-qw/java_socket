public class IntQueue {

  final int SIZE = 1000;
  private int[] values = new int[SIZE];
  private int tail = -1;

  synchronized boolean enqueue(int data) {
    if (data < 0) {
      System.out.println("enqueue :えら１");
      return false;
    }
    if (tail == SIZE - 1) {
      System.out.println("enqueue :えら１");
      return false;
    }
    tail++;
    values[tail] = data;
    System.out.println("enqueue :" + data + " tail:" + tail);
    return true;
  }

  synchronized int dequeue() {
    if (isEmpty()) {
      return -1;
    }
    int data = values[0];
    for (int i = 0; i < SIZE - 1; i++) {
      values[i] = values[i + 1];
    }
    tail--;
    System.out.println("dequeue :" + data + " tail:" + tail);
    return data;
  }

  boolean isEmpty() {
    return (tail == -1);
  }

  public static void main(String[] args) {
    IntQueue q = new IntQueue();
    EnQueueThread eq1 = new EnQueueThread(q);
    EnQueueThread eq2 = new EnQueueThread(q);
    DeQueueThread dq1 = new DeQueueThread(q);
    DeQueueThread dq2 = new DeQueueThread(q);
    eq1.start();
    eq2.start();
    try {
      eq1.join();
      eq2.join();
    } catch (InterruptedException e) {}

    dq1.start();
    dq2.start();
  }
}

class EnQueueThread extends Thread {

  IntQueue q;

  public EnQueueThread(IntQueue q) {
    this.q = q;
  }

  public void run() {
    for (int i = 0; i < 100; i++) {
      int data = (int) (Math.random() * 100 + 1);
      q.enqueue(data);
    }
  }
}

class DeQueueThread extends Thread {

  IntQueue q;

  public DeQueueThread(IntQueue q) {
    this.q = q;
  }

  public void run() {
    for (int i = 0; i < 100; i++) {
      q.dequeue();
    }
  }
}
