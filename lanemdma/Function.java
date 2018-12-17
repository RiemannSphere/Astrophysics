/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanemdma;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author pkolodziejski
 * @param <X>
 * @param <Y>
 */
public class Function<X,Y> {

    private List<Pair<X,Y>> function = new ArrayList<>();
    
    public class Pair<X,Y>{
        X x;
        Y y;
        
        Pair(X x, Y y){
            this.x = x;
            this.y = y;
        }
        
        public X getX(){
            return x;
        }
        
        public Y getY(){
            return y;
        }

        @Override
        public String toString() {
            return x + ", " + y;
        }

    }
    
    public void addPair(X x, Y y){
        function.add(new Pair(x,y));
    }
    
    public ArrayList getFunctionList(){
        return new ArrayList<>(function);
    }
    
    public ArrayList getArguments(){
        
        ArrayList arguments = new ArrayList<>();
        
        for( Pair p : function ){
            arguments.add(p.getX());
        }
        
        return arguments;
    }
    
    public ArrayList getValues(){
        
        ArrayList values = new ArrayList<>();
        
        for( Pair p : function ){
            values.add(p.getY());
        }
        
        return values;
    }
    
    public void toFile(String fileName){
        try {
            PrintWriter pw = new PrintWriter(new File(fileName));
            
            for(Pair p : function){
                pw.println(p.toString());
                System.out.println(p.toString());
            }

            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }
        
    }
    
    //For given x finds y.
    public double interpolate(double x){
        ArrayList<Pair> list = this.getFunctionList();
        
        for(int i=0; i!=list.size()-1; i++){
            if( (double)list.get(i).x <= x && (double)list.get(i+1).x >= x  ){
                double y1 = (double)list.get(i).y;
                double y2 = (double)list.get(i+1).y;
                return (y1+y2)/2;
            }
        }
        
        System.out.println("There is no such argument");
        
        return -1;
    }
    
    //For given y finds x
    public double interpolateReversed(double y){
        ArrayList<Pair> list = this.getFunctionList();
        
        for(int i=0; i!=list.size()-1; i++){
            if( (double)list.get(i).y >= y && (double)list.get(i+1).y <= y  ){
                double x1 = (double)list.get(i).x;
                double x2 = (double)list.get(i+1).x;
                return (x1+x2)/2;
            }
        }
        
        System.out.println("There is no such argument");
        
        return -1;
    }
    
}
