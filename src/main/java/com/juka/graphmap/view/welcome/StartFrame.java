package com.juka.graphmap.view.welcome;

import com.juka.graphmap.main.Main;

import javax.swing.*;

import java.awt.event.ActionListener;

import static com.juka.graphmap.view.swing.components.ButtonBuilder.aButton;
import static com.juka.graphmap.view.swing.components.FileChooserBuilder.aFileChooser;
import static com.juka.graphmap.view.swing.components.LabelBuilder.aLabel;
import static com.juka.graphmap.view.swing.components.PanelBuilder.aPanel;

public class StartFrame extends JFrame {

    private final Main main;

    public StartFrame(Main main) {
        this.main = main;
    }

    public void display() {

        this.setTitle("GraphMap");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JFileChooser fileChooser = aFileChooser()
                .withSize(600, 400)
                .withDirectory(".")
                .build();

        this.setContentPane(aPanel()
                .withYBoxLayout()
                .addVerticalGlue()
                .add(aLabel().withText("Bienvenue sur le GraphMap").isBigTitle().isXCentered().build())
                .add(aLabel().withText("Choisissez le fichier du graphe et le mode d'affichage").isTitle().isXCentered().build())
                .addVerticalGlue()
                .add(fileChooser)
                .addVerticalGlue()
                .add(aPanel()
                        .withXBoxLayout()
                        .isXCentered()
                        .addHorizontalGlue()
                        .addHorizontalGlue()
                        .add(aButton()
                                .withText("Terminal")
                                .isYCentered()
                                .withSize(200, 60)
                                .withAction(getAction(ViewType.TERMINAl, fileChooser))
                                .build())
                        .addHorizontalGlue()
                        .add(aButton()
                                .withText("Interface graphique")
                                .isYCentered()
                                .withSize(200, 60)
                                .withAction(getAction(ViewType.GRAPHICAL_INTERFACE, fileChooser))
                                .build())
                        .addHorizontalGlue()
                        .addHorizontalGlue()
                        .build())
                .addVerticalGlue()
                .build());

        this.setVisible(true);
    }

    private ActionListener getAction(ViewType choice, JFileChooser fileChooser) {
        return e -> {
            super.dispose();
            main.start(choice, fileChooser.getSelectedFile().getAbsolutePath());
        };
    }

}
