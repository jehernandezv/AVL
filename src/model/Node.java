package model;

public class Node {

	private int information;
	private Node left;
	private Node right;
	
	public Node(int information) {
		this.information = information;
	}

	public int getInformation() {
		return information;
	}

	public void setInformation(int information) {
		this.information = information;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
	
	public int calculateBalance(){
		return this.valueHeigth(this.left) - this.valueHeigth(this.right);
	}
	
	
	public int valueHeigth(Node node){
		int value = 0;
		Node actual = node;
		
			while(actual != null){
				if(actual.getLeft() != null && actual.getRight() != null){ //tiene 2 hijos
					value ++;
					if(actual.getLeft() != null && actual.getRight() == null){
						actual = actual.getLeft();
					} else{
						actual = actual.getRight();
	
					}
					
				}else if(actual.getLeft() != null && actual.getRight() == null){
					value ++;
					actual = actual.getLeft();
				}else {
					value ++;
					actual = actual.getRight();
				}
			}	
		
		actual = null;
	  
		return value;
	}
	
	
//	public int heigth(){
//		int value = 0;
//		if(this.left != null || this.right != null){
//			int valueLeft = (this.left != null)? this.left.heigth() : 0;
//			int valueRigth = (this.right != null)? this.right.heigth() : 0;
//			value = 1 + ((valueLeft <= valueRigth) ? valueRigth : valueLeft);
//		}
//		return value;
//	}
//	
//	public int factorBalance(){
//		int value = 0;
//		if(this.left != null && this.right != null){
//			value = this.left.heigth() - this.right.heigth();
//		}
//		return value;
//	}
	
//	public int valueHeigthLeft(){
//		int value = 0;
//		Node actual = this;
//		if(actual.getLeft() != null){
//			actual = actual.getLeft();
//			
//			while(actual != null){
//				if(actual.getLeft() != null && actual.getRight() != null){ //tiene 2 hijos
//					value ++;
//					if(actual.getLeft() != null && actual.getRight() == null){
//						actual = actual.getLeft();
//						
//					} else{
//						actual = actual.getRight();
//	
//					}
//					
//				}else if(actual.getLeft() != null && actual.getRight() == null){
//					value ++;
//					actual = actual.getLeft();
//				}else {
//					value ++;
//					actual = actual.getRight();
//				}
//			}	
//		}
//		actual = null;
//	  
//		return value;
//	}
//	
	 
	
	@Override
	public String toString() {
		return String.valueOf(information);
	}
}