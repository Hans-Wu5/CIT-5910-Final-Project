import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zihan wu
 */

public class ShipTest {
    Ocean ocean;
    Ship ship;

///////////////////////////////////////////////////////// setup ////////////////////////////////////////////////////////

    @BeforeEach
    void setUP() {
        ocean = new Ocean();
    }

///////////////////////////////////////////////////// getBowColumn /////////////////////////////////////////////////////

    @Test
    void testGetBowColumn_horizontalBattleship() {
        //battleship's bow column
        Ship battleship = new Battleship();
        int row = 5;
        int column = 5;
        battleship.placeShipAt(row, column, true, ocean);
        assertEquals(column, battleship.getBowColumn());
    }

    @Test
    void testGetBowColumn_horizontalDestroyer() {
        Ship destroyer = new Destroyer();
        int row = 6;
        int column = 5;
        destroyer.placeShipAt(row, column, true, ocean);
        assertEquals(column, destroyer.getBowColumn());
    }

    @Test
    void testGetBowColumn_verticalCruiser() {
        Ship cruiser = new Cruiser();
        int row = 3;
        int column = 7;
        cruiser.placeShipAt(row, column, false, ocean);
        assertEquals(column, cruiser.getBowColumn());
    }

    @Test
    void testGetBowColumn_submarine() {
        Ship submarine = new Submarine();
        int row = 9;
        int column = 9;
        submarine.placeShipAt(row, column, false, ocean);
        assertEquals(column, submarine.getBowColumn());
    }

////////////////////////////////////////////////////// getBowRow ///////////////////////////////////////////////////////

    @Test
    void testGetBowRow_verticalBattleship() {
        Ship battleship = new Battleship();
        int row = 5;
        int column = 9;
        battleship.placeShipAt(row, column, false, ocean);
        assertEquals(row, battleship.getBowRow());
    }

    @Test
    void testGetBowRow_horizontalDestroyer() {
        Ship destroyer = new Destroyer();
        int row = 3;
        int column = 7;
        destroyer.placeShipAt(row, column, true, ocean);
        assertEquals(row, destroyer.getBowRow());
    }

    @Test
    void testGetBowRow_verticalCruiser() {
        Ship cruiser = new Cruiser();
        int row = 4;
        int column = 7;
        cruiser.placeShipAt(row, column, false, ocean);
        assertEquals(row, cruiser.getBowRow());
    }

    @Test
    void testGetBowRow_submarine() {
        Ship submarine = new Submarine();
        int row = 2;
        int column = 8;
        submarine.placeShipAt(row, column, false, ocean);
        assertEquals(row, submarine.getBowRow());
    }

///////////////////////////////////////////////////// isHorizontal /////////////////////////////////////////////////////

    @Test
    void testIsHorizontal_battleshipTrue() {
        Ship battleship = new Battleship();
        int row = 5;
        int column = 5;
        battleship.placeShipAt(row, column, true, ocean);
        assertTrue(battleship.isHorizontal());
    }

    @Test
    void testIsHorizontal_cruiserFalse () {
        Ship cruiser = new Cruiser();
        int row = 3;
        int column = 7;
        cruiser.placeShipAt(row, column, false, ocean);
        assertFalse(cruiser.isHorizontal());
    }

    @Test
    void testIsHorizontal_submarineTrue() {
        Ship submarine = new Submarine();
        int row = 0;
        int column = 0;
        submarine.placeShipAt(row, column, true, ocean);
        assertTrue(submarine.isHorizontal());
    }

    @Test
    void testIsHorizontal_emptySeaFalse() {
        Ship emptySea = new EmptySea();
        int row = 3;
        int column = 6;
        emptySea.placeShipAt(row, column, false, ocean);
        assertFalse(emptySea.isHorizontal());
    }

////////////////////////////////////////////////////// getShipType /////////////////////////////////////////////////////

    @Test
    void testGetShipType_battleship(){
        ship = new Battleship();
        assertEquals("Battleship", ship.getShipType());
    }

    @Test
    void testGetShipType_destroyer(){
        ship = new Destroyer();
        assertEquals("Destroyer", ship.getShipType());
    }

    @Test
    void testGetShipType_cruiser(){
        ship = new Cruiser();
        assertEquals("Cruiser", ship.getShipType());
    }

    @Test
    void testGetShipType_submarine(){
        ship = new Submarine();
        assertEquals("Submarine", ship.getShipType());
    }

    @Test
    void testGetShipType_emptySea(){
        ship = new EmptySea();
        assertEquals("Empty", ship.getShipType());
    }

//////////////////////////////////////////////////////// isSunk ////////////////////////////////////////////////////////


//////////////////////////////////////////////////// okToPlaceShipAt ///////////////////////////////////////////////////


////////////////////////////////////////////////////// placeShipAt /////////////////////////////////////////////////////


//////////////////////////////////////////////////////// shootAt ///////////////////////////////////////////////////////


//////////////////////////////////////////////////////// toString ///////////////////////////////////////////////////////
}
