/**************************************
* Henrik Schulz - 2016                *
* DM803 - Advanced Datastructures     *
**************************************/

public class Program{
	public static void main(String args[]) {
		ScapegoatTree tree = new ScapegoatTree(0.5);
		tree.insert(24);
		tree.insert(11);
		tree.insert(15);
		tree.insert(26);
		tree.insert(32);
		tree.insert(16);
		tree.insert(27);
		tree.insert(25);
		tree.insert(14);
		tree.printTree();
	}
}