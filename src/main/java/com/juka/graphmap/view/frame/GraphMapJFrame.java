package com.juka.graphmap.view.frame;

import com.formdev.flatlaf.FlatLightLaf;
import com.juka.graphmap.ui.home.HomeUI;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;

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
        JMenuBar jMenuBar = new JMenuBar();

        JMenu menu = new JMenu("Quitter");
        JMenuItem item = new JMenuItem("Revenir à l'accueil");
        item.addActionListener(e -> homeUI.interact());
        menu.add(item);
        item = new JMenuItem("Quitter");
        item.addActionListener(e -> System.exit(0));
        menu.add(item);
        jMenuBar.add(menu);

        menu = new JMenu("À propos");
        item = new JMenuItem("Auteurs");
        item.addActionListener(e -> JOptionPane.showMessageDialog(this, "Projet réalisé par Julien LINGET et Luka MARET", "À propos", JOptionPane.INFORMATION_MESSAGE));
        menu.add(item);
        jMenuBar.add(menu);

        menu = new JMenu("Aide");
        item = new JMenuItem("Consignes de cet écran");
        menu.add(item);
        jMenuBar.add(menu);

        return jMenuBar;
    }

}
