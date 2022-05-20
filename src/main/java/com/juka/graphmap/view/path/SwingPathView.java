package com.juka.graphmap.view.path;

import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.path.PathUI;
import com.juka.graphmap.ui.path.PathView;
import com.juka.graphmap.view.SwingView;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingPathView extends SwingView implements PathView {

    private final JFrame frame;
    private final GraphUI graphUI;
    private final PathUI pathUI;

    @Inject
    public SwingPathView(JFrame frame, GraphUI graphUI, PathUI pathUI) {
        this.frame = frame;
        this.graphUI = graphUI;
        this.pathUI = pathUI;
    }

    @Override
    public void display(List<String> nodes, String node1, String node2, Path path) {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.frame.setContentPane(panel);

        panel.add(buildTitle("Plus court chemin", 6), BorderLayout.NORTH);
        panel.add(buildCenterPanel(nodes, node1, node2, path), BorderLayout.CENTER);
        panel.add(buildBottomPanel(), BorderLayout.SOUTH);

        panel.updateUI();

    }

    private JPanel buildCenterPanel(List<String> nodes, String node1, String node2, Path path) {

        JPanel superPanel = new JPanel();
        superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.X_AXIS));

        superPanel.add(Box.createHorizontalGlue());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Départ :");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        JList<String> leftList = new JList<>(nodes.toArray(new String[0]));
        leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        leftList.setLayoutOrientation(JList.VERTICAL);
        leftList.setVisibleRowCount(-1);
        leftList.setFixedCellWidth(200);
        leftList.setFixedCellHeight(20);
        leftList.setFont(new Font("Arial", Font.PLAIN, 20));
        if (node1 != null) {
            leftList.setSelectedValue(node1, true);
        }
        JScrollPane leftScrollPane = new JScrollPane(leftList);
        leftScrollPane.setPreferredSize(new Dimension(250, 400));
        leftScrollPane.setMaximumSize(new Dimension(250, 400));
        leftScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        leftScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        leftScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(leftScrollPane);
        superPanel.add(panel);

        JList<String> rightList = new JList<>(nodes.toArray(new String[0]));
        rightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rightList.setLayoutOrientation(JList.VERTICAL);
        rightList.setVisibleRowCount(-1);
        rightList.setFixedCellWidth(200);
        rightList.setFixedCellHeight(20);
        rightList.setFont(new Font("Arial", Font.PLAIN, 20));
        if (node2 != null) {
            rightList.setSelectedValue(node2, true);
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

        JButton button = new JButton("Trouver le chemin");
        button.addActionListener(e -> pathUI.interact(leftList.getSelectedValue(), rightList.getSelectedValue()));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 50));
        button.setMinimumSize(new Dimension(200, 50));
        button.setMaximumSize(new Dimension(200, 50));
        button.setSize(new Dimension(200, 50));
        button.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(button);

        panel.add(Box.createVerticalGlue());

        JLabel label2 = new JLabel("Distance : " + path.getDistance() + " km");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setFont(new Font("Arial", Font.PLAIN, 17));
        panel.add(label2);
        label2 = new JLabel("Étapes : ");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setFont(new Font("Arial", Font.PLAIN, 17));
        panel.add(label2);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        String[] steps = path.getPath().stream()
                .map(step -> "=> " + step.getDestination().getName() + (step.getOriginLink() != null ? ", via " + step.getOriginLink().getRoadNameWithIndex() + " (" + step.getOriginLink().getDistance() + "km)" : ""))
                .toList()
                .toArray(new String[0]);
        JList<String> pathList = new JList<>(steps);
        pathList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pathList.setLayoutOrientation(JList.VERTICAL);
        pathList.setVisibleRowCount(-1);
        pathList.setFixedCellWidth(200);
        pathList.setFixedCellHeight(20);
        pathList.setFont(new Font("Arial", Font.PLAIN, 20));
        JScrollPane pathScrollPane = new JScrollPane(pathList);
        pathScrollPane.setPreferredSize(new Dimension(400, 200));
        pathScrollPane.setMaximumSize(new Dimension(400, 200));
        pathScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pathScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pathScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(pathScrollPane);

        panel.add(Box.createVerticalGlue());
        panel.add(Box.createVerticalGlue());
        panel.add(Box.createVerticalGlue());

        superPanel.add(panel);

        superPanel.add(Box.createHorizontalGlue());

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        label = new JLabel("Arrivée :");
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
