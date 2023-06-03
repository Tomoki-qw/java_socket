import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TurtleGraphics extends Application {

  private int initialSceneWidth = 600;
  private int initialSceneHeight = 600;

  @Override
  public void start(Stage st) throws Exception {
    Group root = new Group();

    drawTurtles(root);

    Scene scene = new Scene(
      root,
      initialSceneWidth,
      initialSceneHeight,
      Color.rgb(255, 255, 255)
    );
    st.setTitle("Turtle Graphics1");
    st.setScene(scene);
    st.show();
  }

  public static void main(String[] a) {
    launch(a);
  }

  public void drawTurtles(Group root) {
    Turtle kamekichi = new Turtle(200, 200, root);
    Turtle kamekichi1 = new Turtle(400, 400, root);
    Turtle kamekichi2 = new Turtle(200, 400, root);
    Turtle kamekichi3 = new Turtle(200, 200, root);
    triangles(kamekichi, 40, 2, 20);
    triangles(kamekichi1, 40, 2, 10);
    takakukei(kamekichi2, 40);
    square2(kamekichi3, 40);
  }

  public void square(Turtle t) {
    for (int i = 0; i < 4; i++) {
      t.move(50);
      t.turn(90);
    }
  }

  public void triangle(Turtle t, int size) {
    for (int i = 0; i < 3; i++) {
      t.move(size);
      t.turn(120);
    }
  }

  public void triangles(Turtle t, int n, int c, int d) {
    for (int i = 0; i++ < n;) {
      triangle(t, i * c);
      t.turn(d);
    }
  }

  public void takakukei(Turtle t, int size) {
    for (int i = 3; i < 11; i++) {
      for (int j = 0; j < i; j++) {
        t.move(size);
        t.turn(360 / i);
      }
    }
  }

  public void square2(Turtle t, int size) {
    for (int i = 0; i < 4; i++) {
      t.penColor(Color.rgb(255, 0, 0));
      t.penSize(2);
      t.move(size);
      t.penColor(Color.rgb(0, 255, 0));
      t.penSize(3);
      t.move(size);
      t.penColor(Color.rgb(0, 0, 255));
      t.penSize(4);
      t.move(size);
      t.turn(90);
    }
  }
}

//turtle
class Turtle {

  private double currentX = 0;
  private double currentY = 0;
  private double currentXx = 0;
  private double currentYy = 0;
  private double currentAngle = 0;
  private Color pencolor;
  private double penSize;
  private Group region;

  //Turtle
  public Turtle(double x, double y, Group root) {
    currentX = x;
    currentY = y;
    currentAngle = 0.0;
    penSize = 2;
    pencolor = Color.rgb(0, 0, 0);
    region = new Group();
    root.getChildren().add(region);
  }

  //move
  public void move(double length) {
    currentXx = currentX + (length * Math.cos(Math.toRadians(currentAngle)));
    currentYy = currentY + (length * Math.sin(Math.toRadians(currentAngle)));
    if (penSize != 0) {
      Line line = new Line(currentX, currentY, currentXx, currentYy);
      line.setStroke(pencolor);
      line.setStrokeWidth(penSize);
      line.setOnMouseDragged(this::mouseDragged); //lineに対してmouseRaggedを適用させる。
      line.setOnMousePressed(this::mousePressed);
      region.getChildren().add(line);
    }
    currentX = currentXx;
    currentY = currentYy;
  }

  //turn
  public void turn(double deg) {
    currentAngle += -deg;
  }

  //penSize
  public void penSize(double size) {
    penSize = size;
  }

  //penColor
  public void penColor(Color c) {
    pencolor = c;
  }

  private void mousePressed(MouseEvent e) {
    region.toFront();
  }

  private void mouseDragged(MouseEvent e) {
    region.setTranslateX((e.getSceneX() - currentX)); //currentXがないとずれる（絶対座標で見てる、帳尻合わせ）
    region.setTranslateY((e.getSceneY() - currentY));
  }
  //paint
}
