package javapattern.chainofresp.demo1;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Version 1.0
 * Created by lll on 17/6/27.
 * Description 责任链模式测试
 * copyright generalray4239@gmail.com
 */
public class DrumBeater {
  private static Player firstPlayer;
  public static boolean stopped = false;
  Timer mTimer;


  /**
   * @param stopInSeconds after  stopInSeconds second  the beat stop
   */
  public void startBeating(int stopInSeconds) {
    System.out.print("Drum beating started...");
    mTimer = new Timer();
    mTimer.schedule(new StopBeatingReminder(), stopInSeconds * 1000);
  }


  public class StopBeatingReminder extends TimerTask {

    @Override
    public void run() {
      System.out.println("Drum beating stopped!");
      stopped = true;
      mTimer.cancel(); //Terminate the timer thread
    }
  }


  public static void main(String[] args) {
    DrumBeater drumBeater = new DrumBeater();

    JiaMu jiaMu = new JiaMu(null);

    jiaMu.setNextPlayer(new JiaShe(
            new JiaZheng(
                    new JiaBaoYu(
                            new JiaHuan(null)))));

//        drumBeater.startBeating(1);

    firstPlayer = jiaMu;
    firstPlayer.next();
  }
}
