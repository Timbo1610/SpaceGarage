package sample.physWorld.physObjects;



public class PhysLink {
    private PhysObject obj;
    private PhysLink to;
    private double torsion  = 0;
    private double forceX = 0;
    private double forceY = 0;
    private double distance;
    private double angle;

    public PhysLink(PhysObject obj, double distance , double angle)
    {
        this.obj = obj;
        this.distance = distance;
        this.angle = angle;
    }

    public void addForceX(double forceX)
    {
        this.forceX += forceX;
    }

    public void addForceY(double forceY)
    {
        this.forceY += forceY;
    }

    public PhysObject getObj() {
        return obj;
    }

    public void setObj(PhysObject obj) {
        this.obj = obj;
    }

    public PhysLink getTo() {
        return to;
    }

    public void setTo(PhysLink to) {
        this.to = to;
    }

    public double getTorsion() {
        return torsion;
    }

    public void setTorsion(double torsion) {
        this.torsion = torsion;
    }

    public double getForceX() {
        return forceX;
    }

    public void setForceX(double forceX) {
        this.forceX = forceX;
    }

    public double getForceY() {
        return forceY;
    }

    public void setForceY(double forceY) {
        this.forceY = forceY;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}
