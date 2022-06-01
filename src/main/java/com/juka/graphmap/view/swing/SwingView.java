package com.juka.graphmap.view.swing;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.view.Title;
import com.juka.graphmap.view.frame.GraphMapJFrame;
import com.juka.graphmap.view.swing.components.SwingGraphPanel;

import javax.swing.*;

import java.awt.*;
import java.util.List;

import static com.juka.graphmap.view.swing.components.LabelBuilder.aLabel;
import static com.juka.graphmap.view.swing.components.PanelBuilder.aPanel;

/**
 * Global swing view. Regroup all the methods to build any view.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public abstract class SwingView {

    /**
     * The frame of the application.
     */
    protected final GraphMapJFrame frame;

    /**
     * Constructor.
     *
     * @param frame the frame of the application
     */
    protected SwingView(GraphMapJFrame frame) {
        this.frame = frame;
    }

    /**
     * Display the view.
     *
     * @param nodes the nodes of the graph
     */
    public void display(List<Node> nodes) {

        this.frame.setContentPane(
                aPanel().withBorderLayout()
                        .add(buildTitle(), BorderLayout.NORTH)
                        .add(buildSidePanel(buildLeftPanel()), BorderLayout.WEST)
                        .add(new SwingGraphPanel(nodes, this), BorderLayout.CENTER)
                        .add(buildSidePanel(buildRightPanel()), BorderLayout.EAST)
                        .add(buildBottomPanel(), BorderLayout.SOUTH)
                        .build()
        );

        JMenu menu = this.frame.getJMenuBar().getMenu(2);
        JMenuItem item = menu.getItem(0);

        menu.remove(item);

        item = new JMenuItem("Consignes de cet écran");
        item.addActionListener(e -> JOptionPane.showMessageDialog(this.frame, this.getHelp(), "Aide", JOptionPane.INFORMATION_MESSAGE));
        menu.add(item);

        ((JPanel) this.frame.getContentPane()).updateUI();
    }

    /**
     * Get the help message of the screen.
     *
     * @return the help message
     */
    protected abstract String getHelp();

    private JPanel buildSidePanel(JPanel panel) {
        return aPanel()
                .withSize(300, 600)
                .withXBoxLayout()
                .isYCentered()
                .addHorizontalGlue()
                .add(panel)
                .addHorizontalGlue()
                .build();
    }

    /**
     * Build the title of the screen.
     *
     * @return the title panel of the screen
     */
    protected JPanel buildTitle() {

        return aPanel()
                .withYBoxLayout()
                .addRigidArea(0, 10)
                .isXCentered()
                .add(aLabel().withText("~ Écran n°" + getTitle().screenNumber + " ~")
                        .isBigTitle()
                        .isXCentered()
                        .build())
                .add(aLabel().withText(getTitle().title)
                        .isSubBigTitle()
                        .isXCentered()
                        .build())
                .addRigidArea(0, 10)
                .build();
    }

    private JPanel buildBottomPanel() {
        return aPanel()
                .withSize(1300, 100)
                .withXBoxLayout()
                .addHorizontalGlue()
                .isYCentered()
                .addAllFollowedByHorizontalGlue(getButtons())
                .build();
    }

    /**
     * Get the Title of the screen.
     *
     * @return the title of the screen
     */
    protected abstract Title getTitle();

    /**
     * Get the buttons of the screen.
     *
     * @return the buttons of the screen
     */
    protected abstract List<JButton> getButtons();

    /**
     * Build the left panel of the screen.
     *
     * @return the left panel of the screen
     */
    protected abstract JPanel buildLeftPanel();

    /**
     * Build the right panel of the screen.
     *
     * @return the right panel of the screen
     */
    protected abstract JPanel buildRightPanel();

    /**
     * Action to perform when the left mouse button is clicked.
     *
     * @param node the clicked node
     * @param link the clicked link
     */
    public void leftClick(Node node, Link link) {
    }

    /**
     * Action to perform when the right mouse button is clicked.
     *
     * @param node the clicked node
     * @param link the clicked link
     */
    public void rightClick(Node node, Link link) {
    }

    /**
     * Action to perform when the left mouse button is clicked with the shift key pressed.
     *
     * @param node the clicked node
     * @param link the clicked link
     */
    public void shiftClick(Node node, Link link) {
    }

}
