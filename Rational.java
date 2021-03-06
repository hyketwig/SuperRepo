//Jannie Li
//APCS1 pd10
//HW45 -- Come Together
//2015-12-9

public class Rational implements Comparable {

    // INSTANCE VARS
    // ===========================================
    private int num;
    private int den;

    
    /*=====================================
      ACCESSORS
      =====================================*/
    public float getValue() {
	return floatValue();
    }

    
    // CONSTRUCTORS
    // ===========================================
    //default constructor
    public Rational(){
	num = 0;
	den = 1;
    }
	
     //overloaded constructor: checks for division by 0
    public Rational(int n, int d){
	this();
	if (d == 0) {
	    System.out.print ("MathError: Division by 0.");
	}
	else {
	    num = n;
	    den = d;
	}
    }


    // METHODS
    // ===========================================

    //prints rational number with newlines
    public String toString(){
	return num + "/" + den;
    }

    
    //float approximation of rational number
    public float floatValue(){
	return (float)(num)/(den);
	//Casting has precedence to division.
    }

    
    //multiplication
    public void multiply (Rational n){
        num *= n.num;
	den *= n.den;
	//Needn't worry about division by 0, param is also rational.
    }
    
	
    //division
    public void divide(Rational n){
	if (n.num == 0){
	    System.out.println("MathError: Division by 0");
	    return;
	}
	num *= n.den;
	den *= n.num;
    }


    //overrides equals method
    public boolean equals(Object o){

	//null? error!
	if (o == null) {
	    throw new NullPointerException(".equals() Input null");
	}

	//both Rational objects? if not, error!
	if (! (o instanceof Rational) ) {
	    throw new ClassCastException(".equals() Input not a Rational");
	}
	
    	boolean retVal = this == o;
    	if (!retVal){
	    if (o instanceof Rational){
		retVal = this.num == ((Rational)o).num
		    && this.den == ((Rational)o).den;
	    }
	}
    	return retVal;
    }

    // calc gcd of two ints
    public static int gcd(int a, int b){
	// deal with negatives
	a = Math.abs(a);
	b = Math.abs(b);
		
        while (b != 0) {
            int num = b;
            b = a % b;
            a = num;
        }
        return a;
    }


    // return gcd of numerator and denominator
    public int gcd(){
	return gcd(num,den);
    }


    // simplify the numerator and denominator of a Rational object
    public static void reduce(Rational n){
	int gcd = gcd(n.num, n.den);
	n.num /= gcd;
	n.den /= gcd;
	if(n.den < 0){
		n.num *= -1;
		n.den *= -1;
		//How in the world is it so compact? Well, if den (and num) is (are) < 0
		//This will make n all positive. If only den is less than 0, sign flips to numerator.
		//If both are positive, w/e.
	}
    }


    // add to another Rational object
    public void add(Rational a){
	num = num * a.den + den * a.num;
	den *= a.den;
	reduce(this);
    }


    // subtract another Rational object
    public void subtract(Rational a){
	num = num * a.den - den * a.num;
	den *= a.den;
	reduce(this);
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Comparable objects is greater
      pre:  other is instance of class Comparable
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {

	//null? error!
	//(placed before instanceof bc null would set that off too...?)
	if (other == null) {
	    throw new NullPointerException(".compareTo() Input null");
	}

	//both Comparable objects? if not, error!
	if (!(other instanceof Comparable) )  {
	    throw new ClassCastException(".compareTo() Input not a Comparable");
	}
	
	//return difference
	//check for what kind of Comparable to retrieve value
	float diff;
	
	if (other instanceof Rational) {
	    diff = this.getValue() - ((Rational)other).getValue();

	}
	else if (other instanceof Binary) {
	    diff = this.getValue() - ((Binary)other).getValue();
	}
	else {
	    diff = this.getValue() - ((Hexadecimal)other).getValue();
	}
	
	if (diff == 0) {
	    return 0;
	}
	else if (diff > 0) {
	    return 1;
	}
	return -1;
    }


    public static void main(String[] args){

	Rational z = new Rational(1,2);
	Rational y = new Rational(1,3);
	Rational x = new Rational(6,9);
	reduce(x);
	System.out.println(x);
	z.add(y);
	System.out.println(z);
	z.subtract(y);
	System.out.println(z);

	Rational d = new Rational(2,4);
	Rational e = new Rational(3,6);
	System.out.println(d.compareTo(e));
	
	Rational a = new Rational(-3,7);
	Rational b = new Rational(2,-4);
	System.out.println(a.compareTo(b));
	
    }

}
