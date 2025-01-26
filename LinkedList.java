/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) { // Replace the following statement with your code
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node result = first;
		int counter = 0;
		while(index != counter){
			result = result.next;
			counter ++;
		}
		return result;   //index == counter and result is the node at the given index
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {		//// Write your code here
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}

		if(index == 0 || size == 0){
			addLast(block);
		} else if(index == size){
			addFirst(block);
		} else {
			Node after = getNode(index);
			Node before = getNode(index - 1);
			Node newBlock = new Node(block);
			before.next = newBlock;
			newBlock.next = after;
		}
		size ++;
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) { //// Write your code here
		if(block == null){			//null block
			return;
		}

		Node newBlock = new Node(block);
		if(last == null){			//empty list
			first = newBlock;
			last = newBlock;
			size ++;
			return;
		}

		last.next = newBlock;  		//add to the end
		size ++;
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) { //// Write your code here
		if(block == null){			//null block
			return;
		}

		Node newBlock = new Node(block);
		newBlock.next = first;
		first = newBlock;
		if(last == null){			//empty list
			last = newBlock;
		}
		size ++;
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) { //// Replace the following statement with your code
		if (index < 0 || index > size || size == 0){
			throw new IllegalArgumentException(
					"index must be between 0 and list's size");
		}

		Node result = first;
		int counter = 0; 
		while(counter != index){   	
			result = result.next;
			counter ++;
		} 
		return result.block;		//counter == index and result is the node at the given index
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) { 	//// Replace the following statement with your code
		int counter = 0;
		Node correntNode = first;
		while(correntNode != null){            		//check all the nodes in the list
			if(correntNode.block.equals(block)){
				return counter;
			}
			correntNode = correntNode.next;
			counter ++;
		}
		return -1;     //block is not in this list
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {	 //// Write your code here
		int index = indexOf(node.block);
		if(index == -1){
			throw new IllegalArgumentException(
					"node is not in this list");
		}

		if(size == 1){                 				   //node is the only one
			first = null;
			last = null;
		} else if(index == 0){           			 //node is the first
			first = first.next;
		} else if (index == (size - 1)){			//node is the last
			last = getNode(index -1);
			last.next = null;
		} else {                                     //node is somewhere in between 
			Node before = getNode(index -1);
			before.next = getNode(index +1);
		}	
		size --;
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {	//// Write your code here
		if (index < 0 || index >= size){
			throw new IllegalArgumentException(
					" NullPointerException!");
		}
	
		Node removeNode = getNode(index);
		remove(removeNode);
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {	//// Write your code here
		int index = indexOf(block);
		Node removeNode = getNode(index);
		remove(removeNode);
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {	//// Replace the following statement with your code
		if (first == null){
			return "the list is empty";
		} 
		String result = "{";
		Node corrent = first;
		for(int i = 0; i < size - 1; i++) {
			result += corrent.toString() + " ";
			corrent = corrent.next;
		}
		result += corrent.toString() + " -> null}";
		return result;
	}
}