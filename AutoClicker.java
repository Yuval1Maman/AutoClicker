import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

public class AutoClicker {
    private static final Color color1 = new Color(239,209,144);
    //private static final Color color2 = new Color(255,255,255);
    public static int total_counter = 0;
    private static final int COLOR_RANGE = 3;

    private Rectangle region;

    public AutoClicker(Rectangle region) {
        this.region = region;
    }

    private boolean isInRange(Color color, Color targetColor, int range) {
        int redDiff = Math.abs(color.getRed() - targetColor.getRed());
        int greenDiff = Math.abs(color.getGreen() - targetColor.getGreen());
        int blueDiff = Math.abs(color.getBlue() - targetColor.getBlue());
        return redDiff <= range && greenDiff <= range && blueDiff <= range;
    }

    public void search() {
        int counter =0;
        System.out.println("Thread for region " + region + " started.");
        long endTime = System.currentTimeMillis() + 50000; // 10 seconds
        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            return;
        }
        while (System.currentTimeMillis() < endTime) {
            BufferedImage screenshot = robot.createScreenCapture(region);
            boolean isBreak = false;
            for (int x = 0; x < screenshot.getWidth(); x++) {
                for (int y = 0; y < screenshot.getHeight(); y++) {
                    int pixel = screenshot.getRGB(x, y);
                    Color color = new Color(pixel);
                    if (isInRange(color, color1, COLOR_RANGE)) {
                        robot.mouseMove(region.x + x + 2, region.y + y);
                        robot.mousePress(java.awt.event.InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(java.awt.event.InputEvent.BUTTON1_DOWN_MASK);
                        counter++;
                        isBreak = true;
                        break;
                    }
                }
                if (isBreak) {
                    break;
                }
            }
        }
        System.out.println("Thread for region " + region + " finished. and counter = " + counter);
        total_counter += counter;
    }
}
