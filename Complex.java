/**
 * Write a description of class Complex here.
 *
 * @author Val Barnes
 * @version 9/30/25
 */
public class Complex
{
    private double real;
    private double imaginary;


    public Complex(double real, double imaginary) 
    {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex(double real) 
    {
        this(real,0);
    }

    public double getReal() 
    {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public String toString() {
        if (real == 0)
            return imaginary + "i";

        else if (imaginary > 0)
            return real + " + " + imaginary + "i";

        else if (imaginary < 0)
            return real + " - " + Math.abs(imaginary) + "i";

        else 
            return real + "";
    }

    public Complex conjugate() {
        return new Complex(this.real,this.imaginary*(-1));
    }

    public Complex add(Complex rhs) {
        return new Complex(this.real+rhs.real,this.imaginary+rhs.imaginary);
    }

    public Complex add(double x) {
        return new Complex(this.real+x,this.imaginary);
    }

    public Complex subtract(Complex rhs) {
        return new Complex(this.real-rhs.real,this.imaginary-rhs.imaginary);
    }

    public Complex subtract(double x) {
        return new Complex(this.real-x,this.imaginary);
    }

    public Complex multiply(Complex rhs) {
        double F = this.real*rhs.real;
        double O = this.real*rhs.imaginary;
        double I = this.imaginary*rhs.real;
        double L = this.imaginary*rhs.imaginary;

        return new Complex(F-L,O+I);

    }

    public Complex multiply(double x) {
        return new Complex(this.real*x,this.imaginary*x);
    }

    public Complex divide(Complex rhs) {
       Complex denom = (rhs.multiply(rhs.conjugate()));
       Complex numer = this.multiply(rhs.conjugate());
        return new Complex(numer.real/denom.real,numer.imaginary/denom.real);
    }

    public Complex divide(double x) {
        return new Complex(this.real/x,this.imaginary/x);
    }

    public static void main(String[] args) {
        Complex c1 = new Complex(4, 9);
        Complex c2 = new Complex(3, 5);
        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);

        Complex c1Conjagate = c1.conjugate();
        System.out.println("Conjugate of " + c1 + ": " + c1Conjagate);

        Complex c1Add = c1.add(c2);
        System.out.println(c1 + " + " + c2 + ": " + c1Add);

        Complex c2Add =  c1.add(4);
        System.out.println(c1 + " + 4: " + c2Add);

        Complex c1Subtract = c1.subtract(c2);
        System.out.println(c1 + " - " + c2 + ": " + c1Subtract);

        Complex c2Subtract =  c1.subtract(4);
        System.out.println(c1 + " - 4: " + c2Subtract);

        Complex c1Multiply = c1.multiply(c2);
        System.out.println(c1 + " * " + c2 + ": " + c1Multiply);

        Complex c2Multiply = c1.multiply(4);
        System.out.println(c1 + " * 4: " + c2Multiply);

        Complex c1Divide = c1.divide(c2);
        System.out.println(c1 + " / " + c2 + ": " + c1Divide);

        Complex c2Divide = c1.divide(4);
        System.out.println(c1 + " / 4: " + c2Divide);
    }
}
