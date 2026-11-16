
package com.unpri.charactermanager.view;

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

        // Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(AppTheme.BACKGROUND);
        panel.setBorder(AppTheme.PANEL_BORDER);

        // Judul aplikasi
        JLabel titleLabel = new JLabel("DAFTAR KARAKTER");
        titleLabel.setFont(AppTheme.TITLE_FONT);
        titleLabel.setForeground(AppTheme.TEXT);
        panel.add(titleLabel);

        panel.add(Box.createVerticalStrut(15));

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

            // Properti tambahan 1: tooltip HP dan ATK
            card.setToolTipText(
                "HP: " + character.getHitPoints() +
                " | ATK: " + character.getAttack()
            );

            // Properti tambahan 2: karakter level 1 tidak aktif
            if (character.getLevel() == 1) {
                characterLabel.setEnabled(false);
            }

            card.add(characterLabel, BorderLayout.CENTER);
            card.setAlignmentX(Component.LEFT_ALIGNMENT);

            panel.add(card);
            panel.add(Box.createVerticalStrut(10));
        }

        // Menampilkan rata-rata level
        JLabel averageLabel = new JLabel(
            "Rata-rata Level: " + manager.getAverageLevel()
        );

        averageLabel.setFont(AppTheme.HIGHLIGHT_FONT);
        averageLabel.setForeground(AppTheme.TEXT);

        panel.add(Box.createVerticalStrut(10));
        panel.add(averageLabel);

        add(new JScrollPane(panel));

        // Menyesuaikan ukuran jendela dengan isi
        pack();
        setLocationRelativeTo(null);
    }
}
