
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.ParseException;

public class CharacterFormPanel extends JPanel {

    private final CharacterManager manager;
    private final MainWindow mainWindow;

    private final JTextField nameField;
    private final JComboBox<CharacterType> roleComboBox;
    private final JSpinner levelSpinner;
    private final JLabel messageLabel;

    private final Border normalNameBorder;
    private final Border normalLevelBorder;

    public CharacterFormPanel(
        CharacterManager manager,
        MainWindow mainWindow
    ) {
        this.manager = manager;
        this.mainWindow = mainWindow;

        setLayout(new GridLayout(5, 2, 10, 10));
        setBackground(AppTheme.BACKGROUND);
        setBorder(AppTheme.PANEL_BORDER);

        JLabel nameLabel = new JLabel("Nama Karakter:");
        nameField = new JTextField(15);
        normalNameBorder = nameField.getBorder();

        JLabel roleLabel = new JLabel("Role:");
        roleComboBox = new JComboBox<>(CharacterType.values());

        JLabel levelLabel = new JLabel("Level:");
        levelSpinner = new JSpinner(
            new SpinnerNumberModel(1, 1, 99, 1)
        );
        normalLevelBorder = levelSpinner.getBorder();

        JButton addButton = new JButton("Tambah Karakter");
        messageLabel = new JLabel(" ");

        add(nameLabel);
        add(nameField);
        add(roleLabel);
        add(roleComboBox);
        add(levelLabel);
        add(levelSpinner);
        add(addButton);
        add(messageLabel);

        addButton.addActionListener(e -> addCharacter());
    }

    // Memeriksa nama sesuai urutan validasi
    private String validateName(String name) {

        // 1. Kelengkapan
        if (name.isEmpty()) {
            return "Nama karakter wajib diisi.";
        }

        // 2. Panjang nama
        if (name.length() < 3 || name.length() > 20) {
            return "Nama harus terdiri dari 3 sampai 20 karakter.";
        }

        // 3. Format nama
        if (!name.matches("[a-zA-Z]+( [a-zA-Z]+)*")) {
            return "Nama hanya boleh berisi huruf dan satu spasi.";
        }

        // 4. Keunikan nama
        if (manager.isNameTaken(name)) {
            return "Nama karakter sudah digunakan.";
        }

        return null;
    }

    // Menambahkan karakter setelah input dinyatakan valid
    private void addCharacter() {

        nameField.setBorder(normalNameBorder);
        levelSpinner.setBorder(normalLevelBorder);

        String name = nameField.getText().trim();
        String error = validateName(name);

        if (error != null) {
            showError(error, nameField);
            return;
        }

        int level;

        try {
            levelSpinner.commitEdit();
            level = (Integer) levelSpinner.getValue();
        } catch (ParseException ex) {
            showError("Level harus berupa angka.", levelSpinner);
            return;
        }

        CharacterType type =
            (CharacterType) roleComboBox.getSelectedItem();

        try {
            Character character = new Character(
                name,
                type,
                level,
                level * 100,
                level * 10
            );

            if (!manager.addCharacter(character)) {
                showError("Karakter gagal ditambahkan.", nameField);
                return;
            }

            messageLabel.setForeground(new Color(0, 128, 0));
            messageLabel.setText("Karakter berhasil ditambahkan.");

            nameField.setText("");
            levelSpinner.setValue(1);

            mainWindow.refreshList();

        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage(), levelSpinner);
        }
    }

    // Menampilkan pesan kesalahan pada form
    private void showError(String message, JComponent component) {

        messageLabel.setForeground(Color.RED);
        messageLabel.setText(message);

        component.setBorder(
            BorderFactory.createLineBorder(Color.RED)
        );

        component.requestFocusInWindow();
    }
}
