/**
 * A ship with a length of four tiles.
 * @author zihan wu
 */

public class Battleship extends Ship{

///////////////////////////////////////////////////// constructor //////////////////////////////////////////////////////

    /**
     * Sets the inherited length variable and initializes the hit array, based on the size of this ship (4 tiles).
     */
    public Battleship() {
        //call superclass constructor with length 4
        super(4);
    }

///////////////////////////////////////////////////// getShipType //////////////////////////////////////////////////////

    /**
     * @return "Battleship", indicating that this is a battleship
     */
    @Override
    public String getShipType() {
        return "Battleship";
    }
}