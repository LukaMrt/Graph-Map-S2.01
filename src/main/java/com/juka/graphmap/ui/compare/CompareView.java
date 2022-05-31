package com.juka.graphmap.ui.compare;

import com.juka.graphmap.domain.model.comparaison.Comparaison;
import com.juka.graphmap.domain.model.node.Node;

import java.util.List;

public interface CompareView {

    void display(List<Node> nodes, List<String> cities, List<Comparaison> result, String city1, String city2);

}
