import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {
    private static ArrayList<String> itemList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            displayList();
            displayMenu();
            String choice = SafeInput.getRegExString(scanner, "Enter your choice", "[AaDdIiPpQq]");

            String upperChoice = choice.toUpperCase(); // Convert to uppercase once

            if (upperChoice.equals("A")) {
                addItem();
            }
            else if (upperChoice.equals("D")) {
                deleteItem();
            }
            else if (upperChoice.equals("I")) {
                insertItem();
            }
            else if (upperChoice.equals("P")) {
                displayList();
            }
            else if (upperChoice.equals("Q")) {
                running = confirmQuit();
            }
        }
    }

    private static void displayList() {
        System.out.println("Current List:");
        if (itemList.isEmpty()) {
            System.out.println("The list is empty");
        } else {
            for (int i = 0; i < itemList.size(); i++) {
                System.out.println((i + 1) + ". " + itemList.get(i));
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Menu Options:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("I - Insert an item into the list");
        System.out.println("P - Print (display) the list");
        System.out.println("Q - Quit the program");
    }

    private static void addItem() {
        String item = SafeInput.getNonZeroLenString(scanner, "Enter the item to add");
        itemList.add(item);
        System.out.println("Item added: " + item);
    }

    private static void deleteItem() {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty - nothing to delete");
            return;
        }

        int itemNum = SafeInput.getRangedInt(scanner, "Enter the item number to delete", 1, itemList.size());
        String removedItem = itemList.remove(itemNum - 1);
        System.out.println("Item removed: " + removedItem);
    }

    private static void insertItem() {
        int itemNum = SafeInput.getRangedInt(scanner, "Enter the position to insert at", 1, itemList.size() + 1);
        String item = SafeInput.getNonZeroLenString(scanner, "Enter the item to insert");

        itemList.add(itemNum - 1, item);
        System.out.println("Item inserted at position " + itemNum + ": " + item);
    }

    private static boolean confirmQuit() {
        boolean quit = SafeInput.getYNConfirm(scanner, "Are you sure you want to quit?");
        if (quit) {
            System.out.println("Quitting the program.");
        }
        return !quit;
    }
}