public class Pie {
    public static void main(String[] args) {
      double[] a = new double[1000]; // double型の5つの要素からなる配列を宣言
  
      // a[i]に値を設定する
      for (int i=0; i < 1000; i++) {
        a[i] = 1.0/((i + 1.0) * 2.0 - 1.0);
      }
  
      double sum = 0;
  
      // iが奇数のときはsumにa[i]を足して，偶数のときはsumからa[i]を引く
      for (int i=0; i < 1000; i++) {
        if (i % 2 == 0) {
          sum += a[i];
        } else {
          sum -= a[i];
        }
      }
      
      System.out.println("π= " + (4.0 * sum));
    }
  }