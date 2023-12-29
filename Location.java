/**
 * Model a location in a city.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2023.10.10 DP classes 
 */

public class Location{
    private final int x;  //posicion horizontal
    private final int y;  //posicion vertical

    /**
     * Model a location in the city.
     * @param x The x coordinate. Must be positive.
     * @param y The y coordinate. Must be positive.
     * @throws IllegalArgumentException If a coordinate is negative.
     */
    public Location(int x, int y){
        if(x < 0) {
            throw new IllegalArgumentException(
                "Negative x-coordinate: " + x);
        }
        if(y < 0) {
            throw new IllegalArgumentException(
                "Negative y-coordinate: " + y);
        }        
        this.x = x;
        this.y = y;
    }

    /**
     * @return The x coordinate.
     */
    public int getX(){
        return x;
    }

    /**
     * @return The y coordinate.
     */
    public int getY(){    
        return y;
    }

    /**
     * Generate the next location to visit in order to
     * reach the destination.
     * @param destination Where we want to get to.
     * @return A location in a direct line from this to
     *         destination.
     */
    public Location nextLocation(Location destination){
        Location locIni = new Location(this.getX(), this.getY());
        int xFinal; int yFinal;
        Location locFinal;
        if(destination != null){
            if(!destination.equals(locIni)){
                //caso en el que ni la x ni y sean iguales
                if(destination.getX() != locIni.getX() && destination.getY() != locIni.getY()){
                    if(locIni.getX() < destination.getX()) xFinal = locIni.getX() + 1;
                    else xFinal = locIni.getX() -1;

                    if(locIni.getY() < destination.getY()) yFinal = locIni.getY() + 1;
                    else yFinal = locIni.getY() -1;
                    //caso en el que o la x o la y sean iguales
                }else{
                    if(destination.getX() == locIni.getX()){
                        xFinal = locIni.getX();
                        if(locIni.getY() < destination.getY()){
                            yFinal = locIni.getY() + 1;
                        }else yFinal = locIni.getY() -1;
                    }else{
                        yFinal = locIni.getY();
                        if(locIni.getX() < destination.getX()) xFinal = locIni.getX() + 1;
                        else xFinal = locIni.getX() -1;
                    }
                }
            }else{
                xFinal = locIni.getX();
                yFinal = locIni.getY();
            }
        }else return null;
        locFinal = new Location(xFinal, yFinal);
        return locFinal;
    }

    /**
     * Determine the number of movements required to get
     * from here to the destination.
     * @param destination The required destination.
     * @return the number of movement steps.
     */
    public int distance(Location destination){
        int xI = this.getX(), yI = this.getY();
        int distX = Math.abs((xI - destination.getX())); 
        int distY = Math.abs((yI - destination.getY()));

        return Math.max(distX, distY);
    }

    /**
     * Implement content equality for locations.
     * @return true if this location matches the other, false otherwise.
     */
    public boolean equals(Object other){
        if(other instanceof Location) {
            Location otherLocation = (Location) other;
            return x == otherLocation.getX() &&
            y == otherLocation.getY();
        }
        else return false;
    }

    /**
     * @return A representation of the location.
     */
    public String toString(){
        return "location " + x + "," + y;
    }

    /**
     * Use the top 16 bits for the y value and the bottom for the x.
     * Except for very big grids, this should give a unique hash code
     * for each (x, y) pair.
     * @return A hashcode for the location.
     */
    public int hashCode(){
        return (y << 16) + x;
    }


}
