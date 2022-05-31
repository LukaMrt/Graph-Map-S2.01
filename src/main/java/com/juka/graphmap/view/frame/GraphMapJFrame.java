package com.juka.graphmap.view.frame;

import com.formdev.flatlaf.FlatLightLaf;
import com.juka.graphmap.ui.home.HomeUI;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;

import static com.juka.graphmap.view.swing.components.ItemBuilder.anItem;
import static com.juka.graphmap.view.swing.components.MenuBarBuilder.aMenuBar;
import static com.juka.graphmap.view.swing.components.MenuBuilder.aMenu;

@Singleton
public class GraphMapJFrame extends JFrame {

    @Inject
    public GraphMapJFrame(HomeUI homeUI) {
        this.setTitle("GraphMap");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1300, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.setJMenuBar(buildJMenuBar(homeUI));
    }

    private JMenuBar buildJMenuBar(HomeUI homeUI) {

        return aMenuBar()
                .add(aMenu().withText("Allez à")
                        .add(anItem().withText("Accueil").withListener(e -> homeUI.interact()).build())
                        .add(anItem().withText("Quitter").withListener(e -> System.exit(0)).build())
                        .build())
                .add(aMenu().withText("À propos")
                        .add(anItem().withText("Auteurs").withListener(e -> JOptionPane.showMessageDialog(this, "Projet réalisé par Julien LINGET et Luka MARET", "À propos", JOptionPane.INFORMATION_MESSAGE)).build())
                        .build())
                .add(aMenu().withText("Aide")
                        .add(anItem().withText("Aide").build())
                        .build())
                .build();
    }

}
