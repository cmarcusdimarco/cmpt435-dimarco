/**
 * A program which reads a constant number of Strings
 * from a file and sorts them using various methods,
 * comparing the efficiency of the sorts by
 * printing the number of comparisons for each.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment2 {
    public static void main(String[] args) {
        final int NUM_OF_ITEMS = 666;                   // Length of file as constant
        String[] magicItems = new String[NUM_OF_ITEMS]; // Array of file strings
        MarcusSort sorter = new MarcusSort();           // Instance of MarcusSort

        // Try/catch block for file import and reading
        try {
            File file = new File("magicitems.txt");
            Scanner read = new Scanner(file);
            for (int i = 0; i < NUM_OF_ITEMS; i++) {
                magicItems[i] = read.nextLine();
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("Whoops! Couldn't find magicitems.txt");
            e.printStackTrace();
        }

        // Shuffle array before beginning sorts
        sorter.notRosannaShuffle(magicItems);

        // Create second array to store the shuffle
        String[] magicItemsShuffled = new String[NUM_OF_ITEMS];
        System.arraycopy(magicItems, 0, magicItemsShuffled, 0, NUM_OF_ITEMS);

        // Sort using selection sort, print comparisons, reset shuffle
        sorter.selectionSort(magicItems);
        System.arraycopy(magicItemsShuffled, 0, magicItems, 0, NUM_OF_ITEMS);

        // Sort using insertion sort, print comparisons, reset shuffle
        sorter.insertionSort(magicItems);
        System.arraycopy(magicItemsShuffled, 0, magicItems, 0, NUM_OF_ITEMS);

        // Sort using merge sort, print comparisons, reset shuffle
        sorter.mergeSort(magicItems);
        System.arraycopy(magicItemsShuffled, 0, magicItems, 0, NUM_OF_ITEMS);

        // Sort using quicksort, print comparisons
        sorter.quickSort(magicItems);
    }
}
