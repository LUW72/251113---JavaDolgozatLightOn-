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
    private int lekapcsoltLampak = 0;
    
    public LampakVezerlo(LampakModell modell, GUINezetLampak nezet) 
    {
        this.modell = modell;
        this.nezet = nezet;
        
        this.lampaListaInit();
    }
    
    public void init()
    {
        jatekterInit();
        gombnyomasokListener();
        
        lekapcsoltSzamlal();
        
        nezet.getJbtnUjra().addActionListener((e) -> {
            nezet.setJlblJatekAllas("A játék újraindult");
            ujraindit();
            jatekterInit();
            lekapcsoltSzamlal();
        });
        
        nezet.getJmItemKilepes().addActionListener((e)->{
            int valasz = JOptionPane.showConfirmDialog(null, "Biztosan kilépsz?", "Kilépés", JOptionPane.YES_NO_OPTION);
            if (valasz == JOptionPane.YES_OPTION) 
            {
                System.exit(0);
            }
        });
        
        nezet.getJmItemLeiras().addActionListener((e)->{
            JOptionPane.showMessageDialog(null, "A játék megnyeréséhez az összes lámpát le kell kapcsolnod!\n\nLekapcsolt - zöld\nFelkapcsolt - sárga");
        });        

        nezet.getJmItemBetoltes().addActionListener((e)->{
            betoltesFajlbol();
            lampakEnable(true);
            lekapcsoltSzamlal();
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

    private void gombnyomasokListener()
    {
        nezet.setJlblJatekAllas("Sok sikert!");
        for (int i = 0; i < modell.getLampak().length; i++) 
        {
            final int index = i;
            lampaLista[i].addActionListener((e)->{
                    
                //System.out.println("nyom");
                // állapotváloztatás
                //modell.getLampak()[index].setAllapot(!modell.getLampak()[index].isAllapot());
                lampakKapcsol(index);

                // lekapcs. száml
                lekapcsoltSzamlal();

                //jatekterInit();
            });
        }
        
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
        lampakEnable(true);
        this.modell = new LampakModell();
    }

    public void lekapcsoltSzamlal()
    {
        int db = 0;
        for (int i = 0; i < modell.getLampak().length; i++) 
        {
            if (!modell.getLampak()[i].isAllapot()) 
            {
                db++;
            }
        }
        lekapcsoltLampak = db;
        nyeresEllenor(db);
        
        nezet.setJspnrLekapcsoltLampakSzama(lekapcsoltLampak);
    }    
    
    private void nyeresEllenor(int db) 
    {
        if(db == modell.getLampak().length)
        {
            JOptionPane.showMessageDialog(null, "GRATULÁLOK, NYERTÉL " + nezet.getJtxtFldJatekosNev() + "!");
            nezet.setJlblJatekAllas("Szeretnél új játékot kezdeni?");
            lampakEnable(false);            
        }
    }

    private void lampakEnable(boolean beallit)
    {
        for (int i = 0; i < modell.getLampak().length; i++)
        {
            lampaLista[i].setEnabled(beallit);
        }
    }    
    
    public void lampakKapcsol(int index) 
    {
        lampaEllenkezoreAllit(index);
        lampaIkonBeallit(index);

        int sorSzomszed = index / 3;
        int oszlopSzomszed = index % 3;

        // bal 
        if(oszlopSzomszed > 0) 
        {
            int bal = index - 1;
            lampaEllenkezoreAllit(bal);
            lampaIkonBeallit(bal);
        }
        // jobb 
        if(oszlopSzomszed < 2) 
        {
            int jobb = index + 1;
            lampaEllenkezoreAllit(jobb);
            lampaIkonBeallit(jobb);
        }
        // fenti 
        if(sorSzomszed > 0) 
        {
            int fel = index - 3;
            lampaEllenkezoreAllit(fel);
            lampaIkonBeallit(fel);
        }
        // lenti
        if(sorSzomszed < 2) 
        {
            int le = index + 3;
            lampaEllenkezoreAllit(le);
            lampaIkonBeallit(le);
        }
    }

    private void lampaEllenkezoreAllit(int index)
    {
        modell.getLampak()[index].setAllapot(!modell.getLampak()[index].isAllapot());
    }
    
    
    private void mentesFajlba() 
    {
        String sorok = "";
        
        for (int i = 0; i < modell.getLampak().length; i++) 
            {
                String allapot = modell.getLampak()[i].isAllapot() ? "1" : "0";
                //System.out.println(allapot);
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
        try 
        {
            String sorok = Files.readString(Path.of("mentes.txt"), StandardCharsets.UTF_8);
            
            for (int i = 0; i < sorok.length(); i++) 
            {
                boolean allapot = sorok.charAt(i) == '1';
                //System.out.print(allapot + " ");
                modell.getLampak()[i].setAllapot(allapot);
            }
            jatekterInit();
            nezet.setJlblJatekAllas("Játék betöltve!");
            
        } 
        catch (IOException e) 
        {
            nezet.setJlblJatekAllas("Hiba a betöltés közben: " + e.getMessage());
        }
    }
    
    // Tesztre

    public int getLekapcsoltLampak() 
    {
        return lekapcsoltLampak;
    }
    
    
}
