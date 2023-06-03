import java.util.Random; // 乱数を発生させるクラス
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class T11 extends Application {

  private int initialSceneWidth = 800;
  private int initialSceneHeight = 800;
  //色を楽しむための乱数
  Random rnd = new Random();

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
    Turtle kamekichi3 = new Turtle(600, 600, root);
    triangles(kamekichi, 40, 2, 20);
    triangles(kamekichi1, 40, 2, 10);
    takakukei(kamekichi2, 40);
    free(kamekichi3);
  }

  // 三角形の回転を行うメソッド
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

  public void free(Turtle t) {
    for (int i = 0; i < 200; i++) {
      t.penSize(5 - i * 0.025);
      t.move(200 - 1 * i);
      t.penColor(
        Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
      );
      t.turn(90);
    }
  }
}

// ここにTurtleクラスを実装する．
class Turtle {

  private double currentX = 0;
  private double currentY = 0;
  private double currentX1 = 0;
  private double currentY1 = 0;
  private double currentAngle = 0;
  //ペンの色フィールド
  private Color pencolor;
  //ペンの太さフィールド
  public double pensize;
  //描画内容のフィールド。Lineインスタンス等の集合を管理するGroupクラス等のインスタンス
  private Group naiyou;

  //コンストラクタ
  public Turtle(double x, double y, Group root) {
    currentAngle = 0;
    pensize = 2.0;
    pencolor = Color.rgb(0, 0, 0);
    currentX = x;
    currentY = y;
    naiyou = new Group();
    root.getChildren().add(naiyou);
  }

  //メソッド
  public void move(double length) {
    //1.計算2.描画3.更新
    //1
    currentX1 = currentX + (length * Math.cos(currentAngle));
    currentY1 = currentY + (length * Math.sin(currentAngle));
    //2
    if (pensize != 0) {
      Line line = new Line(currentX, currentY, currentX1, currentY1);
      line.setStroke(pencolor);
      line.setStrokeWidth(pensize);
      line.setOnMouseDragged(this::mouseDragged);//lineに対してmouseRaggedを適用させる。
      line.setOnMousePressed(this::mousePressed);
      naiyou.getChildren().add(line);
    }
    //3
    currentX = currentX1;
    currentY = currentY1;
  }

  public void turn(double deg) {
    currentAngle = currentAngle - deg * Math.PI / 180.0;
  }

  public void penSize(double size) {
    pensize = size;
  }

  public void penColor(Color c) {
    pencolor = c;
  }

  private void mousePressed(MouseEvent e) {
    naiyou.toFront();
  }

  private void mouseDragged(MouseEvent e) {
    naiyou.setTranslateX((e.getSceneX() - currentX));
    naiyou.setTranslateY((e.getSceneY() - currentY));
  }
}
