/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swerc;

import java.util.Scanner;

/**
 *
 * @author lluuldea
 */
public class Transportation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner kbd = new Scanner( System.in );
        String line;
        while ( !( line = kbd.nextLine() ).equals( "0 0 0" ) ) {
            String[] components = line.split( " " );
            int cap = Integer.parseInt( components[ 0 ] );
            int Bnum = Integer.parseInt( components[ 1 ] );
            int numTickets = Integer.parseInt( components[ 2 ] );
            int[] route=new int[Bnum];
            for(int i=0;i<numTickets;i++){
                String[] data = kbd.nextLine().split( " " );
                int start = Integer.parseInt( data[ 0 ] );
                int dest = Integer.parseInt( data[ 1 ] );
                int pass = Integer.parseInt( data[ 2 ] );
                for(int j=start;j<dest;j++){
                    if((route[j]+pass)<=cap) route[j]+=pass;
                    else{
                        for(int z=j-1;z>=start;z--){
                            route[z]-=pass;
                        }
                        break;
                    }
                }
            }
            int value=0;
            for(int i=0;i<route.length;i++){
                value+=route[i];
            }
            System.out.println(value);
        }
    }
}
