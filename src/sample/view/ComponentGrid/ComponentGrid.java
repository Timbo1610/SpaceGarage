package sample.view.ComponentGrid;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import sample.Settings;
import sample.model.NodeType;

public class ComponentGrid extends Region{

    VBox vbox = new VBox();
    GridElement currentNode;

    public ComponentGrid() {
        vbox.setPadding(new Insets(Settings.GRID_COMPONENT_PADDING, Settings.GRID_COMPONENT_PADDING, Settings.GRID_COMPONENT_PADDING, Settings.GRID_COMPONENT_PADDING));
        getChildren().add(vbox);

        currentNode = new GridElement(NodeType.STRUCTURE);
        vbox.getChildren().add(currentNode);
        vbox.getChildren().add(new GridElement(NodeType.BIG_BOOSTER));
        vbox.getChildren().add(new GridElement(NodeType.ATTACHMENT));
        vbox.getChildren().add(new GridElement(NodeType.LASER_CANNON));
        vbox.getChildren().add(new GridElement(NodeType.SMALL_BOOSTER));


        vbox.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getTarget().getClass().equals(GridElement.class)) {
                    currentNode.setHighlighted(false);
                    currentNode = (GridElement) event.getTarget();
                    currentNode.setHighlighted(true);
                    System.out.println(currentNode.getType().toString() + " selected");
                }
            }
        });


    }


    public GridElement getCurrentNode() {
        return currentNode;
    }
}
