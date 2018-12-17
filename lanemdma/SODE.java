/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanemdma;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Second Order Differential Equation:
 * 
 * y' = u = g()
 * u' = f()
 * 
 * @author pkolodziejski
 */
abstract public class SODE {
    
    private Scanner scanner = new Scanner(System.in);
    
    //Function y
    private Function<Double,Double> function = new Function<>();
    //Derivative of y
    private Function<Double,Double> derivative = new Function<>();
    //Step
    private final double h;
    //Stop - when to stop the algorithm
    private int stop;
    //Boundary conditions
    private final double X_0; // <-- Starting point x_0
    private final double Y_0;
    private final double U_0;
    
    public SODE(double h, double X_0, double Y_0, double U_0){
        if( h > 0 ){
            this.h = h;
        }else{
            this.h = 0;
            System.out.println("Step must be greater than zero! h has been set to 0.");
        }
        this.X_0 = X_0;
        this.Y_0 = Y_0;
        this.U_0 = U_0;
    }
    
    
    public void run(){
//        System.out.println("How many steps?");
//        setStop(scanner.nextInt());
        setStop(5000);
        System.out.println("Simulation is running...");
        
        double y;
        double u;
        double dy;
        double du;
        double k1;
        double k2;
        double k3;
        double k4;      
        double q1;
        double q2;
        double q3;
        double q4;
        double x;
        double z;
        
        x = X_0;
        y = Y_0;
        u = U_0;
        z = u;

        k1 = this.h*g(x,y,z);
        q1 = this.h*f(x,y,z);

        k2 = this.h*g(x+h/2,y+k1/2,z+q1/2);
        q2 = this.h*f(x+h/2,y+k1/2,z+q1/2);

        k3 = this.h*g(x+h/2,y+k2/2,z+q2/2);
        q3 = this.h*f(x+h/2,y+k2/2,z+q2/2);

        k4 = this.h*g(x+h,y+k3,z+q3);
        q4 = this.h*f(x+h,y+k3,z+q3);

        dy = (k1+2*k2+2*k3+k4)/6;
        y = y + dy;

        du = (q1+2*q2+2*q3+q4)/6;
        u = u + du;
        
        function.addPair(x, y);
        derivative.addPair(x, u);
        
        System.out.println("y("+x+") = "+y);
        
        for( int i=0; i!=this.stop; i++ ){
        
            x = x + this.h;
            z = u;
            
            k1 = this.h*g(x,y,z);
            q1 = this.h*f(x,y,z);

            k2 = this.h*g(x+h/2,y+k1/2,z+q1/2);
            q2 = this.h*f(x+h/2,y+k1/2,z+q1/2);

            k3 = this.h*g(x+h/2,y+k2/2,z+q2/2);
            q3 = this.h*f(x+h/2,y+k2/2,z+q2/2);

            k4 = this.h*g(x+h,y+k3,z+q3);
            q4 = this.h*f(x+h,y+k3,z+q3);

            dy = (k1+2*k2+2*k3+k4)/6;
            y = y + dy;

            du = (q1+2*q2+2*q3+q4)/6;
            u = u + du;      
            
            function.addPair(x, y);
            derivative.addPair(x, u);
            
            System.out.println("y("+x+") = "+y);
        }
    }
    
    public void setStop(int stop){
        this.stop = stop;
        System.out.println("Alogrithm will stop after : "+this.stop);
    }
    
    
    //This is your system of equations. Extend this class and fill in those methods. 
    abstract double g(double x, double y, double z);
    abstract double f(double x, double y, double z);

    public Function<Double, Double> getFunction() {
        return function;
    }

    public Function<Double, Double> getDerivative() {
        return derivative;
    }


}
