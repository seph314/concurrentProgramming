public class RunnerDriver {

    final Runner runner = new Runner();

    Runner t1 = new Runner();
    t1.
    Thread t1 = new Thread(() -> {
        try{
            try {
                runner.firstThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            System.out.println("Kunde inte starta firsta tråden");
        }
    });



    Thread t2 = new Thread(() -> {
        try{
            try {
                runner.secondThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            System.out.println("Kunde inte starta firsta tråden");
        }
    });




}
