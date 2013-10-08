import java.util.Scanner;

/** NOT WORKING */

class AddBigNumbers {
	public static void main(String[] args ) {
		Scanner kbd = new Scanner(System.in);
		String s = "";
		long[][] table = new long[101][10];
		int j = 0;
		while(kbd.hasNextLine() && !(s = kbd.nextLine()).equals("0")) {
			System.out.println(s);
			int i;
			for(i = 9;i>=0 && s.length() >= 10;i--) {
				table[j][i] = Long.parseLong(s.substring(s.length()-10,s.length()));
				s = s.substring(0,s.length()-10);
			}
			if(i>0) table[j][i] = Long.parseLong(s);
			j++;
		}
		
		String s_total = "";
		
		for(int m = 9;m>=0;m--) {
			long suma = 0;
			for(int n = 0;n<j+1;n++) {
				suma += table[n][m];
			}
			if(m!=0) {
				table[j+1][m-1]=  suma/100000/100000;
				String g = (""+ suma);
				s_total += g.substring(g.length()-10,g.length());
			} else {
				s_total += (""+ suma);
			}
			
		}
		System.out.println(s_total);
	}
	

}
