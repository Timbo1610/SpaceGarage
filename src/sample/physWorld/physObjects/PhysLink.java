package sample.physWorld.physObjects;


import sample.physWorld.Kinematic;

public class PhysLink {
    private PhysObject obj;
    private PhysLink to;
    private double torsion = 0;
    private Kinematic kinematic = new Kinematic();

    public PhysLink(PhysObject obj, double x, double y) {
        this.obj = obj;
        kinematic.setX(x);
        kinematic.setY(y);
    }

    public void addForceX(double forceX) {
        this.forceX += forceX;
    }

    public void addForceY(double forceY) {
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

    public Kinematic getKinematic() {
        return kinematic;
    }

    public void setKinematic(Kinematic kinematic) {
        this.kinematic = kinematic;
    }
}


