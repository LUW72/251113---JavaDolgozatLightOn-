package teszt;

import modell.LampakModell;
import nezet.GUINezetLampak;
import vezerlo.LampakVezerlo;

public class LampakVezerloTeszt 
{
    public static void main(String[] args) 
    {
        LampakModell modell = new LampakModell();
        GUINezetLampak nezet = new GUINezetLampak();
        LampakVezerlo vez = new LampakVezerlo(modell, nezet);

        
        // Középső - 4-es - lámpát átkapcsoljuk, hogy lássuk valóban a megf. helyiértékeken módosulnak-e a lámpák
        kozepsoLampaAtkapcsolTeszt(modell, vez);
        // Felső - 1-es - lámpát átkapcsoljuk, hogy lássuk valóban a megf. helyiértékeken módosulnak-e a lámpák
        felsoKozepLampaAtkapcsolTeszt(modell, vez);
        
        // Megszámláljuk a lekapcsolt lámpákat, hogy valóban annyi-e, amennyit kiír
        lekapcsoltLampaSzamlaloTeszt(modell);
        
        // Megnézzük, hogy valóban 9 lekapcsolt esetén lesz veége
        nyeresiLogikaTeszt(modell, vez);

        System.out.println("Minden teszt sikeresen lefutott!");        
    }
    
    private static void kozepsoLampaAtkapcsolTeszt(LampakModell modell, LampakVezerlo vez) 
    {
        // mindent true-ra initelünk
        for (int i = 0; i < modell.getLampak().length; i++) 
        {
            modell.getLampak()[i].setAllapot(true);
        }

        // középső katt.
        vez.lampakKapcsol(4);

        boolean[] vart = {  true, false, true,
                            false, false, false,
                            true, false, true};

        for (int i = 0; i < modell.getLampak().length; i++) 
        {
            assert modell.getLampak()[i].isAllapot() == vart[i] : "HIBA: A " + i + ". számú lámpa állapota nem jó!";
        }
    }
    
    private static void felsoKozepLampaAtkapcsolTeszt(LampakModell modell, LampakVezerlo vez) 
    {
        for (int i = 0; i < modell.getLampak().length; i++) 
        {
            modell.getLampak()[i].setAllapot(true);
        }

        vez.lampakKapcsol(1);

        boolean[] vart = {  false, false, false,
                            true, false, true,
                            true, true, true};

        for (int i = 0; i < modell.getLampak().length; i++) 
        {
            assert modell.getLampak()[i].isAllapot() == vart[i] : "HIBA: A " + i + ". számú lámpa állapota nem jó!";
        }
    }      
    
    private static void lekapcsoltLampaSzamlaloTeszt(LampakModell modell) 
    {
        int db = 0;
        for (int i = 0; i < modell.getLampak().length; i++) 
        {
            if (!modell.getLampak()[i].isAllapot()) db++;
        }

        // A felsoKozepLampaAtkapcsolTeszt()-ben 4 lámpa lett lekapcsolva, így arra tesztelek
        assert db == 4 : "HIBA: lekapcsolt lámpák száma helytelen! (" + db + ")";
    }    
    
    private static void nyeresiLogikaTeszt(LampakModell modell, LampakVezerlo vez) 
    {
        // mindegyik false
        for (int i = 0; i < modell.getLampak().length; i++) 
        {
            modell.getLampak()[i].setAllapot(false);
        }

        // Ellenőrizzük, hogy a játék valóban nyert állapotban van-e
        vez.lekapcsoltSzamlal();
        assert vez.getLekapcsoltLampak() == modell.getLampak().length : "HIBA: A nyerés logikája nem működik megfelelően!";
    }
    
}
