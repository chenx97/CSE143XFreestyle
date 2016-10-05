/*
 * Without any limit of programming knowledge,
 * let's try to produce an awesome class for generating that song!
 */
public class SongCrazy {

    public static void main(String[] args) {
        String[] animals = {"spider", "bird", "cat", "dog", "fennekin"};
        String[] secondline = {
            "That wriggled and iggled and jiggled inside her.",
            "How absurd to swallow a bird.",
            "Imagine that to swallow a cat.",
            "What a hog to swallow a dog.",
            "I think she's burnin'."
        };
        startLine("fly", ".");
        end();
        for (int x = 0; x < 5; x++) {
            startLine(animals[x],",");
            System.out.println(secondline[x]);
            for (int y = x; y > 0; y--) {
                swallowAtoCatchB(animals[y], animals[y-1]);
            }
            swallowAtoCatchB("spider", "fly");
            end();
        }
        System.out.println("There was an old woman who swallowed a horse,");
        System.out.println("She died of course.");
    }

    public static void startLine(String something, String punctuation) {
        System.out.println("There was an old woman who swallowed a " + something + punctuation);
    }

    public static void swallowAtoCatchB(String first, String second) {
        System.out.println("She swallowed the " + first + " to catch the " + second + ",");
    }

    public static void end() {
        System.out.println("I don't know why she swallowed that fly,");
        System.out.println("Perhaps she'll die.\n");
    }
}
