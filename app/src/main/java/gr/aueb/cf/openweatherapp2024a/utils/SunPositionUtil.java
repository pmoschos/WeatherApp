package gr.aueb.cf.openweatherapp2024a.utils;

import android.view.View;
import android.widget.ImageView;

public class SunPositionUtil {
    // Private constructor to prevent instantiation
    private SunPositionUtil(){}

    /**
     * Calculates the sun's position on an arc and moves the sun icon accordingly.
     *
     * @param arcContainer The container representing the arc.
     * @param sunIcon      The ImageView representing the sun icon.
     * @param progress     A value between 0 and 1 (0 = sunrise, 1 = sunset).
     */
    public static void setSunPosition(View arcContainer, ImageView sunIcon, double progress) {
        // progress: A value between 0 and 1 (0 = sunrise, 1 = sunset)

        // Arc center and radius
        double centerX = arcContainer.getWidth() / 2f; // Middle of the arc
        double centerY = arcContainer.getHeight();    // Bottom of the arc (container's height)
        double radius = arcContainer.getWidth() / 2f - sunIcon.getWidth(); // Arc radius

        // Convert progress to an angle in radians (180° arc = Math.PI radians)
        double angle = Math.PI * progress;

        // Calculate x and y positions
        double x = centerX + (radius * Math.cos(angle)); // Horizontal position
        double y = centerY - (radius * Math.sin(angle)); // Vertical position (subtract for upward motion)

        // Move sun icon to calculated position
        sunIcon.setX((float) x - sunIcon.getWidth() / 2f); // Center the icon horizontally
        sunIcon.setY((float) y - sunIcon.getHeight() / 2f); // Center the icon vertically
    }

    /**
     * Calculates the progress of the sun's position between sunrise and sunset.
     *
     * @param sunrise The UNIX timestamp of the sunrise (in seconds).
     * @param sunset  The UNIX timestamp of the sunset (in seconds).
     * @return A value between 0 and 1 (0 = sunrise, 1 = sunset).
     */
    public static double calculateSunProgress(long sunrise, long sunset) {
        // Get the current time in seconds
        long currentTime = System.currentTimeMillis() / 1000;

        // Ensure the current time is between sunrise and sunset
        if (currentTime < sunrise) {
            return 0.00; // Before sunrise
        } else if (currentTime > sunset) {
            return 1.00; // After sunset
        }

        // Calculate progress as a fraction of the time between sunrise and sunset
        return (double) (currentTime - sunrise) / (sunset - sunrise);
    }
}
