package javapattern.chainofresp.demo1;

class JiaHuan extends Player {



    public JiaHuan(Player aSuccessor) {
        super();
        this.setNextPlayer(aSuccessor);
    }


    @Override
    public boolean handle() {
        System.out.println("JiaHuan------handle ----true");

        return true;
    }

    @Override
    public boolean doAfter() {
        System.out.println("JiaHuan------doAfter ----false");
        return false;
    }
}
