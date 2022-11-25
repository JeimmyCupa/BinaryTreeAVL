package test;
import structure.BinaryTree;
public class Test {
	private BinaryTree<Integer> objBinaryTree;
	public Test() {
		objBinaryTree = new BinaryTree<Integer>((x,y)->x.compareTo(y));
	}
	public void init() {
		
		/*
		objBinaryTree.insertValue(15);
		objBinaryTree.insertValue(8);
		objBinaryTree.insertValue(17);
		objBinaryTree.insertValue(18);
		objBinaryTree.insertValue(2);
		objBinaryTree.insertValue(9);
		objBinaryTree.insertValue(10);
		objBinaryTree.insertValue(22);
		objBinaryTree.insertValue(1);
		objBinaryTree.insertValue(4);
		objBinaryTree.insertValue(16);
		*/
		/*
		objBinaryTree.insertValue(70);
		objBinaryTree.insertValue(43);
		objBinaryTree.insertValue(90);
		objBinaryTree.insertValue(21);
		objBinaryTree.insertValue(58);
		objBinaryTree.insertValue(80);
		objBinaryTree.insertValue(110);
		objBinaryTree.insertValue(62);
		objBinaryTree.insertValue(72);
		objBinaryTree.insertValue(88);
		objBinaryTree.insertValue(95);
		objBinaryTree.insertValue(85);
		*/
		
		
		objBinaryTree.insertValue(2);
		objBinaryTree.insertValue(5);
		objBinaryTree.insertValue(6);
		objBinaryTree.insertValue(10);
		objBinaryTree.insertValue(15);
		/*
		objBinaryTree.insertValue(7);
		objBinaryTree.insertValue(53);
		objBinaryTree.insertValue(6);
		objBinaryTree.insertValue(9);
		objBinaryTree.insertValue(1);
		objBinaryTree.insertValue(8);
		objBinaryTree.insertValue(4);
		objBinaryTree.insertValue(5);
		objBinaryTree.insertValue(12);
		objBinaryTree.insertValue(10);
		objBinaryTree.insertValue(14);
		objBinaryTree.insertValue(20);
		objBinaryTree.insertValue(18);
		objBinaryTree.insertValue(24);
		objBinaryTree.insertValue(21);
		objBinaryTree.insertValue(19);
		objBinaryTree.insertValue(60);
		objBinaryTree.insertValue(55);
		objBinaryTree.insertValue(65);
		objBinaryTree.insertValue(58);
		objBinaryTree.insertValue(17);
		objBinaryTree.insertValue(16);
		objBinaryTree.insertValue(0);
		objBinaryTree.insertValue(2);
		objBinaryTree.insertValue(3);
		objBinaryTree.insertValue(30);
		objBinaryTree.insertValue(25);
		objBinaryTree.insertValue(28);
		objBinaryTree.insertValue(22);
		objBinaryTree.insertValue(32);
		objBinaryTree.insertValue(23);
		objBinaryTree.insertValue(80);
		objBinaryTree.insertValue(70);
		objBinaryTree.insertValue(40);
		objBinaryTree.insertValue(54);
		objBinaryTree.insertValue(90);
		objBinaryTree.insertValue(75);
		objBinaryTree.insertValue(96);
		/*
		System.out.println(objBinaryTree.isEmpty());
		System.out.println("Buscado: "+objBinaryTree.searchValue(2));
		System.out.println(objBinaryTree.existValue(900));
		System.out.println("Preorder: "+objBinaryTree.arrayPreorder());//5, 3, 2, 7, 6, 9
		System.out.println("Inorder:  "+objBinaryTree.arrayInorder());//2, 3, 5, 6, 7, 9
		System.out.println("Postorder:"+objBinaryTree.arrayPostorder());//2, 3, 6, 9, 7, 5
		*/
		System.out.println(objBinaryTree.toString());
		
		
		
		
		objBinaryTree.removeValue(5);
		//objBinaryTree.insertValue(0);
		//objBinaryTree.removeValue(32);
		
		System.out.println("/////////////");
		System.out.println(objBinaryTree.toString());
	}
	public static void main(String[] args) {
		Test test = new Test();
		test.init();
	}
}
