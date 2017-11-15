package sample.physWorld.physObjects;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import sample.physWorld.Kinematic;
import sample.physWorld.PID;
import sample.physWorld.Vector;

import java.util.ArrayList;

public class PhysObject extends Region{

    private Kinematic kinematic = new Kinematic();
    private Kinematic nextKinematic = new Kinematic();

    private int diameter = 20;
    private double mass = 10;
    private double strength = 1;
    private double stiffness = 10;
    private double dampening = 10;
    private double oldDX = 0;
    private double oldDY = 0;
    private double oldvelDX = 0;
    private double oldvelDY = 0;

    private double maxForce = 1;

    RootObject rootObject;

    private ArrayList<PhysObject> neighbours = new ArrayList<>();

    private Circle circle = new Circle(diameter/2);
    private Rectangle rect = new Rectangle(-5,-5,10,10);
    Rotate rot = new Rotate(kinematic.getRotation(), 0, 0);

    public PhysObject()
    {
        this(0,0);
    }
    public PhysObject(int x ,int y)
    {
        relocate(x,y);
        rect.setFill(Color.BLUE);
        getChildren().addAll(circle,rect);
        getTransforms().add(rot);
    }

    //    090°
    //180°    009°
    //    270°
    public boolean addNeighbour(PhysObject childObj, Vector position)
    {
        childObj.relocate(getKinematic().getOrigin().clone().addVector(position));
        childObj.setRootObject(rootObject);

        System.out.println(childObj.toString() + " relocate to: X:" + childObj.getKinematic().getOrigin().getX() + " Y:" + childObj.getKinematic().getOrigin().getY());
        return true;
    }

    @Override
    public void relocate(double x, double y) {
        kinematic.getOrigin().setX(x);
        kinematic.getOrigin().setY(y);

        System.out.println(this.toString() + " relocate to: X:" + getKinematic().getOrigin().getX() + " Y:" + getKinematic().getOrigin().getY());
        rot.setAngle(kinematic.getRotation());

        calcNextVector();
        super.relocate(x, y);
    }

    public void relocate(Vector vector) {
        kinematic.setOrigin(vector);
        calcNextVector();
        rot.setAngle(kinematic.getRotation());
        //System.out.println(this.toString() + " Neighbour add at: X:" + childObj.getKinematic().getOrigin().getX() + " Y:" + childObj.getKinematic().getOrigin().getY());
        super.relocate(vector.getX(), vector.getY());

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
        kinematic.rotateBy(kinematic.getRadialSpeed());
        relocate(kinematic.getOrigin().getX(), kinematic.getOrigin().getY());
    }

    public void update()
    {
        updateVector();
    }


    public void calcNextVector()
    {
        nextKinematic.setOrigin(kinematic.getOrigin().clone().addVector(kinematic.getSpeed()));
        nextKinematic.setRotation(kinematic.getRotation()+ kinematic.getRadialSpeed());
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

    public double getStiffness() {
        return stiffness;
    }

    public void setStiffness(double stiffness) {
        this.stiffness = stiffness;
    }
}
