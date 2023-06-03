import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import javafx.scene.shape.Circle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;


public class SampleCircle1 extends Application {
  private int x = 225;
  private int y = 225;
  Group root;
  
  @Override
  public void start(Stage st) throws Exception {
    root = new Group();
    drawShapes();
    
    Scene scene = new Scene(root, 400, 400, Color.rgb(255,255,255));
    st.setTitle("Mouse Event Test1");
    st.setScene(scene);
    st.show();
  }
  
  public static void main(String[] a) {
    launch(a);
  }

  private void drawShapes() {
    Circle circle = new Circle(50);
    circle.setTranslateX(200);
    circle.setTranslateY(200);
    
    circle.setFill(Color.rgb(0, 0, 255));
    root.getChildren().add(circle);
  }
}
