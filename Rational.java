/*General Principles for Rational
 * 1) Internally, we will represent every Rational object with numerator and denominator integers only.  We will not use
 *    real number values (double) because they are imprecise
 * 2) Internally, we will ensure that our Rational object is ALWAYS in a "good state"  (no denominator of 0)
 * 3) Internally, we will keep the numerator and denominator in their most reduced forms.
 * 4) We will make our Rational objects basically immutable.  (no public methods will modify the numerator and denominator
 *      other than the Constructors)
 **/
public class Rational
{
    
    /* 1)  Create the instance variables here.  You should have only 2*/
    private int numerator;
    private int denominator;
    // We will write 3 Constructors so that we can create Rational objects using 
    // a) numerator and denominator,
    // b) a whole number and a numerator and denominator, or 
    // c) a whole number only.  
    // We can have 3 different constructors as long as they
    // take different parameters (different number or type of parameters.  
    // This is called overloading the Constructor, and it is an example of method overloading.
      
    public Rational(int numerator, int denominator)
    {
        if (denominator == 0)
        {
            throw new IllegalStateException("Denominator cannot be zero.");
        }
        /* 2) Assign values to the instance variables */ 
        this.numerator=numerator;
        this.denominator=denominator;
        //Every time we construct a Rational object, we will reduce it.
        reduce();
    }
    
    public Rational(int wholeNumber)
    {
        //this() is used to call another version of the constructor.  
        //In this case, we are calling the main Constructor (above) sending wholeNumber as the numerator 
        //and 1 as the denominator. 
        this(wholeNumber,1);
    }
   
    //if the user sends 2 and 3/4 - store 11/4   This is (2*4) +3 for the numerator, and 4 for the denominator
    public Rational(int whole, int numer, int denom)
    {
        //this() is used to call another version of the constructor.
        /* 3)  Make a call to the constructor using this (see above code as an example) to set the numerator and
         * denominator appropriately
         */
        //use the absolute values of the whole, numer, and denom to computer the numerator and denominator of the improper fraction.
        this(Math.abs(whole) * Math.abs(denom) + Math.abs(numer) , Math.abs(denom) );
        //if the whole number is non zero AND either denominator is negative or the numerator is negative
        if (whole != 0 && (denom < 0 || numer < 0))
            throw new IllegalArgumentException("numerator and denominator values must be positive when a non-zero whole number is provided");
        //and then apply the negative at the very end if necessary
        if (whole < 0)
            numerator *= -1;
    }
    //This method can be static because it does not use or depend on any instance data.
    //This method will actually call itself!
    private static int gcd(int a, int b)
    {
        /* ALGORITHM: euclid's algorithm for computing GCD.  (you do not need to know this for this course)
         * if b is 0 then the gcd is a
         * otherwise, the gcd is the gcd of b and the remainder that you get
         * when you divide a by b.
         * See: https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/the-euclidean-algorithm
         */
        if (b == 0)
            return a;
        else return gcd (b, a % b);
    }    
      
    //this method does modify the Rational object.  However, it does so in a controlled way AND 
    // this method is private.  So the object's data remains safe and controlled
    private void reduce() //why no parameters?
    {
        /* 4) Complete the reduce() method to reduce a rational number.
         * The result should be that the "fraction" is in reduced form.
         *    In general, both the numerator and the denominator should be divided by the gcd (of the numerator and denominator)
         * if the numerator is 0, the denominator should be 1
         * The denominator should not be negative
         */
        int divisor = gcd(numerator,denominator);
        this.numerator=numerator/divisor;
        this.denominator=denominator/divisor;
        if (denominator < 0)
        {
            denominator *= -1;
            numerator *= -1;
        }
    }    
    
    
    /* 5) Write Accessor methods for the numerator and denominator 
       call them getNumerator() and getDenominator()
       */
    public int getNumerator()
    {
        return numerator;
    }
    
     public int getDenominator()
    {
        return denominator;
    }
    /* 6) Write a toString() method that shows the numerator and denominator separated by a '/' character */
    
    public String toString()
    {
        if (denominator == 1)
            return numerator + "";
        else if (numerator > denominator)
        {
            int whol = numerator/denominator;
            int nume = numerator%denominator;
            return whol + " " + nume + "/" + denominator;
        }
        else 
         return numerator + "/" + denominator;
    }
    //now lets do the operations.  Lets start with multiply.  Its the easiest.
    
    /* Later, in some other code, multiply will be used like this: 
    //Rational one = new Rational(2,3);
    //Rational two = new Rationnal(4,5);
    //Rational three = one.multiply(two);  // will result in 8/15
    //                  ^            ^
    //                  this         rhs
    // Within our code here, one's numerator and denominator are this.numerator and this.denominator
    // This is because it is the one object that is calling the multiply method.  So one, is "this" object
    
    // two's numerator and denominator are rhs.numerator and rhs.denominator.  This is because I arbitrarily
    // decided to call the formal parameter rhs (which stands for right hand side)
    // 
    // Notice that we are not modifying either of the Rational objects (this or rhs).
    // Instead, we are bulding a new Rational object using the numerators and denominators
    // of this object and the rhs object.
    */ 
    public Rational multiply (Rational rhs)
    {
        return new Rational (this.numerator*rhs.numerator,this.denominator*rhs.denominator);  

    }
    
    /* constructs and returns a new Rational object with the numerator and denominator inverted. */
    public Rational reciprocal()
    {
        return new Rational (this.denominator,this.numerator);
    }

    /* returns a new Rational object -- the result of multiplying this by the reciprocal of rhs */ 
    public Rational divide (Rational rhs)
    {
        return new Rational(this.numerator*rhs.denominator,rhs.numerator*this.denominator);
    }
    
    /* constructs and returns a new Rational object that represents the sum of this and rhs.
     * Hint:  Find the easiest common denominator that you can.  It doesn't have to be the least common denominator.
     * When you construct the new Rational object (representing the sum), your constructor will reduce it 
     * (because that is how you coded your constructor)
     */
    public Rational add (Rational rhs)
    {
        return new Rational(this.numerator*rhs.denominator+rhs.numerator*this.denominator,this.denominator*rhs.denominator);
    }
 
    
    public Rational subtract(Rational rhs)
    {
        return new Rational(this.numerator*rhs.denominator-rhs.numerator*this.denominator,this.denominator*rhs.denominator);
        
    }
    
    //method for testing Rational objects.  Run this method from BlueJ.
    /* 11) As you are writing the above code, write code here to test that code.  Keep adding to this code
     * so that by the end, you show your tests for EVERYTHING you have written.
     * 
     * This will be the longest part of your code, even though it will likely never be used after testing.
     * 
     * Thinking about testing all of the "special" cases.  What if one of the rational objects is equal to 0?
     * In some cases the correct result, would be that an error is thrown.  You can comment these tests out so that
     * they don't run all the time, but leave them there for reference.
     */
    public static void main(String[] args)
    {
        // Add lines of code below to test all three of your Constructors.  Test any "special" cases that 
        // you can think of, including the case where the denominator is 0.  (should throw an exception)
        // Use println statements to see each result.
        // 
        Rational r1 = new Rational(1,3,4);
        System.out.println(r1);
        Rational r2 = new Rational(1,2);
        System.out.println(r2);
        Rational r3 = new Rational(7);
        System.out.println(r3);
        
        
        Rational product1 = r1.multiply(r2);
        System.out.println(product1);
        
         Rational product4 = r1.divide(r2);
        System.out.println(product4);
        
         Rational product2 = r1.add(r2);
        System.out.println(product2);
        
         Rational product3 = r1.subtract(r2);
        System.out.println(product3);
        
         
        
        // Add lines of code to test multiply, reciprocal, divide, add, and subtract. Test any "special" cases
        // that you can think of, including the case where you are dividing by the Rational object (0/1).
        // Use println statements to see each result.  (should throw an exception)
         
        //Rational product1 = r1.multiply(r2);
        //System.out.println("The product of " + r1 + " and " + r2 + " = " + product1);
        

    }
}