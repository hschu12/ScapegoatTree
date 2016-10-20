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
	Node parent;

	ScapegoatTree(double alpha) {
		setAlpha(alpha);
	}	

	public boolean insert(int key) {
		if (root == null) {
			System.out.println(key + " inserted as root");
			root = new Node(key,null);
			incSize();
			maxSize = Math.max(size, maxSize);
			return true;	
		}
		else {
			return insert(key, root);
		}
	}

	public boolean insert(int key, Node node) {
		if (key == node.getKey()) {
			System.out.println(key + " Already exists");
			return false;
		}
		if (key < node.getKey()) {
			Node left = node.getLeftChild();
			if (left == null) {
				System.out.println(key + " inserted as left Child of " + node.getKey());
				Node n = new Node(key, node);
				node.setLeftChild(n);
				incSize();
				maxSize = Math.max(size, maxSize);
				if (toDeep(n)) {
					//System.out.println("Finding scape");
					Node scape = findscapegoat(root, n);
					if (parent != null && parent.getLeftChild() == scape ) {
						System.out.println("Parent: " + parent.getKey());
						parent.setLeftChild(rebuild(scape.getSize(), scape)); //sæt til at være left child
					}	
					if (parent != null && parent.getRightChild() == scape ) {
						System.out.println("Parent: " + parent.getKey());
						parent.setRightChild(rebuild(scape.getSize(), scape)); //sæt til at være left child
					}
					else {
						root = rebuild(scape.getSize(), scape); //sæt til at være left child
					}
					System.out.println("printing after rebuild");
					printTree(root);
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
				System.out.println(key + " inserted as right Child of " + node.getKey());
				Node n = new Node(key, node);		
				node.setRightChild(n);
				incSize();
				maxSize = Math.max(size, maxSize);
				if (toDeep(n)) {
					//System.out.println("Finding scape");
					Node scape = findscapegoat(root, n);
					//System.out.println("scape found: " + scape.getKey());
					if (parent != null && parent.getLeftChild() == scape ) {
						System.out.println("Parent: " + parent.getKey());
						parent.setLeftChild(rebuild(scape.getSize(), scape)); //sæt til at være left child
					}	
					if (parent != null && parent.getRightChild() == scape ) {
						System.out.println("Parent: " + parent.getKey());
						parent.setRightChild(rebuild(scape.getSize(), scape)); //sæt til at være left child
					}
					else {
						root = rebuild(scape.getSize(), scape); //sæt til at være left child
					}
					System.out.println("printing after rebuild");					
					printTree(root);
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
			System.out.println("Tree is empty");
			return false;
		}
		else {
			return delete(key, root);
		}
	}

	public boolean delete(int key, Node node) {
		if (key == node.getKey()) {
			if (node.getLeftChild() == null && node.getRightChild() == null) {
				System.out.println(node.getKey() + " has been deleted");
				node = null;
				decSize();
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
				System.out.println("Not found");
				return false;
			}
		}
		if (key > node.getKey()) {
			Node right = node.getRightChild();
			if (right != null) {
				return delete(key, right);
			}
			else {
				System.out.println("not found");
				return false;
			}
		}
		return false;
	}

	public boolean findReplace(Node node) {
		Node right = node.getRightChild();
		Node left = node.getLeftChild();
		if (left == null) {
			System.out.println(node.getKey() + " has been deleted and " + right.getKey() + " took its place");
			node.setKey(right.getKey());
			node.setLeftChild(right.getLeftChild());
			node.setRightChild(right.getRightChild());
			right = null;
			decSize();
			if (size < alpha * maxSize) {
				root = rebuild(size, root);
				maxSize = size;
			}
			return true;
		}
		if (right == null) {
			System.out.println(node.getKey() + " has been deleted and " + left.getKey() + " took its place");
			node.setKey(left.getKey());
			node.setLeftChild(left.getLeftChild());
			node.setRightChild(left.getRightChild());
			left = null;
			decSize();
			if (size < alpha * maxSize) {
				root = rebuild(size, root);
				maxSize = size;
			}
			return true;
		}
		if (right != null && left != null) {
			Node rightChildMin = getMin(right);
			System.out.println(node.getKey() + " has been deleted and " + rightChildMin.getKey() + " took its place");
			node.setKey(rightChildMin.getKey());
			rightChildMin = null;
			decSize();
			if (size < alpha * maxSize) {
				root = rebuild(size, root);
				maxSize = size;
			}
			return true;
		}
		return false;
	}

	public boolean toDeep(Node n) {
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

	public Node findscapegoat(Node x, Node n) {
		//System.out.println("finding scape from: " + x.getKey() + " going towards " + n.getKey() );
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
				//System.out.println("scape is: " + x.getKey());
				return x;
			}
		}
		if (x.getKey() > n.getKey()) {
			if (leftsize <= (alpha * x.getSize()) && rightsize <= (alpha * x.getSize())) {
				parent = x;
				return findscapegoat(left, n);
			}
			else {
				//System.out.println("scape is: " + x.getKey());
				return x;
			}		
		}
		System.out.println("returning null");
		return null;
	}
	
	public void search(int key) {
		if (search(key, root)) {
			System.out.println(key + " found");
		}
		else {
			System.out.println(key + " not found");
		}
	}

	public boolean search(int key, Node node) {
		if (node == null) {
			return false;
		}
		if (key == node.getKey()) {
			return true;
		}
		if (key < node.getKey()) {
			depth++;
			Node left = node.getLeftChild();
			return search(key, left);
		}
		if (key > node.getKey()) {
			depth++;
			Node right = node.getRightChild();
			return search(key, right);
		}
		return false;
	}

	//Noget galt i BuildTree
	public Node buildTree(double subSize, Node  x) {
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

	public Node rebuild(double subSize, Node scapegoat) {
		System.out.println("rebuild with size: " + subSize + " and node: " + scapegoat.getKey());
		Node w = new Node();
		Node z = flatten(scapegoat, w);
		buildTree(subSize, z);
		return w.getLeftChild();
	}

	public Node flatten(Node x, Node y) {
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

	public void printTree() {
		printTree(root);
	}

	public void printTree(Node x) {
		if (x != null) {
			System.out.println("hit: " + x.getKey());
		}
		if (x.getLeftChild() != null) {
			System.out.println("Go left");
			printTree(x.getLeftChild());
		}
		if (x.getRightChild() != null) {
			System.out.println("Go right");
			printTree(x.getRightChild());
		}
	}

	public void incSize() {
		size++;
	}

	public void decSize() {
		size--;
	}

	public int getSize() {
		return size;
	}

	public void setAlpha(double a) {
		alpha = Math.max(a, 0.5);
		alpha = Math.min(1, alpha);
	}
}