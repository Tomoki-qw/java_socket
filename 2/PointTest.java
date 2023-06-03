import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;
import java.util.ArrayList;

public class PointTest extends Application {
  @Override
  public void start(Stage st) throws Exception {
    Group root = new Group();
    Canvas canvas = new Canvas(400, 400);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    drawShapes(gc);
    
    root.getChildren().add(canvas);

    Scene scene = new Scene(root, 400, 400, Color.WHITE);
    st.setTitle("Point Test");
    st.setScene(scene);
    st.show();
  }
  
  public static void main(String[] a) {
    launch(a);
  }


  private void drawShapes(GraphicsContext gc) {
    // ArrayList <要素のクラス> ... 要素数可変のリスト
    // Point: 二次元座標を格納するクラス
    ArrayList <Point2D> points = new ArrayList <Point2D>();

    points.add(new Point2D(100, 100)); // 要素の追加
    points.add(new Point2D(150, 150));
    points.add(new Point2D(100, 200));
    points.add(new Point2D(50, 150));

    // points.size(): リストの要素数を取得
    for (int i = 0; i < points.size() - 1; i++) {
      // ArrayList.get(i): i番目の要素(Point)を取得
      // Point.getX(), Point.getY(): x座標，y座標を取得
      gc.strokeLine((int) points.get(i).getX(), (int) points.get(i).getY(),
                 (int) points.get(i+1).getX(), (int) points.get(i+1).getY());
    }
  }
}