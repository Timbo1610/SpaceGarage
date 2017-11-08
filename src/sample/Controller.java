package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import sample.model.Node;
import sample.physWorld.PhysWorld;
import sample.physWorld.physObjects.ChildObject;
import sample.physWorld.physObjects.PhysLink;
import sample.physWorld.physObjects.PhysObject;
import sample.physWorld.physObjects.RootObject;

import java.util.ArrayList;

public class Controller {

    private AnimationTimer gameTimer;
    private Pane root , gamefield;

    private boolean wKey;
    private boolean aKey;
    private boolean sKey;
    private boolean dKey;

    ArrayList<Node> nodes = new ArrayList<Node>();
    Node currentNode, underlayingNode;
    boolean placeAllowed = false;

    PhysWorld world = new PhysWorld();






    public Controller(Pane root, Scene scene)
    {
        this.root = root;
        gamefield = new Pane();
        gamefield.setPrefWidth(Settings.FIELD_WIDTH);
        gamefield.setPrefHeight(Settings.FIELD_HEIGHT);
        root.getChildren().add(gamefield);


        RootObject rootObj = new RootObject(Settings.WINDOW_WIDTH/2,Settings.WINDOW_HEIGHT/2);
        gamefield.getChildren().add(rootObj);
        world.add(rootObj);

        ChildObject childObj0 = new ChildObject();
        ChildObject childObj1 = new ChildObject();
        ChildObject childObj2 = new ChildObject();
        ChildObject childObj3 = new ChildObject();
        ChildObject childObj4 = new ChildObject();
        ChildObject childObj5 = new ChildObject();
        ChildObject childObj6 = new ChildObject();
        ChildObject childObj7 = new ChildObject();

        rootObj.addNeighbour(childObj0,0,20);
        rootObj.addNeighbour(childObj1,1,20);
        rootObj.addNeighbour(childObj2,2,20);
        rootObj.addNeighbour(childObj3,3,20);
        rootObj.addNeighbour(childObj4,4,20);
        rootObj.addNeighbour(childObj5,5,20);
        rootObj.addNeighbour(childObj6,6,20);
        rootObj.addNeighbour(childObj7,7,20);


        gamefield.getChildren().add(childObj0);
        world.add(childObj0);
        gamefield.getChildren().add(childObj1);
        world.add(childObj1);
        gamefield.getChildren().add(childObj2);
        world.add(childObj2);
        gamefield.getChildren().add(childObj3);
        world.add(childObj3);
        gamefield.getChildren().add(childObj4);
        world.add(childObj4);
        gamefield.getChildren().add(childObj5);
        world.add(childObj5);
        gamefield.getChildren().add(childObj6);
        world.add(childObj6);
        gamefield.getChildren().add(childObj7);
        world.add(childObj7);




        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.W))
                    wKey = true;
                if(event.getCode().equals(KeyCode.A))
                    aKey = true;
                if(event.getCode().equals(KeyCode.S))
                    sKey = true;
                if(event.getCode().equals(KeyCode.D))
                    dKey = true;
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.W))
                    wKey = false;
                if(event.getCode().equals(KeyCode.A))
                    aKey = false;
                if(event.getCode().equals(KeyCode.S))
                    sKey = false;
                if(event.getCode().equals(KeyCode.D))
                    dKey = false;
            }
        });

        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if(wKey)
                    gamefield.relocate(gamefield.getLayoutX(), gamefield.getLayoutY()+5);
                if(aKey)
                    gamefield.relocate(gamefield.getLayoutX() +5, gamefield.getLayoutY());
                if(sKey)
                    gamefield.relocate(gamefield.getLayoutX(), gamefield.getLayoutY()-5);
                if(dKey)
                    gamefield.relocate(gamefield.getLayoutX()-5, gamefield.getLayoutY());

                world.update();
            }


        };
        gameTimer.start();



    }

}
