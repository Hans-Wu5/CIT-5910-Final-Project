/**
 * This class manages the game state by keeping track of what entity is
 * contained in each position on the game board.
 * 
 * @author harry
 *
 */
public class Ocean implements OceanInterface {

/////////////////////////////////////////--------------- Fields ---------------/////////////////////////////////////////

	/**
	 * A 10x10 2D array of Ships, which can be used to quickly determine which ship
	 * is in any given location.
	 */
	protected Ship[][] ships;

	/**
	 * The total number of shots fired by the user
	 */
	protected int shotsFired;

	/**
	 * The number of times a shot hit a ship. If the user shoots the same part of a
	 * ship more than once, every hit is counted, even though the additional "hits"
	 * don't do the user any good.
	 */
	protected int hitCount;

	/**
	 * The number of ships totally sunk.
	 * 
	 */
	protected int shipsSunk;

///////////////////////////////////////////////////// Constructor //////////////////////////////////////////////////////

	/**
	 * Creates an "empty" ocean, filling every space in the <code>ships</code> array
	 * with EmptySea objects. Should also initialize the other instance variables
	 * appropriately.
	 */
	public Ocean() {

	}

//////////////////////////////////////////////// placeAllShipsRandomly /////////////////////////////////////////////////

	/**
	 * Place all ten ships randomly on the (initially empty) ocean. Larger ships
	 * must be placed before smaller ones to avoid cases where it may be impossible
	 * to place the larger ships.
	 * 
	 * @see java.util.Random
	 */
	public void placeAllShipsRandomly() {

	}

////////////////////////////////////////////////////// isOccupied //////////////////////////////////////////////////////

	/**
	 * Checks if this coordinate is not empty; that is, if this coordinate does not
	 * contain an EmptySea reference.
	 *
	 * @param row    the row (0 to 9) in which to check for a floating ship
	 * @param column the column (0 to 9) in which to check for a floating ship
	 * @return {@literal true} if the given location contains a ship, and
	 *         {@literal false} otherwise.
	 */
	public boolean isOccupied(int row, int column) {
		return false;
	}

/////////////////////////////////////////////////////// shootAt ////////////////////////////////////////////////////////

/**
 * Fires a shot at this coordinate. This will update the number of shots that
 * have been fired (and potentially the number of hits, as well). If a location
 * contains a real, not sunk ship, this method should return {@literal true}
 * every time the user shoots at that location. If the ship has been sunk,
 * additional shots at this location should return {@literal false}.
 *
 * @param row    the row (0 to 9) in which to shoot
 * @param column the column (0 to 9) in which to shoot
 * @return {@literal true} if the given location contains a float ship (not an
 *         EmptySea), {@literal false} if it does not.
 */
public boolean shootAt(int row, int column) {
    //increase the shot
    this.shotsFired++;

    //get result of ship shoot at
    boolean result = ships[row][column].shootAt(row, column);
    //return false for empty sea
    if(ships[row][column] instanceof EmptySea) {
        System.out.println("Oops. You missed 😛");
        return false;
    }

    if(result) {
        System.out.println("You just hit a ship!");
        this.hitCount++;
        if(ships[row][column].isSunk()){
            System.out.println("You have sunk a " + this.ships[row][column].getShipType() + "!");
            this.shipsSunk++;
        }
    }
    return result;
}

//////////////////////////////////////////////////// getShotsFired /////////////////////////////////////////////////////

	/**
	 * @return the number of shots fired in this game.
	 */
	public int getShotsFired() {
		return this.shotsFired;
	}

////////////////////////////////////////////////////// getHitCount /////////////////////////////////////////////////////

	/**
	 * @return the number of hits recorded in this game.
	 */
	public int getHitCount() {
		return this.shotsFired;
	}

////////////////////////////////////////////////////// getShipSunk /////////////////////////////////////////////////////

	/**
	 * @return the number of ships sunk in this game.
	 */
	public int getShipsSunk() {
		return this.shipsSunk;
	}

////////////////////////////////////////////////////// isGameOver //////////////////////////////////////////////////////

	/**
	 * @return {@literal true} if all ships have been sunk, otherwise
	 *         {@literal false}.
	 */
	public boolean isGameOver() {
		return false;
	}

///////////////////////////////////////////////////// getShipArray /////////////////////////////////////////////////////

	/**
	 * Provides access to the grid of ships in this Ocean. The methods in the Ship
	 * class that take an Ocean parameter must be able to read and even modify the
	 * contents of this array. While it is generally undesirable to allow methods in
	 * one class to directly access instancce variables in another class, in this
	 * case there is no clear and elegant alternatives.
	 * 
	 * @return the 10x10 array of ships.
	 */
	public Ship[][] getShipArray() {
		return null;
	}

///////////////////////////////////////////////////////// print ////////////////////////////////////////////////////////

	/**
	 * Prints the ocean. To aid the user, row numbers should be displayed along the
	 * left edge of the array, and column numbers should be displayed along the top.
	 * Numbers should be 0 to 9, not 1 to 10. The top left corner square should be
	 * 0, 0.
	 * <ul>
	 * <li>Use 'S' to indicate a location that you have fired upon and hit a (real)
	 * ship</li>
	 * <li>'-' to indicate a location that you have fired upon and found nothing
	 * there</li>
	 * <li>'x' to indicate a location containing a sunken ship</li>
	 * <li>'.' (a period) to indicate a location that you have never fired
	 * upon.</li>
	 * </ul>
	 * 
	 * This is the only method in Ocean that has any printing capability, and it
	 * should never be called from within the Ocean class except for the purposes of
	 * debugging.
	 * 
	 */
	public void print() {
		//initialize a table to print later
		char[][] table = new char[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if(this.ships[i][j] instanceof EmptySea){
					if(this.ships[i][j].isSunk()) {
						table[i][j] = '-';
					}else{
						table[i][j] = '.'; // Unfired locations are initialized to '.'
					}
				}else {
					if (this.ships[i][j].isSunk()) {
						table[i][j] = 'x';
					} else {
						if(( this.ships[i][j]).isHorizontal()){
							if(this.ships[i][j].hit[j-this.ships[i][j].getBowColumn()]){
								table[i][j] = 'S';
							}else{
								table[i][j] = '.';
							}
						}else{
							if(this.ships[i][j].hit[i-this.ships[i][j].getBowRow()]){
								table[i][j] = 'S';
							}else{
								table[i][j] = '.';
							}
						}


					}
				}
			}
		}


    //print the column number
    System.out.println();
    System.out.print("   ");
    for (int i = 0; i < 10; i++) {
        System.out.print(i+"  ");
    }
    System.out.println();

    //print row number and the value/state
    for (int i = 0; i < 10; i++) {
        System.out.print(i+"  ");
        for (int j = 0; j < 10; j++) {
            System.out.print(table[i][j]+"  ");
        }
        System.out.println();
    }


}}
