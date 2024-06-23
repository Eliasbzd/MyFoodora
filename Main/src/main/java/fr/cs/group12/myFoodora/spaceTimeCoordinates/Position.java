package fr.cs.group12.myFoodora.spaceTimeCoordinates;
/**
 * The Position class represents a position in two-dimensional space with coordinates (x, y).
 */
public class Position {
    private double x;
    private double y;
    /**
     * Constructs a new Position object with the specified x and y coordinates.
     *
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Calculates the Euclidean distance between this position and another position.
     *
     * @param other The other position to calculate the distance to.
     * @return The Euclidean distance between this position and the other position.
     */
    public double distanceTo(Position other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
    /**
     * Returns a string representation of this position.
     *
     * @return A string representation of this position, including its x and y coordinates.
     */
    @Override
    public String toString() {
        return String.format("Position (x: %.2f, y: %.2f)", x, y);
    }
}

