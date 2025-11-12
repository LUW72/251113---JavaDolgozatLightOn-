package modell;

public class LampaModell 
{
    private boolean allapot; // true - felkapcsolt, false - lekapcsolt
    private int sorszam;

    public LampaModell(boolean allapot, int sorszam) 
    {
        this.allapot = allapot;
        this.sorszam = sorszam;
    }

    public boolean isAllapot() 
    {
        return allapot;
    }

    public int getSorszam() 
    {
        return sorszam;
    }
        
}
