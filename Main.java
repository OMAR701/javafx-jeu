import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application{
    private double widthWindow=800;
    private double heightWindow=600;
    private Pane container = new Pane();
    Line line=new Line(0,200,widthWindow,200 );
    Zone zone1 = new Zone(0,0,line.getEndX()-50,line.getEndY()-50);
    Zone zone2 = new Zone(line.getStartX(),line.getStartY(),line.getEndX()-50,heightWindow-50);
    private Player player = new Player(zone2);
    private List<Monstre> monstres = new ArrayList<>();
    private List<Balle> balles = new ArrayList<>();

    private TextField tfTimer = new TextField();

    GridPane gridPane = new GridPane();

    Arme arme = new Arme(player);
    AnimationTimer animation = new AnimationTimer() {
        @Override
        public void handle(long l) {
            refreshContent();
        }
    };



EventHandler<KeyEvent> event = new EventHandler<KeyEvent>() {
    @Override
    public void handle(KeyEvent event) {
        if(event.getCode()== KeyCode.X){
            arme.rotateLeft();
        }

        if(event.getCode()== KeyCode.W){
            arme.rotateRight();
        }
        if(event.getCode()==KeyCode.SPACE){

            Balle balle = new Balle(arme);
            container.getChildren().add(balle.getCorps());
            balles.add(balle);
            AudioClip sound = new AudioClip(this.getClass().getResource("shoot.wav").toExternalForm());
            sound.play();
        }
        if(event.getCode()==KeyCode.UP){
            player.getCorps().setTranslateY(player.getCorps().getTranslateY()-5);
            arme.attachplayer(player);

        }
        if(event.getCode()==KeyCode.DOWN){
            player.getCorps().setTranslateY(player.getCorps().getTranslateY()+5);
            arme.attachplayer(player);

        }
        if(event.getCode()==KeyCode.LEFT){
            player.getCorps().setTranslateX(player.getCorps().getTranslateX()-5);
            arme.attachplayer(player);

        }
        if(event.getCode()==KeyCode.RIGHT){
            player.getCorps().setTranslateX(player.getCorps().getTranslateX()+5);
            arme.attachplayer(player);
        }
    }
};


   /* long start = System.nanoTime();
    // some time passes
    long end = System.nanoTime();
    long elapsedTime = end - start;
    long startTime = System.currentTimeMillis();
    // ... do something ...
    long estimatedTime = System.currentTimeMillis() - startTime;

    System.out.println(el);*/

  /*  private void dotime(){

        Timeline time= new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        if(time!=null){
            time.stop();
        }

        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                seconds--;
                label.setText("CountDown: "+seconds.toString());
                if(seconds<=0){
                    time.stop();
                }
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();

    }
*/
 /* private void timer() {

      int seconds = Integer.parseInt(tfTimer.getText());

      long startTime = System.currentTimeMillis();

      long endTime = System.currentTimeMillis() + (seconds * 1000);

      long temp = startTime + 1000;


      System.out.print(seconds + "\n");


      while (startTime < endTime) {

          if (startTime == temp) {

              seconds--;

              System.out.print(seconds + "\n");

              temp += 1000;

          }


          startTime = System.currentTimeMillis();
      }
  }

*/
      private void refreshContent(){

        for(Balle balle: balles){
            for(Monstre monstre:monstres){
                if(balle.touch(monstre)){

                /*    deads++;
                if((LocalTime.now().getSecond())>currentTime.getSecond() ){
                       int  second = LocalTime.now().getSecond()-currentTime.getSecond();
                       if(second < 45 && deads >20){

                           AudioClip sound = new AudioClip(this.getClass().getResource("win.mp3").toExternalForm());
                           //MediaPlayer mediaPlayer = new MediaPlayer(sound);
                           sound.play();
                       }else{
                           AudioClip sound = new AudioClip(this.getClass().getResource("lose.wav").toExternalForm());
                           //MediaPlayer mediaPlayer = new MediaPlayer(sound);
                           sound.play();
                           System.exit(0);
                       }
                    }*/
                    container.getChildren().removeAll(balle.getCorps(),monstre.getCorps());
                    balle.setAlive(false);
                    monstre.setAlive(false);
                }
            }
        }
        monstres.removeIf(GraphicObject::getDead);
        balles.removeIf(GraphicObject::getDead);

        for (Balle balle: balles){
            balle.update();
        }
        if(Math.random()<0.01) {
            Monstre m = new Monstre(zone1);
            container.getChildren().add(m.getCorps());
            monstres.add(m);
        }

    }
    public static void main(String[] args){
        Application.launch(args);
    }
    private void createContent(){
        container.getChildren().add(line);
        container.getChildren().add(player.getCorps());
        container.getChildren().add(arme.getCorps());
        container.getChildren().add(arme.getSortie());




    }
    @Override
    public void start(Stage window) throws Exception {


        window.setWidth(widthWindow);
        window.setHeight(heightWindow);
        window.setTitle("Worrier Game");
        Scene  scene = new Scene(container);
        createContent();
        window.setScene(scene);
        animation.start();
       scene.setOnKeyPressed(event);
      //  dotime();

        window.show();




    }
}
