public class HelloWorld4 {
    public static void main(String[] args) {
      for (int i=0; i < 5; i++) {
        if (i % 2 == 0) { // 偶数回目の繰り返しのときに"Hello World!"を表示する
          System.out.println(i + "\t" + "Hello World!");
        }
      }
    }
  }