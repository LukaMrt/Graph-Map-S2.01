package com.juka.graphmap.view.graph;

import com.juka.graphmap.ui.compare.CompareUI;
import com.juka.graphmap.ui.graph.GraphView;
import com.juka.graphmap.ui.neighbours.direct.DirectNeighborsUI;
import com.juka.graphmap.ui.neighbours.indirect.IndirectNeighborsUI;
import com.juka.graphmap.ui.path.PathUI;
import com.juka.graphmap.view.SwingView;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingGraphView extends SwingView implements GraphView {

    private final JFrame frame;
    private final CompareUI compareUI;
    private final PathUI pathUI;
    private final DirectNeighborsUI directNeighborsUI;
    private final IndirectNeighborsUI indirectNeighborsUI;

    @Inject
    public SwingGraphView(JFrame frame, CompareUI compareUI, PathUI pathUI, DirectNeighborsUI directNeighborsUI, IndirectNeighborsUI indirectNeighborsUI) {
        this.frame = frame;
        this.compareUI = compareUI;
        this.pathUI = pathUI;
        this.directNeighborsUI = directNeighborsUI;
        this.indirectNeighborsUI = indirectNeighborsUI;
    }

    @Override
    public void display(List<String> nodes, List<String> links) {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.frame.setContentPane(panel);

        panel.add(buildTitle("Graphe", 2), BorderLayout.NORTH);
        panel.add(buildLeftPanel(nodes), BorderLayout.WEST);
        panel.add(buildCenterPanel(), BorderLayout.CENTER);
        panel.add(buildRightPanel(links), BorderLayout.EAST);

        panel.updateUI();
    }

    private JPanel buildLeftPanel(List<String> nodes) {

        JPanel superPanel = new JPanel();
        superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.X_AXIS));

        superPanel.add(Box.createRigidArea(new Dimension(40, 0)));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());

        JLabel label = new JLabel("Liste des lieux :");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JList<String> list = new JList<>();
        list.setListData(nodes.toArray(new String[0]));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.setFixedCellWidth(200);
        list.setFixedCellHeight(20);
        list.setFont(new Font("Arial", Font.PLAIN, 20));

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(200, 400));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane);

        panel.add(Box.createVerticalGlue());

        JButton button = new JButton("Comparer 2 villes");
        button.setPreferredSize(new Dimension(200, 40));
        button.setAlignmentX(JButton.CENTER_ALIGNMENT);
        button.addActionListener(e -> compareUI.interact(null, null));
        panel.add(button);

        panel.add(Box.createVerticalGlue());

        button = new JButton("Plus courts chemin");
        button.setPreferredSize(new Dimension(200, 40));
        button.setAlignmentX(JButton.CENTER_ALIGNMENT);
        button.addActionListener(e -> pathUI.interact(null, null));
        panel.add(button);

        panel.add(Box.createVerticalGlue());

        superPanel.add(panel);

        return superPanel;
    }

    private JPanel buildCenterPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(Box.createGlue());

        ImageIcon image = new ImageIcon("graph.png");
        image.setImage(image.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH));
        JLabel label = new JLabel(image);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        panel.add(Box.createGlue());

        return panel;
    }

    private JPanel buildRightPanel(List<String> links) {

        JPanel superPanel = new JPanel();
        superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.X_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());

        JLabel label = new JLabel("Liste des routes :");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JList<String> list = new JList<>();
        list.setListData(links.toArray(new String[0]));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.setFixedCellWidth(200);
        list.setFixedCellHeight(20);
        list.setFont(new Font("Arial", Font.PLAIN, 20));

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(200, 400));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane);

        panel.add(Box.createVerticalGlue());

        JButton button = new JButton("Lieu / lien");
        button.setPreferredSize(new Dimension(200, 40));
        button.setAlignmentX(JButton.CENTER_ALIGNMENT);
        button.addActionListener(e -> directNeighborsUI.interact(null, null));
        panel.add(button);

        panel.add(Box.createVerticalGlue());

        button = new JButton("2-distance");
        button.setPreferredSize(new Dimension(200, 40));
        button.setAlignmentX(JButton.CENTER_ALIGNMENT);
        button.addActionListener(e -> indirectNeighborsUI.interact(null, null));
        panel.add(button);

        panel.add(Box.createVerticalGlue());

        superPanel.add(panel);
        superPanel.add(Box.createRigidArea(new Dimension(40, 0)));

        return superPanel;
    }

}
