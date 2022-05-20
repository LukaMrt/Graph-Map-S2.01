package com.juka.graphmap.view.neighbours;

import com.google.inject.Inject;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.neighbours.indirect.IndirectNeighborsUI;
import com.juka.graphmap.ui.neighbours.indirect.IndirectNeighborsView;
import com.juka.graphmap.view.SwingView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/***
 * @author Julien Linget
 */
public class SwingIndirectNeighborsView extends SwingView implements IndirectNeighborsView {

    private final JFrame frame;
    private final IndirectNeighborsUI indirectNeighborsUI;
    private final GraphUI graphUI;
    private JLabel resultLabel;
    private JList<String> leftList;
    private JList<String> rightList;

    @Inject
    public SwingIndirectNeighborsView(JFrame frame, IndirectNeighborsUI indirectNeighborsUI, GraphUI graphUI) {
        this.indirectNeighborsUI = indirectNeighborsUI;
        this.frame = frame;
        this.graphUI = graphUI;
    }

    @Override
    public void display(List<Node> nodes, String location1, String location2, boolean result) {
        if (location1 == null || location2 == null) {
            displayPanels(nodes);
        } else {
            displayResult(location1, location2, result);
        }
    }

    private JPanel buildSidePanel(List<String> nodes, boolean isLeftPanel) {
        JPanel containerPanel = new JPanel();
        JPanel panel = new JPanel();
        JList<String> nodeList = new JList<>(nodes.toArray(new String[0]));
        if (isLeftPanel) {
            leftList = nodeList;
        } else  {
            rightList = nodeList;
        }

        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));
        if (isLeftPanel) containerPanel.add(Box.createRigidArea(new Dimension(40, 0)));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Liste des lieux : ");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(label);

        panel.add(nodeList);
        nodeList.setAlignmentX(Component.CENTER_ALIGNMENT);
        nodeList.setAlignmentY(Component.CENTER_ALIGNMENT);

        JScrollPane scrollPane = new JScrollPane(nodeList);
        scrollPane.setPreferredSize(new Dimension(200, 400));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane);

        panel.add(Box.createVerticalGlue());

        containerPanel.add(panel);
        if (!isLeftPanel) containerPanel.add(Box.createRigidArea(new Dimension(40, 0)));

        return containerPanel;
    }

    private JPanel buildSouthPanel() {
        JPanel southPanel = new JPanel();
        JButton backButton = new JButton("Retour");

        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.add(backButton);

        backButton.setPreferredSize(new Dimension(200, 40));
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> graphUI.interact());

        return southPanel;
    }

    private JPanel buildCenterPanel() {
        JPanel centerPanel = new JPanel();
        JPanel topCenter = new JPanel();
        JPanel bottomCenter = new JPanel();
        JButton compareButton = new JButton("Étudier si les lieux sont à 2 distance");
        compareButton.addActionListener(e ->
                indirectNeighborsUI.interact(leftList.getSelectedValue(), rightList.getSelectedValue()));
        resultLabel = new JLabel();

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        topCenter.setLayout(new GridBagLayout());
        topCenter.add(compareButton);

        bottomCenter.setLayout(new GridBagLayout());
        bottomCenter.add(resultLabel);

        centerPanel.add(topCenter);
        centerPanel.add(bottomCenter);

        return centerPanel;
    }

    private void displayPanels(List<Node> nodes) {
        JPanel panel = new JPanel();
        List<String> nodeNames = nodes.stream().map(Node::getName).toList();
        panel.setLayout(new BorderLayout());

        panel.add(buildTitle("Voisinage indirect", 4), BorderLayout.NORTH);

        panel.add(buildSidePanel(nodeNames, false), BorderLayout.EAST);
        panel.add(buildSidePanel(nodeNames, true), BorderLayout.WEST);
        panel.add(buildSouthPanel(), BorderLayout.SOUTH);
        panel.add(buildCenterPanel(), BorderLayout.CENTER);

        frame.setContentPane(panel);
        panel.updateUI();
    }

    private void displayResult(String location1, String location2, boolean areAt2Distance) {
        if (location1.equals(location2)) {
            resultLabel.setText("Villes identiques");
        } else if (areAt2Distance) {
            resultLabel.setText(location1 + " et " + location2 + " sont à 2 distance");
        } else {
            resultLabel.setText(location1 + " et " + location2 + " ne sont pas à 2 distance");
        }
    }

}