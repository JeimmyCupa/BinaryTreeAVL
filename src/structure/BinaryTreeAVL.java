package structure;

import java.util.ArrayList;
import java.util.Comparator;

public class BinaryTreeAVL<T>{
	private Node<T> root;
	private Comparator<T> comparator;
	public BinaryTreeAVL(Comparator<T> comparator) {
		this.root = null;
		this.comparator = comparator;
	}
	public void insertValue(T value) {
		
		if(this.isEmpty()) {
			this.root = new Node<T>(value);
		}else {
			root = insert(root, value);
		}
	}
	
	public Node<T> insert(Node<T> auxRoot,T value) { // aux 8 
		if(comparator.compare(value,auxRoot.getValue()) < 0) {
			if(auxRoot.getLeft() == null) {
				auxRoot.setLeft(new Node<T>(value));
			}else
				auxRoot.setLeft(insert(auxRoot.getLeft(),value));
		}else if((comparator.compare(value,auxRoot.getValue()) > 0)){
			if(auxRoot.getRight() == null) {
				auxRoot.setRight(new Node<T>(value));
			}else
				auxRoot.setRight(insert(auxRoot.getRight(),value));
		}
		auxRoot.setFe(level(auxRoot.getRight())-level(auxRoot.getLeft()));
		auxRoot = this.balanceTree(auxRoot);
		return auxRoot;
	}

	public int level(Node<T> auxRoot) {
		int levelRight = 0;
		int levelLeft = 0;
		if(auxRoot != null) {
			levelRight += level(auxRoot.getRight())+1;
			levelLeft += level(auxRoot.getLeft())+1;
		}
		return levelRight>levelLeft ? levelRight:levelLeft;
	}
	public int calculeFe(Node<T> node) {
		return level(node.getRight())-level(node.getLeft());
	}
	public Node<T> balanceTree(Node<T> auxRoot) {
		if(auxRoot.getFe() < -1 || auxRoot.getFe()>1) {
			if(auxRoot.getFe() == -2) {
				if(auxRoot.getLeft().getFe() <= 0) {
					auxRoot = this.simpleII(auxRoot);
				}else if(auxRoot.getLeft().getFe()==1){
					auxRoot = this.doubleDI(auxRoot);
				}
			}else if(auxRoot.getFe() == 2) {
				if(auxRoot.getRight().getFe() >= 0) {
					auxRoot = this.simpleDD(auxRoot);
				}else if(auxRoot.getRight().getFe()==-1){
					auxRoot = this.doubleID(auxRoot);
				}
			}
		}
		return auxRoot;
	}
	public Node<T> simpleII(Node<T> auxRoot) {
		Node<T> aux = auxRoot.getLeft(); 
		auxRoot.setLeft(aux.getRight());
		auxRoot.setFe(level(auxRoot.getRight())-level(auxRoot.getLeft()));
		aux.setRight(auxRoot);
		aux.setFe(level(aux.getRight())-level(aux.getLeft()));
		return aux;
	}
	public Node<T> simpleDD(Node<T> auxRoot) {
		Node<T> aux = auxRoot.getRight(); 
		auxRoot.setRight(aux.getLeft());
		auxRoot.setFe(calculeFe(auxRoot));
		aux.setLeft(auxRoot);
		aux.setFe(calculeFe(aux));
		return aux;
	}
	public Node<T> doubleID(Node<T> auxRoot) {
		Node<T> aux1 = auxRoot.getRight(); 
		Node<T> aux2 = auxRoot.getRight().getLeft(); 
		auxRoot.setRight(aux2.getLeft());
		auxRoot.setFe(calculeFe(auxRoot));
		aux2.setLeft(auxRoot);
		aux1.setLeft(aux2.getRight());
		aux1.setFe(calculeFe(aux1));
		aux2.setRight(aux1);
		aux2.setFe(calculeFe(aux2));
		return aux2;
	}
	public Node<T> doubleDI(Node<T> auxRoot) {
		Node<T> aux1 = auxRoot.getLeft(); 
		Node<T> aux2 = auxRoot.getLeft().getRight(); 
		auxRoot.setLeft(aux2.getRight());
		auxRoot.setFe(calculeFe(auxRoot));
		aux2.setRight(auxRoot);
		aux1.setRight(aux2.getLeft());
		aux1.setFe(calculeFe(aux1));
		aux2.setLeft(aux1);
		aux2.setFe(calculeFe(aux2));
		return aux2;
	}
	
	public boolean existValue(T value) {
		boolean exist = false;
		if(!isEmpty()) {
			Node<T> aux = root;
			while(aux != null && !exist) {
				if(comparator.compare(value, aux.getValue()) == 0)
					exist = true;
				else
					if(comparator.compare(value, aux.getValue()) < 0)
						aux = aux.getLeft();
					else
						aux = aux.getRight();
			}
		}
		return exist;
	}
	public void removeValue(T value) {
		if(existValue(value)) {
			if(comparator.compare(value,root.getValue()) == 0) {
				if(root.getLeft() == null && root.getRight() == null)
					root = null;
				else if(root.getLeft() != null && root.getRight() == null)
					root = root.getLeft();
				else if(root.getRight() != null && root.getLeft() == null)
					root = root.getRight();
				else {
					Node<T> mayor = node(root.getLeft());
					root.setValue(mayor.getValue());
					root =remove(root,root.getLeft(),mayor.getValue());
				}
			}else{
				if(comparator.compare(value,root.getValue()) < 0) 
					root = remove(root, root.getLeft(), value);
				else if(comparator.compare(value,root.getValue()) > 0)
					root = remove(root, root.getRight(), value);
			}
			if(root != null) {
				root.setFe(calculeFe(root));
				root = this.balanceTree(root);
			}
			
		}
	}
	public Node<T> remove(Node<T> padre, Node<T> aux,T value) {
		Node<T> auxNode = null;
		if(comparator.compare(value,aux.getValue()) == 0) {
			//Caso 3
			if(aux.getLeft() != null && aux.getRight() != null) {
				Node<T> mayor = this.node(aux.getLeft());//mayor
				aux.setValue(mayor.getValue());
				auxNode = remove(aux,aux.getLeft(),mayor.getValue());
			}//caso 2
			else if(aux.getLeft() != null && aux.getRight() == null) {
				if(comparator.compare(padre.getValue(), aux.getLeft().getValue()) > 0)
					padre.setLeft(aux.getLeft());
				else
					padre.setRight(aux.getLeft());
			}else if(aux.getRight() != null && aux.getLeft() == null) {
				if(comparator.compare(padre.getValue(), aux.getRight().getValue()) > 0)
					padre.setLeft(aux.getRight());
				else
					padre.setRight(aux.getRight());
			}else {
				//caso 1
				if(padre.getLeft() != null) {
					if(comparator.compare(value,padre.getLeft().getValue()) == 0)
						padre.setLeft(null);
				}
				if(padre.getRight() != null) {
					if(comparator.compare(value,padre.getRight().getValue()) == 0)
						padre.setRight(null);
				}	
			}
		}else if(comparator.compare(value,aux.getValue()) < 0) {
			auxNode = remove(aux,aux.getLeft(),value);	
		}else if(comparator.compare(value,aux.getValue()) > 0) {
			auxNode = remove(aux,aux.getRight(),value);	
		}
		if(auxNode != null) {
		if(comparator.compare(padre.getValue(),auxNode.getValue()) < 0)
			padre.setRight(auxNode);
		else
			padre.setLeft(auxNode);
		}
		padre.setFe(calculeFe(padre));
		padre = this.balanceTree(padre);
		return padre;
	}
	
	public Node<T> node(Node<T> auxRoot) {
		Node<T> mayor = auxRoot;
		if(auxRoot != null) {
			if(auxRoot.getRight() != null)
				mayor = node(auxRoot.getRight());
		}
		return mayor;
	}

	public T searchValue(T value) {
		
			boolean exist = false;
			Node<T> temp = root;
			T valueFound = null;
			while(temp != null && !exist) {
				if(comparator.compare(temp.getValue(),value)== 0) {
					valueFound = temp.getValue();
					exist = true;
				}
				else {
					if(comparator.compare(value,temp.getValue()) < 0)
						temp = temp.getLeft();
					else
						temp = temp.getRight();
				}		
			}	
			return valueFound;
	}
	
	public boolean isEmpty() {
		return this.root == null;
	}
	
	//Metodos de recorridos de profundidad
	public ArrayList<T> arrayPreorder(){
		ArrayList<T> arrayPreorder = new ArrayList<T>();
		if(!isEmpty()) {
			arrayPreorder = preorder(root);
		}
		return arrayPreorder;
	}
	public ArrayList<T> preorder(Node<T> auxRoot){
		ArrayList<T> arrayPreorder = new ArrayList<T>();
		if(auxRoot != null) {
			arrayPreorder.add(auxRoot.getValue());
			arrayPreorder.addAll(preorder(auxRoot.getLeft()));
			arrayPreorder.addAll(preorder(auxRoot.getRight()));
		}
		return arrayPreorder;
	}
	
	public ArrayList<T> arrayInorder(){
		ArrayList<T> arrayInorder = new ArrayList<T>();
		if(!isEmpty())
			arrayInorder = inorder(root);
		return arrayInorder;
	}
	public ArrayList<T> inorder(Node<T> auxRoot){
		ArrayList<T> arrayInorder = new ArrayList<T>();
		if(auxRoot != null) {
			arrayInorder.addAll(inorder(auxRoot.getLeft()));
			arrayInorder.add(auxRoot.getValue());
			arrayInorder.addAll(inorder(auxRoot.getRight()));
		}
		return arrayInorder;
	}
	
	public ArrayList<T> arrayPostorder(){
		ArrayList<T> arrayPostorder = new ArrayList<T>();
		if(!isEmpty())
			arrayPostorder = postorder(root);
		return arrayPostorder;
	}
	public ArrayList<T> postorder(Node<T> auxRoot){
		ArrayList<T> arrayPostorder = new ArrayList<T>();
		if(auxRoot != null) {
			arrayPostorder.addAll(postorder(auxRoot.getLeft()));
			arrayPostorder.addAll(postorder(auxRoot.getRight()));
			arrayPostorder.add(auxRoot.getValue());
		}
		return arrayPostorder;
	}
	public ArrayList<Node<T>> arrayNodeInorder(){
		ArrayList<Node<T>> arrayInorder = new ArrayList<Node<T>>();
		if(!isEmpty())
			arrayInorder = nodeinorder(root);
		return arrayInorder;
	}
	public ArrayList<Node<T>> nodeinorder(Node<T> auxRoot){
		ArrayList<Node<T>> arrayInorder = new ArrayList<Node<T>>();
		if(auxRoot != null) {
			arrayInorder.addAll(nodeinorder(auxRoot.getLeft()));
			arrayInorder.add(auxRoot);
			arrayInorder.addAll(nodeinorder(auxRoot.getRight()));
		}
		return arrayInorder;
	}
	@Override
	public String toString() {
		return toString(root, 0);
	}

	public String toString(Node<T> aux, int level) {
		StringBuilder txt = new StringBuilder();
		if(aux != null) {
			txt.append(toString(aux.getRight(), level+1) + "\n");
			if(level != 0) {
				for(int i = 0; i < level-1; i++)
					txt.append("|\t");
				txt.append("[-----> " + aux.getValue() + "(" + aux.getFe() + ")");
			} else
				txt.append(aux.getValue() + "(" + aux.getFe() + ")");
			txt.append(toString(aux.getLeft(), level+1));
		}
		return txt.toString();	
	}
}
