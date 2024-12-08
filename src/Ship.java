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

    public Ship(int length) {
        this.length = length;
        hit = new boolean[length]; //default false
        bowColumn = -1;
        bowRow = -1;
        horizontal = false;
    }
///////////////////////////////////////////////////// setBowColumn /////////////////////////////////////////////////////

    /**
     * @return the column of the bow (front) of the ship
     */
    public void setBowColumn(int bowColumn){
        this.bowColumn = bowColumn;
    }

///////////////////////////////////////////////////// getBowColumn /////////////////////////////////////////////////////

    /**
     * @return the column of the bow (front) of the ship
     */
    public int getBowColumn(){
        if(bowColumn == -1){
            System.out.println("Bow Column Not Initialized");
        }
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
        if(bowRow == -1){
            System.out.println("Bow Row Not Initialized");
        }
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
        for(boolean b : hit){
            if(!b){
                return false;
            }
        }
        return true;
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
     * @return true if it is valid to place this ship of this length in this location with this orientation, and
     * false otherwise.
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        //if ship is horizontally placed
        if (horizontal) {
            if (row < 0 || row > 9 || column + this.getLength() - 1 > 9 || column > 9) {
                return false;
            }
            for (int i = Math.max(0, row - 1); i <= Math.min(row + 1, 9); i++) {
                for (int j = Math.max(0, column-1); j <= Math.min(column + this.getLength(), 9); j++) {
                    if (!(ocean.ships[i][j] instanceof EmptySea)) {
                        return false;
                    }
                }
            }
        //if ship is vertically placed
        } else {
            if ( row < 0 || row + this.getLength() -1 > 9 || column < 0 || column > 9) {
                return false;
            }
            for (int i = Math.max(0, row - 1); i <= Math.min(row + this.getLength(), 9); i++) {
                for (int j = Math.max(0, column - 1); j <= Math.min(column + 1, 9); j++) {
                    if (!(ocean.ships[i][j] instanceof EmptySea)) {
                        return false;
                    }
                }
            }
        }
        return true;
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
        this.setBowRow(row);
        this.setBowColumn(column);
        this.setHorizontal(horizontal);

        for(int i = 0; i < this.getLength(); i++) {
            if(horizontal){
                ocean.ships[row][column + i] = this;
            } else {
                ocean.ships[row + i][column] = this;
            }
        }
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

    //have some confusion with the prof, checking
    public boolean shootAt(int row, int column){
        if(!(this instanceof EmptySea)){

            if(this.isHorizontal()){
                if(this.getBowRow() == row){
                    if(column >= this.getBowColumn() && column <= (this.getBowColumn()+this.getLength())){
                      if(!this.hit[column-this.getBowColumn()]) {
                          this.hit[column - this.getBowColumn()] = true;
                          return true;
                      }
                    }
                }
            }else {
                if (this.getBowColumn() == column) {
                    if (row >= this.getBowRow() && row <= (this.getBowRow() + this.getLength())) {
                        if(!this.hit[row-this.getBowRow()]) {
                            this.hit[row - this.getBowRow()] = true;
                            return true;
                        }
                    }
                }
            }
        }else{
            this.hit[0] = true;
        }
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
        if (this.isSunk()) {
            return "x";
        } else {
            return "S";
        }
    }


}
