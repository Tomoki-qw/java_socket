import java.io.*;
import java.util.*;
import java.util.List;

public class Suudoku {

  public Suudoku(int[][] data) {
    solve(data);
  }

  void c_Row(int row, boolean[] list, int[][] data) {
    for (int i = 0; i < 9; i++) if (data[row][i] != 0) list[data[row][i] - 1] =
      true;
  }

  void c_Column(int col, boolean[] list, int[][] data) {
    for (int i = 0; i < 9; i++) if (data[i][col] != 0) list[data[i][col] - 1] =
      true;
  }

  void c_Block(int row, int col, boolean[] list, int[][] data) {
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) {
      int r = i + (row / 3) * 3;
      int c = j + (col / 3) * 3;
      if (data[r][c] != 0) list[data[r][c] - 1] = true;
    }
  }

  public void solve(int[][] data) {
    for (int i = 0; i < 9; i++) for (int j = 0; j < 9; j++) if (
      data[i][j] == 0
    ) {
      boolean[] list = new boolean[9];
      c_Row(i, list, data);
      c_Column(j, list, data);
      c_Block(i, j, list, data);

      for (int k = 0; k < 9; k++) if (!list[k]) {
        data[i][j] = k + 1;
        solve(data);
      }
      data[i][j] = 0;
      return;
    }
    print_suudoku(data);
  }

  void print_suudoku(int[][] data) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) System.out.print(data[i][j] + " ");
      System.out.println();
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int[][] data = new int[9][9];
    if (args.length != 1) {
      System.err.println("need 1 argument");
      System.exit(1);
    }

    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader(args[0]));

      for (int i = 0; i < 9; i++) {
        String tmp[] = br.readLine().split(" ");
        for (int j = 0; j < 9; j++) {
          data[i][j] = Integer.parseInt(tmp[j]);
        }
      }
    } catch (Exception e) {
      System.err.println("" + e);
      System.exit(1);
    }
    new Suudoku(data);
  }
}
