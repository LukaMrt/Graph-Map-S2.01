package com.juka.graphmap.view.swing;

import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.view.Title;
import com.juka.graphmap.view.frame.GraphMapJFrame;
import com.juka.graphmap.view.swing.components.SwingGraphPanel;

import javax.swing.*;

import java.awt.*;
import java.util.List;

import static com.juka.graphmap.view.swing.components.LabelBuilder.aLabel;
import static com.juka.graphmap.view.swing.components.PanelBuilder.aPanel;

public abstract class GlobalSwingView {

    private final GraphMapJFrame frame;

    protected GlobalSwingView(GraphMapJFrame frame) {
        this.frame = frame;
    }

    public void display(List<Node> nodes) {

        this.frame.setContentPane(
                aPanel().withBorderLayout()
                        .add(buildTitle(), BorderLayout.NORTH)
                        .add(buildSidePanel(buildLeftPanel()), BorderLayout.WEST)
                        .add(new SwingGraphPanel(nodes), BorderLayout.CENTER)
                        .add(buildSidePanel(buildRightPanel()), BorderLayout.EAST)
                        .add(buildBottomPanel(), BorderLayout.SOUTH)
                        .build()
        );

        ((JPanel) this.frame.getContentPane()).updateUI();
    }

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

    protected abstract Title getTitle();

    protected abstract List<JButton> getButtons();

    protected abstract JPanel buildLeftPanel();

    protected abstract JPanel buildRightPanel();

}
