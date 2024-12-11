import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class OceanTest {

    Ocean ocean;

    ///////////////////////////////////////////////////////// setup ////////////////////////////////////////////////////////

    @BeforeEach
    public void setUp() {
        ocean = new Ocean();
    }


    ///////////////////////////////////////////////////////// Constructor ////////////////////////////////////////////////////////

    @Test
    public void testOceanConstructorInstance() {
        assertInstanceOf(Ocean.class, ocean, "The ocean is not the class of ocean");
        assertInstanceOf(Ship[][].class, ocean.getShipArray(), "The ship array is not the class of Ship[][]");
        Random random = new Random();
        for(int i=0; i<20; i++){
            int row = random.nextInt(10);
            int col = random.nextInt(10);
            assertInstanceOf(EmptySea.class,ocean.ships[row][col],
                    "The initialized class of ship[" + row +"][" + col + "] should be ship");
        }
    }

    @Test
    public void testOceanConstructorNull() {
        assertNotNull(ocean, "The ocean is not initialized");
        assertNotNull(ocean.getShipArray(), "The ship array is not initialized");
        for(int i = 0; i < 10; i++){
            assertNotNull(ocean.getShipArray()[i], "The ship array is not initialized in 2D method");
        }

    }


    @Test
    public void testOceanConstructorInitializedValue() {
        assertEquals(0,ocean.getHitCount(), "The hit count should be 0!");
        assertEquals(0,ocean.getShotsFired(),"The shot fired should be 0!");
        assertEquals(0,ocean.getShipsSunk(), "It should be 0 sunk ship!");
        assertEquals(ocean.getShipArray().length,10,"The length should be 10.");

        for(int i=0; i<10; i++) {
            assertEquals(ocean.getShipArray()[i].length, 10,"The length should be 10.");
            for(int j=0; j<10; j++){
                assertFalse(ocean.isOccupied(i,j),"It should not be occupied.");
            }
        }

        assertFalse(ocean.isGameOver(), "We just start the game. It is not game over.");
    }

    ///////////////////////////////////////////////////////// placeAllShipsRandomly ////////////////////////////////////////////////////////

    @Test
    public void testOceanPlaceAllShipsRandomlyNumber() {

        //initialized
        ocean.placeAllShipsRandomly();
        Ship[][] ships = ocean.getShipArray();
        int battleships = 0, cruisers = 0, destroyers = 0, submarines = 0, emptySea = 0;
        ArrayList<Ship> generatedShips = new ArrayList<>();


        //count the number of the ships and empty sea
        for (Ship[] ship : ships) {
            for (Ship value : ship) {
                if (!(value instanceof EmptySea)) {
                    if (!generatedShips.contains(value)) {
                        generatedShips.add(value);
                        switch (value) {
                            case Battleship battleship -> battleships++;
                            case Cruiser cruiser -> cruisers++;
                            case Destroyer destroyer -> destroyers++;
                            case Submarine submarine -> submarines++;
                            default -> {
                            }
                        }
                    }
                } else {
                    emptySea++;
                }
            }
        }

        //check the number
        assertEquals(1, battleships, "The battleships count should be 1");
        assertEquals(2, cruisers, "The cruisers count should be 2");
        assertEquals(3, destroyers, "The destroyers count should be 3");
        assertEquals(4, submarines, "The submarines count should be 4");
        assertEquals(10, generatedShips.size(), "The generated ships count should be 10");
        assertEquals(80, emptySea, "The empty sea count should be 80");

    }

    @Test
    public void testOceanPlaceAllShipsRandomlyOverlapAndBounds10Times() {

        //initialize field
        ocean.placeAllShipsRandomly();
        Ship[][] ships = ocean.getShipArray();
        boolean overlaps = false;
        boolean outOfBounds = false;
        for(int counter = 0; counter < 10; counter++ ){
            //check if overlap
            for (int rowOuter = 0; rowOuter < ocean.ships.length; rowOuter++) {
                for (int columnOuter = 0; columnOuter < ocean.ships[rowOuter].length; columnOuter++) {
                    Ship ship = ocean.ships[rowOuter][columnOuter];
                    int row = ship.getBowRow();
                    int column = ship.getBowColumn();
                    if (!(ship instanceof EmptySea)) {
                        if (ship.isHorizontal()) {
                            if (row < 0 || row > 9 || column + ship.getLength() - 1 > 9 || column > 9) {
                                outOfBounds = true;
                            }
                            for (int i = Math.max(0, row - 1); i <= Math.min(row + 1, 9); i++) {
                                for (int j = Math.max(0, column - 1); j <= Math.min(column + ship.getLength(), 9); j++) {
                                    if (!(ocean.ships[i][j] instanceof EmptySea)) {
                                        if (!ocean.ships[i][j].equals(ship)) {
                                            overlaps = true;
                                        }
                                    }
                                }
                            }
                            //if ship is vertically placed
                        } else {
                            if (row < 0 || row + ship.getLength() - 1 > 9 || column < 0 || column > 9) {
                                outOfBounds = true;
                            }
                            for (int i = Math.max(0, row - 1); i <= Math.min(row + ship.getLength(), 9); i++) {
                                for (int j = Math.max(0, column - 1); j <= Math.min(column + 1, 9); j++) {
                                    if (!(ocean.ships[i][j] instanceof EmptySea)) {
                                        if (!ocean.ships[i][j].equals(ship)) {
                                            overlaps = true;
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }

            //check if overlap
            assertFalse(overlaps, "Ships should not overlap with each other");
            assertFalse(outOfBounds, "Ships should not be placed out of boundaries");
        }

    }

    @Test
    public void testOceanPlaceAllShipsRandomlyIsRandomly() {

        //initialization
        boolean isEqual = true;
        ArrayList<ArrayList<Integer>> arrayList2D = new ArrayList<>();

        //generate the data
        for(int i = 0; i < 10; i ++){
            arrayList2D.add(new ArrayList<>());
            Ocean oceanForTest = new Ocean();
            oceanForTest.placeAllShipsRandomly();

            //get the position
            Ship[][] ships = oceanForTest.getShipArray();
            for(int j = 0; j < 10; j++){
                for (int k = 0; k < 10; k++){
                    if(!(ships[j][k] instanceof EmptySea)){
                        arrayList2D.get(i).add(j);
                        arrayList2D.get(i).add(k);
                    }
                }
            }
        }

        //check if same
        for (int col = 0; col < 40; col++) {
            for (int row = 1; row < 10; row++) {
                if (!Objects.equals(arrayList2D.get(row - 1).get(col), arrayList2D.get(row).get(col))) {
                    isEqual = false;
                    break;
                }
            }
        }

        //then check the result
       assertFalse(isEqual, "The ship position generated is identical, which is not randomly placed");

    }

    ///////////////////////////////////////////////////////// print ////////////////////////////////////////////////////////

    @Test
    void testOceanPrintDots() {
        //setup
        PrintStream out = System.out;
        //change outputstream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String output;
        String parseOutput;

        System.setOut(new PrintStream(baos));

        //print and parse
        ocean.print();
        output = baos.toString();
        parseOutput = output.replaceAll("[^\\.xS-]", "");
        for(char c : parseOutput.toCharArray()){
            assertEquals(c,'.',"It should only contain \'.\' because we did not fire yet");
        }
        assertEquals(100,parseOutput.length(),"The length should be 100");

        //reset
        System.setOut(out);
    }

    @Test
    void testOceanPrintAtLeastOneNotDot() {
        String parseOutput;
        String output;
        boolean isNotDots = false;
        boolean isOtherDots = true;
        ocean.placeAllShipsRandomly();
        ocean.shootAt(1,1);
        //setup
        PrintStream out = System.out;
        //change outputstream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //print and parse
        System.setOut(new PrintStream(baos));
        ocean.print();
        output = baos.toString();
        parseOutput = output.replaceAll("[^\\.xS-]", "");
        for(int i = 0; i <100;i++){
            if((parseOutput.indexOf(i) != '.') || i==11){
                isNotDots = true;
            }else if(parseOutput.indexOf(i) !='.'){
                isOtherDots= false;
            }

        }
        assertTrue(isNotDots,"It should contain at least one not dot");
        assertTrue(isOtherDots,"Other should be dots");
        assertEquals(100,parseOutput.length(),"The length should be 100");

        //reset
        System.setOut(out);
        //test code for print
        //ocean.print();
        //System.out.println(baos.toString());
        //System.out.println(parseOutput);
    }

    @Test
    public void testOceanPrintAtDifferentSituation() {

        //To shoot at random
        Random rand = new Random();
        int row = rand.nextInt(10);
        int column = rand.nextInt(10);
        String output;
        String parseOutput;
        ocean.placeAllShipsRandomly();

        //setup
        PrintStream out = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //change outputstream
        for(int n = 0; n < 10; n++){
            ocean.shootAt(row,column);
            System.setOut(new PrintStream(baos));
            ocean.print();
            output = baos.toString();
            parseOutput = output.replaceAll("[^\\.xS-]", "");
            System.setOut(out);
            for(int i = 0; i <100;i++){
                if(i==row*10+column){
                    switch (ocean.ships[row][column].getShipType()) {
                        case "Submarine" -> assertEquals('x',parseOutput.charAt(i),
                                "Shooting a submarine should be S");
                        case "Battleship" -> assertEquals('S',parseOutput.charAt(i),
                                "Shooting a part of Battleship should be S");
                        case "Cruiser" -> assertEquals('S', parseOutput.charAt(i),
                                "Shooting a part of Cruiser should be S");
                        case "Destroyer" -> assertEquals('S',parseOutput.charAt(i),
                                "Shooting a part of Destroyer should be S");
                        case "empty" -> assertEquals('-',parseOutput.charAt(i),
                                "Shooting a part of empty sea should be -");
                        default -> System.out.println("Unknown ship type.");
                    }
                }
            }
        }

        //then shoot the ship to sunk and then try again
        if(!(ocean.ships[row][column] instanceof EmptySea) ){

            for (int k = row; k < Math.min(9,row+ocean.ships[row][column].getLength());k++){
                for (int j = column; j < Math.min(9,column+ocean.ships[row][column].getLength());j++){
                    ocean.shootAt(k,j);
                }
            }
            System.setOut(new PrintStream(baos));
            ocean.print();

            output = baos.toString();
            parseOutput = output.replaceAll("[^\\.xS-]", "");
            System.setOut(out);
            assertEquals('x',parseOutput.charAt(row*10+column),
                    "The ship should sink and print x");

    }

    }


    @Test
    void testOceanPrintNotDotsAndXSBar() {
        String parseOutput;
        String output;
        boolean isDots = false;
        boolean isX = false;
        boolean isBar = false;
        PrintStream out = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ocean.placeAllShipsRandomly();
        for(int i = 0; i <10; i++){
            for(int j = 0; j < 10; j++){
                ocean.shootAt(i,j);
            }
        }


        //print and parse
        System.setOut(new PrintStream(baos));
        ocean.print();
        output = baos.toString();
        parseOutput = output.replaceAll("[^\\.xS-]", "");
        for(char c : parseOutput.toCharArray()){
            switch(c){
                case '.':
                    isDots = true;
                    break;
                case 'x':
                    isX = true;
                    break;
                case '-':
                    isBar = true;
                    break;
            }
        }

        //reset
        System.setOut(out);
        //ocean.print();

        //check
        assertEquals(100,parseOutput.length(),"The length should be 100");
        assertFalse(isDots,"It should not contain dots");
        assertTrue(isX,"It should contain x");
        assertTrue(isBar,"It should contain bar");

    }

///////////////////////////////////////////////////////// shootAt ////////////////////////////////////////////////////////

    @Test
    public void testOceanShootAtEmptySeaRandomly10Times() {
        //setup
        Random rand = new Random();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = System.out;
        String[] actualOutput;
        String expected =  "Oops. You missed 😛";
        //for print

        System.setOut(new PrintStream(baos));

        //shoot at
        for(int n = 0; n < 10; n++){
            assertFalse(ocean.shootAt( rand.nextInt(10), rand.nextInt(10)));
        }


        actualOutput = baos.toString().trim().split("\\R");

        for (int i = 0; i < 10; i++) {
            assertEquals(expected, actualOutput[i], "Line " + (i + 1) + " does not match.");
        }
        System.setOut(out);

    }

    @Test
    public void testOceanShootAtAll() {
        //setup
        String[] actualOutput;
        int missShot = 0;
        int hitShip = 0;
        int htiBattleship = 0;
        int htiCruiser = 0;
        int htiDestroyer = 0;
        int htiSubmarine = 0;
        int reHit = 0;
        int reMiss = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = System.out;
        ocean.placeAllShipsRandomly();

        //for print
        System.setOut(new PrintStream(baos));

        //shoot at

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(ocean.shootAt(i,j)){
                    reHit++;
                }else{
                    reMiss++;
                };
            }
        }

        actualOutput = baos.toString().trim().split("\\R");

        for (int k = 0; k < actualOutput.length; k++) {
            switch (actualOutput[k]) {
                case "Oops. You missed 😛":
                    missShot++;
                    break;
                case "You just hit a ship!":
                    hitShip++;
                    break;
                case "You have sunk a Battleship!":
                    htiBattleship++;
                    break;
                case "You have sunk a Cruiser!":
                    htiCruiser++;
                    break;
                case "You have sunk a Destroyer!":
                    htiDestroyer++;
                    break;
                case "You have sunk a Submarine!":
                    htiSubmarine++;
                    break;
            }
        }
        System.setOut(out);

        //check
        assertEquals(20,hitShip, "Should have 10 shiphit");
        assertEquals(80,missShot, "Should have 80 shipmiss");
        assertEquals(1,htiBattleship, "Should have 1 battleship sunk");
        assertEquals(2,htiCruiser,  "Should have 2 cruisers");
        assertEquals(3,htiDestroyer,  "Should have 3 cruisers");
        assertEquals(4,htiSubmarine,  "Should have 4 cruisers");
        assertEquals(20, reHit, "Should have 20 true return from the function");
        assertEquals(80, reMiss, "Should have 80 false return from the function");
    }

    @Test
    public void testOceanShootSunkRepeatedly() {
        //setup
        ocean.placeAllShipsRandomly();
        int sunkShip = 0;
        int miss = 0;


        //for print
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = System.out;


        //shoot at
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                ocean.shootAt(i,j);
            }
        }

        System.setOut(new PrintStream(baos));
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                ocean.shootAt(i,j);
            }
        }

        String[] actualOutput = baos.toString().trim().split("\\R");
        for(int i = 0; i < 100; i++){
            if (actualOutput[i].equals("You just hit a sunk ship!")) {
                sunkShip++;
            }else if(actualOutput[i].equals("Oops. You missed 😛")){
                miss++;
            }
        }
        System.setOut(out);

        //check
        assertEquals( 20, sunkShip,
                "Should have 20 sunk ship printout from the function");
        assertEquals(80, miss, "Should still have 80 miss");
    }

    @Test
    public void testOceanShootRepeatedlyOnUnsunkShip() {
        //setup

        int row;
        int col;
        String[] actualOutput;
        int isTrue = 0;
        int sunkHit = 0;
        String type;
        Random rand = new Random();
        ocean.placeAllShipsRandomly();
        //for print
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = System.out;


        //shoot at

        do{
            row = rand.nextInt(10);
            col = rand.nextInt(10);
            ocean.shootAt(rand.nextInt(10), rand.nextInt(10));
            type = ocean.ships[row][col].getShipType();
        }while(type.equals("empty")||type.equals("Submarine"));

        System.setOut(new PrintStream(baos));
        for (int i = 0; i < 10; i++){
            if(ocean.shootAt(row,col)){
                isTrue++;
            }
        }

        System.setOut(out);
        actualOutput = baos.toString().trim().split("\\R");
        for (String s : actualOutput) {
            if (s.equals("You just hit a ship!")) {
                sunkHit++;
            }
        }

        assertEquals(10,isTrue, "Should have 10 true value returned from shootAt");
        assertEquals(10,sunkHit, "Should have 10 counts for hitting the ship");

    }

    ///////////////////////////////////////////////////////// getHitCount ////////////////////////////////////////////////////////

    @Test
    public void testGetHitCountZero(){
        //hit count should be zero because we did not initiate any ship.
        Random rand = new Random();

        for(int i = 0; i < 10; i++){
            ocean.shootAt(rand.nextInt(10), rand.nextInt(10));
        }

        assertEquals(0,ocean.getHitCount(),
                "Should have 0 count because we have not initialized the ships");
    }

    @Test
    public void testGetHitCount20(){
        ocean.placeAllShipsRandomly();

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                ocean.shootAt(i,j);
            }
        }

        assertEquals(20,ocean.getHitCount(),
                "Should have 20 hit counts from the function");
    }

    @Test
    public void testGetHitCountIncremental(){
        int hit = 0;
        Random rand = new Random();
        ocean.placeAllShipsRandomly();

        for(int i = 0; i < 100; i++){
                if(ocean.shootAt(rand.nextInt(10), rand.nextInt(10) )){
                    hit++;
                    assertEquals(hit,ocean.getHitCount(),
                            "increment 1 by 1 as we hit the ship");
                }

        }


    }

    ///////////////////////////////////////////////////////// getShipArray() ////////////////////////////////////////////////////////

    @Test
    public void testGetShipArrayAllEmpty(){
        //hit count should be zero because we did not initiate any ship.
        Ship[][] ship = ocean.getShipArray();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                assertInstanceOf(EmptySea.class, ship[i][j],
                        "The ship array should be all EmptySea");
            }
        }
    }

    @Test
    public void testGetShipArrayAllShip(){
        //hit count should be zero because we did not initiate any ship.
        ocean.placeAllShipsRandomly();
        Ship[][] ship = ocean.getShipArray();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                assertNotNull(ship[i][j],
                        "The getShipArray should not return NULL");
            }
        }
    }

    ///////////////////////////////////////////////////////////getShipsSunk ////////////////////////////////////////////////////

    @Test
    public void testGetShipsSunkEmpty(){
        //hit count should be zero because we did not initiate any ship.

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                ocean.shootAt(i,j);
                assertEquals(0, ocean.getShipsSunk(),
                        "The getShipsSunk should not return 0 because of empty sea.");
            }
        }
    }

    @Test
    public void testGetShipsSunkIncremental(){
        int shipSunk = 0;

        ocean.placeAllShipsRandomly();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(ocean.shootAt(i,j)){
                    if(ocean.ships[i][j].isSunk()){
                        shipSunk++;
                        assertEquals(shipSunk,ocean.getShipsSunk(),
                                "The getShipsSunk incremental test fail.");
                    }
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////getShotsFired////////////////////////////////////////////////////

    @Test
    public void testGetShotsFired(){

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                assertEquals(i*10+j, ocean.getShotsFired(),
                        "The getShipsSunk should be incremented");
                ocean.shootAt(i,j);
            }
        }
    }

    @Test
    public void testGetShotsFiredWithRandomShip(){
        ocean.placeAllShipsRandomly();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                assertEquals(i*10+j, ocean.getShotsFired(),
                        "The getShipsSunk should be incremented");
                ocean.shootAt(i,j);
            }
        }
    }

    @Test
    public void testGetShotsFiredWithRandomShot(){
        Random rand = new Random();
        ocean.placeAllShipsRandomly();
        for(int i = 0; i < 100; i++){
            assertEquals(i, ocean.getShotsFired(),
                    "The getShipsSunk should be incremented");
            ocean.shootAt(rand.nextInt(10), rand.nextInt(10));
            }
    }


    ///////////////////////////////////////////////////////////isGameOver////////////////////////////////////////////////////

    @Test
    public void testIsGameOverEmpty(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                ocean.shootAt(i,j);
                assertFalse(ocean.isGameOver(),
                        "Should never be game over because we have not initialized the ships");
            }
        }
    }

    @Test
    public void testIsGameOverTrue(){
        ocean.placeAllShipsRandomly();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                ocean.shootAt(i,j);
            }
        }
        assertTrue(ocean.isGameOver(),
                "Should be game over after we have shoot every ships");
    }

    @Test
    public void testIsGameOverShuttle(){
        ocean.placeAllShipsRandomly();
        List<int[]> pairs = randomPair();

        for (int[] pair : pairs) {
            ocean.shootAt(pair[0], pair[1]);
            if(ocean.getShipsSunk()==10){
                assertTrue(ocean.isGameOver(),
                        "Should be game over after we reached the number of ships");
            }else{
                assertFalse(ocean.isGameOver(),
                        "Should not be game over after we did not reach the number of ships");
            }
        }

    }


    ///////////////////////////////////////////////////////////isOccupied////////////////////////////////////////////////////

    @Test
    public void testIsOccupiedEmpty(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                ocean.shootAt(i,j);
                assertFalse(ocean.isOccupied(i,j),
                        "Should never be occupied because we have not initialized the ships");
            }
        }
    }

    @Test
    public void testIsOccupiedNotEmpty(){
        int count = 0;
        ocean.placeAllShipsRandomly();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                ocean.shootAt(i,j);
                if(ocean.isOccupied(i,j)){
                    count++;
                }
            }
        }
        assertEquals(20,count,
                "Should be occupied 20 spots");
    }

    @Test
    public void testIsOccupiedRanodomly(){
        int count = 0;
        List<int[]> pairs = randomPair();
        ocean.placeAllShipsRandomly();

        for(int[] pair : pairs){
            ocean.shootAt(pair[0], pair[1]);
            if(ocean.isOccupied(pair[0], pair[1])){
                count++;
            }
        }
        assertEquals(20,count,
                "Should be occupied 20 spots");
    }





    ///////////////////////////////////////////////////////////Helper(Not Test)///////////////////////////////////////////////////
    private List<int[]> randomPair(){
        List<int[]> pairs = new ArrayList<>();

        for (int a = 0; a < 10; a++) {
            for (int b = 0; b < 10; b++) {
                pairs.add(new int[]{a, b});
            }
        }
        Collections.shuffle(pairs);
        return pairs;
    }

}
