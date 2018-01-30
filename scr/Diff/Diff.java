package Diff;

/**
 * The isDiff command compares corresponding lines in the given files and
 * prints both lines to the standard output, if the lines are different.
 *
 * If one of the files is longer then the other,
 * isDiff prints all extra lines in the longer file to the standard output.
 */
public class Diff {

    /**
     * Returns true if there is a difference between strings
     * @param str1
     * @param str2
     * @return
     */
    public boolean isDiff(String str1, String str2) {
        int index = str1.lastIndexOf(str2);
        if (index > -1) {
            return false;
        }
        return true;
    }



//    public String getDiff(String str1, String str2) {
//        int index = str1.lastIndexOf(str2);
//        if (index > -1) {
//            return str1.substring(str2.length());
//        }
//        return str1;
//    }
}
