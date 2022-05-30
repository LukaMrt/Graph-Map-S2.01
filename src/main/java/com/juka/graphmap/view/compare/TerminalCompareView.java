package com.juka.graphmap.view.compare;

import com.juka.graphmap.domain.model.comparaison.Comparaison;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.compare.CompareView;

import java.util.List;

public class TerminalCompareView implements CompareView {

    @Override
    public void display(List<Node> nodes, List<String> cities, List<Comparaison> result) {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°5 - Comparaison de villes");
        System.out.println();

        System.out.println("Liste des noeuds :");
        for (String city : cities) {
            System.out.println("- " + city);
        }

        System.out.println();
        System.out.println("Résultat :");
        for (Comparaison comparaison : result) {
            System.out.println(comparaison.best + " est plus " + comparaison.name + " que " + comparaison.worst);
        }

        System.out.println();
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("0 - Retour à l'écran précédent");
        System.out.println("1 - Choisissez 2 villes à comparer");
        System.out.println("2 - Retour à l'écran principal");
    }

}
