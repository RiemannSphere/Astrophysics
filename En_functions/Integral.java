/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astrot05;

/**
 *
 * @author pkolodziejski
 */
abstract public class Integral {
    
    // y_0
    private double A;
    // y_N
    private double B;
    // Number of intervals
    private int N;
    
    // Your function to integrate.
    abstract public double function( double x );
    
    // Value of integral over range [a,b] in given x.
    public double integral(){
        
        double h = (B-A)/(double)N;
        double s = 0;
        double x = A;
        
        for( int i=0; i<=N; i++ ){
            //System.out.println("y = " + function(x));
            if( i == 0 ){
                s += function(x);
            }else if( i == N){
                s += function(x);
            }else if( i % 2 == 1 ){
                s += 4*function(x);
            }else if( i % 2 == 0 ){
                s += 2*function(x);
            }else{
                System.out.println("Error #1");
            }
            
            x += h;
            
        }
        
        return (h/3)*s;
        
    }

    public void setA(double A) {
        this.A = A;
    }

    public void setB(double B) {
        this.B = B;
    }

    public void setN(int N) {
        this.N = N;
    }
    
}
