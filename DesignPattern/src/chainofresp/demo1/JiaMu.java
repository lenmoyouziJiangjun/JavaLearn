package chainofresp.demo1;

class JiaMu extends Player {
    public JiaMu(Player aSuccessor) {
        super();
        this.setNextPlayer(aSuccessor);
    }


    @Override
    public boolean handle() {
        System.out.println("JiaMu----handle");
        return false;
    }

    @Override
    public boolean doAfter() {
        System.out.println("JiaMu----doAfter");
        return false;
    }
}


