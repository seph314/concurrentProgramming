package Diff;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader extends Thread{

    private String filename;
    private List<String> listOfLines;

    public Reader(String filename) {
        this.filename = filename;
    }

    public List<String> getListOfLines() {
        return listOfLines;
    }

    @Override
    public void run() {
        try {
            readAllLines();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts all lines from a textfile to a list of Strings
     * @return a list of Strings
     * @throws IOException
     */
    private void readAllLines() throws IOException {

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            listOfLines = stream
                    .collect(Collectors.toList());
        }

    }
}
