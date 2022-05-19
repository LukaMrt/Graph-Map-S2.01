package com.juka.graphmap.view.compare;

import com.google.inject.Inject;
import com.juka.graphmap.domain.model.comparaison.Comparaison;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.compare.CompareUI;
import com.juka.graphmap.ui.compare.CompareView;
import com.juka.graphmap.view.SwingView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingCompareView extends SwingView implements CompareView {

    private final JFrame frame;
    private final CompareUI compareUI;

    @Inject
    public SwingCompareView(JFrame frame, CompareUI compareUI) {
        this.frame = frame;
        this.compareUI = compareUI;
    }

    @Override
    public void display(List<Node> cities, List<Comparaison> result) {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.frame.setContentPane(panel);

        panel.add(buildTitle("Comparaison", 5), BorderLayout.NORTH);
        panel.add(buildCenterPanel(), BorderLayout.CENTER);

        panel.updateUI();

    }

    private JPanel buildCenterPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());


        return panel;
    }

}
