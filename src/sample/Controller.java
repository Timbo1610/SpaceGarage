package sample;

import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import sample.model.AttachmentNode;
import sample.model.Node;
import sample.model.NodeType;
import sample.model.StructuralNode;
import sample.view.ComponentGrid.ComponentGrid;

import java.util.ArrayList;

public class Controller {

    private AnimationTimer gameTimer;
    private Pane root , gamefieled;

    private boolean wKey;
    private boolean aKey;
    private boolean sKey;
    private boolean dKey;

    ArrayList<Node> nodes = new ArrayList<Node>();
    Node currentNode, underlayingNode;
    boolean placeAllowed = false;


    public Controller(Pane root, Scene scene)
    {
        this.root = root;
        gamefieled = new Pane();
        gamefieled.setPrefWidth(Settings.FIELD_WIDTH);
        gamefieled.setPrefHeight(Settings.FIELD_HEIGHT);

        root.getChildren().add(gamefieled);


        ComponentGrid compGrid = new ComponentGrid();
        compGrid.relocate(30,30);
        root.getChildren().add(compGrid);

        /*
        scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(nodes.contains(event.getTarget()))
                {
                    System.out.println(event.getTarget().toString() + " already exists");
                }
                else if(event.getTarget().equals(root) && false)
                {
                    System.out.println(event.getTarget().toString() + " does not exist");
                    StructuralNode node = new StructuralNode((int)(event.getX()-root.getLayoutX()),(int)(event.getY()-root.getLayoutY()), NodeType.STRUCTURE);
                    nodes.add(node);
                    System.out.println(node.toString() + " added");
                    gamefieled.getChildren().add(node);
                }
            }
        });
        */
        compGrid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(currentNode == null)
                {

                    Node node = new Node((int) (event.getX()), (int) (event.getY()));
                    node.setType(compGrid.getCurrentNode().getType());
                    node.toFront();
                    node.setMouseTransparent(true);
                    System.out.println(node.toString() + " added");
                    gamefieled.getChildren().add(node);
                    currentNode = node;

                }
                else
                {
                    currentNode.setType(compGrid.getCurrentNode().getType());
                }
            }
        });

        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(currentNode != null) {
                    currentNode.relocate((int)(event.getX()-gamefieled.getLayoutX()),(int)(event.getY()-gamefieled.getLayoutY()));
                }
            }
        });

        gamefieled.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event.getTarget());
                if(nodes.contains(event.getTarget()))
                {
                    System.out.println("recognized");
                }

                if(event.getTarget().getClass().equals(AttachmentNode.class))
                {
                    placeAllowed = true;
                    underlayingNode = (AttachmentNode)event.getTarget();
                    System.out.println("allowed");
                }
                else {
                    placeAllowed = false;
                    System.out.println("not allowed");
                }

            }
        });


        //Place Node
        gamefieled.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(currentNode != null && placeAllowed && underlayingNode != null) {
                    StructuralNode node = new StructuralNode(0,0, compGrid.getCurrentNode().getType());
                    System.out.println(node.toString() + " added@ " + (int)node.getVector().getX()+ " " + (int)node.getVector().getY());
                    node.relocate(underlayingNode.getVector().getX(),underlayingNode.getVector().getY());

                    underlayingNode.replaceNode(node);
                    node.toBack();



                }
                else if(currentNode != null && event.getButton().equals(MouseButton.SECONDARY)) {
                    StructuralNode node = new StructuralNode((int) (event.getX()), (int) (event.getY()), compGrid.getCurrentNode().getType());

                    System.out.println(node.toString() + " added");
                    gamefieled.getChildren().add(node);
                    nodes.add(node);
                    node.setIsParent(true);

                }
            }
        });



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
                    gamefieled.relocate(gamefieled.getLayoutX(),gamefieled.getLayoutY()+5);
                if(aKey)
                    gamefieled.relocate(gamefieled.getLayoutX() +5,gamefieled.getLayoutY());
                if(sKey)
                    gamefieled.relocate(gamefieled.getLayoutX(),gamefieled.getLayoutY()-5);
                if(dKey)
                    gamefieled.relocate(gamefieled.getLayoutX()-5,gamefieled.getLayoutY());

            }


        };
        gameTimer.start();



    }



    public void detectConnections()
    {
        for(Node node1: nodes){
            for(Node node2: nodes){
                //if()
            }
        }

    }
}
