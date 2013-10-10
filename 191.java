import java.util.Scanner;

class Main {
    
    public static void main(String[] args) {
        
        Scanner kbd = new Scanner(System.in);
        
        int n = kbd.nextInt();
        for(int i = 0; i <n;i++) {
            //Line
            int spx = kbd.nextInt();
            int spy = kbd.nextInt();
            int epx = kbd.nextInt();
            int epy = kbd.nextInt();
            int lpx = kbd.nextInt();
            int lpy = kbd.nextInt();
            int rbx = kbd.nextInt();
            int rby = kbd.nextInt();
            
            
            int maxX = (spx>=epx) ? spx: epx;
            int minX = (spx<epx) ? spx: epx;
            int maxY = (spy>=epy) ? spy: epy;
            int minY = (spy<epy) ? spy : epy;
            
            double a = (double) ((spy - epy) / (spx -epx));
            double b = spy -spx*a;
            
            double res = lpx*a + b;
           
            double res2 = rbx*a + b;
       
            double res3 = (lpy - b)/a;
        
            double res4 = (rby - b)/a;
 
            if(res<=lpy && res>=rby) {
                if(res<=maxY && res>=minY) {
                    System.out.println("T");
                   continue;
                }
            } else if(res2 <= lpy && res2 >=rby) {
                if(res2<=maxY && res2>=minY) {
                    System.out.println("T");
                   continue;
                }
            } else if(res3 >= lpx && res3<=rbx) {
               if(res3<=maxX && res3>=minX) {
                    System.out.println("T");
                   continue;
                }
            
            } else if(res4 >= lpx && res4<=rbx) {
              if(res4<=maxX && res4>=minX) {
                    System.out.println("T");
                   continue;
                }
            
            }
            System.out.println("F");
            
        }
    
    
    
    }
}
