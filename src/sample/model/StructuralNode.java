package sample.model;

import sample.Settings;

public class StructuralNode extends Node {


    public StructuralNode(int x,int y,NodeType type)
    {
        super(x,y);
        setType(type);
        showAtachmentNodes();
        setPrefSize(Settings.GAMEFIELD_GRID_WIDTH,Settings.GAMEFIELD_GRID_WIDTH);
    }



}
