/**
 * @author: zihan wu & junxiang li
 */

public class Cruiser extends Ship{

///////////////////////////////////////////////////// constructor //////////////////////////////////////////////////////

    /**
     * Sets the inherited length variable and initializes the hit array, based on the size of this ship (3 tiles).
     */
    public Cruiser() {
        //call superclass constructor with length 3
        super(3);
    }

///////////////////////////////////////////////////// getShipType //////////////////////////////////////////////////////

    /**
     * @return "Cruiser", indicating that this is a Cruiser.
     */
    @Override
    public String getShipType() {
        return "Cruiser";
    }

}
