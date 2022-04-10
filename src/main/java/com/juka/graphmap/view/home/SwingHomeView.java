package com.juka.graphmap.view.home;

import com.juka.graphmap.domain.model.graph.GraphCharacteristics;
import com.juka.graphmap.ui.home.HomeView;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

public class SwingHomeView implements HomeView {

    private final JFrame frame;

    @Inject
    public SwingHomeView(JFrame frame) {
        this.frame = frame;
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

    private JPanel buildNorthPanel(boolean error) {
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel label = new JLabel("~ Écran n°1 ~");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        panel.add(label);

        label = new JLabel("Accueil");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 35));
        panel.add(label);


        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        String text = "Le graphe a été correctement chargé";
        if (error) {
            text = "Le graphe n'a pas pu être chargé correctement";
        }
        label = new JLabel(text);
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
        panel.add(label);

        label = new JLabel("   - " + Math.round(graph.cityPercentage * 10000) / 100.0 + " % de villes");
        panel.add(label);

        label = new JLabel("   - " + Math.round(graph.restaurantPercentage * 10000) / 100.0 + " % de restaurants");
        panel.add(label);

        label = new JLabel("   - " + Math.round(graph.recreationPercentage * 10000) / 100.0 + " % de centres de loisirs");
        panel.add(label);

        panel.add(Box.createVerticalGlue());
        panel.add(Box.createVerticalGlue());
        panel.add(Box.createVerticalGlue());
        panel.add(Box.createVerticalGlue());

        JButton button = new JButton("Retour");
        button.setAlignmentX(JButton.RIGHT_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 40));
        panel.add(button);
        panel.add(Box.createVerticalGlue());

        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        superPanel.add(panel);

        return superPanel;
    }

    private JPanel buildCenterPanel(GraphCharacteristics graph) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(Box.createGlue());

        if (!graph.error) {
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
        panel.add(label);

        label = new JLabel(" - " + Math.round(graph.highwayPercentage * 10000) / 100.0 + " % d'autoroutes");
        panel.add(label);

        label = new JLabel(" - " + Math.round(graph.nationalPercentage * 10000) / 100.0 + " % de routes nationales");
        panel.add(label);

        label = new JLabel(" - " + Math.round(graph.departementalPercentage * 10000) / 100.0 + " % de routes départementales");
        panel.add(label);

        panel.add(Box.createVerticalGlue());
        panel.add(Box.createVerticalGlue());

        superPanel.add(panel);
        superPanel.add(Box.createRigidArea(new Dimension(40, 0)));

        return superPanel;
    }

}
