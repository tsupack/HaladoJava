package hu.me;

public class Output {

    private double eredmeny;
    private String uzenet;
    private Hibakod hibakod;

    public double getEredmeny() {
        return eredmeny;
    }

    public void setEredmeny(final double eredmeny) {
        this.eredmeny = eredmeny;
    }

    public String getUzenet() {
        return uzenet;
    }

    public void setUzenet(final String uzenet) {
        this.uzenet = uzenet;
    }

    public Hibakod getHibakod() {
        return hibakod;
    }

    public void setHibakod(Hibakod hibakod) {
        this.hibakod = hibakod;
    }
}
