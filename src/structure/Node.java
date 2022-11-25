package structure;

public class Node<T> {
	private T value;
	private int fe;
	private Node<T> right;
	private Node<T> left;
	public Node(T value){
		this.value = value;
		this.right= null;
		this.left = null;
		this.fe = 0;
	}
	public void setFe(int fe) {
		this.fe = fe;
	}
	public int getFe() {
		return fe;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public Node<T> getRight() {
		return right;
	}
	public void setRight(Node<T> right) {
		this.right = right;
	}
	public Node<T> getLeft() {
		return left;
	}
	public void setLeft(Node<T> left) {
		this.left = left;
	}
	 @Override
	    public String toString(){
	    	String leftValue = "null";
	    	String rightValue = "null";
	    	if(left != null) {
	    		leftValue = this.left.toString();
	    	}
	    	if(right != null) {
	    		rightValue = this.right.toString();
	    	}
	        return "(fe "+fe+")(Izquierda: "+ leftValue + ", Valor: " + this.value+", Derecha: " + rightValue +")";
	    }
}
