package com.unpri.charactermanager.view;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    // Batas level untuk memberikan warna khusus
    private static final int GOLD_LEVEL_THRESHOLD = 5;

    public MainWindow(CharacterManager manager) {

        setTitle("Character Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel utama menggunakan BorderLayout
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(AppTheme.BACKGROUND);
        panel.setBorder(AppTheme.PANEL_BORDER);

        // Panel untuk judul
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(AppTheme.BACKGROUND);

        // Panel untuk daftar karakter
        JPanel characterPanel = new JPanel();
        characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.Y_AXIS));
        characterPanel.setBackground(AppTheme.BACKGROUND);

        // Judul aplikasi
        JLabel titleLabel = new JLabel("DAFTAR KARAKTER");
        titleLabel.setFont(AppTheme.TITLE_FONT);
        titleLabel.setForeground(AppTheme.TEXT);

        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Mengambil dan menampilkan data karakter
        ArrayList<Character> characters = manager.getAllCharacters();

        for (Character character : characters) {

            // Kartu karakter
            JPanel card = new JPanel();
            card.setLayout(new BorderLayout());
            card.setBackground(
                AppTheme.colorFor(character.getType().name())
            );
            card.setOpaque(true);
            card.setBorder(AppTheme.PANEL_BORDER);

            // Informasi nama dan role
            JLabel characterLabel = new JLabel(
                character.getName() + " | " +
                character.getType() + " | Level " +
                character.getLevel()
            );

            characterLabel.setFont(AppTheme.NORMAL_FONT);
            characterLabel.setForeground(AppTheme.TEXT);

            // Level 5 ke atas menggunakan teks emas dan tebal
            if (character.getLevel() >= GOLD_LEVEL_THRESHOLD) {
                characterLabel.setForeground(AppTheme.GOLD);
                characterLabel.setFont(AppTheme.HIGHLIGHT_FONT);
            }

            // Tooltip HP dan ATK
            card.setToolTipText(
                "HP: " + character.getHitPoints() +
                " | ATK: " + character.getAttack()
            );

            // Karakter level 1 tidak aktif
            if (character.getLevel() == 1) {
                characterLabel.setEnabled(false);
            }

            card.add(characterLabel, BorderLayout.CENTER);
            card.setAlignmentX(Component.LEFT_ALIGNMENT);

            characterPanel.add(card);
            characterPanel.add(Box.createVerticalStrut(10));
        }

        // Menampilkan rata-rata level
        JLabel averageLabel = new JLabel(
            "Rata-rata Level: " + manager.getAverageLevel()
        );

        averageLabel.setFont(AppTheme.HIGHLIGHT_FONT);
        averageLabel.setForeground(AppTheme.TEXT);

        characterPanel.add(Box.createVerticalStrut(10));
        characterPanel.add(averageLabel);

        // Panel pembungkus daftar karakter
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(AppTheme.BACKGROUND);

        wrapperPanel.add(characterPanel, BorderLayout.NORTH);

        // Membuat daftar karakter dapat digulir
        JScrollPane scroll = new JScrollPane(wrapperPanel);
        scroll.setPreferredSize(new Dimension(560, 360));

        // Panel legenda role
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));
        legendPanel.setBackground(AppTheme.BACKGROUND);
        legendPanel.setBorder(AppTheme.PANEL_BORDER);

        // Judul legenda
        JLabel legendTitle = new JLabel("LEGENDA ROLE");
        legendTitle.setFont(AppTheme.HIGHLIGHT_FONT);
        legendTitle.setForeground(AppTheme.TEXT);
        legendTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        legendPanel.add(legendTitle);
        legendPanel.add(Box.createVerticalStrut(15));

        // Mengambil daftar role dari enum CharacterType
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

        // Menempatkan panel pada wilayah BorderLayout
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(legendPanel, BorderLayout.EAST);

        add(panel);

        // Menyesuaikan ukuran jendela dengan isi
        pack();
        setLocationRelativeTo(null);
    }
}
