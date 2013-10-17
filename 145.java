
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
    
    public static final int DayRateStart = 8 * 60;
    public static final int DayRateEnd = 18 * 60;
    public static final int EveningRateStart = 18 * 60;
    public static final int EveningRateEnd = 22 * 60;
    public static final int NightRateStart = 22 * 60;
    public static final int NightRateEnd = 32 * 60;

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
        
        if ( endMinute < startMinute ) endMinute += 24 * 60;
        
        if ( startMinute < DayRateStart || startMinute > EveningRateEnd ) {
            System.out.println( "N" );
            // Night rate...
            if ( endMinute > NightRateEnd && endMinute < NightRateStart ) {
                // Day rate applicable...
                timeInNightRate = ( 24 * 60 + DayRateStart ) - startMinute;
                if ( endMinute > DayRateEnd ) {
                    // Evening rate applicable...
                    timeInDayRate = DayRateEnd - DayRateStart;
                    if ( endMinute > EveningRateEnd ) {
                        // Again in night rate...
                        timeInEveningRate = EveningRateEnd - EveningRateStart;
                        timeInNightRate += endMinute - timeInEveningRate - timeInDayRate - timeInNightRate;
                    } else {
                        timeInEveningRate = endMinute - timeInDayRate - timeInNightRate;
                    }
                } else {
                    timeInDayRate = endMinute - timeInNightRate - startMinute;
                }
            } else {
                if ( endMinute <= startMinute ) {
                    timeInNightRate = endMinute - startMinute;
                } else {
                    timeInNightRate = endMinute - startMinute;
                }
            }
        } else if ( startMinute < EveningRateStart ) {
            System.out.println( "D" );
            // Day rate...
            if ( endMinute > EveningRateStart ) {
                // Evening rate applicable...
                timeInDayRate = EveningRateStart - startMinute;
                if ( endMinute > EveningRateEnd ) {
                    // Night rate applicable...
                    timeInEveningRate = EveningRateEnd - EveningRateStart;
                    if ( endMinute > NightRateEnd ) {
                        // Again in day rate...
                        timeInNightRate = NightRateEnd - NightRateStart;
                        timeInDayRate += endMinute - timeInEveningRate - timeInDayRate - timeInNightRate;
                    } else {
                        if ( endMinute <= startMinute ) {
                            timeInNightRate = endMinute - timeInDayRate - timeInEveningRate - startMinute;
                        } else {
                            timeInNightRate = endMinute - timeInDayRate - timeInEveningRate;
                        }
                    }
                } else {
                    timeInEveningRate = endMinute - timeInDayRate - startMinute;
                }
            } else {
                timeInDayRate = endMinute - startMinute;
            }
        } else {
            System.out.println( "E" );
            // Evening rate...
            if ( endMinute > NightRateStart ) {
                // Night rate applicable...
                timeInEveningRate = NightRateStart - startMinute;
                if ( endMinute > NightRateEnd && endMinute < NightRateStart ) {
                    // Day rate applicable...
                    timeInNightRate = NightRateEnd - NightRateStart;
                    if ( endMinute > DayRateEnd ) {
                        // Again in evening rate...
                        timeInDayRate = DayRateEnd - DayRateStart;
                        timeInEveningRate += endMinute - timeInEveningRate - timeInDayRate - timeInNightRate;
                    } else {
                        timeInDayRate = endMinute - timeInNightRate - timeInEveningRate;
                    }
                } else {
                    if ( endMinute <= startMinute ) {
                        timeInNightRate = endMinute - timeInEveningRate - startMinute; 
                    } else {
                        timeInNightRate = endMinute - timeInEveningRate - startMinute;
                    }
                }
            } else {
                timeInEveningRate = endMinute - startMinute;
            }
        }
        
        double total = 0;
        
        switch ( step ) {
            case 'A':
                total = 0.10 * timeInDayRate + 0.06 * timeInEveningRate + 0.02 * timeInNightRate;
                break;
            case 'B':
                total = 0.25 * timeInDayRate + 0.15 * timeInEveningRate + 0.05 * timeInNightRate;
                break;
            case 'C':
                total = 0.53 * timeInDayRate + 0.33 * timeInEveningRate + 0.13 * timeInNightRate;
                break;
            case 'D':
                total = 0.87 * timeInDayRate + 0.47 * timeInEveningRate + 0.17 * timeInNightRate;
                break;
            case 'E':
                total = 1.44 * timeInDayRate + 0.80 * timeInEveningRate + 0.30 * timeInNightRate;
                break;
        }
                
        return String.format( "%10s%6d%6d%6d%3c%8.2f", phone, timeInDayRate, timeInEveningRate, timeInNightRate, step, total );
    }
    
}
