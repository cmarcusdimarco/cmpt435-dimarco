/**
 * A program which completes two tasks. The first is creating graphs from
 * a file and printing the matrices, adjacency lists, depth-first and
 * breadth-first traversals.
 * 
 * The second is reading a file of a constant number of Strings and
 * populating a binary search tree with the items, printing the paths
 * taken on the tree. It then prints an in-order traversal, followed
 * by the search results of finding 42 distinct items and the average
 * comparisons needed to search in the tree.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment4 {
    public static void main(String[] args) {
        final int NUM_OF_ITEMS = 666;                   // Length of file as constant
        String[] magicItems = new String[NUM_OF_ITEMS]; // Array of file strings

        // Read graphs1.txt and create matrix, adjacency list, and linked objects
        try {
            File graphs = new File("graphs1.txt");
            Scanner graphRead = new Scanner(graphs);
            MarcusGraphs graph = null;
            String command = null;
            String item = null;
            while (graphRead.hasNextLine()) {
                command = graphRead.next();
                if (command.equals("--")) {
                // Skip comment if null, print and execute methods if exists
                    if (graph == null) {
                        graphRead.nextLine();
                    } else {
                        System.out.println("Matrix:");
                        graph.printMatrix();
                        System.out.println("Adjacency list:");
                        graph.printAdjacencyList();
                        System.out.println("Depth-first traversal:");
                        graph.depthFirstTraversal(graph.getInitialVertex());
                        System.out.print("\n\n");
                        System.out.println("Breadth-first traversal:");
                        graph.breadthFirstTraversal(graph.getInitialVertex());
                    }
                } else if (command.equals("new")) {
                // Create new graph
                    graph = new MarcusGraphs();
                    graphRead.nextLine();
                } else if (command.equals("add")) {
                    item = graphRead.next();
                    if (item.equals("vertex")) {
                    // Add new vertex to graph
                        MarcusVertex vertex = new MarcusVertex(graphRead.nextInt());
                        graph.addVertex(vertex);
                    } else if (item.equals("edge")) {
                    // Add new edge to graph
                        int a = graphRead.nextInt();
                        graphRead.next();
                        int b = graphRead.nextInt();
                        MarcusVertex first = graph.getVertexById(a);
                        MarcusVertex second = graph.getVertexById(b);
                        first.addNeighbor(second);
                        second.addNeighbor(first);
                    }
                }
            }
            System.out.println("Matrix:");
            graph.printMatrix();
            System.out.println("Adjacency list:");
            graph.printAdjacencyList();
            System.out.println("Depth-first traversal:");
            graph.depthFirstTraversal(graph.getInitialVertex());
            System.out.print("\n\n");
            System.out.println("Breadth-first traversal:");
            graph.breadthFirstTraversal(graph.getInitialVertex());
            graphRead.close();
        } catch (FileNotFoundException e) {
            System.out.println("Whoops! Couldn't find graphs1.txt");
            e.printStackTrace();
        }

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

        // Populate BST with magicItems, printing the path from the root
        MarcusBST binarySearchTree = new MarcusBST();
        for (String item : magicItems) {
            MarcusNode node = new MarcusNode(item);
            binarySearchTree.insertNode(node);
            System.out.println(item + ": " + binarySearchTree.getPath());
        }
        System.out.print("\n\n");

        // Print the entire BST with an in-order traversal
        binarySearchTree.inOrderTraversal(binarySearchTree.getRoot());

        // Read magicitems-find-in-bst.txt and lookup in BST, printing path
        try {
            File itemsToFind = new File("magicitems-find-in-bst.txt");
            Scanner bstRead = new Scanner(itemsToFind);
            String currentItem;
            double average = 0;
            while (bstRead.hasNextLine()) {
                currentItem = bstRead.nextLine();
                binarySearchTree.search(currentItem);
                System.out.println(currentItem + ": " + binarySearchTree.getPath());
                System.out.println("    Number of comparisons: "
                                   + binarySearchTree.getCounter());
                average += binarySearchTree.getCounter();
            }
            bstRead.close();
            average /= 42.0;
            System.out.println("Average comparisons: " + average);
        } catch (FileNotFoundException e) {
            System.out.println("Whoops! Couldn't find magicitems-find-in-bst.txt");
            e.printStackTrace();
        }
    }
}
