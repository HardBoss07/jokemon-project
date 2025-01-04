package ch.bbw.cge;

import ch.bbw.cge.building.Building;
import ch.bbw.cge.map.screens.Screen;

import java.util.HashMap;
import java.util.Map;

public class ANSI_CODES {
    // GENERAL CODES
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String DIM = "\u001B[2m";
    public static final String ITALIC = "\u001B[3m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String BLINK = "\u001B[5m";
    public static final String INVERT = "\u001B[7m";
    public static final String HIDDEN = "\u001B[8m";
    public static final String STRIKETHROUGH = "\u001B[9m";

    public static final Map<String, String> textColorMap = new HashMap<>();

    public ANSI_CODES() {
        textColorMap.put("Black", createAnsiCode(0, true));
        textColorMap.put("Cyan", createAnsiCode(87, true));
        textColorMap.put("White", createAnsiCode(231, true));
        textColorMap.put("Magenta", createAnsiCode(201, true));
        textColorMap.put("Light Yellow", createAnsiCode(226, true));
        textColorMap.put("Yellow", createAnsiCode(220, true));
        textColorMap.put("Light Green", createAnsiCode(46, true));
        textColorMap.put("Green", createAnsiCode(2,true));
        textColorMap.put("Light Red", createAnsiCode(9, true));
        textColorMap.put("Orange", createAnsiCode(202, true));
        textColorMap.put("Brown", createAnsiCode(130, true));
        textColorMap.put("Red", createAnsiCode(160, true));
        textColorMap.put("Teal", createAnsiCode(49, true));
        textColorMap.put("Light Brown", createAnsiCode(172, true));
        textColorMap.put("Light Purple", createAnsiCode(141, true));
        textColorMap.put("Blue", createAnsiCode(27, true));
        textColorMap.put("Beige", createAnsiCode(180, true));
        textColorMap.put("Light Blue", createAnsiCode(75, true));
        textColorMap.put("Water Blue", createAnsiCode(12, true));
        textColorMap.put("Grass Green", createAnsiCode(108, true));
        textColorMap.put("Sand Yellow", createAnsiCode(222, true));
        textColorMap.put("Forest Green", createAnsiCode(70, true));
        textColorMap.put("Mountain Gray", createAnsiCode(110, true));
        textColorMap.put("Pink", createAnsiCode(219, true));
    }

    public String  getTextColor(String color) {
        return textColorMap.get(color);
    }
    public void printAllTextColors() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int code = i * 16 + j;
                System.out.print("\u001b[38;5;" + code + "m" + String.format("%-4d", code));
            }
            System.out.println("\u001b[0m");
        }
    }
    public void printAllBackgroundColors() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int code = i * 16 + j;
                System.out.print("\u001b[48;5;" + code + "m" + "\u001b[37m" + String.format("%-4d", code));
            }
            System.out.println("\u001b[0m");
        }
    }

    public String createAnsiCode(int code, boolean isText) {
        if (code < 0 || code > 255) {
            throw new IllegalArgumentException("Code must be between 0 and 255.");
        }

        String ansiCode;
        if (isText) {
            ansiCode = "\u001b[38;5;" + code + "m";
        } else {
            ansiCode = "\u001b[48;5;" + code + "m";
        }

        return ansiCode;
    }
    public String colorChar(char currentCell, Building.BuildingType type, Screen.Region region) {
        String coloredCell = "";
        switch (currentCell) {
            case '█':
                if (type == Building.BuildingType.MEDICAL) {
                    coloredCell = getTextColor("Beige") + currentCell + " " + ANSI_CODES.RESET;
                    break;
                }
                if (type == Building.BuildingType.SHOP) {
                    coloredCell = getTextColor("Light Blue") + currentCell + " " + ANSI_CODES.RESET;
                    break;
                }
                if (type == Building.BuildingType.HOUSE) {
                    coloredCell = getTextColor("Brown") + currentCell + " " + ANSI_CODES.RESET;
                    break;
                }
                else {
                    coloredCell = getTextColor("Pink") + currentCell + " " + ANSI_CODES.RESET;
                    break;
                }
            case '░':
                if (region == Screen.Region.GRASSLAND){
                    coloredCell = getTextColor("Grass Green") + currentCell + " " + ANSI_CODES.RESET;
                    break;
                }
                if (region == Screen.Region.DESERT) {
                    coloredCell = getTextColor("Sand Yellow") + currentCell + " " + ANSI_CODES.RESET;
                    break;
                }
                if (region == Screen.Region.FOREST){
                    coloredCell = getTextColor("Forest Green") + currentCell + " " + ANSI_CODES.RESET;
                    break;
                }
                if (region == Screen.Region.MOUNTAIN) {
                    coloredCell = getTextColor("Mountain Gray") + currentCell + " " + ANSI_CODES.RESET;
                    break;
                }
                else {
                    coloredCell = getTextColor("White") + currentCell + " " + ANSI_CODES.RESET;
                    break;
                }
            case 'T':
                coloredCell = getTextColor("Green") + currentCell + " " + ANSI_CODES.RESET;
                break;
            case 'N':
                coloredCell = getTextColor("Red") + currentCell + " " + ANSI_CODES.RESET;
                break;
            case 'Ɉ':
                coloredCell = getTextColor("Teal") + currentCell + " " + ANSI_CODES.RESET;
                break;
            case 'M':
                coloredCell = getTextColor("Light Green") + currentCell + " " + ANSI_CODES.RESET;
                break;
            case 'S':
                coloredCell = getTextColor("Light Yellow") + currentCell + " " + ANSI_CODES.RESET;
                break;
            case 'H':
                coloredCell = getTextColor("Cyan") + currentCell + " " + ANSI_CODES.RESET;
                break;
            case '$':
                coloredCell = getTextColor("Yellow") + currentCell + " " + ANSI_CODES.RESET;
                break;
            case '^':
                coloredCell = getTextColor("Cyan") + currentCell + " " + ANSI_CODES.RESET;
                break;
            case '|', '-':
                coloredCell = getTextColor("Light Brown") + currentCell + " " + ANSI_CODES.RESET;
                break;
            case '◫':
                coloredCell = getTextColor("Brown") + currentCell + " " + ANSI_CODES.RESET;
                break;
            case '+':
                coloredCell = getTextColor("Light Red") + currentCell + " " + ANSI_CODES.RESET;
                break;
            case '↑', '→', '↓', '←':
                coloredCell = getTextColor("Magenta") + currentCell + " " + ANSI_CODES.RESET;
                break;
            case '║', '═', '╔', '╗', '╚', '╝':
                if (type == Building.BuildingType.MEDICAL || type == null) {
                    coloredCell = getTextColor("Light Red") + currentCell + " " + ANSI_CODES.RESET;
                    break;
                }
                if (type == Building.BuildingType.SHOP) {
                    coloredCell = getTextColor("Blue") + currentCell + " " + ANSI_CODES.RESET;
                    break;
                }
                if (type == Building.BuildingType.HOUSE) {
                    coloredCell = getTextColor("Beige") + currentCell + " " + ANSI_CODES.RESET;
                    break;
                } else {
                    coloredCell = getTextColor("Light Brown") + currentCell + " " + ANSI_CODES.RESET;
                    break;
                }
            case '▒':
                coloredCell = getTextColor("Water Blue") + currentCell +  " " + ANSI_CODES.RESET;
                break;
            default:
                coloredCell = currentCell + " ";
                break;
        }
        return coloredCell;
    }

}
