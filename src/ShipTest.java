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

///////////////////////////////////////////////////// setBowColumn /////////////////////////////////////////////////////

    @Test
    void testSetBowColumn_Submarine() {
        Ship submarine = new Submarine();
        int col = 1;
        submarine.setBowColumn(col);
        assertEquals(col, submarine.getBowColumn());
    }

    @Test
    void testSetBowColumn_Destroyer() {
        Ship destroyer = new Destroyer();
        int col = 7;
        destroyer.setBowColumn(col);
        assertEquals(col, destroyer.getBowColumn());
    }

    @Test
    void testSetBowColumn_Cruiser() {
        Ship cruiser = new Cruiser();
        int col = 4;
        cruiser.setBowColumn(col);
        assertEquals(col, cruiser.getBowColumn());
    }

    @Test
    void testSetBowColumn_Battleship() {
        Ship battleship = new Battleship();
        int col = 6;
        battleship.setBowColumn(col);
        assertEquals(col, battleship.getBowColumn());
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

////////////////////////////////////////////////////// setBowRow ///////////////////////////////////////////////////////

    @Test
    void testSetBowRow_Submarine() {
        Ship submarine = new Submarine();
        int row = 0;
        submarine.setBowRow(row);
        assertEquals(row, submarine.getBowRow());
    }

    @Test
    void testSetBowRow_Destroyer() {
        Ship destroyer = new Destroyer();
        int row = 7;
        destroyer.setBowRow(row);
        assertEquals(row, destroyer.getBowRow());
    }

    @Test
    void testSetBowRow_Cruiser() {
        Ship cruiser = new Cruiser();
        int row = 9;
        cruiser.setBowRow(row);
        assertEquals(row, cruiser.getBowRow());
    }

    @Test
    void testSetBowRow_Battleship() {
        Ship battleship = new Battleship();
        int row = 4;
        battleship.setBowRow(row);
        assertEquals(row, battleship.getBowRow());
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

////////////////////////////////////////////////////// getLength ///////////////////////////////////////////////////////

    @Test
    void testGetLength_battleship() {
        Ship battleship = new Battleship();
        assertEquals(4, battleship.getLength());
    }

    @Test
    void testGetLength_cruiser() {
        Ship cruiser = new Cruiser();
        assertEquals(3, cruiser.getLength());
    }

    @Test
    void testGetLength_destroyer() {
        Ship destroyer = new Destroyer();
        assertEquals(2, destroyer.getLength());
    }

    @Test
    void testGetLength_submarine() {
        Ship submarine = new Submarine();
        assertEquals(1, submarine.getLength());
    }

///////////////////////////////////////////////////// setHorizontal ////////////////////////////////////////////////////

    @Test
    void testSetHorizontal_horizontalBattleship() {
        Ship battleship = new Battleship();
        boolean horizontal = true;
        battleship.setHorizontal(horizontal);
        assertEquals(horizontal, battleship.isHorizontal());
    }

    @Test
    void testSetHorizontal_verticalDestroyer() {
        Ship destroyer = new Destroyer();
        boolean horizontal = false;
        destroyer.setHorizontal(horizontal);
        assertEquals(horizontal, destroyer.isHorizontal());
    }

    @Test
    void testSetHorizontal_horizontalCruiser() {
        Ship cruiser = new Cruiser();
        boolean horizontal = true;
        cruiser.setHorizontal(horizontal);
        assertEquals(horizontal, cruiser.isHorizontal());
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

    @Test
    void testIsSunk_submarineOneShotSunk() {
        Ship submarine = new Submarine();
        int row = 8;
        int column = 7;
        submarine.placeShipAt(row, column, false, ocean);
        assertFalse(submarine.isSunk());
        submarine.shootAt(row, column);
        assertTrue(submarine.isSunk());
    }

    @Test
    void testIsSunk_submarineTwoShotsSunk() {
        Ship submarine = new Submarine();
        int row = 5;
        int column = 9;
        submarine.placeShipAt(row, column, true, ocean);
        assertFalse(submarine.isSunk());
        submarine.shootAt(row, column);
        assertTrue(submarine.isSunk());
        submarine.shootAt(row, column);
        assertTrue(submarine.isSunk());
    }

    @Test
    void testIsSunk_verticalDestroyerTwoShotsSunk() {
        Ship destroyer = new Destroyer();
        int row = 7;
        int column = 3;
        destroyer.placeShipAt(row, column, false, ocean);
        assertFalse(destroyer.isSunk());
        destroyer.shootAt(row, column);
        assertFalse(destroyer.isSunk());
        destroyer.shootAt(row + 1, column);
        assertTrue(destroyer.isSunk());
    }

    @Test
    void testIsSunk_horizontalCruiserThreeShotsSunk() {
        Ship cruiser = new Cruiser();
        int row = 1;
        int column = 2;
        cruiser.placeShipAt(row, column, true, ocean);
        assertFalse(cruiser.isSunk());
        cruiser.shootAt(row, column);
        assertFalse(cruiser.isSunk());
        cruiser.shootAt(row, column + 1);
        assertFalse(cruiser.isSunk());
        cruiser.shootAt(row, column + 2);
        assertTrue(cruiser.isSunk());
    }

    @Test
    void testIsSunk_verticalBattleshipFourShotsSunk() {
        Ship battleship = new Battleship();
        int row = 6;
        int column = 4;
        battleship.placeShipAt(row, column, false, ocean);
        assertFalse(battleship.isSunk());
        battleship.shootAt(row, column);
        assertFalse(battleship.isSunk());
        battleship.shootAt(row + 1, column);
        assertFalse(battleship.isSunk());
        battleship.shootAt(row + 2, column);
        assertFalse(battleship.isSunk());
        battleship.shootAt(row + 3, column);
        assertTrue(battleship.isSunk());
    }

    @Test
    void testIsSunk_horizontalBattleshipThreeShotsNotSunk() {
        Ship battleship = new Battleship();
        int row = 1;
        int column = 2;
        battleship.placeShipAt(row, column, true, ocean);
        assertFalse(battleship.isSunk());
        battleship.shootAt(row, column);
        assertFalse(battleship.isSunk());
        battleship.shootAt(row, column);
        assertFalse(battleship.isSunk());
        battleship.shootAt(row, column);
        assertFalse(battleship.isSunk());
    }

    @Test
    void testIsSunk_emptySeaNeverSink() {
        Ship empty = new EmptySea();
        int row = 5;
        int column = 3;
        empty.placeShipAt(row, column, false, ocean);
        assertFalse(empty.isSunk());
        empty.shootAt(row, column);
        assertFalse(empty.isSunk());
    }

//////////////////////////////////////////////////// okToPlaceShipAt ///////////////////////////////////////////////////

    @Test
    void testOkToPlaceShipAt_battleshipWithinBound() {
        Ship battleship = new Battleship();
        int row = 0;
        int column = 0;
        assertTrue(battleship.okToPlaceShipAt(row, column, true, ocean));
    }

    @Test
    void testOkToPlaceShipAt_cruiserEntirelyOutsideBound() {
        Ship cruiser = new Cruiser();
        int row = 10;
        int column = 7;
        assertFalse(cruiser.okToPlaceShipAt(row, column, false, ocean));
    }

    @Test
    void testOkToPlaceShipAt_destroyerPartiallyOutsideBound() {
        Ship destroyer = new Destroyer();
        int row = 5;
        int column = 9;
        assertFalse(destroyer.okToPlaceShipAt(row, column, true, ocean));
    }

    @Test
    void testOkToPlaceShipAt_cruiserOverlapsWithExistingShip() {
        Ship cruiser = new Cruiser();
        Ship battleship = new Battleship();
        battleship.placeShipAt(4, 5, false, ocean);
        assertFalse(cruiser.okToPlaceShipAt(7, 5, false, ocean));
    }

    @Test
    void testOkToPlaceShipAt_submarineBattleshipHorizontalAdjacency() {
        Ship submarine = new Submarine();
        Ship battleship = new Battleship();
        battleship.placeShipAt(0, 5, true, ocean);
        assertFalse(submarine.okToPlaceShipAt(0, 9, false, ocean));
    }

    @Test
    void testOkToPlaceShipAt_cruiserDestroyerVerticalAdjacency() {
        Ship cruiser = new Cruiser();
        Ship destroyer = new Destroyer();
        cruiser.placeShipAt(4, 5, false, ocean);
        assertFalse(destroyer.okToPlaceShipAt(3, 5, true, ocean));
    }

    @Test
    void testOkToPlaceShipAt_cruiserSubmarineDiagonalAdjacency() {
        Ship submarine = new Submarine();
        Ship cruiser = new Cruiser();
        cruiser.placeShipAt(6, 3, false, ocean);
        assertFalse(submarine.okToPlaceShipAt(5, 4, true, ocean));
    }

    @Test
    void testOkToPlaceShipAt_2DestroyersParallel() {
        Ship destroyer1 = new Destroyer();
        Ship destroyer2 = new Destroyer();
        destroyer1.placeShipAt(3, 3, true, ocean);
        assertTrue(destroyer2.okToPlaceShipAt(1, 3, true, ocean));
    }

////////////////////////////////////////////////////// placeShipAt /////////////////////////////////////////////////////

    @Test
    void testPlaceShipAt_horizontalBattleship() {
        Ship battleship = new Battleship();
        int row = 5;
        int column = 5;
        battleship.placeShipAt(row, column, true, ocean);
        //check position & orientation of ship
        assertEquals(row, battleship.getBowRow());
        assertEquals(column, battleship.getBowColumn());
        assertTrue(battleship.horizontal);
        //check location of ship
        //empty sea at 5, 4
        assertEquals("Empty", ocean.getShipArray()[5][4].getShipType());
        //empty sea at 5, 9
        assertEquals("Empty", ocean.getShipArray()[5][9].getShipType());
        //actual position
        assertEquals(battleship, ocean.getShipArray()[5][5]);
        assertEquals(battleship, ocean.getShipArray()[5][6]);
        assertEquals(battleship, ocean.getShipArray()[5][7]);
        assertEquals(battleship, ocean.getShipArray()[5][8]);
    }

    @Test
    void testPlaceShipAt_verticalCruiser() {
        Ship cruiser = new Cruiser();
        int row = 5;
        int column = 8;
        cruiser.placeShipAt(row, column, false, ocean);
        //check position & orientation of ship
        assertEquals(row, cruiser.getBowRow());
        assertEquals(column, cruiser.getBowColumn());
        assertFalse(cruiser.horizontal);
        //check location of ship
        //empty sea at 6, 7
        assertEquals("Empty", ocean.getShipArray()[6][7].getShipType());
        //empty sea at 5, 9
        assertEquals("Empty", ocean.getShipArray()[5][9].getShipType());
        //actual position
        assertEquals(cruiser, ocean.getShipArray()[5][8]);
        assertEquals(cruiser, ocean.getShipArray()[6][8]);
        assertEquals(cruiser, ocean.getShipArray()[7][8]);
    }

//////////////////////////////////////////////////////// shootAt ///////////////////////////////////////////////////////

    @Test
    void testShootAt_missBattleship() {
        Ship battleship = new Battleship();
        battleship.placeShipAt(0, 0, false, ocean);
        assertFalse(battleship.shootAt(0, 1));
    }

    @Test
    void testShootAt_hitEntireDestroyer() {
        Ship destroyer = new Destroyer();
        destroyer.placeShipAt(7, 9, false, ocean);
        assertTrue(destroyer.shootAt(7, 9));
        assertTrue(destroyer.shootAt(8, 9));
    }

    @Test
    void testShootAt_sunkenSubmarine() {
        Ship submarine = new Submarine();
        submarine.placeShipAt(4, 6, false, ocean);
        assertTrue(submarine.shootAt(4, 6));
        assertFalse(submarine.shootAt(4, 6));
    }

//////////////////////////////////////////////////////// toString //////////////////////////////////////////////////////

    @Test
    void testToString_battleship() {
        Ship battleship = new Battleship();
        int row = 0;
        int column = 0;
        battleship.placeShipAt(row, column, true, ocean);
        assertEquals("S", battleship.toString());
        battleship.shootAt(row, column);
        assertEquals("S", battleship.toString());
        battleship.shootAt(row, column + 1);
        assertEquals("S", battleship.toString());
        battleship.shootAt(row, column + 2);
        assertEquals("S", battleship.toString());
        battleship.shootAt(row, column + 3);
        assertEquals("x", battleship.toString());
    }

    @Test
    void testToString_cruiser() {
        Ship cruiser = new Cruiser();
        int row = 2;
        int column = 9;
        cruiser.placeShipAt(row, column, false, ocean);
        assertEquals("S", cruiser.toString());
        cruiser.shootAt(row, column);
        assertEquals("S", cruiser.toString());
        cruiser.shootAt(row + 1, column);
        assertEquals("S", cruiser.toString());
        cruiser.shootAt(row + 2, column);
        assertEquals("x", cruiser.toString());
    }

    @Test
    void testToString_destroyer() {
        Ship destroyer = new Destroyer();
        int row = 7;
        int column = 5;
        destroyer.placeShipAt(row, column, true, ocean);
        assertEquals("S", destroyer.toString());
        destroyer.shootAt(row, column);
        assertEquals("S", destroyer.toString());
        destroyer.shootAt(row, column + 1);
        assertEquals("x", destroyer.toString());
        //shoot at bow again
        destroyer.shootAt(row, column);
        assertEquals("x", destroyer.toString());
    }

    @Test
    void testToString_submarine() {
        Ship submarine = new Submarine();
        int row = 8;
        int column = 9;
        submarine.placeShipAt(row, column, false, ocean);
        assertEquals("S", submarine.toString());
        submarine.shootAt(row, column);
        assertEquals("x", submarine.toString());
        //shoot at submarine again
        submarine.shootAt(row, column);
        assertEquals("x", submarine.toString());
    }

    @Test
    void testToString_emptySea() {
        Ship emptySea = new EmptySea();
        int row = 3;
        int column = 3;
        emptySea.placeShipAt(row, column, true, ocean);
        assertEquals("-", emptySea.toString());
        emptySea.shootAt(row, column);
        assertEquals("-", emptySea.toString());
    }

}
