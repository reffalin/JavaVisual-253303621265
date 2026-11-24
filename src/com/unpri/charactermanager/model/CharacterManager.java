
package com.unpri.charactermanager.model;

import java.util.ArrayList;

public class CharacterManager {

    private ArrayList<Character> characters;
    private static int totalCharacterCreated = 0;

    // Constructor
    public CharacterManager() {
        characters = new ArrayList<>();
    }
    // Mengecek apakah nama karakter sudah digunakan
    public boolean isNameTaken(String name) {
    if (name == null) {
        return false;
    }

    return findByName(name.trim()) != null;
}

    // Menambahkan karakter dan menolak nama duplikat
    public boolean addCharacter(Character character) {
        if (character == null || characters.contains(character)) {
            return false;
        }

        characters.add(character);
        totalCharacterCreated++;
        return true;
    }

    // Mencari karakter berdasarkan nama
    public Character findByName(String name) {
        for (Character character : characters) {
            if (character.getName().equalsIgnoreCase(name)) {
                return character;
            }
        }
        return null;
    }

    // Menghapus karakter berdasarkan nama
    public boolean removeCharacter(String name) {
        Character character = findByName(name);

        if (character != null) {
            characters.remove(character);
            return true;
        }

        return false;
    }

    // Menghitung jumlah karakter dalam manager
    public int getTotalCharacters() {
        return characters.size();
    }

    // Mengembalikan salinan daftar karakter
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }

    // Menghitung rata-rata level karakter
    public double getAverageLevel() {
        if (characters.isEmpty()) {
            return 0;
        }

        int totalLevel = 0;

        for (Character character : characters) {
            totalLevel += character.getLevel();
        }

        return (double) totalLevel / characters.size();
    }

    // Mencari karakter berdasarkan tipe
    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> result = new ArrayList<>();

        for (Character character : characters) {
            if (character.getType() == type) {
                result.add(character);
            }
        }

        return result;
    }

    /**
     * Mencari karakter dengan attack tertinggi.
     * Mengembalikan null jika daftar karakter kosong.
     */
    public Character findStrongest() {
        if (characters.isEmpty()) {
            return null;
        }

        Character strongest = characters.get(0);

        for (Character character : characters) {
            if (character.getAttack() > strongest.getAttack()) {
                strongest = character;
            }
        }

        return strongest;
    }

    // Mengambil total karakter yang pernah berhasil ditambahkan
    public static int getTotalCharacterCreated() {
        return totalCharacterCreated;
    }
}
