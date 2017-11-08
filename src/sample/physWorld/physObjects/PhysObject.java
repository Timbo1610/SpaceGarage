package sample.physWorld.physObjects;

import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import sample.physWorld.Vector;

public class PhysObject extends Region{

    private Vector vector = new Vector();
    private Vector nextVector = new Vector();

    private int diameter = 10;

    Circle circle = new Circle(diameter);

    public PhysObject()
    {
        this(0,0);
    }
    public PhysObject(int x ,int y)
    {
        vector.setX(x);
        vector.setY(y);

        calcNextVector();

        relocate(vector.getdX(), vector.getdY());

        getChildren().add(circle);
    }

    @Override
    public void relocate(double x, double y) {
        super.relocate(x, y);
    }

    public double distanceToNode(PhysObject object)
    {
        double dx = vector.getX()-object.getVector().getX();
        double dy = vector.getY()-object.getVector().getY();

        double distance = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));

        return  distance;
    }

    public boolean willCollide(PhysObject otherObject) {
            return otherObject.getNextVector().distanceToVector(nextVector) < otherObject.diameter + diameter;
    }

    public void updateVector()
    {
        vector.moveBy(vector.getdX(), vector.getdY());
        calcNextVector();
        relocate(vector.getX(), vector.getY());
    }


    public void calcNextVector()
    {
        nextVector.setX(vector.getX() + vector.getdX());
        nextVector.setY(vector.getY() + vector.getdY());
    }

    public void accelerate(double x, double y)
    {
        vector.setdX(vector.getdX() + x);
        vector.setdY(vector.getdY() + y);
    }

    public void applyDrag(double drag)
    {
        vector.setdX(vector.getdX()/drag);
        vector.setdY(vector.getdY()/drag);
    }

    public Vector getVector() {
        return vector;
    }

    public void setVector(Vector vector) {
        this.vector = vector;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
        circle.setRadius(diameter / 2);
    }

    public Vector getNextVector() {
        return nextVector;
    }

    public void setNextVector(Vector nextVector) {
        this.nextVector = nextVector;
    }


}
