package chainofresp.demo1;

class JiaShe extends Player {

    public JiaShe(Player aSuccessor) {
        super();
        this.setNextPlayer(aSuccessor);
    }


    @Override
    public boolean handle() {
        System.out.println("JiaShe----handle");
        return false;
    }

    @Override
    public boolean doAfter() {
        System.out.println("JiaShe----doAfter");
        return false;
    }
}