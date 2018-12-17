/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astrot05;

import java.util.ArrayList;

/**
 *
 * @author pkolodziejski
 */
public class AstroT05 extends Integral {

    private double Y;
    private double M;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AstroT05 astro = new AstroT05();
        astro.setA(1);
        astro.setB(100_000);
        astro.setN(10_000_000);
        
        ArrayList<Function<Double,Double>> l = new ArrayList<>();
        
        for( int m = 1; m<=4; m++ ){
            l.add(new Function<>());
            astro.setM(m);
            for( double y = 0; y <= 3; y += 0.01 ){
                astro.setY(y);
                l.get(m-1).addPair(y, astro.integral());
            }
            l.get(m-1).toFile("E_" + m + "(x).dat");
            System.out.println("E_" + m + "(x).dat - ZROBIONE!");
        }
        
    }

    @Override
    public double function(double x) {
        return Math.exp(-x*Y)*Math.pow(x, -M);
    }

    public void setY(double Y) {
        this.Y = Y;
    }

    public void setM(double M) {
        this.M = M;
    }
    
}
