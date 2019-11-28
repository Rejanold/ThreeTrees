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
        //getResults(test, s, b, a);
         //askUser(console, s, b, a);
        getResults(console, s, b, a);
    }

    /**
     * This method prints to the user asking if they want to search a tree for a city and state
     *
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
            SplayBSTNode<Place> found = s.search(searchFor);
            BSTNode<Place> found1 = b.search(searchFor);
            AVLNode<Place> found2 = a.search(searchFor);
            if (found1 == null) {
                System.out.println("There is not a city by this name.");
            }
            ArrayList<Integer> zipps = found.getElement().getZipCodes();
            /*System.out.print("The number of comparisons needed to find the entry in BST: ");
            System.out.println(b.getCompareNum());
            System.out.print("The number of comparisons needed to find the entry in AVL: ");
            System.out.println(a.getCompareNum());
            System.out.print("The number of comparisons needed to find the entry in Splay: ");
            System.out.println(s.getCompareNum());
            System.out.print("The zip codes that belong to " + response + " are: ");*/
            if (zipps == null) {
                //Nothing
            } else {
                System.out.println(zipps);
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
     *
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
     *
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
        //String cityState = data.nextLine().trim();
        int count = searchPlace.size() - 1;
        for (int i = 0; i < count; i++) {
            Place searchFor = new Place(searchPlace.get(i), 0000);
            System.out.println(searchFor);
            SplayBSTNode<Place> found = s.search(searchFor);
            BSTNode<Place> found1 = b.search(searchFor);
            AVLNode<Place> found2 = a.search(searchFor);
            int splay = s.getCompareNum();
            int bst = b.getCompareNum();
            int avl = a.getCompareNum();
            sHold.add(splay);
            bstHold.add(bst);
            avlHold.add(avl);
        }


        double sumSplay = 0;
        double sumBST = 0;
        double sumAVL = 0;
        double sum2 = 0;
        double sdSplay = 0;
        for (int i = 0; i < sHold.size() - 1; i++) {
            sumSplay += i;
        }
        for (int i = 0; i < bstHold.size() - 1; i++) {
            sumBST += i;
        }
        for (int i = 0; i < avlHold.size() - 1; i++) {
            sumAVL += i;
        }
        double avgSplay = sumSplay / sHold.size();
        double avgBST = sumBST / bstHold.size();
        double avgAVL = sumAVL / avlHold.size();
        /*for (int i = 0; i < sHold.size(); i++) {
            double fvalue = (Math.pow((sHold.get(i) - avgSplay), 2));
            //x1_average[i] = fvalue;
            System.out.println("test: " + fvalue);
        }*/
        sdSplay = Math.sqrt(sum2 / avlHold.size());
        System.out.println("The average # of searches for the Splay tree was: " + avgSplay + " and the Standard Deviation is :");
        System.out.println("The average # of searches for the BST tree was: " + avgBST);
        System.out.println("The average # of searches for the AVL tree was: " + avgAVL);
       /* for(int i = 1; i < 4; i++){
            double sum = 0;
            double sum2= 0;

            double avg = 0;
            double sd = 0;
            double[] num = new double[10];
            for(int j = 0; j < 10; j++) {
                sum += rabbitData[j][i];
            }
            avg = (sum/10); //Final calculation for the mean


            for(int j = 0; j < 10; j++){
                sum2 += Math.pow(rabbitData[j][i] - avg,2);

            }
            sd = Math.sqrt(sum2/10); //Final calculation for the Standard Deviation.




            System.out.printf("Average number of rabbits: %.3f", avg);
            System.out.printf(" with standard deviation of %.3f\n", sd);*/
    }
}