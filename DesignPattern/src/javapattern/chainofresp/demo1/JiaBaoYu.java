package javapattern.chainofresp.demo1;

class JiaBaoYu extends Player {

    public JiaBaoYu(Player aSuccessor) {
        super();
        this.setNextPlayer(aSuccessor);
    }


    @Override
    public boolean handle() {
        System.out.println("JiaBaoYu----handle");
        return false;
    }

    @Override
    public boolean doAfter() {
        System.out.println("JiaBaoYu----doAfter");
        return false;
    }
}
