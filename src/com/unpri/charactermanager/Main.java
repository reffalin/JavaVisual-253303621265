
package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        CharacterManager manager = new CharacterManager();

        // Membuat data karakter
        manager.addCharacter(new Character("Arthur", CharacterType.WARRIOR, 1, 100, 10));
        manager.addCharacter(new Character("Merlin", CharacterType.MAGE, 3, 300, 30));
        manager.addCharacter(new Character("Robin", CharacterType.ARCHER, 2, 200, 20));
        manager.addCharacter(new Character("Luna", CharacterType.MAGE, 4, 400, 40));
        manager.addCharacter(new Character("Thor", CharacterType.WARRIOR, 8, 800, 80));
        manager.addCharacter(new Character("Freya", CharacterType.HEALER, 2, 200, 20));
        manager.addCharacter(new Character("Brutus", CharacterType.WARRIOR, 6, 600, 60));
        manager.addCharacter(new Character("Iris", CharacterType.HEALER, 5, 500, 50));

        // Menjalankan GUI melalui Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow(manager);
            window.setVisible(true);
        });
    }
}