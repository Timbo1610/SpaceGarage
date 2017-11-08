package sample.physWorld;

public class Vector {
    double[] origin = {0,0};
    double[] direction ={0,0};

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

    public void moveBy(double x, double y)
    {
        setX(getX() + x);
        setY(getY() + y);
    }

    public double distanceToVector( Vector vector)
    {
        double dx = getX()-vector.getX();
        double dy = getY()-vector.getY();

        double distance = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));

        return  distance;
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
