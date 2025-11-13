package vezerlo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
        jatekterInit();
        
        nezet.getJbtnUjra().addActionListener((e) -> {
            nezet.setJlblJatekAllas("A játék újraindult");
            ujraindit();
            jatekterInit();            
        });
        
        nezet.getJmItemKilepes().addActionListener((e)->{
            int valasz = JOptionPane.showConfirmDialog(null, "Biztosan kilépsz?", "Kilépés", JOptionPane.YES_NO_OPTION);
            if (valasz == JOptionPane.YES_OPTION) 
            {
                System.exit(0);
            }
        });

        nezet.getJmItemBetoltes().addActionListener((e)->{
            
        });
        
        nezet.getJmItemMentes().addActionListener((e)->{
            mentesFajlba();
        });
    }
    
    private void lampaListaInit()
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

    private void jatekterInit() 
    {
        for (int i = 0; i < modell.getLampak().length; i++)
        {
            lampaIkonBeallit(i);
        }        
    }

    private void lampaIkonBeallit(int i) 
    {
        if(modell.getLampak()[i].isAllapot())
        {
            // felkapcsolt
            lampaLista[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/nezet/lampa_sarga.png")));
        }
        else
        {
            // lekapcsolt
            lampaLista[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/nezet/lampa_zold.png")));
        }        
    }
    
    private void ujraindit()
    {
        this.modell = new LampakModell();
    }

    private void mentesFajlba() 
    {
        String sorok = "";
        
        for (int i = 0; i < modell.getLampak().length; i++) 
            {
                String allapot = modell.getLampak()[i].isAllapot() ? "1" : "0";
                System.out.println(allapot);
                sorok += allapot;
            }
        
        try 
        {
            Files.write(Paths.get("mentes.txt"), sorok.getBytes(StandardCharsets.UTF_8));
            nezet.setJlblJatekAllas("Játék mentve!");
        } 
        catch (IOException e) 
        {
            nezet.setJlblJatekAllas("Hiba a mentés közben: " + e.getMessage());
        }
    }
    
    private void betoltesFajlbol()
    {
        
    }
    
}
