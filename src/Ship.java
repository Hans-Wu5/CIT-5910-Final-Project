public abstract class Ship {

/////////////////////////////////////////--------------- Fields ---------------/////////////////////////////////////////

    /**
     * The column (0 to 9) which contains the bow (front) of the ship.
     */
    protected int bowColumn;

    /**
     * The row (0 to 9) which contains the bow (front) of the ship.
     */
    protected int bowRow;

    /**
     * An array of four booleans telling whether that part of the ship has been hit.
     */
    protected boolean[] hit;

    /**
     * true if the ship occupies a single row, false otherwise.
     */
    protected boolean horizontal;

    /**
     * The number of tiles occupied by the ship.
     */
    protected int length;

///////////////////////////////////////////////////// Constructor //////////////////////////////////////////////////////

    public Ship() {

    }

///////////////////////////////////////////////////// getBowColumn /////////////////////////////////////////////////////

    /**
     * @return the column of the bow (front) of the ship
     */
    public int getBowColumn(){
        return this.bowColumn;
    }

////////////////////////////////////////////////////// setBowRow ///////////////////////////////////////////////////////

    /**
     * @param bowRow - the bowRow to set
     */
    public void setBowRow(int bowRow){
        this.bowRow = bowRow;
    }

////////////////////////////////////////////////////// getBowRow ///////////////////////////////////////////////////////

    /**
     * @return the row of the bow (front) of the ship
     */
    public int getBowRow(){
        return this.bowRow;
    }

////////////////////////////////////////////////////// getLength ///////////////////////////////////////////////////////

    /**
     * @return ship length
     */
    public int getLength(){
        return this.length;
    }

///////////////////////////////////////////////////// setHorizontal ////////////////////////////////////////////////////

    /**
     * @param horizontal - the horizontal to set
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

///////////////////////////////////////////////////// isHorizontal /////////////////////////////////////////////////////

    /**
     * @return true if this boat is horizontal (facing left), false otherwise.
     */
    public boolean isHorizontal() {
        return this.horizontal;
    }

////////////////////////////////////////////////////// getShipType /////////////////////////////////////////////////////

    /**
     * create an abstract class for each ship type, returns the type of ship as a string
     *
     * @return the String representing the type of this ship.
     */
    public abstract String getShipType();

//////////////////////////////////////////////////////// isSunk ////////////////////////////////////////////////////////

    /**
     * Returns true if this Ship has been sunk and false otherwise
     *
     * @return true if every part of the ship is hit, false otherwise
     */
    public boolean isSunk() {
        /// //CHANGE
        return false;
    }

//////////////////////////////////////////////////// okToPlaceShipAt ///////////////////////////////////////////////////

    /**
     * Determines whether this represents a valid placement configuration for this Ship in this Ocean. Ship objects in
     * an Ocean must not overlap other Ship objects or touch them vertically, horizontally, or diagonally. Additionally,
     * the placement cannot be such that the Ship would extend beyond the extents of the 2D array in which it is placed.
     * Calling this method should not change either the Ship or the provided Ocean.
     *
     * @param row - the candidate row to place the ship
     * @param column - the candidate column to place the ship
     * @param horizontal - whether or not to have the ship facing to the left
     * @param ocean - the Ocean in which this ship might be placed
     *
     * @return true if every part of the ship is hit, false otherwise
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        /// //CHANGE
        return false;
    }

////////////////////////////////////////////////////// placeShipAt /////////////////////////////////////////////////////

    /**
     * Puts the Ship in the Ocean. This will give values to the bowRow, bowColumn, and horizontal instance variables in
     * the Ship. This should also place a reference to this Ship in each of the one or more locations (up to four) in
     * the corresponding Ocean array this Ship is being placed in. Each of the references placed in the Ocean will be
     * identical since it is not possible to refer to a "part" of a ship, only the whole ship.
     *
     * @param row - the row to place the ship
     * @param column - the column to place the ship
     * @param horizontal - whether to have the ship facing to the left
     * @param ocean - the Ocean in which this ship will be placed
     */
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        /// /CHANGE
    }

//////////////////////////////////////////////////////// shootAt ///////////////////////////////////////////////////////

    /**
     * If a part of this ship occupies this coordinate, and if the ship hasn't been sunk, mark the part of the ship at
     * that coordinate as "hit".
     *
     * @param row - the row of the shot
     * @param column - the column of the shot
     *
     * @return true if this ship hasn't been sunk and a part of this ship occupies the given row and column and false
     * otherwise
     */
    public boolean shootAt(int row, int column){
        /// //////////CHANGE
        return false;
    }

//////////////////////////////////////////////////////// toString ///////////////////////////////////////////////////////

    /**
     * Returns a single character String to use in the Ocean's print method. This method should return "x" if the ship
     * has been sunk, and "S" if it has not yet been sunk. This method can only be used to print out locations in the
     * ocean that have been shot at; it should not be used to print locations that have not been the target of a
     * shot yet.
     *
     * @override toString in class Object
     *
     * @return "x" if this ship has been sunk, and "S" otherwise
     */
    @Override
    public String toString() {
        //determine if the ship is sunk
        if (isSunk()) {
            return "x";
        } else {
            return "S";
        }
    }

}
