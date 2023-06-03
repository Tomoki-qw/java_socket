public class IntQueue2 {

  final int SIZE = 10;
  private int[] values = new int[SIZE];
  private int tail = -1;

  synchronized boolean enqueue(int data) {
    //System.out.println("Enqueue");
    if (data < 0) {
      return false;
    }
    while (tail == SIZE - 1) {
      System.out.println("enqueue :Queue Full.. wait dequeue()");
      try {
        wait();
      } catch (InterruptedException e) {}
    }

    tail++;
    values[tail] = data;
    System.out.println("enqueue :" + data + " tail:" + tail);
    getState();
    notify();
    return true;
  }

  synchronized int dequeue() {
    //System.out.println("Dequeue");
    while (isEmpty()) {
      System.out.println("dequeue :Queue Empty.. wait enqueue()");
      try {
        wait();
      } catch (InterruptedException e) {}
    }
    int data = values[0];
    for (int i = 0; i < SIZE - 1; i++) {
      values[i] = values[i + 1];
    }
    tail--;
    System.out.println("dequeue :" + data + " tail:" + tail);
    getState();
    notify();
    return data;
  }

  boolean isEmpty() {
    return (tail == -1);
  }

  static void getState() {
    System.out.println("eq1" + eq1.getState());
    System.out.println("eq2" + eq2.getState());
    System.out.println("dq1" + dq1.getState());
    System.out.println("dq2" + dq2.getState());
    Thread.yield();
  }

  static EnQueueThread eq1;
  static EnQueueThread eq2;
  static DeQueueThread dq1;
  static DeQueueThread dq2;

  public static void main(String[] args) {
    IntQueue2 q = new IntQueue2();
    eq1 = new EnQueueThread(q);
    eq2 = new EnQueueThread(q);
    dq1 = new DeQueueThread(q);
    dq2 = new DeQueueThread(q);
    eq1.start();
    eq2.start();
    /*	try{
	eq1.join();
	eq2.join();
	}catch(InterruptedException e){
	}
	*/
    dq1.start();
    dq2.start();
  }
}

class EnQueueThread extends Thread {

  IntQueue2 q;

  public EnQueueThread(IntQueue2 q) {
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

  IntQueue2 q;

  public DeQueueThread(IntQueue2 q) {
    this.q = q;
  }

  public void run() {
    for (int i = 0; i < 100; i++) {
      q.dequeue();
    }
  }
}
