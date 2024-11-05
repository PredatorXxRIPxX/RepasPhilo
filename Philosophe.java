public class Philosophe implements Runnable {
    private String nom;
    private long plat;
    private Fourchette fourchette1;
    private Fourchette fourchette2;
    private static final long THINKING_TIME = 1000;
    private static final long EATING_TIME = 1000;
    private volatile boolean running = true;

    public Philosophe(String nom, long plat, Fourchette fourchette1, Fourchette fourchette2) {
        this.nom = nom;
        this.plat = plat;
        this.fourchette1 = fourchette1;
        this.fourchette2 = fourchette2;
    }

    private synchronized void manger() {
        try {
            if(fourchette1.isAvailable() && fourchette2.isAvailable()) {
                fourchette1.setAvailable(false);
                fourchette2.setAvailable(false);
                
                System.out.println(nom + " commence a manger son plat qui reste " + plat);
                plat--;
                Thread.sleep(EATING_TIME);
                System.out.println(nom + " a fini de manger son plat qui reste " + plat);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            fourchette1.setAvailable(true);
            fourchette2.setAvailable(true);
        }
    }

    private synchronized void penser() {
        try {
            System.out.println(nom + " pense");
            Thread.sleep(THINKING_TIME);
            System.out.println(nom + " a fini de penser");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void setPlat(long plat) {
        this.plat = plat;
    }

    public long getPlat() {
        return plat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running && plat > 0) {
            penser();
            manger();
        }
        System.out.println(nom + " a terminé son repas");
    }
}