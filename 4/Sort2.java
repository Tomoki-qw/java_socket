import java.util.Arrays;

public class Sort2 {

  private int array[];

  public Sort2(int n) {
    array = new int[n];
    for (int i = 0; i < n; i++) {
      array[i] = (int) (Math.random() * Integer.MAX_VALUE);
    }
    //ここからソート呼び出し
    long start = System.currentTimeMillis();
    ParallelSort bs = new ParallelSort(array);
    array = bs.getArray();
    //printArray(array); //配列表示
    long end = System.currentTimeMillis();
    System.out.println(
      "Sort?: " +
      SortCheck(array) +
      ", Processing time: " +
      (end - start) +
      "ms"
    );
  }

  /** ソートチェック */
  public static boolean SortCheck(int array[]) {
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
    new Sort2(100000);
  }
}

class ParallelSort {

  private int array[];

  ParallelSort(int[] n) {
    array = n;
    sort();
  }

  /** ソート コンストラクタから自動で実行される */
  private void sort() {
    Arrays.parallelSort(array);
  }

  /** ソート結果を得るメソッド */
  public int[] getArray() {
    return array;
  }
}
