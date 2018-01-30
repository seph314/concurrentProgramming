package Diff;

import java.util.List;

public class Compare extends Thread{

    List<String> oneList;
    List<String> anotherList;
    int lengthDifference;

    public Compare(List<String> oneList, List<String> anotherList, int lengthDifference) {
        this.oneList = oneList;
        this.anotherList = anotherList;
        this.lengthDifference = lengthDifference;
    }

    @Override
    public void run() {
        compare();
    }

    private void compare(){
        Diff diff = new Diff();

        for (int i=0; i < oneList.size()-1; i++){
            if (diff.isDiff(anotherList.get(i), oneList.get(i))){
                System.out.println(anotherList.get(i) + "\n" + oneList.get(i));
            }
        }
        // print the remaining rows in the longer list
        for (int i=anotherList.size()-lengthDifference; i < anotherList.size(); i++){
            System.out.println(anotherList.get(i));
        }
    }
}
