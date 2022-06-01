package com.juka.graphmap.view.compare;

import com.juka.graphmap.domain.model.comparaison.Comparaison;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;
import com.juka.graphmap.domain.model.view.Title;
import com.juka.graphmap.ui.compare.CompareUI;
import com.juka.graphmap.ui.compare.CompareView;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.view.frame.GraphMapJFrame;
import com.juka.graphmap.view.swing.SwingView;
import com.juka.graphmap.view.swing.components.ScrollPaneBuilder;

import javax.inject.Inject;
import javax.swing.*;
import java.util.List;

import static com.juka.graphmap.view.swing.components.ButtonBuilder.aButton;
import static com.juka.graphmap.view.swing.components.LabelBuilder.aLabel;
import static com.juka.graphmap.view.swing.components.PanelBuilder.aPanel;
import static com.juka.graphmap.view.swing.components.ScrollPaneBuilder.anHorizontalList;

/**
 * The compare view with graphical interface.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class SwingCompareView extends SwingView implements CompareView {

    private final GraphUI graphUI;
    private final CompareUI compareUI;
    private List<String> cities;
    private List<Comparaison> result;
    private String city1;
    private String city2;

    /**
     * Constructor.
     *
     * @param frame the fram of the application
     * @param graphUI the graph ui
     * @param compareUI the compare ui
     */
    @Inject
    public SwingCompareView(GraphMapJFrame frame, GraphUI graphUI, CompareUI compareUI) {
        super(frame);
        this.graphUI = graphUI;
        this.compareUI = compareUI;
    }

    @Override
    public void display(List<Node> nodes, List<String> cities, List<Comparaison> result, String city1, String city2) {
        this.cities = cities;
        this.result = result;
        super.display(nodes);
        this.city1 = city1;
        this.city2 = city2;
    }

    @Override
    protected String getHelp() {
        return "Cet écran propose de comparer 2 villes (et uniquement des villes) selon " +
                "les lieux présents autour d'elles. Si une villes a plus d'autres villes à " +
                "2 distances d'elle que l'autre, alors elle est plus ouverte, si elle a plus " +
                "de restaurants elle est plus gastronomique et si elle a plus de lieux de loisirs " +
                "elle est plus culturelle. Vous pouvez sélectionner les villes dans la liste à gauche " +
                "et cliquer sur le bouton Analyser.\n\n" +
                "Pour l'interface graphique, les contrôles sont les suivants :\n" +
                "- Clic droit pour sélectionner le premier lieu\n" +
                "- Clic gauche pour sélectionner le second lieu";
    }

    @Override
    protected Title getTitle() {
        return new Title("Comparaison", 5);
    }

    @Override
    protected List<JButton> getButtons() {
        return List.of(aButton()
                .withText("Retour")
                .withSize(200, 50)
                .isYCentered()
                .withAction(e -> graphUI.interact())
                .build());
    }

    @Override
    protected JPanel buildLeftPanel() {

        String city1 = result.stream()
                .findFirst()
                .map(comparaison -> comparaison.best)
                .orElse("");

        String city2 = result.stream()
                .findFirst()
                .map(comparaison -> comparaison.worst)
                .orElse("");

        ScrollPaneBuilder builder1 = anHorizontalList()
                .withData(this.cities)
                .withSize(200, 200)
                .withSingleSelection()
                .withSelectedValue(city1)
                .isYCentered()
                .alwaysScrollVertical()
                .neverScrollHorizontal();

        JScrollPane nodeList1 = builder1
                .build();

        ScrollPaneBuilder builder2 = anHorizontalList()
                .withData(this.cities)
                .withSize(200, 200)
                .withSingleSelection()
                .withSelectedValue(city2)
                .isYCentered()
                .alwaysScrollVertical()
                .neverScrollHorizontal();

        JScrollPane nodeList2 = builder2
                .build();

        JButton button = aButton()
                .withText("Analyser")
                .withSize(100, 40)
                .isXCentered()
                .withAction(e -> compareUI.interact(builder1.getSelectedValue(), builder2.getSelectedValue()))
                .build();

        return aPanel()
                .withYBoxLayout()
                .isXCentered()
                .addRigidArea(0, 10)
                .add(aLabel().withText("Première ville : ").isXCentered().isTitle().build())
                .addRigidArea(0, 5)
                .add(nodeList1)
                .addVerticalGlue()
                .add(aLabel().withText("Seconde ville : ").isXCentered().isTitle().build())
                .addRigidArea(0, 5)
                .add(nodeList2)
                .addVerticalGlue()
                .add(button)
                .addVerticalGlue()
                .build();
    }

    @Override
    protected JPanel buildRightPanel() {

        List<JLabel> comparaisons = result.stream()
                .map(comparaison -> aLabel().withText(comparaison.toShortString()).isText().isXCentered().build())
                .toList();

        return aPanel()
                .withYBoxLayout()
                .addVerticalGlue()
                .add(aLabel().withText("Résultat :").isTitle().isXCentered().build())
                .addAll(comparaisons)
                .addVerticalGlue()
                .build();
    }

    @Override
    public void leftClick(Node node, Link link) {

        String newNode = city1;

        if ((node != null && node.getType() == NodeType.CITY)) {
            newNode = node.getName();
        }

        compareUI.interact(newNode, city2);
    }

    @Override
    public void rightClick(Node node, Link link) {

        String newNode = city2;

        if ((node != null && node.getType() == NodeType.CITY)) {
            newNode = node.getName();
        }

        compareUI.interact(city1, newNode);
    }

}
