import java.util.List;

public class Main {
    public static void main(String[] args) {
        Fourchette f1 = new Fourchette(true);
        Fourchette f2 = new Fourchette(true);
        Fourchette f3 = new Fourchette(true);
        Fourchette f4 = new Fourchette(true);
        Fourchette f5 = new Fourchette(true);
        List <Integer> plats = List.of(10000000, 1000000000, 1000000000, 1000000000, 1000000000);
        Philosophe p1 = new Philosophe("Socrate",plats.get(0), f1, f2);
        Philosophe p2 = new Philosophe("Platon", plats.get(1), f2, f3);
        Philosophe p3 = new Philosophe("Aristote", plats.get(2), f3, f4);
        Philosophe p4 = new Philosophe("Pythagore", plats.get(3), f4, f5);
        Philosophe p5 = new Philosophe("Heraclite", plats.get(4), f5, f1);
        System.out.println("Debut du repas");
        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(p3);
        Thread t4 = new Thread(p4);
        Thread t5 = new Thread(p5);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        
    }
}
