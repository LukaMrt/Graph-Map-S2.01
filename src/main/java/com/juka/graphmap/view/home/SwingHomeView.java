package com.juka.graphmap.view.home;

import com.juka.graphmap.domain.model.graph.GraphCharacteristics;
import com.juka.graphmap.ui.compare.CompareUI;
import com.juka.graphmap.ui.home.HomeView;
import com.juka.graphmap.view.SwingView;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

public class SwingHomeView extends SwingView implements HomeView {

    private final JFrame frame;
    private final CompareUI compareUI;

    @Inject
    public SwingHomeView(JFrame frame, CompareUI compareUI) {
        this.frame = frame;
        this.compareUI = compareUI;
    }

    @Override
    public void display(GraphCharacteristics graph) {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.frame.setContentPane(panel);

        panel.add(buildNorthPanel(graph.error), BorderLayout.NORTH);
        panel.add(buildLeftPanel(graph), BorderLayout.WEST);
        panel.add(buildCenterPanel(graph), BorderLayout.CENTER);
        panel.add(buildRightPanel(graph), BorderLayout.EAST);

        panel.updateUI();
    }

    private JPanel buildNorthPanel(String error) {
        JPanel panel = buildTitle("Accueil", 1);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel label = new JLabel(error);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(label);

        return panel;
    }

    private JPanel buildLeftPanel(GraphCharacteristics graph) {

        JPanel superPanel = new JPanel();
        superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.X_AXIS));

        superPanel.add(Box.createRigidArea(new Dimension(40, 0)));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());

        JLabel label = new JLabel("Le graphe contient " + graph.locationCount + " lieux dont :");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panel.add(label);

        label = new JLabel("   - " + Math.round(graph.cityPercentage * 10000) / 100.0 + " % de villes");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panel.add(label);

        label = new JLabel("   - " + Math.round(graph.restaurantPercentage * 10000) / 100.0 + " % de restaurants");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panel.add(label);

        label = new JLabel("   - " + Math.round(graph.recreationPercentage * 10000) / 100.0 + " % de centres de loisirs");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panel.add(label);

        panel.add(Box.createVerticalGlue());

        JButton button = new JButton("Quitter");
        button.setPreferredSize(new Dimension(200, 40));
        button.setAlignmentX(JButton.CENTER_ALIGNMENT);
        button.addActionListener(e -> System.exit(0));
        panel.add(button);
        panel.add(Box.createVerticalGlue());

        superPanel.add(panel);

        return superPanel;
    }

    private JPanel buildCenterPanel(GraphCharacteristics graph) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(Box.createGlue());

        if (!graph.error.contains("pas")) {
            ImageIcon image = new ImageIcon("graph.png");
            image.setImage(image.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH));
            JLabel label = new JLabel(image);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);
        }

        panel.add(Box.createGlue());

        return panel;
    }

    private JPanel buildRightPanel(GraphCharacteristics graph) {

        JPanel superPanel = new JPanel();
        superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.X_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());

        JLabel label = new JLabel("Le graphe contient " + graph.roadCount + " routes dont :");
        label.setAlignmentX(JButton.CENTER_ALIGNMENT);
        panel.add(label);

        label = new JLabel(" - " + graph.highwayPercentage + " % d'autoroutes");
        label.setAlignmentX(JButton.CENTER_ALIGNMENT);
        panel.add(label);

        label = new JLabel(" - " + graph.nationalPercentage + " % de routes nationales");
        label.setAlignmentX(JButton.CENTER_ALIGNMENT);
        panel.add(label);

        label = new JLabel(" - " + graph.departementalPercentage + " % de routes départementales");
        label.setAlignmentX(JButton.CENTER_ALIGNMENT);
        panel.add(label);

        panel.add(Box.createVerticalGlue());

        JButton button = new JButton("Écran n°2");
        button.setPreferredSize(new Dimension(200, 40));
        button.setAlignmentX(JButton.CENTER_ALIGNMENT);
        button.addActionListener(e -> compareUI.interact(null, null));
        panel.add(button);
        panel.add(Box.createVerticalGlue());

        superPanel.add(panel);
        superPanel.add(Box.createRigidArea(new Dimension(40, 0)));

        return superPanel;
    }

}
