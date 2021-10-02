/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        Integer i = 1;
        String champion = "";
        while (!StdIn.isEmpty()) {
            String inputString = StdIn.readString();
            boolean p = StdRandom.bernoulli((double) 1 / i);
            if (p) champion = inputString;
            i++;
        }
        StdOut.println(champion);
    }
}
