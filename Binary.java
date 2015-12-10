//Jannie Li
//APCS pd10
//HW45 -- Come Together
//2015-12-9


//skeleton file for class Binary

public class Binary implements Comparable {

    private int _decNum;
    private String _binNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _binNum to "0"
      =====================================*/
    public Binary() { 
        _decNum = 0;
	_binNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _binNum to equiv string of bits
      =====================================*/
    public Binary( int n ) {
        _decNum = n;
	_binNum = decToBin(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _binNum to input, _decNum to decimal equiv
      =====================================*/
    public Binary( String s ) {
        _binNum = s;
	_decNum = binToDec(s);
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
	return _binNum;  
    }


    /*=====================================
      String decToBin(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToBin(0) -> "0"
      decToBin(1) -> "1"
      decToBin(2) -> "10"
      decToBin(3) -> "11"
      decToBin(14) -> "1110"
      =====================================*/
    public static String decToBin( int n ) {
        String ret = "";

	//add remainder with 2 to ret backwards until n = 0 or 1
	//then just add it on
	while (n > 1) {
	    ret = (n % 2) + ret;
	    n /= 2;
	}

	return n + ret;
    }


    /*=====================================
      String decToBinR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToBinR(0) -> "0"
      decToBinR(1) -> "1"
      decToBinR(2) -> "10"
      decToBinR(3) -> "11"
      decToBinR(14) -> "1110"
      =====================================*/
    public static String decToBinR( int n ) {	
	//until n == 0, add remainder with 2 backward and recurse on quotient	
	if (n <= 1) {
	    return n + "";
	    //this will also make the ints into a String: n + ""
	}
	
	return decToBinR(n / 2) + (n % 2); 
    }


    /*=====================================
      String binToDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      binToDec("0") -> 0
      binToDec("1") -> 1
      binToDec("10") -> 2
      binToDec("11") -> 3
      binToDec("1110") -> 14
      =====================================*/
    public static int binToDec( String s ) {
        int dec = 0;

	//multiply each digit by 2^index (proper power of 2)
	//add them for dec
	for (int i = 0 ; i < s.length() ; i++) {
	    dec += Integer.parseInt(s.substring(i,i+1)) * Math.pow(2,i);
	}

	return dec;
    }


    /*=====================================
      String binToDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      binToDecR("0") -> 0
      binToDecR("1") -> 1
      binToDecR("10") -> 2
      binToDecR("11") -> 3
      binToDecR("1110") -> 14
      =====================================*/
    public static int binToDecR( String s ) { 
        if (s.length() == 0) {
	    return 0;
	}

	//multiply each by 2^index (equal to s.length in this case)
	//add them for dec recursively by calling s with the first digit
	//removed (it was just used)
	return (Integer.parseInt(s.substring(0,1)) * (int)Math.pow(2,s.length() - 1))
	    + binToDecR(s.substring(1));
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Binary
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/
    public boolean equals( Object other ) {

	//null? error!
	if (other == null) {
	    throw new NullPointerException(".equals() Input null");
	}

	//both Binary objects? if not, error!
	if (!(other instanceof Binary) )  {
	    throw new ClassCastException(".equals() Input not a Binary");
	}

	//aliases?
	boolean ret = this == other;

	//values equal?
	if (!ret) {
	    ret = this._decNum == ((Binary)other)._decNum; 
	    //and/or... this._binNum.equals((Binary)other)._binNum;
	}
	
	return ret;
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Binary objects is greater
      pre:  other is instance of class Binary
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {

	//null? error!
	//(placed before instanceof bc null would set that off too...?)
	if (other == null) {
	    throw new NullPointerException(".compareTo() Input null");
	}

	//both Binary objects? if not, error!
	if (!(other instanceof Binary) )  {
	    throw new ClassCastException(".compareTo() Input not a Binary");
	}
	
	//return difference
	return this._decNum - ((Binary)other)._decNum;
    }


    //main method for testing
    public static void main( String[] args ) {


	  System.out.println();
	  System.out.println( "Testing ..." );

	  Binary b1 = new Binary(5);
	  Binary b2 = new Binary(5);
	  Binary b3 = b1;
	  Binary b4 = new Binary(7);

	  System.out.println( b1 );
	  System.out.println( b2 );
	  System.out.println( b3 );       
	  System.out.println( b4 );       

	  System.out.println( "\n==..." );
	  System.out.println( b1 == b2 ); //should be false
	  System.out.println( b1 == b3 ); //should be true

	  System.out.println( "\n.equals()..." );
	  System.out.println( b1.equals(b2) ); //should be true
	  System.out.println( b1.equals(b3) ); //should be true
	  System.out.println( b3.equals(b1) ); //should be true
	  System.out.println( b4.equals(b2) ); //should be false
	  System.out.println( b1.equals(b4) ); //should be false

	  System.out.println( "\n.compareTo..." );
	  System.out.println( b1.compareTo(b2) ); //should be 0
	  System.out.println( b1.compareTo(b3) ); //should be 0
	  System.out.println( b1.compareTo(b4) ); //should be neg
	  System.out.println( b4.compareTo(b1) ); //should be pos

	  
	  System.out.println("\nConverters...");
	  
	  Binary b5 = new Binary("101");
	  Binary b6 = new Binary("111");

	  System.out.println( b5._decNum ); //should be 5
	  System.out.println( b6._decNum ); //should be 7

	  System.out.println( decToBinR(21) ); //should be 10101
	  System.out.println( decToBinR(31) ); //should be 11111
	  System.out.println( binToDecR("10101") ); //should be 21
	  System.out.println( binToDecR("11111") ); //should be 31


	  System.out.println("\nErrors...");

	  Object b7 = new Object();

	  System.out.println( b6.equals(b7) );
	  //System.out.println( b6.compareTo(b7) );

	  //System.out.println( b6.equals(null) );
	  //System.out.println( b6.compareTo(null) );
	  /*=========================================	  
	  =========================================*/

    }//end main()

} //end class
