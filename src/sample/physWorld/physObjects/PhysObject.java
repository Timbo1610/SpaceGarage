package sample.physWorld.physObjects;

import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import sample.physWorld.Vector;

public class PhysObject extends Region{

    private Vector vector = new Vector();
    private Vector nextVector = new Vector();
    private double rotation = 0;
    private int diameter = 10;
    private double mass;
    private double strength = 1;
    RootObject rootObject;

    //  3 | 2 | 1
    //  4 | X | 0
    //  5 | 6 | 7
    private PhysLink[] links = new PhysLink[8];

    private Circle circle = new Circle(diameter);

    public PhysObject()
    {
        this(0,0);
    }
    public PhysObject(int x ,int y)
    {
        vector.setX(x);
        vector.setY(y);

        calcNextVector();

        super.relocate(vector.getdX(), vector.getdY());

        getChildren().add(circle);
    }

    public boolean addNeighbour(PhysObject childObj, int pos, double dist)
    {
        if(links[pos] == null)
        {
            PhysLink link = new PhysLink(this,childObj,dist);
            links[pos] = link;
            childObj.connectLink(link,(pos + 4) % 8);
            childObj.setRootObject(getRootObject());

            switch (pos)
            {
                case 0:
                    childObj.relocate(vector.getX() + dist,vector.getY());
                    break;
                case 1:
                    childObj.relocate(vector.getX() + dist,vector.getY()- dist);
                    break;
                case 2:
                    childObj.relocate(vector.getX() ,vector.getY() -dist);
                    break;
                case 3:
                    childObj.relocate(vector.getX() - dist,vector.getY() -dist);
                    break;
                case 4:
                    childObj.relocate(vector.getX() - dist,vector.getY());
                    break;
                case 5:
                    childObj.relocate(vector.getX() - dist,vector.getY() + dist);
                    break;
                case 6:
                    childObj.relocate(vector.getX() ,vector.getY() + dist);
                    break;
                case 7:
                    childObj.relocate(vector.getX() + dist,vector.getY() + dist);
                    break;

            }


            return true;
        }
        else
            return false;
    }

    public boolean connectLink(PhysLink link, int pos)
    {
        if(links[pos] == null) {
            links[pos] = link;
            return true;
        }
        else
            return false;
    }

    @Override
    public void relocate(double x, double y) {
        vector.setX(x);
        vector.setY(y);
        calcNextVector();
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

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public RootObject getRootObject() {
        return rootObject;
    }

    public void setRootObject(RootObject rootObject) {
        this.rootObject = rootObject;
    }

    public PhysLink[] getLinks() {
        return links;
    }

    public void setLinks(PhysLink[] links) {
        this.links = links;
    }
}
