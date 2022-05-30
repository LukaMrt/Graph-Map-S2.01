package com.juka.graphmap.view.neighbours.direct;

import com.juka.graphmap.domain.model.link.LinkCharacteristics;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeCharacteristics;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.neighbours.direct.DirectNeighborsUI;
import com.juka.graphmap.ui.neighbours.direct.DirectNeighborsView;
import com.juka.graphmap.view.swing.SwingView;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingDirectNeighborsView extends SwingView implements DirectNeighborsView {

    private final JFrame frame;
    private final DirectNeighborsUI directNeighborsUI;
    private final GraphUI graphUI;

    @Inject
    public SwingDirectNeighborsView(JFrame frame, DirectNeighborsUI directNeighborsUI, GraphUI graphUI) {
        this.frame = frame;
        this.directNeighborsUI = directNeighborsUI;
        this.graphUI = graphUI;
    }

    @Override
    public void display(List<Node> nodes, List<String> links, NodeCharacteristics nodeCharacteristics, LinkCharacteristics linkCharacteristics) {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.frame.setContentPane(panel);

        panel.add(buildTitle("Voisinage direct", 3), BorderLayout.NORTH);
        panel.add(buildLeftPanel(nodes.stream().map(Node::getName).toList(), nodeCharacteristics, linkCharacteristics.name), BorderLayout.WEST);
        panel.add(buildRightPanel(links, linkCharacteristics, nodeCharacteristics.name), BorderLayout.EAST);
        panel.add(buildBottomPanel(), BorderLayout.SOUTH);

        panel.updateUI();
    }

    private JPanel buildLeftPanel(List<String> nodes, NodeCharacteristics nodeCharacteristics, String link) {

        JPanel superPanel = new JPanel();
        superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.X_AXIS));
        superPanel.setPreferredSize(new Dimension(650, 700));
        superPanel.setMinimumSize(new Dimension(650, 700));
        superPanel.setMaximumSize(new Dimension(650, 700));

        superPanel.add(Box.createRigidArea(new Dimension(40, 0)));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Liste des lieux :");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JList<String> list = new JList<>();
        list.setListData(nodes.toArray(new String[0]));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.setFixedCellWidth(200);
        list.setFixedCellHeight(20);
        list.setFont(new Font("Arial", Font.PLAIN, 20));
        list.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (!nodeCharacteristics.name.isEmpty()) {
            list.setSelectedValue(nodeCharacteristics.name, true);
        }

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(250, 500));
        scrollPane.setMaximumSize(new Dimension(250, 500));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane);

        superPanel.add(panel);
        superPanel.add(Box.createRigidArea(new Dimension(30, 0)));

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(250, 500));
        panel.setMaximumSize(new Dimension(250, 500));

        panel.add(Box.createRigidArea(new Dimension(0, 40)));

        JButton button = new JButton("Analyser ce noeud");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> directNeighborsUI.interact(list.getSelectedValue(), link));
        button.setPreferredSize(new Dimension(250, 30));
        button.setMaximumSize(new Dimension(250, 30));
        panel.add(button);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        label = new JLabel("Nom :  " + nodeCharacteristics.name);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(label);
        label = new JLabel("Type : " + nodeCharacteristics.type);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(label);
        label = new JLabel("Voisins (" + nodeCharacteristics.neighbors.size() + ") :");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(label);

        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JList<String> list2 = new JList<>();
        list2.setListData(nodeCharacteristics.neighbors.toArray(new String[0]));
        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list2.setLayoutOrientation(JList.VERTICAL);
        list2.setVisibleRowCount(-1);
        list2.setFixedCellWidth(100);
        list2.setFixedCellHeight(20);
        list2.setFont(new Font("Arial", Font.PLAIN, 15));
        list2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JScrollPane scrollPane2 = new JScrollPane(list2);
        scrollPane2.setPreferredSize(new Dimension(150, 205));
        scrollPane2.setMaximumSize(new Dimension(150, 205));
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane2);

        superPanel.add(panel);

        return superPanel;
    }

    private JPanel buildRightPanel(List<String> links, LinkCharacteristics linkCharacteristics, String node) {

        JPanel superPanel = new JPanel();
        superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.X_AXIS));
        superPanel.setPreferredSize(new Dimension(650, 700));
        superPanel.setMinimumSize(new Dimension(650, 700));
        superPanel.setMaximumSize(new Dimension(650, 700));
        superPanel.add(Box.createRigidArea(new Dimension(60, 0)));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(250, 500));
        panel.setMaximumSize(new Dimension(250, 500));

        panel.add(Box.createRigidArea(new Dimension(0, 40)));

        JList<String> list = new JList<>();
        JButton button = new JButton("Analyser ce lien");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> directNeighborsUI.interact(node, list.getSelectedValue()));
        button.setPreferredSize(new Dimension(250, 30));
        button.setMaximumSize(new Dimension(250, 30));
        panel.add(button);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel label = new JLabel("Nom : " + linkCharacteristics.name);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(label);
        label = new JLabel("Type : " + linkCharacteristics.type);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(label);
        label = new JLabel("Allant de " + linkCharacteristics.start);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(label);
        label = new JLabel("Vers " + linkCharacteristics.end);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(label);
        label = new JLabel("Distance " + linkCharacteristics.distance + " km ");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(label);

        panel.add(Box.createVerticalGlue());
        panel.add(Box.createVerticalGlue());
        panel.add(Box.createVerticalGlue());

        superPanel.add(Box.createRigidArea(new Dimension(30, 0)));

        superPanel.add(panel);
        superPanel.add(Box.createRigidArea(new Dimension(30, 0)));

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        label = new JLabel("Liste des liens :");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(label);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        list.setListData(links.toArray(new String[0]));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.setFixedCellWidth(200);
        list.setFixedCellHeight(20);
        list.setFont(new Font("Arial", Font.PLAIN, 20));
        list.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (!linkCharacteristics.name.isEmpty()) {
            list.setSelectedValue(linkCharacteristics.name, true);
        }

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(250, 500));
        scrollPane.setMaximumSize(new Dimension(250, 500));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane);

        superPanel.add(panel);

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
