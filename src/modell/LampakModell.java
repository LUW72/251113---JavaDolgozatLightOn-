package modell;

import java.util.Random;

public class LampakModell 
{
    private LampaModell[] lampak = new LampaModell[9];
    private final static Random rnd = new Random();

    public LampakModell() 
    {
        lampaInit();
    }
    
    private void lampaInit()
    {
        for (int i = 0; i < 9; i++) 
        {
            lampak[i] = new LampaModell(rnd.nextBoolean(), i);
        }
    }
    
    public LampaModell[] getLampak() 
    {
        return lampak;
    }    
}
