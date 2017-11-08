package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import sample.model.Node;
import sample.physWorld.PhysWorld;
import sample.physWorld.physObjects.PhysObject;

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




        for(int i=0; i<10;i++)
        {
            PhysObject obj1 = new PhysObject(400,400);
            gamefield.getChildren().add(obj1);
            world.add(obj1);
            obj1.accelerate(-5 + Math.random()*10,-5 + Math.random()*10);
        }

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
