package chainofresp.demo1;

/**
 * Version 1.0
 * Created by lll on 17/6/27.
 * Description 定义一个抽象处理类
 * copyright generalray4239@gmail.com
 */
public abstract class Player {

    abstract public boolean handle();

    abstract public boolean doAfter();

    private Player nextPlayer;

    public Player() {
        nextPlayer = null;
    }

    protected void setNextPlayer(Player player) {
        this.nextPlayer = player;
    }

    /**
     * 执行逻辑。判断是否继续往下执行，
     */
    public void next() {
        if (nextPlayer != null) {
            if(!nextPlayer.handle()){
                nextPlayer.next();
            }else{
                nextPlayer.doAfter();
            }
        } else {
            System.out.println("Program terminated.");
        }
    }
}
