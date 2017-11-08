package sample.physWorld;

import sample.physWorld.physObjects.PhysObject;

import java.util.ArrayList;

public class CollisionDetecter {

    private ArrayList<PhysObject> physObjects;
    private ArrayList<PhysObject> collidingObjects = new ArrayList<>();

    public CollisionDetecter(ArrayList<PhysObject> physObjects)
    {
        this.physObjects = physObjects;

    }

    public void detectCollisions()
    {
        collidingObjects.clear();

        for(PhysObject object1: physObjects)
        {
            for(PhysObject object2: physObjects)
            {
                //if(object1.distanceToNode(object2) <= PhysSettings.PHYS_COLLSION_DETECTION_MAX_RADIUS)
                if(object1.willCollide(object2))
                {
                    System.out.println(object1.toString() + " will collide with " + object2.toString());
                    collidingObjects.add(object1);
                    collidingObjects.add(object2);
                }
            }
        }
    }

    public void detectCollsionsWithBoundry()
    {
        for(PhysObject object: physObjects)
        {
            if(object.getNextVector().getX() > PhysSettings.PHYS_WORLD_WIDTH ||
                    object.getNextVector().getY() > PhysSettings.PHYS_WORLD_HEIGHT ||
                    object.getNextVector().getX() < 0 ||
                    object.getNextVector().getY() < 0
                    )
            {
                object.getCurrentVector().setdX(0);
                object.getCurrentVector().setdY(0);
            }
        }

    }

    public ArrayList<PhysObject> getCollidingObjects() {
        return collidingObjects;
    }
}
