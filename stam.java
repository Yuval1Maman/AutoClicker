import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.event.InputEvent;

public class ImageRecognitionExample {
    public static void main(String[] args) throws AWTException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Load the target image to detect
        Mat targetImage = Imgcodecs.imread("path/to/your/image.png");

        // Create a Robot instance to control the mouse and keyboard
        Robot robot = new Robot();

        // Create a screen capture
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenImage = robot.createScreenCapture(screenRect);
        Mat screenMat = new Mat();
        Imgcodecs.bufferedImageToMat(screenImage, screenMat);

        // Perform template matching to find the target image
        Mat result = new Mat();
        Imgproc.matchTemplate(screenMat, targetImage, result, Imgproc.TM_CCOEFF_NORMED);
        Core.MinMaxLocResult minMaxLocResult = Core.minMaxLoc(result);

        // Get the location of the matched image
        Point targetLocation = minMaxLocResult.maxLoc;

        // Click on the detected image
        int x = (int) targetLocation.x + targetImage.cols() / 2;
        int y = (int) targetLocation.y + targetImage.rows() / 2;
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
