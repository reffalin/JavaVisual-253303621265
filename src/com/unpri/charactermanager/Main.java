
package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== CHARACTER MANAGER ===");

        // Membuat tiga karakter
        Character character1 = new Character(
                "Arthur", CharacterType.WARRIOR, 1, 100, 10);

        Character character2 = new Character(
                "Merlin", CharacterType.MAGE, 3, 300, 30);

        Character character3 = new Character(
                "Robin", CharacterType.ARCHER, 2, 200, 20);

        // Menampilkan tiga karakter
        System.out.println("\n=== DAFTAR KARAKTER ===");
        System.out.println(character1);
        System.out.println(character2);
        System.out.println(character3);

        // Demo level up
        System.out.println("\n=== DEMO LEVEL UP ===");
        System.out.println("Level Arthur sebelum: " + character1.getLevel());

        character1.setLevel(2);

        System.out.println("Level Arthur sesudah: " + character1.getLevel());
        System.out.println("HP Arthur: " + character1.getHitPoints());
        System.out.println("Attack Arthur: " + character1.getAttack());

        // Demo fitur mana dan isAlive
        System.out.println("\n=== MINI CHALLENGE ===");

        Character character4 = new Character(
                "Luna", CharacterType.MAGE, 4, 400, 40);

        character4.setMana(150);

        System.out.println(character4);
        System.out.println("Mana Luna: " + character4.getMana());
        System.out.println("Apakah Luna masih hidup? " + character4.isAlive());

        System.out.println("\n=== PROGRAM SELESAI ===");
    }
}
