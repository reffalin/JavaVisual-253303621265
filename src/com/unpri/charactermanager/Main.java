
package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== CHARACTER MANAGER TP5 =====");

        CharacterManager manager = new CharacterManager();

        // Membuat karakter
        Character arthur = new Character("Arthur", CharacterType.WARRIOR, 1, 100, 10);
        Character merlin = new Character("Merlin", CharacterType.MAGE, 3, 300, 30);
        Character robin = new Character("Robin", CharacterType.ARCHER, 2, 200, 20);
        Character luna = new Character("Luna", CharacterType.MAGE, 4, 400, 40);

        // 1. ADD CHARACTER
        System.out.println("\n=== ADD CHARACTER ===");
        System.out.println("Arthur: " + manager.addCharacter(arthur));
        System.out.println("Merlin: " + manager.addCharacter(merlin));
        System.out.println("Robin: " + manager.addCharacter(robin));
        System.out.println("Luna: " + manager.addCharacter(luna));

        // 2. DUPLICATE TEST
        System.out.println("\n=== DUPLICATE TEST ===");
        Character duplicate = new Character("Arthur", CharacterType.MAGE, 5, 500, 50);
        System.out.println("Tambah Arthur duplikat: " + manager.addCharacter(duplicate));

        // 3. FIND BY NAME
        System.out.println("\n=== FIND BY NAME ===");
        System.out.println("Cari Merlin: " + manager.findByName("Merlin"));

        // 4. TOTAL CHARACTER
        System.out.println("\n=== TOTAL CHARACTER ===");
        System.out.println("Jumlah karakter: " + manager.getTotalCharacters());
        System.out.println("Total karakter pernah ditambahkan: "
                + CharacterManager.getTotalCharacterCreated());

        // 5. GET ALL CHARACTERS
        System.out.println("\n=== GET ALL CHARACTERS ===");
        ArrayList<Character> copy = manager.getAllCharacters();

        System.out.println("Jumlah dalam salinan: " + copy.size());

        copy.clear();

        System.out.println("Jumlah salinan setelah clear: " + copy.size());
        System.out.println("Jumlah asli setelah salinan diubah: "
                + manager.getTotalCharacters());

        // 6. AVERAGE LEVEL
        System.out.println("\n=== AVERAGE LEVEL ===");
        System.out.println("Rata-rata level: " + manager.getAverageLevel());

        // Menguji list kosong
        CharacterManager emptyManager = new CharacterManager();
        System.out.println("Rata-rata manager kosong: "
                + emptyManager.getAverageLevel());

        // 7. FIND BY TYPE
        System.out.println("\n=== FIND BY TYPE ===");
        ArrayList<Character> mages = manager.findByType(CharacterType.MAGE);

        for (Character character : mages) {
            System.out.println(character);
        }

        // 8. FITUR TAMBAHAN: FIND STRONGEST
        System.out.println("\n=== FIND STRONGEST ===");
        System.out.println("Karakter terkuat: " + manager.findStrongest());

        // 9. REMOVE CHARACTER
        System.out.println("\n=== REMOVE CHARACTER ===");
        System.out.println("Hapus Robin: " + manager.removeCharacter("Robin"));
        System.out.println("Jumlah setelah penghapusan: "
                + manager.getTotalCharacters());

        System.out.println("\n===== PROGRAM SELESAI =====");
    }
}
