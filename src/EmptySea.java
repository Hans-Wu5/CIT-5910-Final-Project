/**
 * Considered a type of Ship so that the Ocean's ship 2D array can consist of EmptySea references for empty tiles and
 * proper ships for tiles with ships actually inside of them.
 * @author zihan wu
 */

public class EmptySea extends Ship{

///////////////////////////////////////////////////// constructor //////////////////////////////////////////////////////

    /**
     * Sets the inherited length variable and initializes the hit array, based on the size of this Empty Sea (1 tiles).
     */
    public EmptySea() {
        //call superclass constructor with length 4
        super(1);
    }

///////////////////////////////////////////////////// getShipType //////////////////////////////////////////////////////

    /**
     * @return "empty", indicating that this is an Empty sea tile.
     */
    @Override
    public String getShipType() {
        return "Empty";
    }

//////////////////////////////////////////////////////// isSunk ////////////////////////////////////////////////////////

    /**
     * Since an EmptySea is empty by definition, it is not possible that one can be sunk.
     *
     * @return false always, since nothing will be hit.
     */
    @Override
    public boolean isSunk() {
        return false;
    }

//////////////////////////////////////////////////////// shootAt ///////////////////////////////////////////////////////

    /**
     * Since an EmptySea is empty by definition, shooting at one will always be a miss.
     *
     * @param row - the row of the shot
     * @param column - the column of the shot
     *
     * @return false always, since nothing will be hit
     */
    @Override
    public boolean shootAt(int row, int column) {
        this.hit[0] = true;
        return false;
    }

//////////////////////////////////////////////////////// toString //////////////////////////////////////////////////////

    /**
     * Returns a single character String to use in the Ocean's print method. This method can only be used to print out
     * locations in the ocean that have been shot at; it should not be used to print locations that have not been the
     * target of a shot yet. Since an EmptySea is empty by definition, targeting it will always result in a miss so
     * return "-".
     *
     * @return "-" always, since nothing will be hit.
     */
    @Override
    public String toString() {
        return "-";
    }
}
