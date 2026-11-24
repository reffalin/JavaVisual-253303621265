
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    // Batas level untuk memberikan warna khusus
    private static final int GOLD_LEVEL_THRESHOLD = 5;

    private final CharacterManager manager;
    private JPanel characterPanel;
    private JLabel totalLabel;
    private JLabel averageLabel;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel utama
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(AppTheme.BACKGROUND);
        panel.setBorder(AppTheme.PANEL_BORDER);

        // Panel header
        JPanel headerPanel = new JPanel(new BorderLayout(10, 10));
        headerPanel.setBackground(AppTheme.BACKGROUND);

        JLabel titleLabel = new JLabel("CHARACTER MANAGER");
        titleLabel.setFont(AppTheme.TITLE_FONT);
        titleLabel.setForeground(AppTheme.TEXT);

        headerPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel statistik
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        statsPanel.setBackground(AppTheme.BACKGROUND);

        totalLabel = new JLabel();
        averageLabel = new JLabel();

        JLabel roleLabel = new JLabel(
            "Jumlah Role: " + CharacterType.values().length
        );

        JLabel versionLabel = new JLabel("Layout: v0.5");

        JLabel[] statistics = {
            totalLabel, averageLabel, roleLabel, versionLabel
        };

        for (JLabel statistic : statistics) {
            statistic.setFont(AppTheme.NORMAL_FONT);
            statistic.setForeground(AppTheme.TEXT);
            statistic.setBorder(AppTheme.PANEL_BORDER);
            statsPanel.add(statistic);
        }

        headerPanel.add(statsPanel, BorderLayout.SOUTH);

        // Panel form karakter
        JPanel formContainer = new JPanel(new BorderLayout());
        formContainer.setBackground(AppTheme.BACKGROUND);
        formContainer.setBorder(
            BorderFactory.createTitledBorder("Tambah Karakter")
        );

        CharacterFormPanel formPanel =
            new CharacterFormPanel(manager, this);

        formContainer.add(formPanel, BorderLayout.CENTER);

        // Panel daftar karakter
        characterPanel = new JPanel();
        characterPanel.setLayout(
            new BoxLayout(characterPanel, BoxLayout.Y_AXIS)
        );
        characterPanel.setBackground(AppTheme.BACKGROUND);

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(AppTheme.BACKGROUND);
        wrapperPanel.add(characterPanel, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(wrapperPanel);
        scroll.setPreferredSize(new Dimension(560, 360));

        // Panel tengah
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(AppTheme.BACKGROUND);
        centerPanel.add(formContainer, BorderLayout.NORTH);
        centerPanel.add(scroll, BorderLayout.CENTER);

        // Panel legenda role
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(
            new BoxLayout(legendPanel, BoxLayout.Y_AXIS)
        );
        legendPanel.setBackground(AppTheme.BACKGROUND);
        legendPanel.setBorder(AppTheme.PANEL_BORDER);

        JLabel legendTitle = new JLabel("LEGENDA ROLE");
        legendTitle.setFont(AppTheme.HIGHLIGHT_FONT);
        legendTitle.setForeground(AppTheme.TEXT);
        legendTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        legendPanel.add(legendTitle);
        legendPanel.add(Box.createVerticalStrut(15));

        for (CharacterType type : CharacterType.values()) {

            JPanel legendItem = new JPanel(new BorderLayout());
            legendItem.setBackground(AppTheme.colorFor(type.name()));
            legendItem.setBorder(AppTheme.PANEL_BORDER);

            JLabel roleNameLabel = new JLabel(type.name());
            roleNameLabel.setFont(AppTheme.NORMAL_FONT);
            roleNameLabel.setForeground(AppTheme.TEXT);

            legendItem.add(roleNameLabel, BorderLayout.CENTER);
            legendPanel.add(legendItem);
            legendPanel.add(Box.createVerticalStrut(10));
        }

        legendPanel.add(Box.createVerticalGlue());

        // Panel status bar
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(AppTheme.BACKGROUND);
        statusPanel.setBorder(AppTheme.PANEL_BORDER);

        JLabel statusLabel = new JLabel(
            "Character Manager - Siap digunakan"
        );

        JLabel appVersionLabel = new JLabel("v0.5");

        statusLabel.setFont(AppTheme.NORMAL_FONT);
        statusLabel.setForeground(AppTheme.TEXT);

        appVersionLabel.setFont(AppTheme.NORMAL_FONT);
        appVersionLabel.setForeground(AppTheme.TEXT);

        statusPanel.add(statusLabel, BorderLayout.WEST);
        statusPanel.add(appVersionLabel, BorderLayout.EAST);

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(legendPanel, BorderLayout.EAST);
        panel.add(statusPanel, BorderLayout.SOUTH);

        add(panel);

        refreshList();

        pack();
        setLocationRelativeTo(null);
    }

    // Memperbarui daftar karakter dan statistik
    public void refreshList() {

        characterPanel.removeAll();

        ArrayList<Character> characters = manager.getAllCharacters();

        for (Character character : characters) {

            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(
                AppTheme.colorFor(character.getType().name())
            );
            card.setOpaque(true);
            card.setBorder(AppTheme.PANEL_BORDER);

            JLabel characterLabel = new JLabel(
                character.getName() + " | " +
                character.getType() + " | Level " +
                character.getLevel()
            );

            characterLabel.setFont(AppTheme.NORMAL_FONT);
            characterLabel.setForeground(AppTheme.TEXT);

            if (character.getLevel() >= GOLD_LEVEL_THRESHOLD) {
                characterLabel.setForeground(AppTheme.GOLD);
                characterLabel.setFont(AppTheme.HIGHLIGHT_FONT);
            }

            card.setToolTipText(
                "HP: " + character.getHitPoints() +
                " | ATK: " + character.getAttack()
            );

            if (character.getLevel() == 1) {
                characterLabel.setEnabled(false);
            }

            card.add(characterLabel, BorderLayout.CENTER);
            card.setAlignmentX(Component.LEFT_ALIGNMENT);

            characterPanel.add(card);
            characterPanel.add(Box.createVerticalStrut(10));
        }

        totalLabel.setText(
            "Total Karakter: " + characters.size()
        );

        averageLabel.setText(
            "Rata-rata Level: " + manager.getAverageLevel()
        );

        characterPanel.revalidate();
        characterPanel.repaint();
    }
}
