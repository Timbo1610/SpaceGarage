package sample.model;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import sample.Settings;
import sample.physWorld.Kinematic;

import java.util.ArrayList;

public class Node extends Region {

    ArrayList<Node> nodes = new ArrayList<>();//0:up 1:right 2:bottom 3:left

    NodeType type = NodeType.STRUCTURE;
    Color color;
    Node parent;
    boolean isParent = false;

    private Kinematic kinematic = new Kinematic();

    public Node(int x, int y)
    {

        super.relocate(x, y);
        redraw();
    }

    public void showAtachmentNodes()
    {
        if(nodes.size() == 0) {
            nodes.add(new AttachmentNode(0, -Settings.GAMEFIELD_GRID_WIDTH));
            nodes.get(0).setParentNode(this);
            nodes.get(0).toBack();

            nodes.add(new AttachmentNode(Settings.GAMEFIELD_GRID_WIDTH, 0));
            nodes.get(1).setParentNode(this);
            nodes.get(1).toBack();

            nodes.add(new AttachmentNode(0, Settings.GAMEFIELD_GRID_WIDTH));
            nodes.get(2).setParentNode(this);
            nodes.get(2).toBack();

            nodes.add(new AttachmentNode(-Settings.GAMEFIELD_GRID_WIDTH, 0));
            nodes.get(3).setParentNode(this);
            nodes.get(3).toBack();

            getChildren().addAll(nodes);
        }
    }

    @Override
    public void relocate(double x, double y) {
        //kinematic.setX(x);
       // kinematic.setY(y);
        super.relocate(x, y);
    }

    //public void moveBy(double x, double y)
    //{
   //     relocate(kinematic.getX()+x, kinematic.getY()+y);
   // }

   // public void updateLocationWithoutCollision()
   // {
   //     moveBy(kinematic.getdX(), kinematic.getdY());
   // }


   // public double distanceToNode(Node node)
   // {
      //  double dx = kinematic.getX()-node.getKinematic().getX();
       // double dy = kinematic.getY()-node.getKinematic().getY();

        //double distance = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));

       // return  distance;
   // }

    public void redraw()
    {
        getChildren().clear();

        switch(type)
        {
            case STRUCTURE:
                color = Color.BLUE;
                Rectangle rect = new Rectangle(-Settings.GAMEFIELD_GRID_WIDTH /4,-Settings.GAMEFIELD_GRID_WIDTH /4,Settings.GAMEFIELD_GRID_WIDTH /2,Settings.GAMEFIELD_GRID_WIDTH /2);
                rect.setFill(color);
                rect.setMouseTransparent(true);
                getChildren().add(rect);
                break;
            case ATTACHMENT:
                color = Color.GRAY;
                Circle circle = new Circle(Settings.GAMEFIELD_GRID_WIDTH /8,color);
                circle.setMouseTransparent(true);
                getChildren().add(circle);
                break;

            case BIG_BOOSTER:
                Color color = Color.RED;
                Rectangle rect2 = new Rectangle(-Settings.GAMEFIELD_GRID_WIDTH /4,-Settings.GAMEFIELD_GRID_WIDTH /4,Settings.GAMEFIELD_GRID_WIDTH /2,Settings.GAMEFIELD_GRID_WIDTH);
                rect2.setFill(color);
                rect2.setMouseTransparent(true);
                getChildren().add(rect2);
                break;

            default:
                color = Color.PINK;
                Circle circle2 = new Circle(Settings.GAMEFIELD_GRID_WIDTH /2,color);
                circle2.setMouseTransparent(true);
                getChildren().add(circle2);
                break;

        }



    }

    public void replaceNode(Node replacingNode)
    {
        replacingNode.setParentNode(parent);
        parent.getNodes().add(replacingNode);
        parent.getChildren().remove(this);
        parent.getChildren().add(replacingNode);
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }


    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
        redraw();
    }

    public Kinematic getKinematic() {
        return kinematic;
    }

    public void setKinematic(Kinematic kinematic) {
        this.kinematic = kinematic;
    }


    public Node getParentNode() {
        return parent;
    }

    public void setParentNode(Node parent) {
        this.parent = parent;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setIsParent(boolean parent) {
        isParent = parent;
    }
}
