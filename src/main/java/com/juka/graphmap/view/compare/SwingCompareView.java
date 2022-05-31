package com.juka.graphmap.view.compare;

import com.google.inject.Inject;
import com.juka.graphmap.domain.model.comparaison.Comparaison;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.compare.CompareUI;
import com.juka.graphmap.ui.compare.CompareView;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.view.swing.SwingView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingCompareView extends SwingView implements CompareView {

    private final JFrame frame;
    private final CompareUI compareUI;
    private final GraphUI graphUI;

    @Inject
    public SwingCompareView(JFrame frame, CompareUI compareUI, GraphUI graphUI) {
        this.frame = frame;
        this.compareUI = compareUI;
        this.graphUI = graphUI;
    }

    @Override
    public void display(List<Node> nodes, List<String> cities, List<Comparaison> result, String city1, String city2) {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.frame.setContentPane(panel);

        panel.add(buildTitle("Comparaison", 5), BorderLayout.NORTH);
        panel.add(buildCenterPanel(cities, result), BorderLayout.CENTER);
        panel.add(buildBottomPanel(), BorderLayout.SOUTH);

        panel.updateUI();
    }

    private JPanel buildCenterPanel(List<String> cities, List<Comparaison> result) {

        JPanel superPanel = new JPanel();
        superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.X_AXIS));

        superPanel.add(Box.createHorizontalGlue());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Ville 1 :");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        JList<String> leftList = new JList<>(cities.toArray(new String[0]));
        leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        leftList.setLayoutOrientation(JList.VERTICAL);
        leftList.setVisibleRowCount(-1);
        leftList.setFixedCellWidth(200);
        leftList.setFixedCellHeight(20);
        leftList.setFont(new Font("Arial", Font.PLAIN, 20));
        if (!result.isEmpty()) {
            leftList.setSelectedValue(result.get(0).best, true);
        }
        JScrollPane leftScrollPane = new JScrollPane(leftList);
        leftScrollPane.setPreferredSize(new Dimension(250, 400));
        leftScrollPane.setMaximumSize(new Dimension(250, 400));
        leftScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        leftScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        leftScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(leftScrollPane);
        superPanel.add(panel);

        JList<String> rightList = new JList<>(cities.toArray(new String[0]));
        rightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rightList.setLayoutOrientation(JList.VERTICAL);
        rightList.setVisibleRowCount(-1);
        rightList.setFixedCellWidth(200);
        rightList.setFixedCellHeight(20);
        rightList.setFont(new Font("Arial", Font.PLAIN, 20));
        if (!result.isEmpty()) {
            rightList.setSelectedValue(result.get(0).worst, true);
        }
        JScrollPane rightScrollPane = new JScrollPane(rightList);
        rightScrollPane.setPreferredSize(new Dimension(250, 400));
        rightScrollPane.setMaximumSize(new Dimension(250, 400));
        rightScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rightScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        superPanel.add(Box.createHorizontalGlue());

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(500, 650));
        panel.setMaximumSize(new Dimension(500, 650));
        panel.setMinimumSize(new Dimension(500, 650));

        panel.add(Box.createVerticalGlue());
        panel.add(Box.createVerticalGlue());
        panel.add(Box.createVerticalGlue());

        JButton button = new JButton("Comparer");
        button.addActionListener(e -> compareUI.interact(leftList.getSelectedValue(), rightList.getSelectedValue()));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 75));
        button.setMinimumSize(new Dimension(200, 75));
        button.setMaximumSize(new Dimension(200, 75));
        button.setSize(new Dimension(200, 75));
        button.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(button);

        panel.add(Box.createVerticalGlue());

        for (Comparaison comparaison : result) {
            label = new JLabel(comparaison.toString());
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 17));
            panel.add(label);
        }

        panel.add(Box.createVerticalGlue());
        panel.add(Box.createVerticalGlue());
        panel.add(Box.createVerticalGlue());

        superPanel.add(panel);

        superPanel.add(Box.createHorizontalGlue());

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        label = new JLabel("Ville 2 :");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(rightScrollPane);
        superPanel.add(panel);

        superPanel.add(Box.createHorizontalGlue());

        return superPanel;
    }

    private JPanel buildBottomPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(800, 80));
        panel.setMinimumSize(new Dimension(650, 80));
        panel.setMaximumSize(new Dimension(650, 80));

        JButton button = new JButton("Retour");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> graphUI.interact());
        button.setPreferredSize(new Dimension(200, 30));
        button.setMaximumSize(new Dimension(200, 30));
        panel.add(button);

        return panel;
    }

}
