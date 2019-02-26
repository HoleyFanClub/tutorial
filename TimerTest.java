import java.util.*;

public class TimerTest
{
  int seconds = 0;
  Timer timer;
  TimerTask timerTask;
  public void start()
  {
    this.timer = new Timer();
    this.timerTask = new TimerTask() {
      public void run()
  {
   seconds++;
   System.out.println(Integer.toString(seconds));
  }
    };
    
    timer.scheduleAtFixedRate(timerTask, 1000, 1000);
  }
  
  
  public static void main(String[] args)
  {
    TimerTest timerTest = new TimerTest();
    timerTest.start();
  }
}