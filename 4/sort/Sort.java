public class Sort {

  private int array[];

  public Sort(int n) {
    array = new int[n];
    for (int i = 0; i < n; i++) {
      array[i] = (int) (Math.random() * Integer.MAX_VALUE);
    }
    //ここからソート呼び出し
    long start = System.currentTimeMillis();
    BubbleSort bs = new BubbleSort(array);
    array = bs.getArray();
    //printArray(array); //配列表示
    long end = System.currentTimeMillis();
    System.out.println(
      "sort?: " +
      sortCheck(array) +
      ", Processing time: " +
      (end - start) +
      "ms"
    );
  }

  /** ソートチェック */
  public static boolean sortCheck(int array[]) {
    for (int i = 0; i < array.length - 1; i++) {
      if (array[i] > array[i + 1]) return false;
    }
    return true;
  }

  /** 確認用の配列表示メソッド */
  public static void printArray(int array[]) {
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println();
  }

  public static void main(String args[]) {
    new Sort(100000);
  }
}

/**
   バブルソート(逐次)
*/
class BubbleSort {

  private int array[];

  BubbleSort(int[] n) {
    array = n;
    sort();
  }

  /** ソート コンストラクタから自動で実行される */
  private void sort() {
    for (int i = array.length - 1; i > 0; i--) {
      for (int j = 0; j < i; j++) {
        if (array[j] > array[j + 1]) {
          int tmp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = tmp;
        }
      }
    }
  }

  /** ソート結果を得るメソッド */
  public int[] getArray() {
    return array;
  }
}
