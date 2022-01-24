import javafx.scene.Node;

public class GraphicObject {
    protected Node corps;
    private boolean alive = true;



    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean getDead(){
        return !alive;
    }
    public Node getCorps() {
        return corps;
    }



    public  boolean touch(GraphicObject obj){

        return corps.getBoundsInParent().intersects(obj.getCorps().getBoundsInParent());

    }
}
