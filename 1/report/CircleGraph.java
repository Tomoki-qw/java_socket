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


public class CircleGraph extends Application {
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
    st.setTitle("Circle Graph");
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
      else if (pa.length > 35){throw new Exception("データ数は35以下にしてください");}
      else if (pa.length == 0){throw new Exception("データが不正です");} 
      else{

    gc.setLineWidth(5); // stroke関係の線の幅の指定
    gc.setFill(Color.rgb(204,204,204));
    gc.fillRect(580,50,100,50 + pa.length * 20);
    int r = 223; // 半径
    double rd = Math.PI / 180; // 一辺の角度
    Random rnd = new Random(); 
    int a = 90;
    
    for(int i = 0;i < pa.length; i++){
        int aa = pa[0];
        for(int j = 1; j < i+1; j++){
            aa += pa[j]; 

        };
        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(i + 1),625,92 + 20 * i);
        gc.setFill(Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        gc.fillRect(600,80 + 20 * i,15,15);
        gc.fillArc(100, 200, 400, 400, a, -(270 + a), ArcType.ROUND);
        int x1 = (int) (r * Math.cos(-(a -(360 * pa[i] / sum)/2) * rd) + 295);
        int y1 = (int) (r * Math.sin(-(a-(360 * pa[i] / sum)/2) * rd) + 405);
        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(pa[i]), x1, y1);
        a = 90 + (-360 * aa / sum);
    }
  }}
  catch(Exception e){
    System.err.println(e);
  }}
}