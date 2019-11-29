import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        File file = new File("zips.txt");
        Scanner data = new Scanner(file);
        Scanner test = new Scanner(file);
        SplayTree<Place> s = new SplayTree<Place>();
        BinarySearchTree<Place> b = new BinarySearchTree<Place>();
        AVLTree<Place> a = new AVLTree<Place>();
        addToTrees(data, s, b, a);
        askUser(console, s, b, a);
        //getResults(console, s, b, a);
    }

    /**
     * This method prints to the user asking if they want to search a tree for a city and state
     * @param console calls the scanner to the console
     * @param s       is the Splay Tree called
     * @param b       is the BST called
     * @param a       is the AVL tree called
     */
    public static void askUser(Scanner console, SplayTree<Place> s, BinarySearchTree<Place> b, AVLTree<Place> a) {
        boolean game = true;
        while (game == true) {
            System.out.print("You want to search for the city: ");
            String response = console.nextLine();
            Place searchFor = new Place(response, 0000);
            SplayNode<Place> found = s.search(searchFor);
            BSTNode<Place> found1 = b.search(searchFor);
            AVLNode<Place> found2 = a.search(searchFor);
            if (found1 == null) {
                System.out.println("There is not a city by this name.");
            }
            System.out.print("The number of comparisons needed to find the entry in BST: ");
            System.out.println(b.getSearchCounter());
            System.out.print("The number of comparisons needed to find the entry in AVL: ");
            System.out.println(a.getSearchCounter());
            System.out.print("The number of comparisons needed to find the entry in Splay: ");
            System.out.println(s.getSearchCounter());
            try{
                ArrayList<Integer> zipps = found.getElement().getZipCodes();
                if(zipps != null){
                    System.out.print("The zip codes that belong to " + response + " are: ");
                    System.out.println(zipps);
                }
            }
            catch(NullPointerException e){
                //nothing
            }
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

    public static ArrayList<String> searchTest() throws FileNotFoundException {
        ArrayList<String> places = new ArrayList<String>();
        File file = new File("zips.txt");
        Scanner data = new Scanner(file);
        String junkLine = data.nextLine();
        while(data.hasNextLine()) {
            int zip = data.nextInt();
            double x = data.nextDouble();
            double y = data.nextDouble();
            String place = data.nextLine();
            places.add(place);
        }
        return places;
    }

    /**
     * This method adds all the cities and states and their associated zip codes to the trees
     * @param data is the scanner called
     * @param s    is the Splay Tree called
     * @param b    is the BST called
     * @param a    is the AVL tree called
     */
    public static void addToTrees(Scanner data, SplayTree<Place> s, BinarySearchTree<Place> b, AVLTree<Place> a) {
        String firstLine = data.nextLine();//gets ignored
        String prevName = " ";//used for comparisons between lines
        Place holdPlace = new Place("Hold", 5); //dummy temp place that gets overwritten
        while (data.hasNextLine()) {
            //get the data for a city
            int zipCode1 = data.nextInt();
            double xValue1 = data.nextDouble(); //ignore
            double yValue1 = data.nextDouble(); //ignore
            String cityState1 = data.nextLine().trim();
            if (prevName.equalsIgnoreCase(" ")) {//the first town to insert
                Place newPlace2 = new Place(cityState1, zipCode1);//creates a place for first line
                s.insert(newPlace2);
                b.insert(newPlace2);
                a.insert(newPlace2);
                holdPlace = newPlace2;//sets the temp place = the first line
            } else {
                if (cityState1.equals(prevName)) {
                    holdPlace.addZip(zipCode1);//add zip code to existing city

                } else {
                    Place newPlace2 = new Place(cityState1, zipCode1);
                    s.insert(newPlace2);
                    b.insert(newPlace2);
                    a.insert(newPlace2);
                    holdPlace = newPlace2;
                }
            }
            prevName = cityState1;
        }
    }

    /**
     * This method calculates the standard deviation and average # of searches for each tree
     * @param data is the scanner thats called
     * @param s    is the Splay Tree called
     * @param b    is the BST tree called
     * @param a    is the AVL tree called
     */
    public static void getResults(Scanner data, SplayTree<Place> s, BinarySearchTree<Place> b, AVLTree<Place> a)throws FileNotFoundException {
        ArrayList<String> searchPlace = new ArrayList<>(searchTest());
        ArrayList<Integer> sHold = new ArrayList<>();
        ArrayList<Integer> bstHold = new ArrayList<>();
        ArrayList<Integer> avlHold = new ArrayList<>();
        int count = searchPlace.size() - 1;
        for (int i = 0; i < count; i++) {
            //System.out.println("Here");
            Place searchFor = new Place(searchPlace.get(i), 0000);
            SplayNode<Place> found = s.search(searchFor);
            BSTNode<Place> found1 = b.search(searchFor); //Blake I think the problem is here lines 144-150
            AVLNode<Place> found2 = a.search(searchFor);
            int splay = s.getSearchCounter();
            int bst = b.getSearchCounter();
            int avl = a.getSearchCounter();
            sHold.add(splay);
            bstHold.add(bst);
            avlHold.add(avl);
        }
        ArrayList<Double> stdDev = new ArrayList<>();
        double sum2 = 0;
        double sumSplay = 0;
        double sumBST = 0;
        double sumAVL = 0;
        double splaySD = 0;
        double bstSD = 0;
        double avlSD = 0;
        for (int i = 0; i < sHold.size() - 1; i++) {
            double indexNum = sHold.get(i);
            sumSplay += indexNum;
            //System.out.println(sumSplay);
        }
        for (int i = 0; i < bstHold.size() - 1; i++) {
            int indexNum = bstHold.get(i);
            sumBST += indexNum;
        }
        for (int i = 0; i < avlHold.size() - 1; i++) {
            int indexNum = avlHold.get(i);
            sumAVL += indexNum;
        }
        double avgSplay = sumSplay / sHold.size(); //Step 1 get mean
        for(int i = 0; i < sHold.size()-1 ; i++){
            stdDev.add(Math.abs(Math.pow(sHold.get(i) - avgSplay, 2))); //Step 2 Find the Deviation |x-u|^2 and add them to a new array
        }
        for(int j = 0; j < stdDev.size()-1; j++){
            double indexNum = stdDev.get(j);
            splaySD += indexNum; //Step 3 Summing the new Values
            splaySD = splaySD / stdDev.size(); //Step 4 dive the new sum by the size(number of data points)
            splaySD = Math.sqrt(splaySD); //Step 5 Take the square Root

        }
        double avgBST = sumBST / bstHold.size();
        double avgAVL = sumAVL / avlHold.size();
        bstSD += Math.pow(bstHold.size() - avgBST, 2);
        bstSD = Math.sqrt(bstSD / bstHold.size());
        avlSD += Math.pow(avlHold.size() - avgAVL, 2);
        avlSD = Math.sqrt(avlSD / avlHold.size());
        System.out.printf("The average # of searches for the Splay tree was: %.3f", avgSplay  );
        System.out.printf( " and the Standard Deviation is : %.3f", splaySD);
        System.out.printf("\nThe average # of searches for the BST tree was: %.3f", avgBST);
        System.out.printf(" and the standard deviation is : %.3f", bstSD);
        System.out.printf("\nThe average # of searches for the AVL tree was: %.3f", avgAVL);
        System.out.printf(" and the standard deviation is: %.3f", avlSD);
    }
}
