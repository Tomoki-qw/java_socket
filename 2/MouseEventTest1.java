import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;


public class MouseEventTest1 extends Application {
  private int x = 225;
  private int y = 225;
  private Canvas canvas;
  private GraphicsContext gc;
  
  @Override
  public void start(Stage st) throws Exception {
    Group root = new Group();
    canvas = new Canvas(400, 400);
    gc = canvas.getGraphicsContext2D();
    drawShapes();
    
    root.getChildren().add(canvas);

    Scene scene = new Scene(root, 400, 400, Color.rgb(255,255,255));
    st.setTitle("Mouse Event Test1");
    st.setScene(scene);
    st.show();
    
    canvas.setOnMouseDragged(this::mouseDragged);
  }
  
  public static void main(String[] a) {
    launch(a);
  }

  private void drawShapes() {
    // 円を描く
    gc.setFill(Color.rgb(0, 0, 255));
    gc.fillOval(x - 25, y - 25, 50, 50);
  }

  private void mouseDragged(MouseEvent e) {
    // 円の中心座標をマウスカーソルの位置に更新
    x = (int) e.getSceneX();
    y = (int) e.getSceneY();
    
    // 再描画
    gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
    drawShapes();
  }
}