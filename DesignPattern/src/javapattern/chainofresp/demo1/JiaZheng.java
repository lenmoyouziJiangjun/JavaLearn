package javapattern.chainofresp.demo1;


class JiaZheng extends Player {

    public JiaZheng(Player aSuccessor) {
        super();
        this.setNextPlayer(aSuccessor);
    }


    @Override
    public boolean handle() {
        System.out.println("JiaZheng----handle");
        return false;
    }

    @Override
    public boolean doAfter() {
        System.out.println("JiaZheng----doAfter");
        return false;
    }
}