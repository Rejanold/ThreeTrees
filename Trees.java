import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Author: Mason Waters
 * Date: 11/16/2019
 * Compare Three Trees Assignment
 * This is the Trees Class Driver
 * In collaboration with: Blake Furlano and Robert Hable
 */
public class Trees {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        File file = new File("ZipsSmall.txt");
        Scanner data = new Scanner(file);

        SplayTree<Place> s = new SplayTree<Place>();
        BinarySearchTree<Place> b = new BinarySearchTree<Place>();
        AVLTree<Place> a = new AVLTree<Place>();

        addToTrees(data, s, b, a);
        askUser(console, s, b, a);

    }

    public static void askUser(Scanner console,SplayTree<Place> s, BinarySearchTree<Place> b, AVLTree<Place> a) throws FileNotFoundException {
        boolean game = true;
        while (game == true) {
            System.out.print("You want to search for the city: ");
            String response = console.nextLine();
            System.out.println("The number of comparisons needed to find the entry in BST: ");
            System.out.println("The number of comparisons needed to find the entry in AVL: ");
            System.out.println("The number of comparisons needed to find the entry in Splay: ");
            System.out.println("The zip codes that belong to " + response + " are: ");
            System.out.print("Do you want me to search again? ");
            String response2 = console.nextLine();
            if (response2.equalsIgnoreCase("yes")) {
                System.out.println();
                game = true;
            } else {
                System.exit(0);
            }
        }
    }

    public static void addToTrees(Scanner data,SplayTree<Place> s, BinarySearchTree<Place> b, AVLTree<Place> a) {
        //SplayTree<Place> s = new SplayTree<Place>();
        //BinarySearchTree<Place> b = new BinarySearchTree<Place>();
        //AVLTree<Place> a = new AVLTree<Place>();

        String lastName;
        int lastCode;

        String firstLine = data.nextLine();
        int zipCode = data.nextInt();
        double xValue = data.nextInt(); //ignore
        double yValue = data.nextInt(); //ignore
        String cityState = data.nextLine();
        Place newPlace1 = new Place(cityState, zipCode);
        lastName = cityState;

        while (data.hasNextLine()) {
            int zipCode1 = data.nextInt();
            double xValue1 = data.nextInt(); //ignore
            double yValue1 = data.nextInt(); //ignore
            String cityState1 = data.nextLine();
            if (cityState1.equals(lastName)) {
                newPlace1.addZip(zipCode1);               //add zip code to existing city
                if (!data.hasNextLine()) {                  // Last line of file check
                    s.insert(newPlace1);
                    b.insert(newPlace1);
                    a.insert(newPlace1);
                }
            } else {
                s.insert(newPlace1);
                b.insert(newPlace1);
                a.insert(newPlace1);
                Place newPlace = new Place(cityState, zipCode);
                if (!data.hasNextLine()) {                  // Last line of file check
                    s.insert(newPlace);
                    b.insert(newPlace);
                    a.insert(newPlace);
                }
                lastName = cityState;
            }
        }
    }
}
