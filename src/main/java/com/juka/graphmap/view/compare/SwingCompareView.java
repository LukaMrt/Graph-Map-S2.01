package com.juka.graphmap.view.compare;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.compare.CompareUI;
import com.juka.graphmap.ui.compare.CompareView;
import com.juka.graphmap.view.SwingView;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class SwingCompareView extends SwingView implements CompareView {

    private final JFrame frame;
    private final CompareUI compareUI;

    @Inject
    public SwingCompareView(JFrame frame, CompareUI compareUI) {
        this.frame = frame;
        this.compareUI = compareUI;
    }

    @Override
    public void display(List<Node> cities) {

        JPanel panel = (JPanel) frame.getContentPane();
        panel.removeAll();
        panel.updateUI();
        panel.setLayout(new BorderLayout());

        panel.add(buildTitle("Comparaison", 5), BorderLayout.NORTH);
        panel.add(buildCenterPanel(cities), BorderLayout.CENTER);

        panel.updateUI();
    }

    private JPanel buildCenterPanel(List<Node> cities) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 11));

        panel2.add(new JLabel());
        panel2.add(new JLabel());

        JLabel label = new JLabel("Ville 1 :");
        panel2.add(label);

        JComboBox<String> comboBox = new JComboBox<>();
        cities.stream().map(Node::getName).forEach(comboBox::addItem);
        panel2.add(comboBox);

        panel2.add(new JLabel());
        panel2.add(new JLabel());
        panel2.add(new JLabel());

        label = new JLabel("Ville 2 :");
        panel2.add(label);

        JComboBox<String> comboBox2 = new JComboBox<>();
        cities.stream().map(Node::getName).forEach(comboBox2::addItem);
        panel2.add(comboBox2);

        panel2.add(new JLabel());
        panel2.add(new JLabel());

        panel2.add(new JLabel());
        panel2.add(new JLabel());
        panel2.add(new JLabel());
        panel2.add(new JLabel());
        panel2.add(new JLabel());

        JPanel panel3 = new JPanel();
        JButton button = new JButton("Comparer");
        button.addActionListener(e -> compareUI.compare(comboBox.getSelectedItem().toString(), comboBox2.getSelectedItem().toString()));
        button.setMaximumSize(new Dimension(100, 30));
        panel3.add(button);
        panel2.add(panel3);

        panel2.add(new JLabel());
        panel2.add(new JLabel());
        panel2.add(new JLabel());
        panel2.add(new JLabel());
        panel2.add(new JLabel());

        panel.add(panel2);
        panel.add(Box.createGlue());

        return panel;
    }

    @Override
    public void displayNodes(NodeRepository nodeRepository) {

    }

    @Override
    public void displayComparaison(Map<String, Boolean> comparaison, Node node1, Node node2) {

        JPanel panel = (JPanel) frame.getContentPane();
        panel.removeAll();
        panel.updateUI();
        panel.setLayout(new BorderLayout());

        panel.add(buildTitle("Comparaison", 5), BorderLayout.NORTH);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

        panel2.add(Box.createGlue());

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

        panel3.add(Box.createGlue());
        panel3.add(Box.createGlue());
        panel3.add(Box.createGlue());
        panel3.add(Box.createGlue());
        panel3.add(Box.createGlue());

        JLabel label = new JLabel("Résultat de la comparaison :");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.add(label);

        panel3.add(Box.createGlue());

        label = new JLabel((comparaison.get("open") ? node1.getName() : node2.getName())
                + " est plus ouverte que "
                + (comparaison.get("open") ? node2.getName() : node1.getName()));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.add(label);

        label = new JLabel((comparaison.get("open") ? node1.getName() : node2.getName())
                + " est plus gastronomique que "
                + (comparaison.get("open") ? node2.getName() : node1.getName()));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.add(label);

        label = new JLabel((comparaison.get("open") ? node1.getName() : node2.getName())
                + " est plus culturelle que "
                + (comparaison.get("open") ? node2.getName() : node1.getName()));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.add(label);

        panel3.add(Box.createGlue());
        panel3.add(Box.createGlue());
        panel3.add(Box.createGlue());
        panel3.add(Box.createGlue());
        panel3.add(Box.createGlue());

        panel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.setAlignmentY(Component.CENTER_ALIGNMENT);

        panel2.add(panel3);
        panel2.add(Box.createGlue());

        panel.add(panel2, BorderLayout.CENTER);

        panel.updateUI();
    }

}
