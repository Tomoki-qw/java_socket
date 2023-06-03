//[クラス]配列の生成、処理時間の計測等を行うクラス。
public class Sort {

  //[フィールド]ソートする配列を用意する。
  private int array[];

  //[コンストラクタ]ランダムな配列の準備、処理時間の計測を行う。
  public Sort(int n) {
    array = new int[n];
    for (int i = 0; i < n; i++) {
      array[i] = (int) (Math.random() * Integer.MAX_VALUE);
    }
    //ここからソート呼び出し
    long start = System.currentTimeMillis();
    MergeSort bs = new MergeSort(array);
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

  //[メソッド]ソートが成功しているか確認するメソッド。成功ならtrue、失敗ならfalseを返す。
  public static boolean sortCheck(int array[]) {
    for (int i = 0; i < array.length - 1; i++) {
      if (array[i] > array[i + 1]) return false;
    }
    return true;
  }

  //[メソッド]ソートされた配列を出力するメソッド。
  public static void printArray(int array[]) {
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println();
  }

  //[メソッド]mainメソッド。Sortクラスの新たなインスタンスを作成する。引数には配列の要素数を取る。
  public static void main(String args[]) {
    new Sort(100000);
  }
}

//[クラス]マージソートの処理を行うクラス。
class MergeSort extends Thread {

  //[フィールド]ソートする配列を用意する。
  private int array[];

  //[コンストラクタ]新たなインスタンスが作成される度にsortメソッドを実行する。
  MergeSort(int[] n) {
    array = n;
    sort();
  }

  //[メソッド]ソートを実行するメソッド。
  synchronized void merge(int[] a1, int[] a2, int[] a) {
    int i = 0, j = 0;
    while (i < a1.length || j < a2.length) {
      if (j >= a2.length || (i < a1.length && a1[i] < a2[j])) {
        a[i + j] = a1[i];
        i++;
      } else {
        a[i + j] = a2[j];
        j++;
      }
    }
  }

  //[メソッド]ソートを実行するメソッド。ここで配列を分割し、それぞれをソートしてマージする。
  synchronized void mergeSort(int[] a) {
    if (a.length > 1) {
      int m = a.length / 2;
      int n = a.length - m;
      int[] a1 = new int[m];
      int[] a2 = new int[n];
      for (int i = 0; i < m; i++) a1[i] = a[i];
      for (int i = 0; i < n; i++) a2[i] = a[m + i];
      new MergeSort(a1);
      new MergeSort(a2);
      merge(a1, a2, a);
    }
  }

  /** ソート コンストラクタから自動で実行される */
  private void sort() {
    mergeSort(array);
  }

  /** ソート結果を得るメソッド */
  public synchronized int[] getArray() {
    return array;
  }

  //[メソッド]MergeSortのインスタンスが作成される度に実行されるメソッド。
  public synchronized void run() {
    mergeSort(array);
  }
}
