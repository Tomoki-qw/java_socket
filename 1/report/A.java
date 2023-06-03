import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import javafx.scene.canvas.GraphicsContext;
import java.io.*;

public class A extends Application {
  //データファイルの読み込み
  public static int[] fileRead(String args) {
    try {
      File inputFile = new File(args); // arg[0]:一つ目のコマンドライン引数で表されるファイル
      FileReader fr = new FileReader(inputFile);
      BufferedReader br = new BufferedReader(fr);
      String str = br.readLine();
      String[] data = str.split(" ");
      int[] pan = new int[data.length];

      for (int i = 0; i < data.length; i++) {
        pan[i] = Integer.parseInt(data[i]);
      }

      br.close();
      return pan;
    } catch (FileNotFoundException e) {
      System.err.println("ファイルが開けませんでした．");
      int[] error = new int[0];
      return error;
    } catch (IOException e) { // ファイル入出力の例外を処理
      System.err.println("ファイルの読み書きに失敗しました．");
      int[] error = new int[0];
      return error;
    }  
  }
  int[] pa = fileRead("./data.txt");

  // 12個のデータからなる配列
  //private int[] pa = {180, 120, 83, 112, 90, 109, 120, 130, 200, 155, 180, 170,1,1,1,4,4,44,4,6,7,77,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,40,};
  //配列paの最大値intMaxを求める
  int intMax = calcMax(pa);
  int intMin = calcMin(pa);
  public static int calcMax(int[] pa) {

    int intMax = pa[0];

    for (int i = 1; i < pa.length; i++ ) {
        if(intMax < pa[i]) {
            intMax = pa[i];
        }
    }
    return intMax;
  }
  public static int calcMin(int[] pa) {

    int intMin = pa[0];

    for (int i = 1; i < pa.length; i++ ) {
        if(intMin > pa[i]) {
            intMin = pa[i];
        }
    }
    return intMin;
}
  @Override
  public void start(Stage st) throws Exception {
    Group root = new Group();
    Canvas canvas = new Canvas(1200, 1200);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    drawShapes(gc);
    
    root.getChildren().add(canvas);

    Scene scene = new Scene(root, 1200, 1200, Color.WHITE);
    st.setTitle("Bar Graph");
    st.setScene(scene);
    st.show();
  }
  
  public static void main(String[] a) {
    launch(a);
  }

  private void drawShapes(GraphicsContext gc) {
    double[] testx = new double[4];
    double[] testy = new double[4];
    testx[0] = 10;
    testx[1] = 40;
    testx[2] = 10;
    testx[3] = 40;
    testy[0] = 210;
    testy[1] = 210;
    testy[2] = 240;
    testy[3] = 240;

    gc.fillPolygon(testx,
                   testy, 4);
}
}