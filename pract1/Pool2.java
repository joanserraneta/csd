// CSD feb 2015 Juansa Sendra

public class Pool2 extends Pool{ //max kids/instructor
    private int cond = 0; // numero instructores nadando
    private int kids = 0;
    private int maxkids;
    public void init(int ki, int cap)           {maxkids = ki;}

    public synchronized void kidSwims() throws InterruptedException {
        while(cond == 0 || (maxkids * cond) <= kids){
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
     public synchronized void instructorSwims()   {
         log.swimming();
         cond++;
         notifyAll();
     }
     public synchronized void instructorRests() throws InterruptedException {
         while(kids > 0 && cond == 1 || (maxkids * (cond-1)) < kids){
             log.waitingToRest();
             wait();
         }
 
         log.resting();
         cond--;
         
     }
 }

