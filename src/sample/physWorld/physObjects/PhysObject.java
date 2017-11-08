package sample.physWorld.physObjects;

import sample.physWorld.Vector;

public class PhysObject {

    private Vector currentVector = new Vector();
    private Vector nextVector = new Vector();

    private int diameter;

    public PhysObject(int x ,int y)
    {
        currentVector.setX(x);
        currentVector.setY(y);

        calcNextVector();

    }

    public double distanceToNode(PhysObject object)
    {
        double dx = currentVector.getX()-object.getCurrentVector().getX();
        double dy = currentVector.getY()-object.getCurrentVector().getY();

        double distance = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));

        return  distance;
    }

    public boolean willCollide(PhysObject otherObject) {
            return otherObject.getNextVector().distanceToVector(nextVector) < otherObject.diameter + diameter;
    }

    public void updateVector()
    {
        currentVector.moveBy(currentVector.getdX(), currentVector.getdY());
        calcNextVector();
    }


    public void calcNextVector()
    {
        nextVector.setX(currentVector.getX() + currentVector.getdX());
        nextVector.setY(currentVector.getY() + currentVector.getdY());
    }

    public void accelerate(double x, double y)
    {
        currentVector.setdX(currentVector.getdX() + x);
        currentVector.setdY(currentVector.getdY() + y);
    }

    public void applyDrag(double drag)
    {
        currentVector.setdX(currentVector.getdX()/drag);
        currentVector.setdY(currentVector.getdY()/drag);
    }


    public Vector getCurrentVector() {
        return currentVector;
    }

    public void setCurrentVector(Vector currentVector) {
        this.currentVector = currentVector;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public Vector getNextVector() {
        return nextVector;
    }

    public void setNextVector(Vector nextVector) {
        this.nextVector = nextVector;
    }
}
