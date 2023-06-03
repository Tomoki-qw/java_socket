
// ここで各自必要なクラスをimportする

public class TurtleGraphics1 extends Application {
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
      Turtle kamekichi = new Turtle(200, 200, root);
  
      square(kamekichi);
    }
    
    public void square(Turtle t) {
      for (int i = 0; i < 4; i++) {
        t.move(50);
        t.turn(90);
      }
    }
  }
  
  // この後にTurtleクラスを実装する．