package sample.physWorld.physObjects;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PhysLink {
    PhysObject from,to;
    double torsion  = 0;
    double force = 0;
    double distance;

    public PhysLink(PhysObject from, PhysObject to, double distance )
    {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public PhysObject getFrom() {
        return from;
    }

    public void setFrom(PhysObject from) {
        this.from = from;
    }

    public PhysObject getTo() {
        return to;
    }

    public void setTo(PhysObject to) {
        this.to = to;
    }

    public double getTorsion() {
        return torsion;
    }

    public void setTorsion(double torsion) {
        this.torsion = torsion;
    }

    public double getForce() {
        return force;
    }

    public void setForce(double force) {
        this.force = force;
    }
}
