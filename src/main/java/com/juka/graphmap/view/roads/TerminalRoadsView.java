package com.juka.graphmap.view.roads;

import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.domain.model.path.Step;
import com.juka.graphmap.ui.roads.RoadsView;

import java.util.List;

public class TerminalRoadsView implements RoadsView {

    @Override
    public void display(List<Node> nodes, List<String> steps, Path path) {

        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°7 - Routes");

        System.out.println("Liste des neuds :");

        for (Node node : nodes) {
            System.out.println("- " + node.getName());
        }


        if (path != null) {
            System.out.println();
            StringBuilder strSteps = new StringBuilder(steps.get(0));

            for (int i = 1; i < steps.size() - 1; i++) {
                strSteps.append(", ")
                        .append(steps.get(i));
            }

            if (steps.size() > 1) {
                strSteps.append(" et ")
                        .append(steps.get(steps.size() - 1));
            }

            System.out.println("Chemin le plus court passant par les étapes " + strSteps + "  (" + path.getDistance() + " km) :");

            System.out.println("=> " + path.getPath().get(0).getDestination().getName());

            for (Step step : path.getPath().subList(1, path.getPath().size())) {
                System.out.println("=> " + step.getDestination().getName() + ", via " + step.getOriginLink().getRoadNameWithIndex() + " (" + step.getOriginLink().getDistance() + " km)");
            }

        }

        System.out.println();
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("0 - Quitter");
        System.out.println("1 - Choisir les étapes");
        System.out.println("2 - Retour");
    }

}
