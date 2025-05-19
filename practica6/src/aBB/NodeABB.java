package aBB;

public class NodeABB <T extends Comparable <T>>{
    private NodeABB <T> fatherNode;
    private NodeABB <T> leftNode;
    private NodeABB <T> rightNode;
    private T data;

    public NodeABB(T data, NodeABB<T> fatherNode, NodeABB<T> leftNode, NodeABB<T> rightNode) {
        this.data = data;
        this.fatherNode = fatherNode;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public NodeABB(T data, NodeABB<T> fatherNode) {
        this.data = data;
        this.fatherNode = fatherNode;
        this.leftNode = new NodeABB<>(null, fatherNode);
        this.rightNode = new NodeABB<>(null, fatherNode);
    }

    public NodeABB<T> getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(NodeABB<T> fatherNode) {
        this.fatherNode = fatherNode;
    }

    public NodeABB<T> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(NodeABB<T> leftNode) {
        this.leftNode = leftNode;
    }

    public NodeABB<T> getRightNode() {
        return rightNode;
    }

    public void setRightNode(NodeABB<T> rightNode) {
        this.rightNode = rightNode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
