/**************************************
* Henrik Schulz - 2016                *
* DM803 - Advanced Datastructures     *
**************************************/

public class ScapegoatTree {
	private Node root;
	private int size;
	private int maxSize;
	private double alpha;
	private int depth = 0;
	private Node parent;
	private boolean test;
	public int pointersused;

	public ScapegoatTree(double alpha, boolean test) {
		setAlpha(alpha);
		this.test = test;
	}	

	public boolean search(int key) {
		pointersused = 0;
		pointersused++;
		if (search(key, root)) {
			if (!test) {
				System.out.print(key + " found. ");
			}
			return true;
		}
		else {
			if (!test) {			
				System.out.print(key + " not found. ");
			}
			return false;
		}
	}

	private boolean search(int key, Node node) {
		if (node == null) {
			return false;
		}
		if (key == node.getKey()) {
			return true;
		}
		if (key < node.getKey()) {
			pointersused++;
			depth++;
			Node left = node.getLeftChild();
			return search(key, left);
		}
		if (key > node.getKey()) {
			pointersused++;
			depth++;
			Node right = node.getRightChild();
			return search(key, right);
		}
		return false;
	}


	public boolean insert(int key) {
		if (root == null) {
			root = new Node(key);
			size++;
			maxSize = Math.max(size, maxSize);
			return true;	
		}
		else {
			return insert(key, root);
		}
	}

	private boolean insert(int key, Node node) {
		if (key == node.getKey()) {
			if (!test) {
				System.out.print(key + " Already exists ");
			}
			return false;
		}
		if (key < node.getKey()) {
			Node left = node.getLeftChild();
			if (left == null) {
				Node n = new Node(key);
				node.setLeftChild(n);
				size++;
				maxSize = Math.max(size, maxSize);
				if (toDeep(n)) {
					Node scape = findscapegoat(root, n);
					if (parent != null && parent.getLeftChild() == scape ) {
						parent.setLeftChild(rebuild(scape.getSize(), scape)); 
					}	
					if (parent != null && parent.getRightChild() == scape ) {
						parent.setRightChild(rebuild(scape.getSize(), scape)); 
					}
					else {
						root = rebuild(scape.getSize(), scape); 
					}
				}
				return true;
			}
			else {
				return insert(key, left);
			}
		}
		if (key > node.getKey()) {
			Node right = node.getRightChild();
			if (right == null) {
				Node n = new Node(key);		
				node.setRightChild(n);
				size++;
				maxSize = Math.max(size, maxSize);
				if (toDeep(n)) {
					Node scape = findscapegoat(root, n);
					if (parent != null && parent.getLeftChild() == scape ) {
						parent.setLeftChild(rebuild(scape.getSize(), scape));
					}	
					if (parent != null && parent.getRightChild() == scape ) {
						parent.setRightChild(rebuild(scape.getSize(), scape)); 
					}
					else {
						root = rebuild(scape.getSize(), scape); 
					}
				}		
				return true;
			}
			else {
				return insert(key, right);
			}
		}
		return false;
	}

	public boolean delete(int key) {
		if (root == null) {
			if (!test) {
				System.out.print("Tree is empty. ");
			}
			return false;
		}
		else {
			return delete(key, root);
		}
	}

	private boolean delete(int key, Node node) {
		if (key == node.getKey()) {
			if (node.getLeftChild() == null && node.getRightChild() == null) {
				node = null;
				size--;
				if (size < alpha * maxSize) {
					root = rebuild(size, root);
					maxSize = size;
				}
				return true;
			}
			else {
				return findReplace(node);
			}
		}
		if (key < node.getKey()) {
			Node left = node.getLeftChild();
			if (left != null) {
				return delete(key, left);
			}
			else {
				if (!test) {
					System.out.print("Not found. ");
				}
				return false;
			}
		}
		if (key > node.getKey()) {
			Node right = node.getRightChild();
			if (right != null) {
				return delete(key, right);
			}
			else {
				if (!test) {
					System.out.print("not found");
				}
				return false;
			}
		}
		return false;
	}

	private boolean findReplace(Node node) {
		Node right = node.getRightChild();
		Node left = node.getLeftChild();
		if (left == null) {
			node.setKey(right.getKey());
			node.setLeftChild(right.getLeftChild());
			node.setRightChild(right.getRightChild());
			right = null;
			size--;
			if (size < alpha * maxSize) {
				root = rebuild(size, root);
				maxSize = size;
			}
			return true;
		}
		if (right == null) {
			node.setKey(left.getKey());
			node.setLeftChild(left.getLeftChild());
			node.setRightChild(left.getRightChild());
			left = null;
			size--;
			if (size < alpha * maxSize) {
				root = rebuild(size, root);
				maxSize = size;
			}
			return true;
		}
		if (right != null && left != null) {
			Node rightChildMin = getMin(right);
			node.setKey(rightChildMin.getKey());
			rightChildMin = null;
			size--;
			if (size < alpha * maxSize) {
				root = rebuild(size, root);
				maxSize = size;
			}
			return true;
		}
		return false;
	}

	private boolean toDeep(Node n) {
		depth = 0;
		search(n.getKey(), root);
		double limit = Math.floor((Math.log(size)/Math.log(1/alpha))); //log_1/alpha (n)
		if (depth > limit) {
			return true;
		}	
		else {
			return false;
		}
	}

	private Node findscapegoat(Node x, Node n) {
		Node left = x.getLeftChild();
		Node right = x.getRightChild();
		int leftsize = 0;
		int rightsize = 0;
		if (left != null) {
			leftsize = left.getSize();
		}
		if (right != null) {
			rightsize = right.getSize();
		}
		if (x.getKey() < n.getKey()) {
			if (leftsize <= (alpha * x.getSize()) && rightsize <= (alpha * x.getSize())) {
				parent = x;
				return findscapegoat(right, n);
			}
			else {
				return x;
			}
		}
		if (x.getKey() > n.getKey()) {
			if (leftsize <= (alpha * x.getSize()) && rightsize <= (alpha * x.getSize())) {
				parent = x;
				return findscapegoat(left, n);
			}
			else {
				return x;
			}		
		}
		return null;
	}
	
	private Node buildTree(double subSize, Node  x) {
		if (subSize == 0) {
			x.setLeftChild(null);
			return x;
		}
		Node r = buildTree(Math.ceil( (subSize-1)/2) , x);
		Node s = buildTree(Math.floor( (subSize-1)/2) , r.getRightChild());
		r.setRightChild(s.getLeftChild());
		s.setLeftChild(r);
		return s;
	}

	private Node rebuild(double subSize, Node scapegoat) {
		Node w = new Node();
		Node z = flatten(scapegoat, w);
		buildTree(subSize, z);
		return w.getLeftChild();
	}

	private Node flatten(Node x, Node y) {
		if (x == null) {
			return y;
		}
		x.setRightChild(flatten(x.getRightChild(), y));
		return flatten(x.getLeftChild(), x);
	}

	public Node getMin(Node n) {
		if (n.getLeftChild() == null) {
			return n;
		}
		else {
			return getMin(n.getLeftChild());
		}
	}

	public void traverseTree() {
		traverseTree(root);
	}

	public void traverseTree(Node x) {
		if (x != null) {
			System.out.println("hit: " + x.getKey());
		}
		if (x.getLeftChild() != null) {
			System.out.println("Go left");
			traverseTree(x.getLeftChild());
		}
		if (x.getRightChild() != null) {
			System.out.println("Go right");
			traverseTree(x.getRightChild());
		}
	}

	public int getSize() {
		return size;
	}

	private void setAlpha(double a) {
		alpha = Math.max(a, 0.5);
		alpha = Math.min(1, alpha);
	}
}