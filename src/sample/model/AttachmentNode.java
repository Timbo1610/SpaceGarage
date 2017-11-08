package sample.model;

public class AttachmentNode extends Node {


    public AttachmentNode(int x, int y)
    {
        super(x,y);
        setType(NodeType.ATTACHMENT);
        redraw();
    }
}
