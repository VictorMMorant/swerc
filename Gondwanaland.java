
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lluuldea
 */
class Gondwanaland {
    
    public static final int PreNightRateStart = 0;
    public static final int PreNightRateEnd = 8 * 60;
    public static final int DayRateStart = 8 * 60;
    public static final int DayRateEnd = 18 * 60;
    public static final int EveningRateStart = 18 * 60;
    public static final int EveningRateEnd = 22 * 60;
    public static final int NightRateStart = 22 * 60;
    public static final int NightRateEnd = 32 * 60;
    public static final int SecondDayRateStart = 24 * 60 + DayRateStart;
    public static final int SecondDayRateEnd = 24 * 60 + DayRateEnd;
    public static final int SecondEveningRateStart = 24 * 60 + EveningRateStart;
    public static final int SecondEveningRateEnd = 24 * 60 + EveningRateEnd;
    public static final int SecondNightRateStart = 24 * 60 + NightRateStart;
    public static final int SecondNightRateEnd = 24 * 60 + NightRateEnd;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner kbd = new Scanner( System.in );
        String line;
        while ( !( line = kbd.nextLine() ).equals( "#" ) ) {
            String[] components = line.split( " " );
            char step = components[ 0 ].charAt( 0 );
            String phone = components[ 1 ];
            int startH = Integer.parseInt( components[ 2 ] );
            int startM = 60 * startH + Integer.parseInt( components[ 3 ] );
            int endH = Integer.parseInt( components[ 4 ] );
            int endM = 60 * endH + Integer.parseInt( components[ 5 ] );
            System.out.printf( solve( step, phone, startM, endM ) );
        }
    }
    
    public static String solve( char step, String phone, int startMinute, int endMinute )
    {
        int timeInDayRate = 0;
        int timeInEveningRate = 0;
        int timeInNightRate = 0;
        
        double dayCost = 0;
        double eveningCost = 0;
        double nightCost = 0;
        
        if ( endMinute <= startMinute ) endMinute += 24 * 60;
        
        double total = 0;
        
        switch ( step ) {
            case 'A':
                dayCost = 0.1;
                eveningCost = 0.06;
                nightCost = 0.02;
                break;
            case 'B':
                dayCost = 0.25;
                eveningCost = 0.15;
                nightCost = 0.05;
                break;
            case 'C':
                dayCost = 0.53;
                eveningCost = 0.33;
                nightCost = 0.13;
                break;
            case 'D':
                dayCost = 0.87;
                eveningCost = 0.47;
                nightCost = 0.17;
                break;
            case 'E':
                dayCost = 1.44;
                eveningCost = 0.80;
                nightCost = 0.30;
                break;
        }
        
        timeInNightRate = minutesBetween( startMinute, endMinute, PreNightRateStart, PreNightRateEnd );
        timeInNightRate += minutesBetween( startMinute, endMinute, NightRateStart, NightRateEnd );
        timeInDayRate = minutesBetween( startMinute, endMinute, DayRateStart, DayRateEnd );
        timeInEveningRate = minutesBetween( startMinute, endMinute, EveningRateStart, EveningRateEnd );
        timeInNightRate += minutesBetween( startMinute, endMinute, SecondNightRateStart, SecondNightRateEnd );
        timeInDayRate += minutesBetween( startMinute, endMinute, SecondDayRateStart, SecondDayRateEnd );
        timeInEveningRate += minutesBetween( startMinute, endMinute, SecondEveningRateStart, SecondEveningRateEnd );
        
        total = dayCost * timeInDayRate + eveningCost * timeInEveningRate + nightCost * timeInNightRate;
             
        return String.format( "%10s%6d%6d%6d%3c%8.2f", phone, timeInDayRate, timeInEveningRate, timeInNightRate, step, total );
    }
    
    /**
     * Returns how many minutes are between two time periods.
     * @param start_1 Start of first period.
     * @param end_1 End of first period.
     * @param start_2 Start of second period.
     * @param end_2 End of second period.
     */
    public static int minutesBetween( int start_1, int end_1, int start_2, int end_2 ) {
        int start = Math.max( start_1, start_2 );
        int end = Math.min( end_1, end_2 );
        if ( start <= end ) return end-start;
        return 0;
    }
    
}
