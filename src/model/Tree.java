package model;

public class Tree {

	private Node root;

	public void add(Node node) {
		if (root != null) {
			add(root, node);
		}else {
			root = node;
		}
	}

	private void add(Node base, Node node) {
		if (node.getInformation() < base.getInformation()) {
			if (base.getLeft() != null) {
				add(base.getLeft(), node);
			}else {
				base.setLeft(node);
			}
		}else {
			if (base.getRight() != null) {
				add(base.getRight(), node);
			}else {
				base.setRight(node);
			}
		}
	}

	public void delete(int info) {
		delete(null, root, info);
	}

	public void delete(Node father, Node actual, int info) {
		if (actual.getInformation() == info) {
			if (isComplete(actual)) {
				deleteComplete(actual);
			}else if (hasOneChildren(actual)) {
				deleteOneChild(father, actual);
			}else {
				deleteLeaf(father, actual);
			}
		}else {
			if (info < actual.getInformation()) {
				delete(actual, actual.getLeft(), info);
			}else {
				delete(actual, actual.getRight(), info);
			}
		}
	}

	private void deleteComplete(Node actual) {
		Node maxLeft = getMaxNode(actual.getLeft());
		Node minRight = getMinNode(actual.getRight());
		int data = (Math.abs(maxLeft.getInformation() - actual.getInformation()) 
				< Math.abs(minRight.getInformation() - actual.getInformation())) ?
					maxLeft.getInformation(): minRight.getInformation();
		delete(data);
		actual.setInformation(data);
	}
	
	
	
	
	public Node getMinNode(Node base) {
		Node actual = base;
		while (actual.getLeft() != null) {
			actual = actual.getLeft();
		}
		return actual;
	}
	
	public Node getMaxNode(Node base) {
		Node actual = base;
		while (actual.getRight() != null) {
			actual = actual.getRight();
		}
		return actual;
	}
	
	public void rotationLL(Node node){
		Node auxNode = new Node(node.getInformation());
		auxNode.setRight(null);
		node.getRight().setLeft(auxNode);
		this.delete(node.getInformation());
	}
	
	public void rotation(Node node){
		//Node node = this.root;
		if(node.calculateBalance() < -1 && node.getRight().calculateBalance() > 0){
			this.rotationRL(node);
		}else if(node.calculateBalance() < -1 && node.getRight().calculateBalance() < 0){
			this.rotationLL(node);
		}else if(node.calculateBalance() > 1 && node.getLeft().calculateBalance() < 0){
			this.rotationLR(node);
		}else{
			this.rotationRR(node);
			
		}
	}
	
	public void rotationRR(Node node){
		Node auxNode = new Node(node.getInformation());
		auxNode.setLeft(null);
		node.getLeft().setRight(auxNode);
		this.delete(node.getInformation());
	}
	
	public void rotationLR(Node node){
		int auxInfo = node.getLeft().getInformation();
		node.getLeft().setInformation(node.getLeft().getRight().getInformation());
		node.getLeft().setLeft(new Node(auxInfo));
		node.getLeft().setRight(null);
		this.rotationRR(node);
	}
	
	public void rotationRL(Node node){
		int auxInfo = node.getRight().getInformation();
		node.getRight().setInformation(node.getRight().getLeft().getInformation());
		node.getRight().setRight(new Node(auxInfo));
		node.getRight().setLeft(null);
		this.rotationLL(node);
	}

	private void deleteOneChild(Node father, Node actual) {
		if (actual == root) {
			root = getOneChild(actual);
		}else if (father.getLeft().equals(actual)) {
			father.setLeft(getOneChild(actual));
		}else {
			father.setRight(getOneChild(actual));
		}
	}

	private void deleteLeaf(Node father, Node actual) {
		if (father == null) {
			root = null;
		}else if (father.getLeft() != null && father.getLeft().equals(actual)) {
			father.setLeft(null);
		}else {
			father.setRight(null);
		}
	}
	
	private Node getOneChild(Node actual) {
		return actual.getLeft() != null ? actual.getLeft() : actual.getRight();
	}

	private boolean hasOneChildren(Node actual) {
		return actual.getLeft() != null || actual.getRight() != null;
	}

	private boolean isComplete(Node actual) {
		return actual.getLeft() != null && actual.getRight() != null;
	}

	public void print() {
		System.out.println("------------------");
		print(root);
	}

	private void print(Node node) {
		if(node != null) {
			System.out.println(node.getInformation());
			print(node.getLeft());
			print(node.getRight());
		}
	}
	
	public Node getRoot() {
		return root;
	}
}
