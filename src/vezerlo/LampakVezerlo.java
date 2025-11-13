package vezerlo;

import javax.swing.JButton;
import modell.LampakModell;
import nezet.GUINezetLampak;

public class LampakVezerlo 
{
    private LampakModell modell;
    private GUINezetLampak nezet;

    private JButton[] lampaLista = new JButton[9];
    
    public LampakVezerlo(LampakModell modell, GUINezetLampak nezet) 
    {
        this.modell = modell;
        this.nezet = nezet;
        
        this.lampaListaInit();
    }
    
    public void init()
    {
        nezet.getJbtnUjra().addActionListener((e) -> {
            nezet.setJlblJatekAllas("A játék újraindult");
        });
    }
    
    public void lampaListaInit()
    {
        this.lampaLista[0] = nezet.getJbtnLampa0();
        this.lampaLista[1] = nezet.getJbtnLampa1();
        this.lampaLista[2] = nezet.getJbtnLampa2();
        this.lampaLista[3] = nezet.getJbtnLampa3();
        this.lampaLista[4] = nezet.getJbtnLampa4();
        this.lampaLista[5] = nezet.getJbtnLampa5();
        this.lampaLista[6] = nezet.getJbtnLampa6();
        this.lampaLista[7] = nezet.getJbtnLampa7();
        this.lampaLista[8] = nezet.getJbtnLampa8();        
    }    
    
    
}
