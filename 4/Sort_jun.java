public class Sort_jun {

  private int array[];

  public Sort_jun(int n, int num) {
    array = new int[n];
    int[][] a = new int[num][];
    MergeSort[] ms = new MergeSort[num];
    for (int i = 0; i < n; i++) {
      array[i] = (int) (Math.random() * Integer.MAX_VALUE);
    }
    for (int i = 0; i < num; i++) {
      if (i != num - 1) {
        a[i] = new int[n / num];
        for (int j = 0; j < n / num; j++) {
          a[i][j] = array[(n / num) * i + j];
        }
      } else {
        a[i] = new int[n - (n / num) * i];
        for (int j = 0; j < n - (n / num) * i; j++) {
          a[i][j] = array[(n / num) * i + j];
        }
      }
    }
    //ここからソート呼び出し
    long start = System.currentTimeMillis();
    //BubbleSort bs = new BubbleSort(array);
    //array = bs.getArray();
    for (int i = 0; i < num; i++) {
      ms[i] = new MergeSort(a[i]);
    }
    for (int i = 0; i < num; i++) {
      try {
        ms[i].join();
      } catch (InterruptedException e) {}
    }
    for (int i = 0; i < num; i++) {
      a[i] = ms[i].getArray();
    }
    sorting(a, n, num);
    long end = System.currentTimeMillis();
    //MergeSort ms1 = new MergeSort(array);
    //array = ms1.getArray();
    for (int i = 0; i < num; i++) {
      //printArray(a[i]); //配列表示
    }
    printArray(array);
    System.out.println(
      "sort?: " +
      sortCheck(array) +
      ", Processing time: " +
      (end - start) +
      "ms"
    );
  }

  public void sorting(int[][] a1, int n, int num) {
    MergeSort[] mss = new MergeSort[num / 2];
    int[][] a0;
    if (num == 1) {
      if (a1.length == 2) {
        MergeSort.sort_jun(a1[0], a1[1], array);
      } else {
        array = a1[0];
      }
      return;
    }
    if (num % 2 == 1) {
      a0 = new int[num / 2 + 1][];
      a0[num / 2] = a1[num - 1];
    } else {
      a0 = new int[num / 2][];
    }
    for (int i = 0; i < num / 2; i++) {
      a0[i] = new int[a1[2 * i].length + a1[2 * i + 1].length];
      mss[i] = new MergeSort(a1[2 * i], a1[2 * i + 1], a0[i]);
      a0[i] = mss[i].getArray();
      //MergeSort.sort(a1[2*i], a1[2*i+1], a0[i]);
    }
    for (int i = 0; i < num / 2; i++) {
      try {
        mss[i].join();
      } catch (InterruptedException e) {
        //TODO: handle exception
      }
    }
    sorting(a0, n, num / 2);
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
    new Sort_jun(100, Integer.parseInt(args[0]));
  }
}

/**
  バブルソート(逐次)
*/
class BubbleSort {

  private int array[];

  BubbleSort(int[] n) {
    array = n;
    sort_jun();
  }

  /** ソート コンストラクタから自動で実行される */
  private void sort_jun() {
    for (int i = array.length - 1; i > 0; i--) {
      for (int j = 0; j < i; j++) {
        if (array[j] < array[j - 1]) {
          int tmp = array[j];
          array[j] = array[j - 1];
          array[j - 1] = tmp;
        }
      }
    }
  }

  /** ソート結果を得るメソッド */
  public int[] getArray() {
    return array;
  }
}

class MergeSort extends Thread {

  private int[] array;

  MergeSort(int[] n) {
    array = n;
    merge(array);
  }

  MergeSort(int[] n1, int[] n2, int[] n) {
    array = n;
    sort_jun(n1, n2, n);
  }

  static void sort_jun(int[] a1, int[] a2, int[] a) {
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

  /*
   * マージソート
   * 既にソート済みの2つの配列を併合して新しい配列を
   * 生成することで、データのソートを行います。
   */
  void merge(int[] a) {
    if (a.length > 1) {
      int m = a.length / 2;
      int n = a.length - m;
      int[] a1 = new int[m];
      int[] a2 = new int[n];
      for (int i = 0; i < m; i++) a1[i] = a[i];
      for (int i = 0; i < n; i++) a2[i] = a[m + i];
      merge(a1);
      merge(a2);
      sort_jun(a1, a2, a);
    }
  }

  /*private void merge(int[] n, int l){
    if(l<=1)return;
    int divi = l / 2;
    int[] left = new int[divi];
    int[] right = new int[l-divi];
    for(int i=0;i<divi;i++){
      left[i] = n[i];
    }
    for(int i=divi;i<l;i++){
      right[i-divi] = n[i];
    }
    merge(left, divi);
    merge(right, l-divi);
    sort(n, left, right, divi, l-divi);
  }

  static void sort(int[] n, int[] l, int[] r, int num1, int num2){
    int i = 0;
    int j = 0;
    int k = 0;
    while(i<num1 && j<num2){
      if(l[i]<=r[j]){
        n[k++] = l[i++];
      } else{
        n[k++] = r[j++];
      }
    }
    while(i<num1){
      n[k++] = l[i++];
    }
    while(j<num2){
      n[k++] = r[j++];
    }
  }*/

  public int[] getArray() {
    return array;
  }
}
