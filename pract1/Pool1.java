// CSD feb 2015 Juansa Sendra

public class Pool1 extends Pool {   //no kids alone
    private int cond = 0;
    private int kids = 0;
    
    public void init(int ki, int cap){}
    
    public synchronized void kidSwims() throws InterruptedException {
       while(cond == 0){
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
        while(kids > 0 && cond == 1){
            log.waitingToRest();
            wait();
        }

        log.resting();
        cond--;
        
    }
}
