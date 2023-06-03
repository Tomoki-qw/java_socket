import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
public class TurtleGraphics extends Application {
    private int initialSceneWidth = 400;
    private int initialSceneHeight = 400;
  
    @Override
    public void start(Stage st) throws Exception {
      Group root = new Group();
      
      drawTurtles(root);
      
      Scene scene = new Scene(root, initialSceneWidth, initialSceneHeight, Color.rgb(255,255,255));
      st.setTitle("Turtle Graphics1");
      st.setScene(scene);
      st.show();
    }
  
    public static void main(String[] a) {
      launch(a);
    }
  
    public void drawTurtles(Group root) {
      Turtle kamekichi = new Turtle(200, 200);
  
      square(kamekichi);
    }
    
    public void square(Turtle t) {
      for (int i = 0; i < 4; i++) {
        t.move(50);
        t.turn(90);
      }
    }
  }

//turtle
  class Turtle{
    double currentX, currentY;
    double currentAngle;
    double penSize;
    ArrayList <Point2D> points = new ArrayList <Point2D>();
    ArrayList <Color> color = new ArrayList <Color>();
    ArrayList <Line> line = new ArrayList <Line>();
    /* ArrayList <BasicStroke> stroke = new ArrayList <BasicStroke>(); 
    Color c = new Color(0, 0, 0);
    BasicStroke def = new BasicStroke(1); */

    //Turtle
    public Turtle(double x,double y) {
        points.add(new Point2D(x, y));
/*         stroke.add(this.def);
        color.add(this.c); */
        currentX = x;
        currentY = y;
        currentAngle = 0.0;
        penSize = 2;
    }
    //move
    public void move(double length){
        double currentX1 = currentX;
        double currentY1 = currentY;
        currentX += length * Math.cos(Math.toRadians(currentAngle));
        currentY += length * Math.sin(Math.toRadians(currentAngle)); 
        line.add(new Line(currentX1,currentY1,currentX,currentY));
        points.add(new Point2D(currentX, currentY)); // 要素の追加
    }
    //turn
    public void turn(double deg){
        currentAngle += -deg;
    }
    //penSize
    /* public void penSize(int size){
        BasicStroke x = new BasicStroke(size); stroke.remove(stroke.get(stroke.size() - 1));
        stroke.add(x); // 要素の追加
    } */
    //penColor
    public void penColor(Color c){ color.remove(color.get(color.size() - 1));
        color.add(c); // 要素の追加
    }
    //paint
    /* public void paint(GraphicsContext gc){
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < points.size() - 1; i++) {
            // Point.getX(), Point.getY(): 座標を取得 
            g.setColor(color.get(i));
            g2.setStroke(stroke.get(i));
            if (stroke.get(i).getLineWidth() != 0){
                g.drawLine((int) points.get(i).getX(), (int) points.get(i).getY(), (int) points.get(i+1).getX(), (int) points.get(i+1).getY());
            }
        }
    } */


    /* public boolean inside(double x, double y){
        //最大座標を読み取る
        int maxX = points.get(0).x;
        int maxY = points.get(0).y;
        for(int i = 1; i < points.size();i++){
            if(points.get(i).x > maxX){
                maxX = points.get(i).x;
            }
            if(points.get(i).y > maxY){
                maxY = points.get(i).y;
            }
        }
        //最小座標を読み取る
        int minX = points.get(0).x;
        int minY = points.get(0).y;
        for(int i = 1; i < points.size();i++){
            if(points.get(i).x < minX){
                minX = points.get(i).x;
            }
            if(points.get(i).y < minY){
                minY = points.get(i).y;
            }
        }
        if(x > minX && x < maxX && y > minY && y < maxY){
            return true;
        }
        else{
            return false;
        }
    }
    public int getpsize(){
        return (int)this.points.size();
    } */
}