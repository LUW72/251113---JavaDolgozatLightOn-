package teszt;

import modell.LampakModell;

public class LampakModellTeszt 
{
    public static void main(String[] args) 
    {
        LampakModell modell = new LampakModell();
        
        // Helyes inicializálás tesztelése (mindig 9 elem)
        modellInicializalasTeszt(modell);
    }
    private static void modellInicializalasTeszt(LampakModell modell) 
    {
    
        assert modell.getLampak().length == 9 : "HIBA: Nem 9 lámpa jött létre!";
        for (int i = 0; i < 9; i++) 
        {
            assert modell.getLampak()[i] != null : "HIBA: A " + i + ". lámpa null!";
        }
        System.out.println("Modell inicializálás teszt sikeres!");
    }

}
