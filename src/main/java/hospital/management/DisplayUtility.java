package hospital.management;

import java.util.List;

/**
 * Utility class for creating beautiful formatted output using Unicode box-drawing characters.
 * Provides methods for creating borders, tables, headers, and formatted displays.
 */
public class DisplayUtility {
    
    // Unicode box-drawing characters
    private static final char TOP_LEFT = '┌';
    private static final char TOP_RIGHT = '┐';
    private static final char BOTTOM_LEFT = '└';
    private static final char BOTTOM_RIGHT = '┘';
    private static final char HORIZONTAL = '─';
    private static final char VERTICAL = '│';
    private static final char TOP_T = '┬';
    private static final char BOTTOM_T = '┴';
    private static final char LEFT_T = '├';
    private static final char RIGHT_T = '┤';
    private static final char CROSS = '┼';
    
    /**
     * Creates a horizontal line of specified length.
     */
    public static String horizontalLine(int length) {
        return String.valueOf(HORIZONTAL).repeat(Math.max(0, length));
    }
    
    /**
     * Creates a bordered box with text inside.
     */
    public static void printBox(String title, String content) {
        int width = 60;
        if (title != null && !title.isEmpty()) {
            width = Math.max(title.length() + 4, width);
        }
        if (content != null) {
            String[] lines = content.split("\n");
            for (String line : lines) {
                width = Math.max(width, line.length() + 4);
            }
        }
        
        System.out.println(TOP_LEFT + horizontalLine(width - 2) + TOP_RIGHT);
        if (title != null && !title.isEmpty()) {
            int padding = (width - title.length() - 2) / 2;
            String titleLine = VERTICAL + " ".repeat(padding) + title + 
                             " ".repeat(width - title.length() - padding - 2) + VERTICAL;
            System.out.println(titleLine);
            System.out.println(LEFT_T + horizontalLine(width - 2) + RIGHT_T);
        }
        if (content != null && !content.isEmpty()) {
            String[] lines = content.split("\n");
            for (String line : lines) {
                System.out.println(VERTICAL + " " + padRight(line, width - 4) + " " + VERTICAL);
            }
        }
        System.out.println(BOTTOM_LEFT + horizontalLine(width - 2) + BOTTOM_RIGHT);
        System.out.println();
    }
    
    /**
     * Creates a section header with decorative borders.
     */
    public static void printHeader(String title) {
        int width = Math.max(title.length() + 6, 60);
        System.out.println();
        System.out.println(TOP_LEFT + horizontalLine(width - 2) + TOP_RIGHT);
        int padding = (width - title.length() - 2) / 2;
        System.out.println(VERTICAL + " ".repeat(padding) + title + 
                          " ".repeat(width - title.length() - padding - 2) + VERTICAL);
        System.out.println(BOTTOM_LEFT + horizontalLine(width - 2) + BOTTOM_RIGHT);
        System.out.println();
    }
    
    /**
     * Creates a formatted table with headers and rows.
     */
    public static void printTable(String[] headers, List<String[]> rows) {
        if (headers == null || headers.length == 0) return;
        
        int[] columnWidths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            columnWidths[i] = headers[i].length();
        }
        
        for (String[] row : rows) {
            for (int i = 0; i < Math.min(row.length, columnWidths.length); i++) {
                columnWidths[i] = Math.max(columnWidths[i], row[i].length());
            }
        }
        
        int totalWidth = columnWidths.length - 1; // for separators
        for (int width : columnWidths) {
            totalWidth += width + 2; // +2 for padding
        }
        
        // Top border
        System.out.print(TOP_LEFT);
        for (int i = 0; i < columnWidths.length; i++) {
            System.out.print(horizontalLine(columnWidths[i] + 2));
            if (i < columnWidths.length - 1) {
                System.out.print(TOP_T);
            }
        }
        System.out.println(TOP_RIGHT);
        
        // Header row
        System.out.print(VERTICAL);
        for (int i = 0; i < headers.length; i++) {
            System.out.print(" " + padRight(headers[i], columnWidths[i]) + " " + VERTICAL);
        }
        System.out.println();
        
        // Header separator
        System.out.print(LEFT_T);
        for (int i = 0; i < columnWidths.length; i++) {
            System.out.print(horizontalLine(columnWidths[i] + 2));
            if (i < columnWidths.length - 1) {
                System.out.print(CROSS);
            }
        }
        System.out.println(RIGHT_T);
        
        // Data rows
        if (rows.isEmpty()) {
            System.out.print(VERTICAL);
            System.out.print(" " + padRight("No data available", totalWidth - 2) + " " + VERTICAL);
            System.out.println();
        } else {
            for (String[] row : rows) {
                System.out.print(VERTICAL);
                for (int i = 0; i < columnWidths.length; i++) {
                    String cell = (i < row.length) ? row[i] : "";
                    System.out.print(" " + padRight(cell, columnWidths[i]) + " " + VERTICAL);
                }
                System.out.println();
            }
        }
        
        // Bottom border
        System.out.print(BOTTOM_LEFT);
        for (int i = 0; i < columnWidths.length; i++) {
            System.out.print(horizontalLine(columnWidths[i] + 2));
            if (i < columnWidths.length - 1) {
                System.out.print(BOTTOM_T);
            }
        }
        System.out.println(BOTTOM_RIGHT);
        System.out.println();
    }
    
    /**
     * Creates a simple bordered message.
     */
    public static void printMessage(String message, boolean isSuccess) {
        String prefix = isSuccess ? "✓ " : "✗ ";
        printBox(null, prefix + message);
    }
    
    /**
     * Creates a separator line.
     */
    public static void printSeparator() {
        System.out.println(horizontalLine(60));
        System.out.println();
    }
    
    /**
     * Pads a string to the right to specified length.
     */
    private static String padRight(String s, int length) {
        if (s == null) s = "";
        if (s.length() >= length) return s;
        return s + " ".repeat(length - s.length());
    }
    
    /**
     * Creates a menu display with options.
     */
    public static void printMenu(String title, String[] options) {
        printHeader(title);
        for (int i = 0; i < options.length; i++) {
            System.out.println("  " + (i + 1) + ". " + options[i]);
        }
        System.out.println();
    }
}

