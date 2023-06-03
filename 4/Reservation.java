//[クラス]このプログラムの本体のクラス。
public class Reservation {

  //[フィールド]二次元配列seatを作成。この配列で座席の予約情報を管理する。
  int seat[][];

  //[コンストラクタ]Reservationクラスのコンストラクタ。ここでseatの要素を全て-1（今回は空席を意味する）にする。
  public Reservation(int n, int m) {
    seat = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        seat[i][j] = -1;
      }
    }
  }

  //[メソッド]reserveにて予約の本処理を行う。
  synchronized boolean reserve(int id, int num) {
    //seat[i][j]の初期位置を設定する
    int i = 0;
    int j = 0;
    //while文中でまだ埋まってない座席を探す
    while (true) {
      //現在地が空席かつ予約したい数の席が現在地(seat[i][j])以降に存在している時
      if (seat[i][j] == -1 && j + num - 1 < 15) {
        for (int b = j; b < j + num; b++) {
          //存在している座席が全て空席(-1)であるかを確認。空席でなければfalseを返し処理を終了。
          if (seat[i][b] == -1) {} else {
            return false;
          }
          //全て空席であることが確認された時
          if (b == j + num - 1) {
            //seat[i][j]からseat[i][j+num-1]まで予約成功とみなし、予約したい座席の値を-1からidに変更
            for (int a = j; a < j + num; a++) {
              seat[i][a] = id;
            }
            //trueを返し処理を終了
            return true;
          }
        }
      }
      //埋まっていない座席を探す処理が最後の座席までたどり着いた(=予約可能な空席の列がなかった)時
      if (i == 5 && j == 14) {
        //座席予約の失敗の旨を出力し、galseを返して処理を終了
        /* System.out.println(id + "は" + num + "席の予約に失敗しました"); */
        return false;
      }
      //確認するseatを次に進める
      else if (j < 14) {
        ++j;
      } else {
        j = 0;
        ++i;
      }
    }
  }

  //[メソッド]最終的な座席表を出力するメソッド。空席は-1のまま、他は予約者のidが出力される。
  void printSeat() {
    for (int i = 0; i < seat.length; i++) {
      for (int j = 0; j < seat[i].length; j++) {
        System.out.print(seat[i][j] + " ");
      }
      System.out.println();
    }
  }

  //[メソッド]mainメソッド。ここで予約者数、座席数を設定する。
  public static void main(String args[]) {
    int thread_num = 5; //予約を取りに来る予約者数
    Reservation rs = new Reservation(6, 15); //6,15は座席数
    //予約者数分のスレッドを用意する。
    Passengers ps[] = new Passengers[thread_num];
    for (int i = 0; i < thread_num; i++) {
      ps[i] = new Passengers(i, rs);
    }
    //printSeatを最後に実行する為にjoinメソッドを使用する。
    for (int i = 0; i < thread_num; i++) {
      try {
        ps[i].join();
      } catch (InterruptedException e) {}
    }
    rs.printSeat();
  }
}

//[クラス]主にスレッドの制御をするクラス。
class Passengers extends Thread {

  //[フィールド]予約者の名前id,rsを定義
  int id;
  Reservation rs;

  //[コンストラクタ]スレッドをスタートさせる。
  public Passengers(int n, Reservation rs) {
    this.id = n;
    this.rs = rs;
    this.start();
  }

  //[メソッド]startにて実行されるメソッド。
  public void run() {
    for (int i = 0; i < 10; i++) { //10回行う
      //1~7の数値をnumに代入し、それを予約する席数とする。
      int num = (int) (Math.random() * 6 + 1);
      //reserveメソッドからtrue(予約成功)が返ってきた場合
      if (rs.reserve(id, num)) {
        System.out.println("ID:" + id + "  reserved " + num + " seats.");
      }
    }
  }
}
