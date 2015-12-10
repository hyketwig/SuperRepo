//Jannie Li
//APCS pd10
//HW45 -- Come Together
//2015-12-9

/*****************************
 * SKELETON for
 * class SuperArray --  A wrapper class for an array. 
 * Maintains functionality:
 *  access value at index
 *  overwrite value at index
 *  report number of meaningful items
 * Adds functionality to std Java array:
 *  resizability
 *  ability to print meaningfully
 *  add item (at end)
 *  insert item
 *  remove item (while maintaining "left-justification")
 *****************************/

public class SuperArray {
 
    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;

		
    //~~~~~METHODS~~~~~
    //default constructor – initializes 10-item array
    public SuperArray() 
    { 
	_data = new Comparable[10];
	_lastPos = -1; //flag to indicate no lastpos yet
	_size = 0;	
    }

		
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() 
    { 
	String foo = "[";
	for( int i = 0; i < _size; i++ ) {
	    foo += _data[i] + ",";
	}
	//shave off trailing comma
	if ( foo.length() > 1 )
	    foo = foo.substring( 0, foo.length()-1 );
	foo += "]";
	return foo;
    }

		
    //double capacity of this SuperArray
    private void expand() 
    { 
	Comparable[] temp = new Comparable[ _data.length * 2 ];
	for( int i = 0; i < _data.length; i++ )
	    temp[i] = _data[i];
	_data = temp;
    }

		
    //accessor -- return value at specified index
    public Comparable get( int index ) { return _data[index]; }

		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public Comparable set( int index, Comparable newVal ) 
    { 
 	Comparable temp = _data[index];
	_data[index] = newVal;
	return temp;
    }


    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( Comparable newVal )
    {
	//make sure there's room
        if (_data.length == _size) {
	    this.expand();
	}

	//add and update vars
	_data[_lastPos + 1] = newVal; 
	_lastPos += 1;
	_size = size();
    }


    //inserts an item at index
    //shifts existing elements to the right
    public void add( int index, Comparable newVal )
    {
	//make sure there's room
        if (_data.length == _size) {
	    this.expand();
	}

	//if index after lastPos, just add it last
	if (index > _lastPos) {
	    add(newVal);
	    //add already updates vars
	}

	//otherwise shift and add
	else {
	    //shift right
	    for (int i = _lastPos ; i >= index ; i--) {
		_data[i+1] = _data[i];
	    }

	    //add and update vars
	    _data[index] = newVal;
	    _lastPos += 1;
	    _size = size();
	}
    }


    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove( int index )
    {
	//shift left, item to remove doesn't matter
	for (int i = index ; i < _lastPos ; i++) {
	    _data[i] = _data[i+1];
	}

	//update vars
	_lastPos -= 1;
	_size = size();
    }


    //return number of meaningful items in _data
    public int size()
    {
        return _lastPos + 1;
    }


    //======== HW 45 ADDITIONS ========

    //returns index of first instance of Comparable
    //returns -1 if not found
    public int linSearch(Comparable num) {

	//check if each item is equal to num
	for (int i = 0; i < this._size; i++) {
	    if (this.get(i).compareTo(num) == 0) {
		return i;
	    }
	}

	return -1;
	
    }


    //returns whether SuperArray sorted least to greatest
    public boolean isSorted() {

	//check if next item is less than item here
	//if true, not sorted
	for (int i = 0; i < this._size - 1; i++) {
	    if (this.get(i).compareTo(this.get(i+1)) < 0) {
		return false;
	    }
	}

	return true;
	
    }



    
    //main method for testing
    public static void main( String[] args ) 
    {
	
        SuperArray curtis = new SuperArray();
	Binary bin1 = new Binary(22);
	Binary bin2 = new Binary("101");
	Rational rat1 = new Rational(1,3);
	Rational rat2 = new Rational(1,9);
	Hexadecimal hex1 = new Hexadecimal(39);
	Hexadecimal hex2 = new Hexadecimal("1A");
	
	
	System.out.println("Printing empty SuperArray curtis...");
	System.out.println(curtis);
	
        curtis.add(bin1);
	curtis.add(bin2);
	curtis.add(rat1);
	curtis.add(rat2);
	curtis.add(hex1);
	curtis.add(hex2);
	
	System.out.println("Printing populated SuperArray curtis...");
	System.out.println(curtis);
       
	curtis.remove(3);
	System.out.println("Printing SuperArray curtis post-remove...");
	System.out.println(curtis);
	curtis.remove(3);
	System.out.println("Printing SuperArray curtis post-remove...");
	System.out.println(curtis);


	Hexadecimal grr = new Hexadecimal("1FF");
	
	curtis.add(3,grr);
	System.out.println("Printing SuperArray curtis post-insert...");
	System.out.println(curtis);
	curtis.add(2,grr);
	System.out.println("Printing SuperArray curtis post-insert...");
	System.out.println(curtis);
	curtis.add(1,new Hexadecimal("1FF"));
	System.out.println("Printing SuperArray curtis post-insert...");
	System.out.println(curtis);

	System.out.println("Printing SuperArray curtis's size...");
	System.out.println(curtis.size());

	
	//===== testing HW45 methods =====
	System.out.println("printing SuperArray curtis's search for grr...");
	System.out.println(curtis.linSearch(grr)); //should be 1

	curtis.add(0,new Hexadecimal("1FF"));

	System.out.println("Printing SuperArray curtis...");
	System.out.println(curtis);
	
	System.out.println("printing SuperArray curtis's search for grr...");
	System.out.println(curtis.linSearch(grr)); //should be 0

	System.out.println("printing SuperArray curtis's search for a Binary...");
	System.out.println(curtis.linSearch(new Binary("1010"))); //should be -1

	System.out.println("Printing whether SuperArray curtis is sorted...");
	System.out.println(curtis.isSorted()); //should be false

	System.out.println("Printing whether SuperArray boo is sorted...");
	SuperArray boo = new SuperArray();
	boo.add(new Hexadecimal());
	boo.add(new Rational());
	boo.add(new Binary());

	System.out.println(boo.isSorted()); //should be true
	

    }//end main
		
}//end class
