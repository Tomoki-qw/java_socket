public class SampleArray {
    public static void main(String[] args) {
      double[] a = new double[5]; // double型の5つの要素からなる配列を宣言
  
      // a[i]に値を設定する
      for (int i=0; i < 5; i++) {
        a[i] = i * 0.5;
        System.out.println("a[" + i + "]: " + a[i]);
      }
  
      double sum = 0;
  
      // iが奇数のときはsumにa[i]を足して，偶数のときはsumからa[i]を引く
      for (int i=0; i < 5; i++) {
        if (i % 2 == 1) {
          sum += a[i];
        } else {
          sum -= a[i];
        }
      }
      
      System.out.println("sum: " + sum);
    }
  }