package vezerlo;

import modell.LampakModell;
import nezet.GUINezetLampak;

public class LampakVezerlo 
{
    private LampakModell modell;
    private GUINezetLampak nezet;

    public LampakVezerlo(LampakModell modell, GUINezetLampak nezet) 
    {
        this.modell = modell;
        this.nezet = nezet;
    }
    
    public void init()
    {
        nezet.getJbtnUjra().addActionListener((e) -> {
            nezet.setJlblJatekAllas("A játék elindult");
        });
    }
    
    
}
