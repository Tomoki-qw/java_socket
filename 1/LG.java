import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import javafx.scene.canvas.GraphicsContext;


public class LG extends Application {
  // 12個のデータからなる配列
  private int[] pa = {180, 120, 83, 112, 90, 109, 120, 130, 200, 155, 180, 170};
  //配列paの最大値intMaxを求める
  int intMax = calcMax(pa);
  public static int calcMax(int[] pa) {

    int intMax = pa[0];

    for (int i = 1; i < pa.length; i++ ) {
        if(intMax < pa[i]) {
            intMax = pa[i];
        }
    }
    return intMax;
  }

  @Override
  public void start(Stage st) throws Exception {
    Group root = new Group();
    Canvas canvas = new Canvas(400, 400);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    drawShapes(gc);
    
    root.getChildren().add(canvas);

    Scene scene = new Scene(root, 400, 400, Color.WHITE);
    st.setTitle("Bar Graph");
    st.setScene(scene);
    st.show();
  }
  
  public static void main(String[] a) {
    launch(a);
  }

  private void drawShapes(GraphicsContext gc) {
    // 棒グラフを描く
    gc.setFill(Color.rgb(204,204,204));
    gc.fillRect(50,50,300,200);
    gc.setStroke(Color.BLACK);
    gc.setFill(Color.BLACK);
    gc.strokeLine(50, 250, 350, 250); // 横軸
    gc.strokeLine(50, 250, 50, 50); // 縦軸

    for (int i = 0; i < (pa.length + 1); i++) {
      gc.strokeLine(50 + i * 25 * 12 / pa.length, 250, 50 + i * 25 * 12 / pa.length, 252); // 横軸の目盛
    }

    for (int i = 0; i < 5; i++) {
      gc.strokeLine(48, 50 + i * 50, 350, 50 + i * 50); // 縦軸の目盛
      gc.fillText(String.valueOf(i * intMax / 4), 20, 250 - i * 50); // 縦軸の値
    }
    gc.setStroke(Color.BLUE);

    for (int i = 0; i < pa.length; i++) {
      gc.setFill(Color.BLACK);
      gc.fillText(String.valueOf(i + 1), 50 + (7 + i * 25) * 12 / pa.length, 263); // 横軸の値
      if (i < (pa.length) - 1){
      gc.strokeLine(50 + (13 + i * 25) * 12 / pa.length, 250 - (pa[i] * 200 / intMax),50 + (13 + (i+1)* 25) * 12 / pa.length ,250 - (pa[i+1] * 200 / intMax));} // 折れ線の描画
    }
  }
}