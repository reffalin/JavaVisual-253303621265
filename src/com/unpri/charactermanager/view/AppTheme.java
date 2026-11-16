
package com.unpri.charactermanager.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class AppTheme {

    // Warna utama aplikasi
    public static final Color BACKGROUND = new Color(245, 247, 250);
    public static final Color TEXT = new Color(45, 52, 60);
    public static final Color GOLD = new Color(212, 160, 23);

    // Warna berdasarkan role karakter
    public static final Color WARRIOR = new Color(255, 220, 220);
    public static final Color MAGE = new Color(220, 230, 255);
    public static final Color ARCHER = new Color(220, 245, 225);
    public static final Color HEALER = new Color(255, 240, 210);

    // Font aplikasi
    public static final Font TITLE_FONT =
            new Font("SansSerif", Font.BOLD, 20);

    public static final Font NORMAL_FONT =
            new Font("SansSerif", Font.PLAIN, 14);

    public static final Font HIGHLIGHT_FONT =
            new Font("SansSerif", Font.BOLD, 14);

    // Border utama
    public static final Border PANEL_BORDER =
            BorderFactory.createEmptyBorder(15, 20, 15, 20);

    // Mengambil warna berdasarkan tipe karakter
    public static Color colorFor(String role) {
        switch (role.toUpperCase()) {
            case "WARRIOR":
                return WARRIOR;
            case "MAGE":
                return MAGE;
            case "ARCHER":
                return ARCHER;
            case "HEALER":
                return HEALER;
            default:
                return BACKGROUND;
        }
    }
}
