package vn.edu.tdt.it.dsa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class WarehouseBook {

	protected static class WarehouseNode {
		private ProductRecord record;
		private WarehouseNode left, right;
		private int size;
		private int height;
		private int depth;
		private int balance;

		public WarehouseNode() {
			
			this.record = null;
			this.left = null;
			this.right = null;
			this.size = 1;
			this.height = 1;
			this.depth = 1;
			this.balance = 0;

		}

		public WarehouseNode(final ProductRecord record) {
			
			this.record = record;
			this.left = null;
			this.right = null;
			this.size = 1;
			this.height = 1;
			this.depth = 1;
			this.balance = 0;

		}

		public WarehouseNode(final int id, int quantity) {

			this.record = new ProductRecord(id, quantity);
			this.left = null;
			this.right = null;
			this.size = 1;
			this.height = 1;
			this.depth = 1;
			this.balance = 0;

		}

		public ProductRecord getRecord() {

			return record;
			
		}

		public WarehouseNode getNode(WarehouseNode node) {//get Node in Tree Node which inputed node usually a getRoot() that's root

			if (node.getLeft() != null) {

				return node.getNode(node.getLeft());

			}

			else if (node.getRight() != null) {

				return node.getNode(node.getRight());

			}

			else {

				return node;

			}

		}

		public WarehouseNode getNodebyDepth(int depth) {//get Node by fixed Depth

			WarehouseNode node = this;

			int cur = node.getDepth();

			if (cur != depth) {

				if (node.getLeft() != null) {
	
					return node.getLeft().getNodebyDepth(depth);

				}

				else if (node.getRight() != null) {
	
					return node.getRight().getNodebyDepth(depth);
	
				}

			}

			return node;

		}

		public int getDepth () {

			return depth;

		}

		public void setDepth (int depth) {

			this.depth = depth;
			
		}

		public int getDepth (WarehouseNode node) {

			if (node == null) {

				System.out.println("null");
				return (0);
				
			}
			
			else if (node.getLeft() != null) {
				
				System.out.println("left");
				
				
				
				return 1 + this.getDepth(node.getLeft());
				
			}
			
			System.out.println("right");

			return 1 + this.getDepth(node.getRight());

		}

		public WarehouseNode getNodebyId(int id) {

			WarehouseNode node = this;

			int checkid = node.getRecord().getProductID();

			if (checkid > id) {

				if (node.getLeft() != null) {

					return node.getLeft().getNodebyId(id);
	
				}
			
			}

			if (checkid < id) {
				
				if (node.getRight() != null) {
	
					return node.getRight().getNodebyId(id);
	
				}

			} 
			
			return node;

		}

		public void setRecord(final ProductRecord record) {
			
			this.record = record;
			
		}

		public WarehouseNode getLeft() {
			
			return left;
			
		}

		public void setLeft(final WarehouseNode left) {
			
			this.left = left;
			
		}

		public WarehouseNode getRight() {
			
			return right;
			
		}

		public void setRight(final WarehouseNode right) {
			
			this.right = right;
			
		}

		public int getSize() {
			
			if (this == null) {

				return 0;

			}
			else {

				return size;

			}
		}

		public void setSize(final int size) {

			this.size = size;
		}

		public int getHeight() {
			
			if (this == null) {

				return 0;

			}
			else {

				return height;

			}
			
		}

		public void setHeight(final int height) {

			this.height = height;
		}

		public int getBalance() {
			
			return balance;
			
		}

		public void setBalance(final int balance) {
			
			this.balance = balance;

		}

		
	}

	private WarehouseNode root;
	private int size;
	private int height;

	public int getSize() {

		if (root == null) {

			return 0;
		}

		return getSize(root);

	}

	public int getSize(WarehouseNode node) {
		
		if (node == null) {
		
			return(0); 
		
		}
		
		else { 
		
			return(getSize(node.getLeft()) + 1 + getSize(node.getRight())); 
		
		} 
		
		
	}

	public int getHeight() {

		if (root == null) {

			return 0;
		}

		return getHeight(root);

	}

	public int getHeight(WarehouseNode node) {
		
		if (node == null) {

			return (0);
		}

		int heightLeft = 0;
		int heightRight = 0;

		if(node.getLeft() != null) {

			heightLeft = getHeight(node.getLeft());
			
		}
		
		if(node.getRight() != null) {

			heightRight = getHeight(node.getRight());

		}

		node.setHeight(1 +  (heightLeft > heightRight?heightLeft:heightRight));

		return node.getHeight();
		
	}

	public WarehouseBook() {
		
		root = null;
		size = 0;

	}

	public WarehouseNode getRoot() {
		
		return root;
		
	}

	public WarehouseNode getNodebyId(WarehouseNode node, int id) {// get by Node ID

		int checkid = node.getRecord().getProductID();

		if (checkid > id) {

			if (node.getLeft() != null) {

				return node.getLeft().getNodebyId(id);

			}
		
		}

		else if (checkid < id) {
			
			if (node.getRight() != null) {

				return node.getRight().getNodebyId(id);

			}

		}

		return node;
	}

	public WarehouseNode getParentNode (WarehouseNode node, WarehouseNode child) {// get Parent WarehouseNode
		
		if (node == null) {

			return null;

		}
		
		if (node.getRecord().getProductID() > child.getRecord().getProductID()) {

			if (node.getLeft() != null) {

				if (node.getLeft().equals(child)) {

					return node;
					
				}
			
				WarehouseNode left = getParentNode(node.getLeft(), child);
				
				return left;
			}
			
		}

		if (node.getRecord().getProductID() < child.getRecord().getProductID()) {

			if (node.getRight() != null) {

				if (node.getRight().equals(child)) {

					return node;

				}
			
				WarehouseNode right = getParentNode(node.getRight(), child);

				return right;
	
			}

		}

		return node;

	}

	public WarehouseNode deleteMin(WarehouseNode node) {

		if (node.left == null) {

			return node.right;
			
		}

		node.left = deleteMin(node.left);
		
		node.setSize((node.left).getSize() + (node.right).getSize() + 1);

		node.setHeight(1 + Math.max((node.left).getHeight(), (node.right).getHeight()));
	
		return node;
		
	}

	public WarehouseNode delete(WarehouseNode node, int id) {//
		
		if (node == null) {

			return null;

		} 

		int cmp = id - node.getRecord().getProductID();

		if (cmp < 0) {

			node.setLeft(delete(node.getLeft(), id));
			
		}
		else if (cmp > 0) {

			node.setRight(delete(node.getRight(), id));

		}
		else {
			if (node.getRight() == null) {

				return node.getLeft();

			}
			if (node.getLeft() == null) {

				return node.getRight();

			}

			WarehouseNode tmp = node;

			node = getMinId(tmp.getRight(), tmp.getRight().getRecord().getProductID());
			
			node.setRight(deleteMin(tmp.getRight()));
			
			node.setLeft(tmp.getLeft());

		}

		node.setSize(getSize(node.getLeft()) + getSize(node.getRight()) + 1);

		return node;

	}

	public WarehouseNode getMinId(WarehouseNode node, int minValue) {// get minimum ID

		int value = node.getRecord().getProductID();

		if (minValue >= value) {

			if (node.getLeft() != null) {

				return getMinId(node.getLeft(), value);

			}
		
		}

		return node;

	}

	public int getDepthUtil(WarehouseNode node, int id, int depth) { // return Depth of Node with id

        if (node == null) {

            return 0; 

		}
   
        if (node.getRecord().getProductID() == id) {

            return depth; 

		}
   
        int depthlevel = getDepthUtil(node.left, id, depth + 1); 
		
		if (depthlevel != 0) {
			
            return depthlevel; 

		}
   
		depthlevel = getDepthUtil(node.right, id, depth + 1); 
		
        return depthlevel; 
    } 
   
    /* Returns depth of given id value */
    public int getDepth(WarehouseNode node, int id) { // return Depth of Node with id

		return getDepthUtil(node, id, 1); 
		
	}
	
	public WarehouseNode deleteNodefromDepth(WarehouseNode root, int depth) {

		if (root == null) { 
			return null; 
		}
		
		else {
			
			if (root.getDepth() == depth) {
			
				return null;
	
			}
		}
	
		root.left = deleteNodefromDepth(root.left, depth); 
	
		root.right = deleteNodefromDepth(root.right, depth); 
  
        return root; 
	
	}

	public WarehouseNode getNodebyHeight(WarehouseNode node, int height) {//start root and inputed height find a node that in root (not null) and inputed height reduced until 1

		if (node.getHeight() != height) {

			if (node.getLeft() != null) {

				return getNodebyHeight(node.getLeft(), height);

			}

			if (node.getRight() != null) {

				return getNodebyHeight(node.getRight(), height);
			
			}

			if (node == null) {

				return null;

			}
		
		}

		return node;
	
	}

	public WarehouseNode getNodefromRoot(WarehouseNode node, int height) {//start root and inputed height find a node that in root (not null) and inputed height reduced until 1

		if (node.getHeight() != 1) {

			if (node.getLeft() != null) {

				return getNodefromRoot(node.getLeft(), height - 1);

			}

			else if (node.getRight() != null) {

				return getNodefromRoot(node.getRight(), height - 1);
			
			}
		
			else {

				return getNodefromRoot(getParentNode(getRoot(), node), height);

			}

		}

		return node;
	
	}

	public WarehouseBook(final File file) throws IOException {
		// sinh vien viet ma tai day
		// Creates a FileReader Object

		FileReader fr = new FileReader(file);

		char[] input = new char[50];

		fr.read(input); // reads the content to the array

		String str = "";

		for(char c : input) {
			
			str += String.valueOf(c);

		}
			
		System.out.println(str.split("  ")[0]); 
		
		String[] strArr = str.replaceAll("[^\\.0123456789]"," ").split(" +");

		strArr = Arrays.copyOfRange(strArr, 1, strArr.length);

		int flag = 1;

		int count = 0;

		for (String Record : strArr) {

			int id = Integer.parseInt(String.valueOf(Record)) / 100;

			int quantity = Integer.parseInt(String.valueOf(Record)) % 100;
			
			if (flag == 1) {// take first group of numbers into root
				
				root = new WarehouseNode(id, quantity);

				count += 1;

				flag = 0;

			}

			else {

				WarehouseNode node = put(getRoot(), id, quantity);

				count += 1;

			}

		}

		fr.close();

		size = count;

	}
	
	public void save(final File file) {
		// sinh vien viet ma tai day

		try {
			FileWriter myWriter = new FileWriter(file);
			
			myWriter.write(toStringtest());
			
			myWriter.close();
			
			System.out.println("Successfully wrote to the file.");
		}
		
		catch (IOException e) {
			
			System.out.println("An error occurred.");
			
			e.printStackTrace();
		}
	}

	public int size() {
	
		return(size(root)); 
	
	}

	/**
	 * Gets the size of the given branch
	* @param node The branch to count from.		
	*/
	private int size(WarehouseNode node) { 
		if (node == null) return(0); 
		
		else { 
		
			return(size(node.left) + 1 + size(node.right)); 
		
		} 
	}

	public WarehouseNode put(WarehouseNode node, int id, int quantity) {// do action for event order 1 and 2

		if (node == null) {
			
			return new WarehouseNode(new ProductRecord(id, quantity));
			
		}
		
		int cmp = id - node.getRecord().getProductID();
		
		if (cmp < 0) {
			
			node.setLeft(put(node.getLeft(), id, quantity));			
			
		}
		
		else if (cmp > 0) {
			
			node.setRight(put(node.getRight(), id, quantity));			
			
		}
		
		else {

			node.getRecord().setQuantity(quantity);

		}
		
		node.setSize(getSize(node.getLeft()) + getSize(node.getRight()) + 1);

		int leftHeight = getHeight(node.getLeft());

		int rightHeight = getHeight(node.getRight());

		node.setHeight((leftHeight>rightHeight?leftHeight:rightHeight) + 1);

		if (id != getRoot().getRecord().getProductID()) {

			WarehouseNode parentNode = getParentNode(getRoot(), getNodebyId(getRoot(), id));
			
			getNodebyId(getRoot(), id).setDepth(getDepth(getRoot(), parentNode.getRecord().getProductID()) + 1);

		}


		return node;

	}


	//case 3

	public WarehouseNode AVL () {

		int sizeBook = getRoot().getSize();

		String[] newBook = toString().replace("  ", " ").split(" ");
		
		// get middle string for create new Root;
		int  middle = Integer.valueOf(newBook[size() / 2]);

		// take middle element in group into root
		WarehouseNode rootNode = new WarehouseNode(middle / 100, middle % 100);

		for (int i = size() / 2; i < newBook.length; i++) {

			if (i >= size() / 2 && i < newBook.length - 1) {

				newBook[i] = newBook[i + 1];

			}
		
		}

		newBook = Arrays.copyOf(newBook, newBook.length - 1);
					
		root = new WarehouseNode(rootNode.getRecord().getProductID(), rootNode.getRecord().getQuantity());

		for (String element : newBook) {
			
			int id = Integer.valueOf(element) / 100;
			
			int quantity = Integer.valueOf(element) % 100;

			WarehouseNode belongs = put(getRoot(), id, quantity);
						
		}

		return root;

	}	
	
	public WarehouseNode leftRotate(WarehouseNode node) {

		WarehouseNode alter = node.getRight();
		
		node.setRight(alter.getLeft());
				
		alter.setLeft(node);

		if (node == getRoot()) {

			root = alter;

		}
		
		//set for node
		node.setSize(1 + getSize(node.left) + getSize(node.right));
		
		node.setHeight(1 + Math.max(getHeight(node.left), getHeight(node.right)));

		
		//set for alter node
		alter.setSize(getSize(node));
		
		alter.setHeight(1 + Math.max(getHeight(alter.left), getHeight(alter.right)));

		return alter;

	}

	public WarehouseNode rightRotate(WarehouseNode node) {

		WarehouseNode alter = node.getLeft();
		
		node.setLeft(alter.getRight());
		
		alter.setRight(node);
		
		if (node == getRoot()) {

			root = alter;
			
		}

		//set for node
		node.size = 1 + size(node.left) + size(node.right);
		
		node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
		
		//set for alter node
		
		alter.setSize(getSize(node));

		alter.setHeight(1 + Math.max(getHeight(alter.left), getHeight(alter.right)));

		return alter;

	}

	public int checkBalance(WarehouseNode node) {
	
		return getHeight(node.getLeft()) - getHeight(node.getRight());
	
	}

	public WarehouseNode balance(WarehouseNode node) {

		if (checkBalance(node) < -1) {

			if (checkBalance(node.right) > 0) {

				node.right = rightRotate(node.right);

			}

			node = leftRotate(node);
			
		}

		else if (checkBalance(node) > 1) {
			
			if (checkBalance(node.left) < 0) {
			
				node.left = leftRotate(node.left);
			
			}

			node = rightRotate(node);
			
		}

		return node;
	
	}

	public void processEvents (String[] strArray){

		// System.out.println(strArray.length);
		
		for (int index = 0; index < strArray.length; index ++) {
			
			for (String element: strArray[index].split(" ")) {
			
				int id = 0;
				int cur = 0;
				int instock = 0;
				String temp = element;
				String item;
				String quantity;
				WarehouseNode product;
				char order = element.charAt(0);

				// 17234 17253 17291 17324 17419 57298 63
				
				switch(Integer.parseInt(String.valueOf(order))) {
					case 0 :
					   // Statements
					   return; // optional
					
					case 1 :
					   // Statements IMPORT	17234 17253 17291 17324 17419 17314 57298 63
						item = new String(element.substring(1, 4));
						quantity = new String(element.substring(element.length() - 1));
	
						id = Integer.parseInt(String.valueOf(item));
	
						product = getNodebyId(root, id);
	
						
						if (root == null) {
							
							instock = Integer.parseInt(String.valueOf(quantity));
							
							root = product;
						
						}
						
						else {
							
							cur = product.getRecord().getQuantity();
							
							instock = cur + Integer.parseInt(String.valueOf(quantity));
	
						}
	
						WarehouseNode added = put(root, id, Integer.parseInt(String.valueOf(instock)));
						
						break; // optional
						
					case 2 :
						// Statements ORDER
	
						item = new String(element.substring(1, 4));
	
						quantity = new String(element.substring(element.length() - 1));
						
						id = Integer.parseInt(String.valueOf(item));
	
						product = getNodebyId(root, id);
	
						cur = product.getRecord().getQuantity();
						
						instock = cur - Integer.parseInt(String.valueOf(quantity));
						
						if (instock <= 0) {
	
							System.out.println("Deleted WarehouseNode: " + getNodebyId(root, id).getRecord().toString());
							
							delete(root, id);
	
						}
	
						else {
							
							WarehouseNode ordered = put(root, id, instock);
	
						}
						
						break; // optional
					
					case 3 :
						// (72304 (N 72503 (N 72901 (N 73204 (N 74109))))) 
					   // Statements AVL simply LNR	(72909 (72503 (72304 N) 74109 (73204 N)))	17234 17253 17291 17324 17419 17314 57298 63
					   AVL();
	
					   break; // optional
					
					case 4 :
					   // Statements AVL complex NRL right rotate or left rotate	(72909 (72503 (72304 N) 74109 (73204 N)))	17234 17253 17291 17324 17419 17314 57298 63
													
						while (checkBalance(getRoot()) <-1 || checkBalance(getRoot()) > 1) {
							
							balance(getRoot());
							
						}
	
					   break; // optional
					
					   case 5 :
					   // Statements RLN specially RLN left rotate	(72909 (72503 (72304 N) 74109 (73204 N)))	17234 17253 17291 17324 17419 17314 57298 63
	
						item = new String(element.substring(1, 4));
	
						quantity = new String(element.substring(element.length() - 1));
	
						id = Integer.parseInt(String.valueOf(item));
	
						product = getNodebyId(root, id);
						
						if (product == null) {
							
							instock = Integer.parseInt(String.valueOf(quantity));//			WW
							
							product = new WarehouseNode(id, instock);
						
						}
						
						else {
	
							cur = product.getRecord().getQuantity();//		ZZ
	
							instock = cur + Integer.parseInt(String.valueOf(quantity));//			WW
	
						}
						
						// get remove as rooted node
	
						String[] newBook = toString(getRoot(), "RLN").replace("  ", " ").split(" ");
						
						for (int i = 0; i < newBook.length; i++) {
							
							if (i < newBook.length - 1) {//newBook[i + 1] != null && 
								
								newBook[i] = newBook[i + 1];
								
							}
							
						}
											
						newBook = Arrays.copyOf(newBook, newBook.length - 1);
						
						root = new WarehouseNode(product.getRecord().getProductID(), product.getRecord().getQuantity());
						
						//add the rests into Book
	
						for (String newElement : newBook) {

							id = Integer.parseInt(String.valueOf(newElement)) / 100;
							
							int numbers = Integer.parseInt(String.valueOf(newElement)) % 100;
	
							WarehouseNode belongs = put(getRoot(), id, numbers);
							
						}
	
					   break; // optional

					default : // Optional
						// Statements
	
						quantity = new String(element.substring(element.length() - 1));
	
						deleteNodefromDepth(getRoot(), Integer.parseInt(String.valueOf(quantity)));
	
				}
				
			}	

		}

	}
	 
	public void process(final File file) throws IOException {
		// sinh vien viet ma tai day
		
		//Read File to get info
		//Put info into toList as a list then change into array to get each element in there

		FileReader in = new FileReader(file);

		BufferedReader br = new BufferedReader(in);

		String line;

		ArrayList<String> toList = new ArrayList<String>();
	
		while ((line = br.readLine()) != null) {

			toList.add(line);
			
		}

		in.close();

		String[] strArray = new String[toList.size()];
		
		toList.toArray(strArray);
		
		//go to each event
		processEvents(strArray);

		System.out.println("root : " + getRoot().getRecord().toString() + " " + toStringtest());
		
		String  test = toStringtest(getRoot());


	}
	
	public void process(final List<String> events) {
		// sinh vien viet ma tai day

		//Read events to get info
		//Put info into toList as a list then change into array to get each element in there

		String[] strArray = new String[events.size()];
		
		events.toArray(strArray);

		processEvents(strArray);

		System.out.println("root : " + getRoot().getRecord().toString() + " " + toStringtest());

		String  test = toStringtest(getRoot());

		String[] str = test.replaceAll("[^\\.0123456789]"," ").split(" +");

	}

	@Override
	public String toString() {
		
		final String res = "";

		return toString(getRoot());
	
	}

	public String toStringtest() {
		
		final String res = "";

		return "(" + toStringtest(getRoot()) + ")";
	
	}

	public static String toStringtest (WarehouseNode node){

		if(node == null ){
			
			return "";
			
		}
		
		else {

			if (node.getLeft() == null && node.getRight() == null) {

				return node.getRecord().toString();
			}

			if (node.getLeft() == null && node.getRight() != null) {

				return "" + node.getRecord().toString() + " (N " + toStringtest(node.getRight()) + ")";

			}

			if (node.getRight() == null && node.getLeft() != null) {

				return "" + node.getRecord().toString() + " (" + toStringtest(node.getLeft()) + " N)" ;
				
			}

			return "" + node.getRecord().toString() + " (" + toStringtest(node.getLeft()) + " " + toStringtest(node.getRight()) + ")";

		}

	}

	public static String toString(WarehouseNode node){

		
		if(node == null){
			
			return "";
			
		}
		
		else {

			if (node.getLeft() == null) {

				return node.getRecord().toString() + " " + toString(node.getRight());

			}

			else if (node.getRight() == null) {

				return toString(node.getLeft()) + " " + node.getRecord().toString();
				
			}

			return toString(node.getLeft()) + " " + node.getRecord().toString() + " " + toString(node.getRight());

		}

	}

	public static String toString(WarehouseNode node, String option){

		if(node == null){

			return "";

		}

		else {

			if (option == "RLN") {

				if (node.getRight() == null) {

					return toString(node.getLeft(), "RLN") + " " + node.getRecord().toString();
	
				}
	
				if (node.getLeft() == null) {
	
					return toString(node.getRight(), "RLN") + " " + node.getRecord().toString();
	
				}
	
				return toString(node.getRight(), "RLN") + " " + toString(node.getLeft(), "RLN") + " " + node.getRecord().toString();	

			}

			if (option == "NRL") {

				if (node.getRight() == null) {

					return node.getRecord().toString() + toString(node.getLeft(), "NRL");
	
				}
	
				if (node.getLeft() == null) {
	
					return node.getRecord().toString() + " " + toString(node.getRight(), "NRL");
	
				}
	
				return node.getRecord().toString() + " " + toString(node.getRight(), "NRL") + " " + toString(node.getLeft(), "NRL");
				
			}

			if (node.getLeft() == null) {

				return node.getRecord().toString() + " " + toString(node.getRight());

			}

			if (node.getRight() == null) {

				return toString(node.getLeft()) + " " + node.getRecord().toString();

			}

			return toString(node.getLeft()) + " " + node.getRecord().toString() + " " + toString(node.getRight());

		}

	}

	public static void main(final String[] args) {
		// vi du ham main de chay
		
		try {
			final WarehouseBook wb = new WarehouseBook(new File("../test/warehouse.txt"));
			wb.process(new File("../test/events.txt"));
			wb.save(new File("../test/warehouse_new.txt"));

			String test = "17234 17253 17291 17324 17419 57298";

			String[] testToList = test.split(" ");
			
			List<String> listStr = new ArrayList<String>();

			for (String ele : testToList) {
				
				listStr.add(ele);
			}

			wb.process(listStr);

		} catch (final Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
