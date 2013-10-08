/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
/**
 *
 * @author vicma10
 */
public class Pipes {
     
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        while(kbd.hasNext()) {
        double b = kbd.nextDouble();
        double h = kbd.nextDouble();
         
        int grid = grid(b,h);
        int skew = skew(b,h);
        int skew2 = skew(h,b);
        if(grid>=skew && grid>=skew2) {
            System.out.println(grid+" grid");
        } else if(skew>skew2) {
            System.out.println(skew+" skew");      
        } else {
            System.out.println(skew2+" skew");      
        }
    }
        }
     
    public static int grid(double b,double h) {
        return (int)b * (int)h;
    }
    public static int skew(double b,double h) {
        int aux = (int) (2*b);
        double n_d = h/(Math.sqrt(3)/2+1);
        int n = (int) n_d;
         
        int parell = 0;
         
        if ( aux % 2 == 0 ) {
            parell = ((int) b) + ( (int) b ) - 1;
        } else {
            parell = ((int) b ) * 2;
        }
         
        int tot = parell;
         
        if ( ( n_d - n ) * h >= 0.5 ) {
            tot += b;
        }
         
        return tot;
         
        /*
        if(aux%2==0) {
            if(n%2==0){
                return (int) (((int)b)*(n/2)+(((int)b)-1)*(n/2));
            }
            else {
                System.out.println("aux"+aux+"n"+n+"b"+b+"h"+h);
                return (int) (((int)b)*(n/2+1)+(((int)b)-1)*(n/2));
            }
        } else {
            return (int) (((int)b)*n);
        }
        * */
    }
}
