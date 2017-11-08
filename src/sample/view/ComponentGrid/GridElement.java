package sample.view.ComponentGrid;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import sample.Settings;
import sample.model.NodeType;

public class GridElement extends Region {

    NodeType type;
    Rectangle background;
    Color backgroundColor = Settings.GRID_COMPONENT_BG_COLOR;
    Color itemcolor;
    boolean highlighted;

    public GridElement(NodeType type)
    {
        this.type = type;

        background = new Rectangle(-Settings.GRID_COMPONENT_WIDTH/2,-Settings.GRID_COMPONENT_WIDTH/2,Settings.GRID_COMPONENT_WIDTH,Settings.GRID_COMPONENT_WIDTH);
        background.setMouseTransparent(true);
        background.setFill(backgroundColor);
        getChildren().add(background);
        setPrefSize(Settings.GRID_COMPONENT_WIDTH,Settings.GRID_COMPONENT_WIDTH);


        switch(type)
        {
            case STRUCTURE:
                itemcolor = Color.BLUE;
                Rectangle rect = new Rectangle(-Settings.GAMEFIELD_GRID_WIDTH /4,-Settings.GAMEFIELD_GRID_WIDTH /4,Settings.GAMEFIELD_GRID_WIDTH /2,Settings.GAMEFIELD_GRID_WIDTH /2);
                rect.setFill(itemcolor);
                rect.setMouseTransparent(true);
                getChildren().add(rect);
                break;
            case ATTACHMENT:
                itemcolor = Color.LIGHTCORAL;
                Circle circle = new Circle(Settings.GAMEFIELD_GRID_WIDTH /8,itemcolor);
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
                getChildren().add(circle2);
                circle2.setMouseTransparent(true);
                break;

        }

    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
        if(highlighted)
            backgroundColor = Settings.GRID_COMPONENT_BG_COLOR_HIGHLIGHTED;
        else
            backgroundColor = Settings.GRID_COMPONENT_BG_COLOR;

        background.setFill(backgroundColor);
    }

    public NodeType getType() {
        return type;
    }
}
