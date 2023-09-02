import java.math.BigDecimal;

public class ComplexNumber {
    private double real;
    private double imaginary;

    ComplexNumber(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    public ComplexNumber add(ComplexNumber a){
        return new ComplexNumber(this.real + a.real, this.imaginary + a.imaginary);
    }

    public ComplexNumber multiply(ComplexNumber z){
        return new ComplexNumber(this.real*z.real - this.imaginary*z.imaginary,this.real*z.imaginary+this.imaginary*z.real);
    }

    public double magnitude(){
        return Math.sqrt(this.real*this.real + this.imaginary*this.imaginary);
    }
}
