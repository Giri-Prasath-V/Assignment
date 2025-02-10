import java.util.concurrent.*;
import java.time.*;


class Biker implements Runnable 
{
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration totalTime;
    private static CountDownLatch latch;

    public Biker(String name, CountDownLatch latch) 
    {
        this.name = name;
        Biker.latch = latch;
    }

    public void run() 
    {
        try 
        {
            latch.await();
            this.startTime = LocalDateTime.now();
            
            System.out.print(".");
            for (int i = 0; i < 10; i++) 
            {
                System.out.print(".");
                Thread.sleep(1000 + (long) (Math.random() * 1000));
            }

            this.endTime = LocalDateTime.now();
            this.totalTime = Duration.between(startTime, endTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getName() 
    {
        return name;
    }

    public Duration getTotalTime() 
    {
        return totalTime;
    }

   
    public String toString() 
    {
        return String.format("Name: %s | Start Time: %s | End Time: %s | Time Taken: %d seconds, %d milliseconds", 
            name, startTime, endTime, totalTime.toSeconds(), totalTime.toMillisPart());
    }

    public static void sortRacers(Biker[] bikers) 
    {
        for (int i = 0; i < bikers.length - 1; i++) 
        {
            for (int j = 0; j < bikers.length - 1 - i; j++) 
            {
                if (bikers[j].getTotalTime().toMillis() > bikers[j + 1].getTotalTime().toMillis()) 
                {
                    Biker temp = bikers[j];
                    bikers[j] = bikers[j + 1];
                    bikers[j + 1] = temp;
                }
            }
        }
    }
}

public class Bikers 
{
    public static void main(String[] args) throws Exception 
    {
        int numBikers = 10;
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executor = Executors.newFixedThreadPool(numBikers);
        Biker[] bikers = new Biker[numBikers];

        for (int i = 0; i < numBikers; i++) 
        {
            bikers[i] = new Biker("Racer-" + (i + 1), latch);
            executor.execute(bikers[i]);
        }

        countdown(10);
        latch.countDown(); 
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        
        Biker.sortRacers(bikers);

        System.out.println("\nRankings:");
        for (int i = 0; i < bikers.length; i++) 
        {
            System.out.println("Rank: " + (i + 1) + " " + bikers[i]);
        }
    }

    public static void countdown(int count) throws Exception 
    {
        System.out.println("Countdown:");
        for (int i = count; i > 0; i--) 
        {
            System.out.println(i);
            Thread.sleep(1000);
        }
        System.out.println("GO!");
    }
}
