package anellcomunicacions_isidro;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/*@author Isidro*/

public class AnellComunicacions_Isidro {
    
    static final int PORT = 5000;
    static final int numProgrames = 5;

    public static void main(String[] args) {

        boolean ultim = false;

        System.out.println("Comença el anell de comunicacions");
        ThreadGroup elsProgrames = new ThreadGroup("elsProgrames");

        for (int i=1 ; i<numProgrames ; i++) {
            if (i == numProgrames-1) ultim = true;
            Programa p = new Programa(elsProgrames, i, ultim, PORT);
            p.start();
        }

        while (elsProgrames.activeCount()>0);
        
        System.out.println("Acaba el programa.");
        
    }

}
