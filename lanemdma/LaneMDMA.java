/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanemdma;

/**
 *
 * @author pkolodziejski
 */
public class LaneMDMA extends SODE {

    private double n; //power
    
    public LaneMDMA(double h, double X_0, double Y_0, double U_0) {
        super(h, X_0, Y_0, U_0);
    }

    @Override
    double g(double x, double y, double z) {
        return z;
    }

    @Override
    public double f(double x, double y, double z) {
        return -2*z/x-Math.pow(y, n);
    }

    public void setN(double n) {
        this.n = n;
    }
    
    public void statistics(){
        double zero = zero();
        //double zero = 9.519999999999841;
        double derivativeInZero = derivativeInZero(zero);
        double densityRatio = densityRatio(zero, derivativeInZero);
        double bindingEnergy = bindingEnergy(derivativeInZero);
        
        System.out.println("Zero = " + zero);
        System.out.println("DerivativeInZero = " + derivativeInZero);
        System.out.println("DensityRatio = " + densityRatio);
        System.out.println("BindingEnergy = " + bindingEnergy);
        
    }
    
    private double zero(){
        return super.getFunction().interpolateReversed(0);
    }
    
    private double derivativeInZero(double x){
        return super.getDerivative().interpolate(x);
    }
    
    private double densityRatio(double zero, double derivativeInZero){
        return -zero/(3*derivativeInZero);
    }
    
    private double bindingEnergy(double derivativeInZero){
        return 1/(4*Math.PI*(this.n+1)*Math.pow(derivativeInZero, 2));
    }
    
}
