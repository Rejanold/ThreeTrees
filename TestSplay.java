/**
 * Author: Mason Waters
 * Date: 11/16/2019
 * Compare Three Trees Assignment
 * This is the TestSPlay Class Driver
 * In collaboration with: Blake Furlano and Robert Hable
 */
import java.io.*;
public class TestSplay {

    public static void main(String[] args) throws FileNotFoundException{

        stringTree();
    }

    public static void stringTree() throws FileNotFoundException{
        FileOutputStream fout = new FileOutputStream("TestSplayResults.txt");
        PrintStream pout = new PrintStream(fout);
        SplayTree<String> s = new SplayTree<String>();
        pout.println("Test Splay Tree driver");

        s.insert("Peculiar",pout);

        s.insert("Crapo",pout);
        s.insert("Accident",pout);
        s.insert("Eau Claire",pout);
        s.insert("Boring",pout);
        s.insert("Hell",pout);
        s.insert("Walla Walla",pout);
        s.insert("Surprise",pout);
        s.insert("Joseph",pout);
        s.insert("Romance",pout);
        s.insert("Mars",pout);
        s.insert("Nuttsville",pout);
        s.insert("Rough and Ready",pout);
        s.insert("Dynamite",pout);
        s.insert("Good Grief",pout);
        s.insert("Zarephath",pout);
        s.delete("Eau Claire",pout);
        s.delete("Accident",pout);
        s.delete("Rough and Ready",pout);
        s.clearTree(pout);
        s.insert("Accident",pout);
        s.insert("Boring",pout);
        s.insert("Crapo",pout);
        s.insert("Dynamite",pout);
        s.insert("Eau Claire",pout);
        s.insert("Good Grief",pout);
        s.insert("Hell",pout);
        s.insert("Joseph",pout);
        s.insert("Mars",pout);
        s.insert("NuttsVille",pout);
        s.insert("Peculiar",pout);
        s.insert("Romance",pout);
        s.insert("Rough and Ready",pout);
        s.insert("Surprise",pout);
        s.insert("Walla Walla",pout);
        s.insert("Zarephath",pout);



    }
}
