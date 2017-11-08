package sample.model;

public class Vector {
    double[] origin = new double[2];
    double[] direction = new double[2];

    public Vector(){}

    public Vector(double x , double y){
        origin[0] = x;
        origin[1] = y;
    }

    public Vector(double x , double y, double dx, double dy){
        origin[0] = x;
        origin[1] = y;

        direction[0] = dx;
        direction[1] = dy;
    }

    public void setX(double x)
    {
        origin[0] = x;
    }

    public void setY(double y)
    {
        origin[1] = y;
    }

    public void setdX(double dx)
    {
        direction[0] = dx;
    }

    public void setdY(double dy)
    {
        direction[1] = dy;
    }

    public double getX()
    {
        return origin[0];
    }

    public double getY()
    {
        return origin[1];
    }

    public double getdX()
    {
        return direction[0];
    }

    public double getdY()
    {
        return direction[1];
    }

}
