import java.util.Scanner;

class Persona {
    public int amount;
    public int finish_at;
    public static int amount_finished = 1;
    public Persona() {
        this.amount = 0;
        this.finish_at = -1;
    }
}

class Main {
    
    public static void main( String[] args )
    {
        Scanner kbd = new Scanner( System.in );

        String line;
        while ( !( line = kbd.nextLine() ).equals( "0 0" ) )
        {
            Scanner l = new Scanner( line );
            int N = l.nextInt();
            int k = l.nextInt();
            
            Persona[] persones = new Persona[ N ];
            
            Persona.amount_finished = 1;
            
            for ( int i = 0; i < persones.length; i++ )
                persones[ i ] = new Persona();
            
            int availableMoney = 1;
            int personaActual = 0;
            int nextMax =  ( availableMoney % k ) + 1;
            
            while ( Persona.amount_finished != ( N + 1 ) )
            {
                
                int remaining = 40 - persones[ personaActual ].amount;
                
                if ( remaining >= availableMoney )
                {
                    persones[ personaActual ].amount += availableMoney;
                    availableMoney = 0;
                    
                }
                else 
                {
                    persones[ personaActual ].amount += remaining;
                    availableMoney -= remaining;
                }                
                
                if ( persones[ personaActual ].amount == 40 && persones[ personaActual ].finish_at < 1 )
                {
                    System.out.printf( "%3d", personaActual + 1 );
                    persones[ personaActual ].finish_at = Persona.amount_finished;
                    Persona.amount_finished++;
                }
                
                if ( availableMoney == 0 )
                {
                    availableMoney = nextMax;
                    nextMax = ( nextMax % k ) + 1;
                }
                
                personaActual = ( personaActual + 1 ) % N;
            }
            
            System.out.println();
                   
        }
        
    }
    
}
