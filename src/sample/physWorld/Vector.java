package sample.physWorld;

public class Vector {

    double x;
    double y;

    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector addVector(Vector vector)
    {
        x = vector.getX();
        y = vector.getY();

        return this;
    }

    public void multiplayBySkalar(double skalar)
    {
        x =  x*skalar;
        y =  y*skalar;
    }

    public double getLength()
    {
        return  Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }

    public double getDistanceToVector(Vector other)
    {
        return clone().addVector(other).getLength();
    }

    public Vector clone()
    {
        return new Vector(x,y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
