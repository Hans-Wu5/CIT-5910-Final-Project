/**
 * @author: zihan wu & junxiang li
 */

public class Destroyer extends Ship{

///////////////////////////////////////////////////// constructor //////////////////////////////////////////////////////

    /**
     * Sets the inherited length variable and initializes the hit array, based on the size of this ship (2 tiles).
     */
    public Destroyer() {
        //call superclass constructor with length 2
        super(2);
    }

///////////////////////////////////////////////////// getShipType //////////////////////////////////////////////////////

    /**
     * @return "Destroyer", indicating that this is a Destroyer.
     */
    @Override
    public String getShipType() {
        return "Destroyer";
    }

}
