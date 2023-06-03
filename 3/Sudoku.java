import java.io.BufferedReader;
import java.io.FileReader;

public class Sudoku {

  int[][] sudoku = new int[9][9];

  void checkRow(int row, boolean[] list) {
    for (int i = 0; i < 9; i++) if (sudoku[row][i] != 0) list[sudoku[row][i] -
      1] =
      true;
  }

  void checkColumn(int col, boolean[] list) {
    for (int i = 0; i < 9; i++) if (sudoku[i][col] != 0) list[sudoku[i][col] -
      1] =
      true;
  }

  void checkBlock(int row, int col, boolean[] list) {
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) {
      int r = i + (row / 3) * 3;
      int c = j + (col / 3) * 3;
      if (sudoku[r][c] != 0) list[sudoku[r][c] - 1] = true;
    }
  }

  public void solveSudoku() { // for文を使ってまだ埋まっていないマス目があるかチェック
    for (int i = 0; i < 9; i++) for (int j = 0; j < 9; j++) if (
      sudoku[i][j] == 0
    ) {
      // 埋まっていない部分を見つけたので
      // ポジション(i,j)に入る候補を探す
      boolean[] list = new boolean[9];
      checkRow(i, list);
      checkColumn(j, list);
      checkBlock(i, j, list);
      for (int k = 0; k < 9; k++) if (!list[k]) {
        sudoku[i][j] = k + 1;
        solveSudoku();
      }
      sudoku[i][j] = 0; //全解探索を行うため現在位置を0に戻す。
      // この時点ではsolveSudoku()を再帰的に呼び出しており、既にi,j以降の
      // マス目は試した後になっている。
      return;
    }
    printSudoku(); //全ての升目が埋まっていたらプリントアウト。
  }

  void printSudoku() {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) System.out.print(sudoku[i][j] + " ");
      System.out.println();
    }
    System.out.println();
  }

  /*
   * 数独読み込みルーチン
   */
  public void readSudoku(String file) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;
      int row = 0;
      while (((line = br.readLine()) != null) && row < 9) {
        if (line.startsWith(";")) continue; //コメント行読み飛ばし
        String[] numbers = line.split(" ");
        for (int i = 0; i < 9; i++) {
          sudoku[row][i] = Integer.parseInt(numbers[i]);
        }
        row++;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Sudoku su = new Sudoku();
    su.readSudoku("data1.txt");
    su.solveSudoku();
  }
}
