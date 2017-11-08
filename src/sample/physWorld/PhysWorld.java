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
    private ArrayList<PhysObject> physObjects = new ArrayList<>();

    CollisionDetecter cd = new CollisionDetecter(physObjects);

    public PhysWorld() {
        width = PhysSettings.PHYS_WORLD_WIDTH;
        height= PhysSettings.PHYS_WORLD_HEIGHT;
    }

    public void update()
    {

        cd.detectCollisions();
        cd.detectCollsionsWithBoundry();
        for(PhysObject physObj: physObjects)
        {
            physObj.updateVector();
        }
    }

    public void add(PhysObject object)
    {
        physObjects.add(object);
    }

    public void addAll(PhysObject ... objects)
    {
        for(PhysObject obj: objects)
            physObjects.add(obj);
    }


    public ArrayList<PhysObject> getPhysObjects() {
        return physObjects;
    }

    public void setPhysObjects(ArrayList<PhysObject> physObjects) {
        this.physObjects = physObjects;
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

