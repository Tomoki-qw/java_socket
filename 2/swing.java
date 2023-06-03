import java.awt.BasicStroke; // BasicStroke のクラス
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point; // Point のクラス
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.util.ArrayList; // ArrayList のクラス
import javax.swing.JFrame;
import javax.swing.JPanel;

public class swing {

  //swing依存ここから
  public static void main(String[] args) {
    JFrame fr = new JFrame("TurtleGraphics");
    fr.setSize(600, 600);
    fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fr.getContentPane().setBackground(new Color(255, 255, 255));
    TurtleGraphicsPanel panel = new TurtleGraphicsPanel();
    panel.setOpaque(false);
    fr.add(panel);
    fr.setVisible(true);
  }
  //ここまで
}

//swing依存ここから
class TurtleGraphicsPanel
  extends JPanel
  implements MouseMotionListener, MouseListener {

  private Turtle kamekichi;
  private Turtle kamekichi2;
  private Turtle kamekichi3;
  private Turtle kamekichi4;
  private Turtle kamekichi5;
  private int selectedTurtle;
  private int lx;
  private int ly;
  ArrayList<Turtle> turtles = new ArrayList<Turtle>();

  TurtleGraphicsPanel() {
    kamekichi = new Turtle(300, 300);
    triangles(kamekichi, 40, 2, 20);
    turtles.add(kamekichi);
    kamekichi2 = new Turtle(200, 200);
    triangles(kamekichi2, 40, 2, 10);
    turtles.add(kamekichi2);
    kamekichi3 = new Turtle(50, 120);
    poligons(kamekichi3);
    turtles.add(kamekichi3);
    kamekichi4 = new Turtle(450, 100);
    oreikarukosu(kamekichi4);
    turtles.add(kamekichi4);
    kamekichi5 = new Turtle(200, 300);
    circle2(kamekichi5);
    turtles.add(kamekichi5);
    selectedTurtle = -1;
    addMouseMotionListener(this);
    addMouseListener(this);
  }

  //三角形
  public void triangle(Turtle t, int size) {
    t.move(size);
    t.turn(120);
    t.move(size);
    t.turn(120);
    t.move(size);
    t.turn(120);
  }

  //三角形たくさん
  public void triangles(Turtle t, int n, int c, int d) {
    for (int i = 0; i++ < n;) {
      triangle(t, i * c);
      t.turn(d);
    }
  }

  //謎の図形
  public void poligons(Turtle t) {
    for (int i = 4; i < 11; i++) {
      for (int j = 0; j < i; j++) {
        t.move(50);
        t.turn((double) 180 - (180.0 * (i - 2) / i));
      }
    }
  }

  public void oreikarukosu(Turtle t) {
    t.penColor(new Color(0, 150, 100));
    t.penSize(3);
    t.turn(-60);
    t.move(198);
    t.penColor(new Color(100, 160, 0));
    t.turn(-150);
    t.move(229);
    t.penColor(new Color(70, 170, 70));
    t.turn(150);
    t.move(198);
    t.penColor(new Color(100, 100, 150));
    t.turn(120);
    t.move(198);
    t.penColor(new Color(150, 10, 20));
    t.turn(150);
    t.move(229);
    t.penColor(new Color(100, 70, 70));
    t.turn(-150);
    t.move(198);
    t.turn(-60);
    for (int i = 1; i < 360; i++) {
      t.penSize(9);
      t.penColor(new Color(100, 0, 200));
      t.turn(-1);
      t.move(2);
    }
  }

  //円2
  public void circle2(Turtle t) {
    t.penSize(3);
    for (int i = 0; i < 360; i++) {
      t.penColor(new Color(200, 0, 0));
      t.turn(-1);
      t.move(2);
    }
    t.penSize(0);
    t.turn(-90);
    t.move(100);
    t.turn(90);
    t.penSize(3);
    for (int i = 1; i < 360; i++) {
      t.penColor(new Color(200, 0, 0));
      t.turn(-1);
      t.move(1);
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    kamekichi.paint(g); // 「亀吉」に足跡を描かせる
    kamekichi2.paint(g); // 「亀吉 2」に足跡を描かせる
    kamekichi3.paint(g); // 「亀吉 3」に足跡を描かせる
    kamekichi4.paint(g); // 「亀吉 4」に足跡を描かせる
    kamekichi5.paint(g); // 「亀吉 5」に足跡を描かせる
  }

  @Override
  public void mouseMoved(MouseEvent e) {}

  @Override
  public void mouseDragged(MouseEvent e) {
    if (selectedTurtle == -1) {} else {
      for (int i = 0; i < turtles.get(selectedTurtle).getpsize(); i++) {
        turtles.get(selectedTurtle).points.get(i).x =
          turtles.get(selectedTurtle).points.get(i).x +
          (int) (e.getPoint().getX() - lx);
        turtles.get(selectedTurtle).points.get(i).y =
          turtles.get(selectedTurtle).points.get(i).y +
          (int) (e.getPoint().getY() - ly);
      }
      lx = (int) (e.getPoint().getX());
      ly = (int) (e.getPoint().getY());
      repaint();
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void mousePressed(MouseEvent e) {
    lx = (int) e.getPoint().getX();
    ly = (int) e.getPoint().getY();
    for (int i = turtles.size() - 1; i >= 0; i--) {
      if (turtles.get(i).inside(e.getPoint().getX(), e.getPoint().getY())) {
        selectedTurtle = i;
        break;
      }
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    selectedTurtle = -1;
  }
}

//ここまで
class Turtle {

  private double x, y;
  private double angle;
  ArrayList<Point> points = new ArrayList<Point>();
  ArrayList<Color> color = new ArrayList<Color>();
  ArrayList<BasicStroke> stroke = new ArrayList<BasicStroke>();
  Color c = new Color(0, 0, 0);
  BasicStroke def = new BasicStroke(1);

  public Turtle(int i, int j) {
    x = i;
    y = j;
    points.add(new Point((int) x, (int) y));
    stroke.add(this.def);
    color.add(this.c);
    angle = 0.0;
  }

  public void move(double length) {
    x += length * Math.cos(Math.toRadians(angle));
    y += length * Math.sin(Math.toRadians(angle));
    stroke.add(stroke.get(stroke.size() - 1)); // 要素の追加
    color.add(color.get(color.size() - 1)); // 要素の追加
    points.add(new Point((int) x, (int) y)); // 要素の追加
  }

  public void turn(double deg) {
    angle += -deg;
  }

  public void penSize(int size) {
    BasicStroke x = new BasicStroke(size);
    stroke.remove(stroke.get(stroke.size() - 1));
    stroke.add(x); // 要素の追加
  }

  public void penColor(Color c) {
    color.remove(color.get(color.size() - 1));
    color.add(c); // 要素の追加
  }

  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    for (int i = 0; i < points.size() - 1; i++) {
      // Point.getX(), Point.getY(): 座標を取得
      g.setColor(color.get(i));
      g2.setStroke(stroke.get(i));
      if (stroke.get(i).getLineWidth() != 0) {
        g.drawLine(
          (int) points.get(i).getX(),
          (int) points.get(i).getY(),
          (int) points.get(i + 1).getX(),
          (int) points.get(i + 1).getY()
        );
      }
    }
  }

  public boolean inside(double x, double y) {
    //最大座標を読み取る
    int maxX = points.get(0).x;
    int maxY = points.get(0).y;
    for (int i = 1; i < points.size(); i++) {
      if (points.get(i).x > maxX) {
        maxX = points.get(i).x;
      }
      if (points.get(i).y > maxY) {
        maxY = points.get(i).y;
      }
    }
    //最小座標を読み取る
    int minX = points.get(0).x;
    int minY = points.get(0).y;
    for (int i = 1; i < points.size(); i++) {
      if (points.get(i).x < minX) {
        minX = points.get(i).x;
      }
      if (points.get(i).y < minY) {
        minY = points.get(i).y;
      }
    }
    if (x > minX && x < maxX && y > minY && y < maxY) {
      return true;
    } else {
      return false;
    }
  }

  public int getpsize() {
    return (int) this.points.size();
  }
}
