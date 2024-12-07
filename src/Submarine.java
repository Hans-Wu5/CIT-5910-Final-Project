/**
 * @author: zihan wu & junxiang li
 */

public class Submarine extends Ship{

///////////////////////////////////////////////////// constructor //////////////////////////////////////////////////////

    /**
     * Sets the inherited length variable and initializes the hit array, based on the size of this ship (1 tiles).
     */
    public Submarine() {
        //call superclass constructor with length 1
        super(1);
    }

///////////////////////////////////////////////////// getShipType //////////////////////////////////////////////////////

    /**
     * @return "Submarine", indicating that this is a Submarine.
     */
    @Override
    public String getShipType() {
        return "Submarine";
    }

}
