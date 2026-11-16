
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    public MainWindow(CharacterManager manager) {

        // Mengatur jendela utama
        setTitle("Character Manager");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Judul aplikasi
        JLabel titleLabel = new JLabel("DAFTAR KARAKTER");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel);

        panel.add(Box.createVerticalStrut(15));

        // Menampilkan karakter secara dinamis
        ArrayList<Character> characters = manager.getAllCharacters();

        for (Character character : characters) {
            JLabel characterLabel = new JLabel(
                character.getName() + " | " +
                character.getType() + " | Level " +
                character.getLevel()
            );

            characterLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            panel.add(characterLabel);
            panel.add(Box.createVerticalStrut(8));
        }

        // Menampilkan rata-rata level
        panel.add(Box.createVerticalStrut(15));

        JLabel averageLabel = new JLabel(
            "Rata-rata Level: " + manager.getAverageLevel()
        );

        averageLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(averageLabel);

        add(new JScrollPane(panel));
    }
}
