/**************************************
* Henrik Schulz - 2016                *
* DM803 - Advanced Datastructures     *
**************************************/

public class Node {
	private Node leftChild;
	private Node rightChild;
	private Node parent;
	private int key;

	public Node() {
	}

	public Node(int key, Node parent) {
		this.key = key;
		this.parent = parent;
	}

	public int getKey() {
		return key;
	}

	public Node getParent() {
		return parent;
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public int getSize() {
		int size = 1;
		if (leftChild != null)
			size += getLeftChild().getSize();
		if (rightChild != null)
			size += getRightChild().getSize();
		return size;
	}

	public int getHight() {
		return 0;
	}

	public void setLeftChild(Node n) {
		leftChild = n;
	}


	public void setRightChild(Node n) {
		rightChild = n;
	}

	public void setParent(Node n) {
		parent = n;
	}

	public void setKey(int k) {
		key = k;
	}
}