package com.juka.graphmap.ui.compare;

import com.juka.graphmap.domain.model.comparaison.Comparaison;
import com.juka.graphmap.domain.model.node.Node;

import java.util.List;

/**
 * Interface to display the compare view.
 *
 * @author Julien Linget
 * @since 0.1.0
 */
public interface CompareView {

    /**
     * Displays the compare view.
     *
     * @param nodes the nodes to display
     * @param cities the cities to display
     * @param result the result of the comparison
     * @param city1 the first city to compare
     * @param city2 the second city to compare
     */
    void display(List<Node> nodes, List<String> cities, List<Comparaison> result, String city1, String city2);

}
