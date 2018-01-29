package Tee;

/**
 * The tee command reads the standard  input and writes it to both the standard output and to the file filename.
 * Your parallel tee program should have three threads:
 * one for reading standard input,
 * one for writing to standard output,
 * and one for writing to file filename.
 */
public class TeeDriver {

    public static void main(String[] args) {

        final Tee.Model model = new Tee.Model();

        Tee.ReadStandardInput thread0 = new Tee.ReadStandardInput(model);
        Tee.WriteStandardOutput thread1 = new Tee.WriteStandardOutput(model);
        Tee.WriteToFile thread2 = new Tee.WriteToFile(model);

            thread1.start();
            thread2.start();
            thread0.start();
    }
}
