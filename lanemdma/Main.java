/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanemdma;

import java.util.Scanner;

/**
 *
 * @author pkolodziejski
 */
public class Main {

    private static Scanner scan = new Scanner(System.in);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        double epsilon = 0.01;
        LaneMDMA lane = new LaneMDMA(0.01, epsilon,1-Math.pow(epsilon,2)/6,-Math.pow(epsilon,2)/3);
//        for( double n=0; n!=5; n=n+0.5 ){
//            String t = scan.next();
//            lane.setN(n);
//            lane.run();
//            lane.getFunction().toFile("function_n=" + n + ".dat");
//        }

        double n = 5;
        lane.setN(n);
        lane.run();
        //lane.getFunction().toFile("function_n=" + n + ".dat");
        lane.getDerivative().toFile("derivative_n=" + n + ".dat");
        lane.statistics();
    }
    
}
