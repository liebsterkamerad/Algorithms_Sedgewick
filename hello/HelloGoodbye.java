/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class HelloGoodbye {
    public static void main(String[] args) {
        if (args.length < 2) System.out.println("Please provide two arguments!");
        System.out.println(String.format("Hello %s and %s.", args[0], args[1]));
        System.out.println(String.format("Goodbye %s and %s.", args[1], args[0]));
    }
}
