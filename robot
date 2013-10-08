import java.util.ArrayList;
import java.util.Scanner;

class _Main {
	public static void main(String[] args ) { Robot.main( args ); }
}

class Point {
	public int x;
	public int y;
	public Point( int x, int y ) { this.x = x; this.y = y; }
	public Point( Point p ) { this.x = p.x; this.y = p.y; }
	public boolean equals( Object o ) {
		if ( o instanceof Point ) {
			Point p = (Point) o;
			return ( p.x == x && p.y == y);
		}
		return false;
	}
}

class Table {
	public static int max_x;
	public static int max_y;
	private static ArrayList<Point> invalid_pos = new ArrayList<Point>();
	
	public static void add_invalid_pos( Point p ) {
		invalid_pos.add( p );
	}
	
	public static boolean valid_pos( Point p ) {
		return !( invalid_pos.contains( p ) );
	}
	
	public static boolean invalid_pos( Point p ) {
		return ( invalid_pos.contains( p ) );
	}
	
	public static boolean out_of_bounds( Point p ) {
		return ( p.x > max_x || p.y > max_y || p.x < 0 || p.y < 0 );
	}
}

class Robot {
	
	public Robot( int x, int y ) {
		this.position = new Point( x, y );
	}
	
	public static void main( String[] args ) {
		Scanner kbd = new Scanner( System.in );
		
		Scanner line = new Scanner( kbd.nextLine() );
		// Get dimensions.
		Table.max_x = line.nextInt();
		Table.max_y = line.nextInt();
		
		ArrayList<Robot> robots = new ArrayList<Robot>();
		
		while ( kbd.hasNext() ) {
			
			// Get robot position.
			line = new Scanner( kbd.nextLine() );
			
			Robot r = new Robot( line.nextInt(), line.nextInt() );
			
			char n_dir = line.next().charAt(0);
			switch ( n_dir ) {
				case 'N':
					r.dir = Robot.N;
					break;
				case 'E':
					r.dir = Robot.E;
					break;
				case 'S':
					r.dir = Robot.S;
					break;
				case 'W':
					r.dir = Robot.W;
					break;
			}
			
			robots.add( r );
			
			// Commands.
			String cmds = kbd.nextLine();
			for ( char a : cmds.toCharArray() ) {
				switch ( a ) {
					case 'R':
						r.turn_right();
						break;
					case 'L':
						r.turn_left();
						break;
					case 'F':
						r.forward();
						break;
					default:
						//System.out.println( a );
						break;
				}
			}
						
		}
		
		for ( Robot a : robots ) {
			System.out.println( a );
		}
		
	}
	
	public static final int N = 0;
	public static final int E = 1;
	public static final int S = 2;
	public static final int W = 3;
	
	public boolean died = false;
	
	private int dir;
	private Point position;
	
	public Point next_pos() {
		
		Point final_pos = new Point( position );
		
		switch ( dir ) {
			case Robot.N:
				final_pos.y++;
				break;
			case Robot.S:
				final_pos.y--;
				break;
			case Robot.E:
				final_pos.x++;
				break;
			case Robot.W:
				final_pos.x--;
				break;
		}
		
		return final_pos;
		
	}
	
	public void turn_left() {
		if ( died ) return;
		dir = ( dir - 1 + 4 ) % 4;
	}
	
	public void turn_right() {
		if ( died ) return;
		dir = ( dir + 1 ) % 4;
	}
	
	public void forward() {
		if ( died ) return;
		Point new_pos = next_pos();
		if ( !Table.out_of_bounds( new_pos ) ) {
			// If it is a valid position, move the robot.
			position = new_pos;
		} else if ( Table.out_of_bounds( new_pos ) && Table.valid_pos( position ) ) {
			// If position is out of bounds, add died position.
			Table.add_invalid_pos( position );
			died = true;
		}
	}
	
	public String toString() {
		
		String s = "";
		
		String d = "";
		if ( died ) d = " LOST";
		
		switch ( dir ) {
			case Robot.N:
				s = "N";
				break;
			case Robot.S:
				s = "S";
				break;
			case Robot.E:
				s = "E";
				break;
			case Robot.W:
				s = "W";
				break;
			default:
				s = "" + dir;
				break;
		}
		
		return position.x + " " + position.y + " " + s + d;
	}

}
