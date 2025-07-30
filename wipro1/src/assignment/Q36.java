package assignment;

class Tank {
    private boolean isReleased = false;

    public void fill() {
        System.out.println("Tank is filled.");
    }

    public void empty() {
        System.out.println("Tank is emptied.");
    }

    public void releaseTank() {
        isReleased = true;
        System.out.println("Tank is released.");
    }

    @Override
    protected void finalize() throws Throwable {
        if (!isReleased) {
            System.out.println("❌ Error: Tank was not released properly before cleanup!");
        } else {
            System.out.println("✅ Tank was properly released before cleanup.");
        }
        super.finalize();
    }
}

public class Q36 {
    public static void main(String[] args) {
        System.out.println(" Test Case 1: Properly releasing tank");
        Tank t1 = new Tank();
        t1.fill();
        t1.empty();
        t1.releaseTank();
        t1 = null; // make object eligible for GC

        System.out.println(" Test Case 2: Forgetting to release tank");
        Tank t2 = new Tank();
        t2.fill();
        t2.empty();
        // no releaseTank() called
        t2 = null; // make object eligible for GC

        // Suggest JVM to run garbage collection
        System.gc();

        // Delay to allow finalizer to run
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*Test Case 1: Properly releasing tank
Tank is filled.
Tank is emptied.
Tank is released.
 Test Case 2: Forgetting to release tank
Tank is filled.
Tank is emptied.
❌ Error: Tank was not released properly before cleanup!
✅ Tank was properly released before cleanup.
*/
