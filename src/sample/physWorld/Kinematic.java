package sample.physWorld;

public class Kinematic {
    // X -- Y
    Vector origin = new Vector(0,0);
    Vector speed = new Vector(0,0);

    Vector force = new Vector(0,0);
    double torsionalForce = 0;
    double radialSpeed = 0;
    double rotation = 0;

    public Kinematic(){}

    public Kinematic(double x , double y){
        origin.setX(x);
        origin.setY(y);
    }

    public Kinematic(double x , double y, double dx, double dy){
        origin.setX(x);
        origin.setY(y);

        speed.setX(dx);
        speed.setY(dy);
    }

    public void moveBy(Vector delta)
    {
        origin.addVector(delta);
    }

    public void rotateBy(double degree)
    {
        rotation = (rotation +  degree) % 360;
    }


    public Vector getOrigin() {
        return origin;
    }

    public void setOrigin(Vector origin) {
        this.origin = origin;
    }

    public Vector getSpeed() {
        return speed;
    }

    public void setSpeed(Vector speed) {
        this.speed = speed;
    }

    public Vector getForce() {
        return force;
    }

    public void setForce(Vector force) {
        this.force = force;
    }

    public double getTorsionalForce() {
        return torsionalForce;
    }

    public void setTorsionalForce(double torsionalForce) {
        this.torsionalForce = torsionalForce;
    }

    public double getRadialSpeed() {
        return radialSpeed;
    }

    public void setRadialSpeed(double radialSpeed) {
        this.radialSpeed = radialSpeed;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }
}
