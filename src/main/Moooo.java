package main;

import javafx.animation.AnimationTimer;  
import javafx.application.Application;  
import javafx.event.EventHandler;  
import javafx.scene.*;  
import javafx.scene.image.*;  
import javafx.scene.input.KeyEvent;  
import javafx.scene.paint.Color;  
import javafx.stage.Stage;  
  
public class Moooo extends Application {  
  
    private static double H = 1200, B = 800;  
  
    private static String Bild =  
            "http://www.mariowiki.com/images/thumb/3/3f/SuperMushroom.png/50px-SuperMushroom.png";  
  
    private Image BildHeld;  
    private Node  Held;  
  
    boolean  Nord, Sued, Ost, West;  
   
    public void start(Stage stage) throws Exception {  
    	BildHeld = new Image(Bild);  
    	Held = new ImageView(BildHeld);  
  
        Group Map = new Group(Held);  
  
        moveHeldTo(H / 2, B / 2);  
  
        Scene scene = new Scene(Map, H, B, Color.BLACK);  
  
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {   
            public void handle(KeyEvent event) {  
                switch (event.getCode()) {  
                    case UP:    Nord = true; break;  
                    case DOWN:  Sued = true; break;  
                    case LEFT:  West = true; break;  
                    case RIGHT: Ost  = true; break;    
                    default: break;
                }  
            }  
        });  
  
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {    
            public void handle(KeyEvent event) {  
                switch (event.getCode()) {  
                    case UP:    Nord = false; break;  
                    case DOWN:  Sued = false; break;  
                    case LEFT:  West = false; break;  
                    case RIGHT: Ost  = false; break;  
                    default: break;
                }  
            }  
        });  
  
        stage.setScene(scene);  
        stage.show();  
  
        AnimationTimer timer = new AnimationTimer() {    
            public void handle(long now) {  
                int dx = 0, dy = 0;  
  
                if (Nord) dy -= 1;  
                if (Sued) dy += 1;  
                if (Ost)  dx += 1;  
                if (West) dx -= 1;                    
  
                moveHeroBy(dx, dy);  
            }  
        };  
        timer.start();  
    }  
  
    private void moveHeroBy(int dx, int dy) {  
        if (dx == 0 && dy == 0) return;  
  
        double cx = Held.getBoundsInLocal().getWidth()  / 2;  
        double cy = Held.getBoundsInLocal().getHeight() / 2;  
  
        double x = cx + Held.getLayoutX() + dx;  
        double y = cy + Held.getLayoutY() + dy;  
  
        moveHeldTo(x, y);  
    }  
  
    private void moveHeldTo(double x, double y) {  
        double cx = Held.getBoundsInLocal().getWidth()  / 2;  
        double cy = Held.getBoundsInLocal().getHeight() / 2;  
  
        if (x - cx >= 0 &&  
            x + cx <= H &&  
            y - cy >= 0 &&  
            y + cy <= B) {  
        	Held.relocate(x - cx, y - cy);  
        }  
    }  
  
    public static void main(String[] args) { launch(args); }  
}  
