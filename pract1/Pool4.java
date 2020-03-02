// CSD feb 2013 Juansa Sendra

public class Pool4 extends Pool { //kids cannot enter if there are instructors waiting to exit
    private int cond = 0; // numero instructores nadando
    private int kids = 0;
    private int maxkids;
    private int aforo;
    private int salir;
    
    public void init(int ki, int cap)           {maxkids = ki; aforo = cap;}

    public synchronized void kidSwims() throws InterruptedException {
        while(cond == 0 || (maxkids * cond) <= kids || (kids+1)+cond > aforo || salir > 0){
           log.waitingToSwim();
             wait();
            
         }
         kids++;
         log.swimming();
         
     }   
     public synchronized void kidRests()      {
         log.resting();
         kids--;
         notifyAll();
     }
     public synchronized void instructorSwims() throws InterruptedException {
         while(kids + (cond+1) > aforo){
             log.waitingToSwim();
             wait();
            }
         log.swimming();
         cond++;
         notifyAll();
     }
     public synchronized void instructorRests() throws InterruptedException {
         while(kids > 0 && cond == 1 || (maxkids * (cond-1)) < kids){
             salir++;
             log.waitingToRest();
             wait();
             salir--;
         }
 
         log.resting();
         cond--;
         
     }
 }