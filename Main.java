import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Rectangle> regions = new ArrayList<>();

        // Divide the screen into 12 regions
        int screenWidth = 430;
        int screenHeight = 800; // Adjusted height for 12 regions
        int W_ratio = 2;
        int H_ratio = 3;
        int startX = 0;
        int startY = 130;

        for (int i = 0; i < W_ratio; i++) {
            for (int j = 0; j < H_ratio; j++) {
                
                int regionX = startX + i * screenWidth/W_ratio;
                int regionY = startY + j * screenHeight/H_ratio;
                
                regions.add(new Rectangle(regionX, regionY, screenWidth/W_ratio, screenHeight/H_ratio));
            }
        }

        

        // Add more regions for other threads...

        List<Thread> threads = new ArrayList<>();
        for (Rectangle region : regions) {
            AutoClicker autoClicker = new AutoClicker(region);
            Thread thread = new Thread(autoClicker::search);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All threads finished.");
        System.out.println(AutoClicker.total_counter);
    }
}
