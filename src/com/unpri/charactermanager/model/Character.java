
package com.unpri.charactermanager.model;

import java.util.Objects;

public class Character {

    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attack;
    private int mana;

    // Constructor pertama
    public Character() {
        this.name = "Unknown";
        this.type = CharacterType.WARRIOR;
        this.level = 1;
        this.hitPoints = 100;
        this.attack = 10;
        this.mana = 0;
    }

    // Constructor kedua
    public Character(String name, CharacterType type, int level, int hitPoints, int attack) {
        setName(name);
        setType(type);
        setLevel(level);

        if (hitPoints < 0 || attack < 0) {
            throw new IllegalArgumentException("HP dan attack tidak boleh negatif.");
        }

        this.hitPoints = hitPoints;
        this.attack = attack;
        this.mana = 0;
    }

    // Getter dan Setter Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama karakter tidak boleh kosong.");
        }

        this.name = name.trim();
    }

    // Getter dan Setter Type
    public CharacterType getType() {
        return type;
    }

    public void setType(CharacterType type) {
        if (type == null) {
            throw new IllegalArgumentException("Role karakter harus dipilih.");
        }

        if (type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException("MAGE tidak boleh memiliki level di atas 60.");
        }

        this.type = type;
    }

    // Getter dan Setter Level
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level < 1 || level > 99) {
            throw new IllegalArgumentException("Level harus berada di antara 1 sampai 99.");
        }

        if (type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException("MAGE tidak boleh memiliki level di atas 60.");
        }

        this.level = level;
        this.hitPoints = level * 100;
        this.attack = level * 10;
    }

    // Getter dan Setter HitPoints
    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        if (hitPoints < 0) {
            throw new IllegalArgumentException("HP tidak boleh negatif.");
        }
        this.hitPoints = hitPoints;
    }

    // Getter dan Setter Attack
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        if (attack < 0) {
            throw new IllegalArgumentException("Attack tidak boleh negatif.");
        }
        this.attack = attack;
    }

    // Getter dan Setter Mana
    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException("Mana tidak boleh negatif.");
        }
        this.mana = mana;
    }

    // Mengecek karakter masih hidup
    public boolean isAlive() {
        return hitPoints > 0;
    }

    // Membandingkan karakter berdasarkan nama
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Character character = (Character) obj;
        return Objects.equals(name, character.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    // Menampilkan informasi karakter
    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", level=" + level +
                ", hitPoints=" + hitPoints +
                ", attack=" + attack +
                ", mana=" + mana +
                '}';
    }
}
