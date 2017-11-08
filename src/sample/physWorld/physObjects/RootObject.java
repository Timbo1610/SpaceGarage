package sample.physWorld.physObjects;

import java.util.ArrayList;

public class RootObject extends PhysObject {

    private ArrayList<ChildObject> childObjects = new ArrayList<>();


    public RootObject(int x, int y)
    {
        super(x,y);
        rootObject = this;
    }

    public ArrayList<ChildObject> getChildObjects() {
        return childObjects;
    }

    public void setChildObjects(ArrayList<ChildObject> childObjects) {
        this.childObjects = childObjects;
    }
}
