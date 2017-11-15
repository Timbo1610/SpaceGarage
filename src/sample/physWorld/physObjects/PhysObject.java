package sample.physWorld.physObjects;

import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import sample.physWorld.Kinematic;
import sample.physWorld.PID;
import sample.physWorld.Vector;

import java.util.ArrayList;

public class PhysObject extends Region{

    private Kinematic kinematic = new Kinematic();
    private Kinematic nextKinematic = new Kinematic();
    private double rotation = 0;
    private int diameter = 10;
    private double mass = 100;
    private double strength = 1;
    private double stiffness = 10;
    private double dampening = 10;
    private double oldDX = 0;
    private double oldDY = 0;
    private double oldvelDX = 0;
    private double oldvelDY = 0;

    private double maxForce = 1;


    PID pid = new PID(0.1, 0.001, 0.2);


    RootObject rootObject;

    //  3 | 2 | 1
    //  4 | X | 0
    //  5 | 6 | 7
    private ArrayList<PhysLink> links = new ArrayList<>();

    private Circle circle = new Circle(diameter);

    public PhysObject()
    {
        this(0,0);
    }
    public PhysObject(int x ,int y)
    {
        pid.setOutputLimits(1);


        calcNextVector();
        kinematic.getOrigin().setX(x);
        kinematic.getOrigin().setY(y);

        super.relocate(x,y);

        getChildren().add(circle);
    }

    //    090°
    //180°    009°
    //    270°
    public boolean addNeighbour(PhysObject childObj, int degree, double dist)
    {
        //ToDo implement addNeighbour
        return true;
    }

    @Override
    public void relocate(double x, double y) {
        kinematic.getOrigin().setX(x);
        kinematic.getOrigin().setY(y);
        calcNextVector();
        super.relocate(x, y);
    }

    public double distanceToNode(PhysObject object)
    {
        return  kinematic.getOrigin().getDistanceToVector(object.getKinematic().getOrigin());
    }

    public boolean willCollide(PhysObject otherObject) {
            return otherObject.getNextKinematic().getOrigin().getDistanceToVector(nextKinematic.getOrigin()) < otherObject.diameter + diameter;
    }

    private void updateVector()
    {
        kinematic.moveBy(kinematic.getSpeed());
        calcNextVector();
        relocate(kinematic.getSpeed().getX(), kinematic.getSpeed().getY());
    }

    private void updateLinks()
    {
        for(PhysLink link: links)
        {
            if(link != null)
            {
                PhysObject otherNode = link.getTo().getObj();
               // double dx = kinematic.getX()-otherNode.getKinematic().getX() + Math.cos(Math.toRadians(link.getAngle())) * link.getDistance()*2;
               // double dy = kinematic.getY()-otherNode.getKinematic().getY() + Math.sin(Math.toRadians(link.getAngle())) * link.getDistance()*2;

               // double dvelX = kinematic.getdX() - otherNode.getKinematic().getdX();
               // double dvelY = kinematic.getdY() - otherNode.getKinematic().getdY();

            }
        }
    }

    private void applyForce()
    {
        for(PhysLink link: links)
        {
            if(link != null)
            {

            }
        }

    }

    public void update()
    {

        updateLinks();
        applyForce();
        updateVector();

    }


    public void calcNextVector()
    {

        nextKinematic.getOrigin().setX(kinematic.getOrigin().getX() + kinematic.getSpeed().getX());
        nextKinematic.getOrigin().setY(kinematic.getOrigin().getY() + kinematic.getSpeed().getY());
    }

    public void applyForce(Vector force)
    {
        kinematic.getForce().addVector(force);
    }

    public void applyDrag(double drag)
    {
       kinematic.getSpeed().multiplayBySkalar(drag);
    }

    public Kinematic getKinematic() {
        return kinematic;
    }

    public void setKinematic(Kinematic kinematic) {
        this.kinematic = kinematic;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
        circle.setRadius(diameter / 2);
    }

    public Kinematic getNextKinematic() {
        return nextKinematic;
    }

    public void setNextKinematic(Kinematic nextKinematic) {
        this.nextKinematic = nextKinematic;
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

    public ArrayList<PhysLink> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<PhysLink> links) {
        this.links = links;
    }

    public double getStiffness() {
        return stiffness;
    }

    public void setStiffness(double stiffness) {
        this.stiffness = stiffness;
    }
}
