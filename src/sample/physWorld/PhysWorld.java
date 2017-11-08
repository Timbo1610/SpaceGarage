package sample.physWorld;

import sample.physWorld.physObjects.PhysObject;
import sample.physWorld.fields.ForceField;

import java.util.ArrayList;

public class PhysWorld {

    int width , height;
    boolean bounceEdges = true;
    boolean drag;
    double resitance;
    Vector gravity;

    private ArrayList<ForceField> forceFields = new ArrayList<>();
    private ArrayList<PhysObject> objects = new ArrayList<>();

    CollisionDetecter cd = new CollisionDetecter(objects);

    public PhysWorld(int width, int height) {
        this.width = width;
        this.height = height;

    }

    public void update()
    {
        cd.detectCollisions();
    }

    public void addPhysObject(PhysObject object)
    {
        objects.add(object);
    }


    public ArrayList<PhysObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<PhysObject> objects) {
        this.objects = objects;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isBounceEdges() {
        return bounceEdges;
    }

    public void setBounceEdges(boolean bounceEdges) {
        this.bounceEdges = bounceEdges;
    }

    public boolean isDrag() {
        return drag;
    }

    public void setDrag(boolean drag) {
        this.drag = drag;
    }

    public double getResitance() {
        return resitance;
    }

    public void setResitance(double resitance) {
        this.resitance = resitance;
    }

    public ArrayList<ForceField> getForceFields() {
        return forceFields;
    }

    public void setForceFields(ArrayList<ForceField> forceFields) {
        this.forceFields = forceFields;
    }
}

