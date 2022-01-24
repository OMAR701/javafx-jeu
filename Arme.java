import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class Arme {
    private Rectangle corps = new Rectangle(15,0,10,50);
    private Circle sortie = new Circle(19,0,5);
    public Arme(GraphicObject player){
        corps.setTranslateX(player.getCorps().getTranslateX());
        corps.setTranslateY(player.getCorps().getTranslateY());
        corps.setFill(Color.DARKGREEN);
        sortie.setFill(Color.DARKORANGE);
        updateSortie();
    }
    public void updateSortie(){
        sortie.setTranslateX(corps.getTranslateX()+2);
        sortie.setTranslateY(corps.getTranslateY()+25);

    }

    public Rectangle getCorps() {
        return corps;
    }

    public Circle getSortie() {
        return sortie;
    }

    public void rotateRight(){
        corps.setRotate(corps.getRotate()-5);

    }

    public void rotateLeft(){
        corps.setRotate(corps.getRotate()+5);
    }


    public double getRotate(){
        return corps.getRotate()-90;
    }

    public void attachplayer(Player player){
        corps.setTranslateX(player.getCorps().getTranslateX());
        corps.setTranslateY(player.getCorps().getTranslateY());
        updateSortie();
    }

}
