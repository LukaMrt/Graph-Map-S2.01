package com.juka.graphmap.ui.compare;

import com.juka.graphmap.domain.model.comparaison.Comparaison;

import java.util.List;

public interface CompareView {

    void display(List<String> cities, List<Comparaison> result);

}
