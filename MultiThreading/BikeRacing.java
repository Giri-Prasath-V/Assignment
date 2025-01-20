class Biker implements Runnable {
    private String name;
    private long startTime;
    private long endTime;
    private long totalTime;
    private static final Object lock = new Object();

    public Biker(String name) 
    {
        this.name = name;
    }

    public void run() 
    {
        synchronized(lock) 
        {
            try 
            {
                lock.wait();
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
       
        this.startTime = System.currentTimeMillis();
        try 
        {
            System.out.print(".");
            for(int i=0;i<10;i++)
            {
                System.out.print(".");
                Thread.sleep(1000 + (long) (Math.random() * 1000));
            }
            
            
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        this.endTime = System.currentTimeMillis();
        this.totalTime = this.endTime - this.startTime;
    }

    public String getName() {
        return name;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public static void countDown(int count) 
    {
        System.out.println("Countdown: ");
        try 
        {
            for (int i = count; i > 0; i--) 
            {
                System.out.println(i);
                Thread.sleep(1000);
            }
            System.out.println("GO!");
            synchronized(lock) 
            {
                lock.notifyAll();
            }
            System.out.print("Race Started");
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }

    public static void sortRacers(Biker[] bikers) 
    {
        for (int i = 0; i < bikers.length - 1; i++) 
        {
            for (int j = 0; j < bikers.length - 1 - i; j++) 
            {
                if (bikers[j].getTotalTime() > bikers[j + 1].getTotalTime()) 
                {
                    Biker temp = bikers[j];
                    bikers[j] = bikers[j + 1];
                    bikers[j + 1] = temp;
                }
            }
        }
    }

    
    public String toString() 
    {
        return String.format("Name: %s | Start Time: %d | End Time: %d | Time Taken: %d ms", name, startTime, endTime, totalTime);
    }
}

public class BikeRacing 
{

    public static void main(String[] args) throws InterruptedException 
    {
        Biker[] bikers = new Biker[10];
        
        for (int i = 0; i < 10; i++) 
        {
            bikers[i] = new Biker("Racer-" + (i + 1));
        }

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) 
        {
            threads[i] = new Thread(bikers[i]);
            threads[i].start();
        }

        Biker.countDown(10);

        for (int i = 0; i < 10; i++) 
        {
            threads[i].join();
        }

        Biker.sortRacers(bikers);

        System.out.println("\nRankings:");
        for (int i = 0; i < bikers.length; i++) 
        {
            System.out.println("Rank: " + (i + 1) + " " + bikers[i]);
        }
    }
}
