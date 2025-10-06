/**
 * Write a description of class Complex here.
 *
 * @author Val Barnes
 * @version 9/30/25
 */
public class Complex
{
    private Rational real;
    private Rational imaginary;


    public Complex(Rational real, Rational imaginary) 
    {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex(Rational real) 
    {
        this(real,new Rational(0));
    }

    public Rational getReal() 
    {
        return real;
    }

    public Rational getImaginary() {
        return imaginary;
    }

    public String toString() {
        if (real.equals(new Rational(0)))
            return imaginary + "i";

        else if (imaginary.getNumerator() > 0) 
            return real + " + " + imaginary + "i";

        else if (imaginary.getNumerator() < 0)
            return real + "" + imaginary + "i";

        else 
            return real + "";
    }

    public Complex conjugate() {
        return new Complex(this.real,this.imaginary.multiply(new Rational(-1)));
    }

    public Complex add(Complex rhs) {
        return new Complex(this.real.add(rhs.real),this.imaginary.add(rhs.imaginary));
    }

    public Complex add(Rational x) {
        return new Complex(this.real.add(x),this.imaginary);
    }

    public Complex subtract(Complex rhs) {
        return new Complex(this.real.subtract(rhs.real),this.imaginary.subtract(rhs.imaginary));
    }

    public Complex subtract(Rational x) {
        return new Complex(this.real.subtract(x),this.imaginary);
    }

    public Complex multiply(Complex rhs) {
        Rational F = this.real.multiply(rhs.real);
        Rational O = this.real.multiply(rhs.imaginary);
        Rational I = this.imaginary.multiply(rhs.real);
        Rational L = this.imaginary.multiply(rhs.imaginary);

        return new Complex(F.subtract(L),O.add(I));

    }

    public Complex multiply(Rational x) {
        return new Complex(this.real.multiply(x),this.imaginary.multiply(x));
    }

    public Complex divide(Complex rhs) {
       Complex denom = rhs.multiply(rhs.conjugate());
       Complex numer = this.multiply(rhs.conjugate());
        return new Complex(numer.real.divide(denom.real),numer.imaginary.divide(denom.real));
    }

    public Complex divide(Rational x) {
        return new Complex(this.real.divide(x),this.imaginary.divide(x));
    }

    public static void main(String[] args) {
        Complex c1 = new Complex(new Rational(4,6), new Rational(9,10));
        Complex c2 = new Complex(new Rational(3), new Rational(5,7));
        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);

        Complex c1Conjagate = c1.conjugate();
        System.out.println("Conjugate of " + c1 + ": " + c1Conjagate);

        Complex c1Add = c1.add(c2);
        System.out.println(c1 + " + " + c2 + ": " + c1Add);

        Complex c2Add =  c1.add(new Rational(4));
        System.out.println(c1 + " + 4: " + c2Add);

        Complex c1Subtract = c1.subtract(c2);
        System.out.println(c1 + " - " + c2 + ": " + c1Subtract);

        Complex c2Subtract =  c1.subtract(new Rational(4));
        System.out.println(c1 + " - 4: " + c2Subtract);

        Complex c1Multiply = c1.multiply(c2);
        System.out.println(c1 + " * " + c2 + ": " + c1Multiply);

        Complex c2Multiply = c1.multiply(new Rational(4));
        System.out.println(c1 + " * 4: " + c2Multiply);

        Complex c1Divide = c1.divide(c2);
        System.out.println(c1 + " / " + c2 + ": " + c1Divide);

        Complex c2Divide = c1.divide(new Rational(4));
        System.out.println(c1 + " / 4: " + c2Divide);
    }
}
