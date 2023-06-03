import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import javafx.scene.canvas.GraphicsContext;
import java.io.*;

public class LineGraph extends Application {
  //データファイルの読み込み
  public static int[] fileRead(String args) {
    try {
      File inputFile = new File(args); 
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
    st.setTitle("Line Graph");
    st.setScene(scene);
    st.show();
  }
  
  public static void main(String[] a) {
    launch(a);
  }

  private void drawShapes(GraphicsContext gc) {
    try{
    if (intMax > 1000){throw new Exception("データは1000以下で入力してください");}
    else if (intMin < 0){throw new Exception("データは0以上で入力してください");}
    else if (intMax == 0){throw new Exception("データは0より大きな値を含むようにしてください");}
    else if (pa.length > 40){throw new Exception("データ数は40以下にしてください");}
    else if (pa.length < 2){throw new Exception("データが不正です");} 
    else{
    // 折れ線グラフを描く
    gc.setFill(Color.rgb(204,204,204));
    gc.fillRect(150,150,900,600);
    gc.setStroke(Color.BLACK);
    gc.setFill(Color.BLACK);
    gc.strokeLine(150, 750, 1050, 750); // 横軸
    gc.strokeLine(150, 750, 150, 150); // 縦軸
    // 横軸の目盛
    for (int i = 0; i < (pa.length + 1); i++) {
      gc.strokeLine(150 + i * 75 * 12 / pa.length, 750, 150 + i * 75 * 12 / pa.length, 756);
    }
    gc.setStroke(Color.BLUE);
    //縦軸の値の調整
    for (int i = 0 ;i < 4; i++){
      if (intMax % 4 != 0){
        intMax++;
      } 
    }
    for (int i = 0; i < pa.length; i++) {
      // 横軸の値
      gc.fillText(String.valueOf(i + 1), 150 + (39 + i * 75) * 12 / pa.length, 770); 
      if (i < (pa.length) - 1){
      gc.strokeLine(150 + (39 + i * 75) * 12 / pa.length, 750 - (pa[i] * 600 / intMax),150 + (39 + (i+1)* 75) * 12 / pa.length ,750 - (pa[i+1] * 600 / intMax));} // 折れ線の描画
    }
    gc.setStroke(Color.BLACK);
    for (int i = 0; i < 5; i++) {
      gc.strokeLine(144, 150 + i * 150, 1050, 150 + i * 150); // 縦軸の目盛
      gc.fillText(String.format("%1$10s",String.valueOf(i * intMax / 4)), 80, 750 - i * 150); // 縦軸の値
    }
    gc.setStroke(Color.BLUE);
  }
}
  catch(Exception e){
    System.err.println(e);
  }
  } 
}