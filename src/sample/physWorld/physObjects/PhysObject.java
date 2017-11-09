package sample.physWorld.physObjects;

import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import sample.physWorld.PID;
import sample.physWorld.Vector;

public class PhysObject extends Region{

    private Vector vector = new Vector();
    private Vector nextVector = new Vector();
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
    private PhysLink[] links = new PhysLink[8];

    private Circle circle = new Circle(diameter);

    public PhysObject()
    {
        this(0,0);
    }
    public PhysObject(int x ,int y)
    {
        pid.setOutputLimits(1);


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
            double angle = 360/8 * pos;

            PhysLink link = new PhysLink(this,dist,angle);
            PhysLink link2 = new PhysLink(childObj,dist,angle+180);

            link.setTo(link2);
            link2.setTo(link);

            connectLink(link,pos);

            childObj.connectLink(link2,(pos + 4) % 8);

            childObj.setRootObject(getRootObject());

            childObj.relocate(vector.getX() + Math.cos(Math.toRadians(angle)) * dist,vector.getY() -  Math.sin(Math.toRadians(angle)) * dist);

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

    private void updateVector()
    {
        vector.moveBy(vector.getdX(), vector.getdY());
        calcNextVector();
        relocate(vector.getX(), vector.getY());
    }

    private void updateLinks()
    {
        for(PhysLink link: links)
        {
            if(link != null)
            {
                PhysObject otherNode = link.getTo().getObj();
                double dx = vector.getX()-otherNode.getVector().getX() + Math.cos(Math.toRadians(link.getAngle())) * link.getDistance()*2;
                double dy = vector.getY()-otherNode.getVector().getY() + Math.sin(Math.toRadians(link.getAngle())) * link.getDistance()*2;

                double dvelX = vector.getdX() - otherNode.getVector().getdX();
                double dvelY = vector.getdY() - otherNode.getVector().getdY();

                if(Math.abs(dvelX) > Math.abs(oldvelDX))
                    accelerate(dvelX /stiffness,0);

                if(Math.abs(dvelY) > Math.abs(oldvelDY))
                    accelerate(0,dvelY /stiffness);




                vector.setdX(vector.getdX() - dvelX);
                vector.setdY(vector.getdY() - dvelY);








                //if(this.getClass().equals(ChildObject.class))
                   // accelerate(link.getForceX()/ mass,link.getForceY() / mass);

                //link.setForceX(stiffness * - dx);
                //link.setForceY(stiffness * - dy);

                //link.addForceX(-Math.pow(dvelX,3) - stiffness * dx);
                //link.addForceY(-Math.pow(dvelY,3) - stiffness * dy);

                //if(Math.abs(dx) < deadzone)
                 //   link.setForceX(0);
                //if(Math.abs(dx) < deadzone)
                //    link.setForceY(0);


                //
                //{
                    //v(pid.getOutput(dx, Math.cos(Math.toRadians(link.getAngle())) * link.getDistance()*2)/mass,
                     //       pid.getOutput(dy, Math.sin(Math.toRadians(link.getAngle())) * link.getDistance()*2)/mass);
                //}


                oldDX = dx;
                oldDY = dy;
                oldvelDX = dvelX;
                oldvelDY = dvelY;



                //System.out.println(toString() + "  dx: " + dx + " dy: " + dy + " Fx: " +  link.getForceX() + " Fy: " +  link.getForceY());

                link.setForceX(link.getForceX() / dampening);
                link.setForceY(link.getForceY() / dampening);





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

    public double getStiffness() {
        return stiffness;
    }

    public void setStiffness(double stiffness) {
        this.stiffness = stiffness;
    }
}
