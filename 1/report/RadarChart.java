import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import java.util.Random;
import javafx.scene.paint.Color;
import java.io.*;


public class RadarChart extends Application {
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
    //リストの最大値、最小値
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
    public static int sumOfList(int[] a){
        int sum = 0;
        for(int i = 0; i < a.length; i ++) {
			sum += a[i];
        }
        return sum;
    }
    int sum = sumOfList(pa);
  @Override
  public void start(Stage st) throws Exception {
    Group root = new Group();
    Canvas canvas = new Canvas(800, 800);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    drawShapes(gc);
    
    root.getChildren().add(canvas);

    Scene scene = new Scene(root, 800, 800, Color.WHITE);
    st.setTitle("Radar Chart");
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
        else if (pa.length < 3){throw new Exception("データが不正です");} 
        else{
    for (int i = 0 ;i < 5; i++){
        if (intMax % 5 != 0){
          intMax++;
        } 
      }
      // ダイヤモンドリングを描く
    int r = 200; // 半径
    double rd = Math.PI / 180; // 一辺の角度
    //チャートの描画
        double[] polyx = new double[pa.length];
        double[] polyy = new double[pa.length];
        for (int i = 0;i < pa.length; i++){
            polyx[i] = (r * pa[i] / intMax * Math.cos((i * 360 / pa.length - 90) * rd) + 400);
            polyy[i] = (r * pa[i] / intMax * Math.sin((i * 360 / pa.length - 90) * rd) + 400);
        }
        gc.setFill(Color.rgb(0,164,172));
        gc.fillPolygon(polyx,polyy,pa.length);
    //蜘蛛の巣の多角形
    gc.setStroke(Color.BLACK);
    for(int m = 0; m < 6;m++){
        r = m * 40;
        for (int i = 0; i < pa.length ; i++) {
          int x1 = (int) (r * Math.cos((i * 360 / pa.length - 90) * rd) + 400);
          int y1 = (int) (r * Math.sin((i * 360 / pa.length - 90) * rd) + 400);
          int x2 = (int) (r * Math.cos(((i + 1) * 360 / pa.length - 90) * rd) + 400);
          int y2 = (int) (r * Math.sin(((i + 1) * 360 / pa.length - 90) * rd) + 400);
          gc.strokeLine(x1, y1, x2, y2);
        }
    }
    //円弧の値
    r = 210;
    gc.setFill(Color.BLACK);
    for (int i = 0; i < pa.length ; i++) {
      int x1 = (int) (r * Math.cos((i * 360 / pa.length - 90) * rd) + 392);
      int y1 = (int) (r * Math.sin((i * 360 / pa.length - 90) * rd) + 405);
      gc.fillText(String.valueOf(i + 1), x1, y1);
    }
    //蜘蛛の巣の放射線
    r = 200;
    for(int m = 0;m < pa.length;m++){
        int x1 = 400;
        int y1 = 400;
        int x2 = (int) (r * Math.cos((m * 360 / pa.length - 90) * rd) + 400);
        int y2 = (int) (r * Math.sin((m * 360 / pa.length - 90) * rd) + 400);
        gc.strokeLine(x1, y1, x2, y2);
    }
    //縦軸の値
    for (int m = 0;m < 6;m++){
        gc.fillText(String.valueOf(intMax * m / 5 ),405,400 - 40 * m );
    }
    //チャートの縁の描画
    gc.setStroke(Color.BLACK);
    for(int i = 0; i < pa.length; i++){
        if(i < pa.length -1){
            int x1 = (int) (r * pa[i] / intMax * Math.cos((i * 360 / pa.length - 90) * rd) + 400);
            int y1 = (int) (r * pa[i] / intMax * Math.sin((i * 360 / pa.length - 90) * rd) + 400);
            int x2 = (int) (r * pa[i + 1] / intMax * Math.cos(((i + 1) * 360 / pa.length - 90) * rd) + 400);
            int y2 = (int) (r * pa[i + 1] / intMax * Math.sin(((i + 1) * 360 / pa.length - 90) * rd) + 400);
            gc.strokeLine(x1,y1,x2,y2);
        }
        else{
            int x1 = (int) (r * pa[i] / intMax * Math.cos((i * 360 / pa.length - 90) * rd) + 400);
            int y1 = (int) (r * pa[i] / intMax * Math.sin((i * 360 / pa.length - 90) * rd) + 400);
            int x2 = (int) (r * pa[0] / intMax * Math.cos((0 * 360 / pa.length - 90) * rd) + 400);
            int y2 = (int) (r * pa[0] / intMax * Math.sin((0 * 360 / pa.length - 90) * rd) + 400);
            gc.strokeLine(x1,y1,x2,y2);

        }
    }
  }}
  catch(Exception e){
    System.err.println(e);
  }}
}