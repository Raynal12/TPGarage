package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

    private final String immatriculation;
    private final List<Stationnement> myStationnements = new LinkedList<>();

    public Voiture(String i) {
        if (null == i) {
            throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
        }

        immatriculation = i;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    /**
     * Fait rentrer la voiture au garage Précondition : la voiture ne doit pas
     * être déjà dans un garage
     *
     * @param g le garage où la voiture va stationner
     * @throws java.lang.Exception Si déjà dans un garage
     */
    public void entreAuGarage(Garage g) throws Exception {
        if (estDansUnGarage()) {
            throw new Exception("Déjà dans un garage");
        }
        Stationnement s = new Stationnement(this, g);
        myStationnements.add(s);
        // Et si la voiture est déjà dans un garage ?
    }

    /**
     * Fait sortir la voiture du garage Précondition : la voiture doit être dans
     * un garage
     *
     * @throws java.lang.Exception si la voiture n'est pas dans un garage
     */
    public void sortDuGarage() throws Exception {
        if (estDansUnGarage() == false) {
            throw new Exception("La voiture doit être dans un garage");
        }
        // TODO: Implémenter cette méthode
        int i = myStationnements.size();
        myStationnements.get(i - 1).terminer();
    }
    // Trouver le dernier stationnement de la voiture
    // Terminer ce stationnement

    /**
     * @return l'ensemble des garages visités par cette voiture
     */
    public Set<Garage> garagesVisites() {
        // TODO: Implémenter cette méthode
        Set<Garage> lesGarages = new HashSet<>();
        for (Stationnement s : myStationnements) {
            lesGarages.add(s.getGarage());
        }
        return lesGarages;
    }

    /**
     * @return vrai si la voiture est dans un garage, faux sinon
     */
    public boolean estDansUnGarage() {
        // TODO: Implémenter cette méthode
        // Vrai si le dernier stationnement est en cours
        boolean estDedans = false;
        int i = myStationnements.size();
        if (i == 0) {
            estDedans = false;
        } else {
            if (myStationnements.get(i - 1).estEnCours()) {
                estDedans = true;
            }
        }
        return estDedans;
    }

    /**
     * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste
     * des dates d'entrée / sortie dans ce garage
     * <br>Exemple :
     * <pre>
     * Garage Castres:
     *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
     *		Stationnement{ entree=28/01/2019, en cours }
     *  Garage Albi:
     *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
     * </pre>
     *
     * @param out l'endroit où imprimer (ex: System.out)
     */
    public void imprimeStationnements(PrintStream out) {
        String text = "";

        for (Garage g : garagesVisites()) {
            text += g;
            text += "\n";
            for (Stationnement s : myStationnements) {
                if (s.getGarage() == g) {
                    text += s + "\n";
                }
            }
        }
        out.println(text);
        // TODO: Implémenter cette méthode

    }

}
