package battleship;

import java.util.Scanner;

public class BattleField {
    protected final Scanner scanner = new Scanner(System.in);

    protected final StringBuilder existingLocation = new StringBuilder();

    protected String[][] battleField;

    private boolean isIterated = false;

    private int AircraftCarrierHit;

    private int BattleshipHit;

    private int SubmarineHit;

    private int CruiserHit;

    private int DestroyerHit;

    public int getAircraftCarrierHit() {
        return AircraftCarrierHit;
    }

    public int getBattleshipHit() {
        return BattleshipHit;
    }

    public int getSubmarineHit() {
        return SubmarineHit;
    }

    public int getCruiserHit() {
        return CruiserHit;
    }

    public int getDestroyerHit() {
        return DestroyerHit;
    }

    /**
     * to identify the vertical coordinate of the battlefield
     */
    enum VERTICAL_POS { A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8), I(9), J(10);
        protected final int value;

        VERTICAL_POS(int value) {
            this.value = value;
        }

        protected static VERTICAL_POS constantExists(String name) {

            for (VERTICAL_POS vPos : VERTICAL_POS.values()) {
                if (vPos.name().equals(name)) {
                    return vPos;
                }
            }
            return null;
        }

    }

    /**
     *  return the location information.
     */
    enum LOCATION { WRONG_LENGTH, WRONG_LOCATION, TOO_CLOSE, RIGHT_LOCATION }

    /**
     * return the response of the shot.
     */
    enum SHOT { HIT, MISS, SANK, DESTROYED}

    /**
     * initiate the battlefield
     */
    public BattleField() {
        this.AircraftCarrierHit = Battleship.SHIPS.AIRCRAFT_CARRIER.getCells();
        this.BattleshipHit = Battleship.SHIPS.BATTLESHIP.getCells();
        this.SubmarineHit = Battleship.SHIPS.SUBMARINE.getCells();
        this.CruiserHit = Battleship.SHIPS.CRUISER.getCells();
        this.DestroyerHit = Battleship.SHIPS.DESTROYER.getCells();
        createBattleField();
    }


    /**
     *  create an array of 11 x 11 where first row will contain the horizontal
     *  position and first column of every row will have the vertical position.
     */
    private void createBattleField() {
        battleField = new String[11][11];
        int character = 65;
        for (int i = 0; i < battleField.length; i++) {
            for (int j = 0; j < battleField[0].length; j++) {
                if (i == 0) {
                    if (j != 0) {
                        battleField[i][j] = String.valueOf(j);
                    }
                } else {
                    if (j == 0) {
                        battleField[i][j] = String.valueOf((char) character);
                        ++character;
                    } else {
                        battleField[i][j] = "~";
                    }

                }
            }
        }
    }


    /**
     * Take the input coordinates of the ship.
     * Validate if the given coordinates length is correct or wrong and print the error message
     * validate if the given coordintate location is correct or wrong and print the error message.
     * if one ship touches the other ship print the error message too close.
     */
    protected boolean mapCoordinates(Battleship.SHIPS currentShip) {


        if (!isIterated) {
            System.out.println("Enter the coordinates of the " + currentShip.getModel() + " (" + currentShip.getCells() +
                    " cells):\n");
        }

        try {
            String inputStr = scanner.nextLine();
            String[] coordinates = inputStr.split("\\s");

            System.out.println();

            LOCATION currentLocation = check_and_map(coordinates[0], coordinates[1], currentShip);

            if (currentLocation.equals(LOCATION.WRONG_LOCATION)){
                System.out.println("Error! Wrong ship location! Try again:\n");
                isIterated = true;
                return false;
            } else if (currentLocation.equals(LOCATION.WRONG_LENGTH)) {
                System.out.println("Error! Wrong length of the Submarine! Try again:\n");
                isIterated = true;
                return false;
            } else if (currentLocation.equals(LOCATION.TOO_CLOSE)) {
                System.out.println("Error! You placed it too close to another one. Try again.\n");
                isIterated = true;
                return false;
            } else if (currentLocation.equals(LOCATION.RIGHT_LOCATION)){
                isIterated = false;
                return true;
            }
        } catch(Exception e) {
            System.out.println("An exception occurred : " + e.getCause());
        }

        return false;
    }


    /**
     * check if the coordinates are correct and whether they can be mapped on the battlefield.
     * @param startPos coordinate start position
     * @param endPos coordinate end position
     * @param currentShip currently which ship needs to be placed.
     * @return return the Location (WRONG_LOCATION, WRONG_LENGTH, TOO_CLOSE, RIGHT_LOCATION)
     */
    private LOCATION check_and_map(String startPos, String endPos, Battleship.SHIPS currentShip) {

        String ship = "";
        if (currentShip.name().equals(Battleship.SHIPS.AIRCRAFT_CARRIER.name())) {
            ship = "AC";
        } else if (currentShip.name().equals(Battleship.SHIPS.BATTLESHIP.name())) {
            ship = "BS";
        } else if (currentShip.name().equals(Battleship.SHIPS.SUBMARINE.name())) {
            ship = "SM";
        } else if (currentShip.name().equals(Battleship.SHIPS.CRUISER.name())) {
            ship = "CR";
        } else if (currentShip.name().equals(Battleship.SHIPS.DESTROYER.name())) {
            ship = "DS";
        }

        int xSPos = Integer.parseInt(String.valueOf(startPos.charAt(1)));
        /*
         *  if the coordinate length is equal to 3 take the last two character
         */
        if (startPos.length() == 3) {
            String temp = String.valueOf(startPos.charAt(1)) + startPos.charAt(2);
            xSPos = Integer.parseInt(temp);
        }
        int ySPos = (VERTICAL_POS.valueOf(String.valueOf(startPos.charAt(0)))).value;


        int xEPos = Integer.parseInt(String.valueOf(endPos.charAt(1)));
        /*
         *  if the coordinate length is equal to 3 take the last two character
         */
        if (endPos.length() == 3) {
            String temp = String.valueOf(endPos.charAt(1)) + (endPos.charAt(2));
            xEPos = Integer.parseInt(temp);
        }

        int yEPos = (VERTICAL_POS.valueOf(String.valueOf(endPos.charAt(0)))).value;


        /*
         * checking if the given coordinates are in same row or same column
         *
         * here:
         *      xSPos/xEPos - denotes the column no
         *          if this is same and ySPos and yEPos are different means its vertical placement
         *      ySPos/yEPos - denotes the row
         *          if this is same and xSPos and xEPos are different means its horizontal placement
         *
         *      if both xsPos/xEPos and ySPos/yEPos are different meaning they are diagonal, which is not possible.
         */
        if (xSPos != xEPos && ySPos == yEPos) {
            if (xSPos > xEPos) {
                int temp = xEPos;
                xEPos = xSPos;
                xSPos = temp;
            }

            if (currentShip.getCells() == Math.abs(xEPos - xSPos) + 1) {
                if (this.existingLocation.length() > 0) {
                    /*
                     *  check if there is any ship before the starting position or
                     *  after the ending position that is going to touch your ship.
                     *
                     *  if the ending position is the boundary of the battlefield, then just check
                     *  if the starting position of the ship is not touching the other ship.
                     */
                    if (xEPos != 10 && ("AC".equals(this.battleField[ySPos][xSPos - 1]) ||
                            "BS".equals(this.battleField[ySPos][xEPos - 1]) ||
                            "SM".equals(this.battleField[ySPos][xEPos - 1]) ||
                            "CR".equals(this.battleField[ySPos][xEPos - 1]) ||
                            "DS".equals(this.battleField[ySPos][xEPos - 1]) ||
                            "AC".equals(this.battleField[ySPos][xSPos + 1]) ||
                            "BS".equals(this.battleField[ySPos][xEPos + 1]) ||
                            "SM".equals(this.battleField[ySPos][xEPos + 1]) ||
                            "CR".equals(this.battleField[ySPos][xEPos + 1]) ||
                            "DS".equals(this.battleField[ySPos][xEPos + 1]))) {
                        return LOCATION.TOO_CLOSE;
                    } else if (xEPos == 10 && ("AC".equals(this.battleField[ySPos][xSPos - 1]) ||
                            "BS".equals(this.battleField[ySPos][xSPos - 1]) ||
                            "SM".equals(this.battleField[ySPos][xSPos - 1]) ||
                            "CR".equals(this.battleField[ySPos][xSPos - 1]) ||
                            "DS".equals(this.battleField[ySPos][xSPos - 1]))) {
                        return LOCATION.TOO_CLOSE;
                    }

                    /*
                     * if the coordinates provided for the ship placement are on the boundary
                     * of the battlefield then check only for the previous position .
                     */
                    for (int i = xSPos; i <= xEPos; i++) {
                        if (ySPos != 10 && ("AC".equals(this.battleField[ySPos - 1][i]) ||
                                "BS".equals(this.battleField[ySPos - 1][i]) ||
                                "SM".equals(this.battleField[ySPos - 1][i]) ||
                                "CR".equals(this.battleField[ySPos - 1][i]) ||
                                "DS".equals(this.battleField[ySPos - 1][i]) ||
                                "AC".equals(this.battleField[ySPos + 1][i]) ||
                                "BS".equals(this.battleField[ySPos + 1][i]) ||
                                "SM".equals(this.battleField[ySPos + 1][i]) ||
                                "CR".equals(this.battleField[ySPos + 1][i]) ||
                                "DS".equals(this.battleField[ySPos + 1][i]))) {
                            String verticalShip1 = "V" + (ySPos - 1) + i;
                            String verticalShip2 = "V" + (ySPos + 1) + i;

                            /*
                             *  check if there is any ship in vertical position touching
                             *  this ship.
                             */
                            if (this.existingLocation.toString().contains(verticalShip1) ||
                                    this.existingLocation.toString().contains(verticalShip2)) {

                                /*
                                 *  revert the mapping for the current ship if any vertical ship is
                                 *  going to touch current ship placement.
                                 */
                                for (;i >= xSPos; i--){
                                    this.battleField[ySPos][i] = "~";

                                    /*
                                     * remove the coordinates of the current ship from the history of the
                                     * coordinates of ship being saved with prefix H for horizontal placement
                                     * and V for Vertical placement of the ship.
                                     */
                                    this.existingLocation.delete(this.existingLocation.length() - 4,
                                            this.existingLocation.length());
                                }
                                return LOCATION.TOO_CLOSE;
                            } else {
                                this.battleField[ySPos][i] = ship;
                                this.existingLocation.append("H").append(ySPos).append(i).append(" ");
                            }
                        } else if (ySPos == 10 && ("AC".equals(this.battleField[ySPos - 1][i]) ||
                                "BS".equals(this.battleField[ySPos - 1][i]) ||
                                "SM".equals(this.battleField[ySPos - 1][i]) ||
                                "CR".equals(this.battleField[ySPos - 1][i]) ||
                                "DS".equals(this.battleField[ySPos - 1][i]))) {
                            String verticalShip1 = "V" + (ySPos - 1) + i;

                            if (this.existingLocation.toString().contains(verticalShip1)) {

                                /*
                                 *  revert the mapping for the current ship if any vertical ship is
                                 *  going to touch current ship placement.
                                 */
                                for (;i >= xSPos; i--){
                                    this.battleField[ySPos][i] = "~";

                                    /*
                                     * remove the coordinates of the current ship from the history of the
                                     * coordinates of ship being saved with prefix H for horizontal placement
                                     * and V for Vertical placement of the ship.
                                     */
                                    this.existingLocation.delete(this.existingLocation.length() - 4,
                                            this.existingLocation.length());
                                }
                                return LOCATION.TOO_CLOSE;
                            } else {
                                this.battleField[ySPos][i] = ship;
                                this.existingLocation.append("H").append(ySPos).append(i).append(" ");
                            }

                        } else {
                            this.battleField[ySPos][i] = ship;
                            this.existingLocation.append("H").append(ySPos).append(i).append(" ");
                        }
                    }
                } else {
                    for (int i = xSPos; i <= xEPos; i++) {
                        this.battleField[ySPos][i] = ship;
                        this.existingLocation.append("H").append(ySPos).append(i).append(" ");
                    }
                }
            } else {
                return LOCATION.WRONG_LENGTH;
            }

            return LOCATION.RIGHT_LOCATION;

        } else if (xSPos == xEPos && ySPos != yEPos) {
            if (ySPos > yEPos) {
                int temp = yEPos;
                yEPos = ySPos;
                ySPos = temp;
            }

            if (currentShip.getCells() == Math.abs(yEPos - ySPos) + 1) {
                if (this.existingLocation.length() > 0) {
                    /*
                     *  check if there is any ship before the starting position or
                     *  after the ending position that is going to touch your ship.
                     *
                     *  if the ending position is the boundary of the battlefield, then just check
                     *  if the starting position of the ship is not touching the other ship.
                     *
                     *  changing from [xSPos][ySPos - 1] to [ySPos - 1][xSPos]
                     */
                    if (yEPos != 10 && ("AC".equals(this.battleField[ySPos - 1][xSPos]) ||
                            "BS".equals(this.battleField[ySPos - 1][xSPos]) ||
                            "SM".equals(this.battleField[ySPos - 1][xSPos]) ||
                            "CR".equals(this.battleField[ySPos - 1][xSPos]) ||
                            "DS".equals(this.battleField[ySPos - 1][xSPos]) ||
                            "AC".equals(this.battleField[yEPos + 1][xSPos]) ||
                            "BS".equals(this.battleField[yEPos + 1][xSPos]) ||
                            "SM".equals(this.battleField[yEPos + 1][xSPos]) ||
                            "CR".equals(this.battleField[yEPos + 1][xSPos]) ||
                            "DS".equals(this.battleField[yEPos + 1][xSPos]))) {
                        return LOCATION.TOO_CLOSE;
                    } else if (yEPos == 10 && ("AC".equals(this.battleField[ySPos - 1][xSPos]) ||
                            "BS".equals(this.battleField[ySPos - 1][xSPos]) ||
                            "SM".equals(this.battleField[ySPos - 1][xSPos]) ||
                            "CR".equals(this.battleField[ySPos - 1][xSPos]) ||
                            "DS".equals(this.battleField[ySPos - 1][xSPos]))) {
                        return LOCATION.TOO_CLOSE;
                    }

                    /*
                     *  check if there is any ship in horizonal position touching
                     *  this ship.
                     */
                    for (int i = ySPos; i <= yEPos; i++) {
                        if (xSPos != 10 && ("AC".equals(this.battleField[i][xSPos - 1]) ||
                                "BS".equals(this.battleField[i][xSPos - 1]) ||
                                "SM".equals(this.battleField[i][xSPos - 1]) ||
                                "CR".equals(this.battleField[i][xSPos - 1]) ||
                                "DS".equals(this.battleField[i][xSPos - 1]) ||
                                "AC".equals(this.battleField[i][xSPos + 1]) ||
                                "BS".equals(this.battleField[i][xSPos + 1]) ||
                                "SM".equals(this.battleField[i][xSPos + 1]) ||
                                "CR".equals(this.battleField[i][xSPos + 1]) ||
                                "DS".equals(this.battleField[i][xSPos + 1]))) {
                            String horizontalShip1 = "H" + i + (xSPos - 1);
                            String horizontalShip2 = "H" + i + (xSPos + 1);

                            if (this.existingLocation.toString().contains(horizontalShip1) ||
                                    this.existingLocation.toString().contains(horizontalShip2)) {

                                /*
                                 *  revert the mapping for the current ship if any horizontal ship is
                                 *  going to touch current ship placement.
                                 */
                                for (;i >= ySPos; i--){
                                    this.battleField[i][xSPos] = "~";

                                    /*
                                     * remove the coordinates of the current ship from the history of the
                                     * coordinates of ship being saved with prefix H for horizontal placement
                                     * and V for Vertical placement of the ship.
                                     */
                                    this.existingLocation.delete(this.existingLocation.length() - 4,
                                            this.existingLocation.length());
                                }
                                return LOCATION.TOO_CLOSE;
                            } else {
                                this.battleField[i][xSPos] =ship;
                                this.existingLocation.append("V").append(i).append(xSPos).append(" ");
                            }
                        } else if (xSPos == 10 && ("AC".equals(this.battleField[i][xSPos - 1]) ||
                                "BS".equals(this.battleField[i][xSPos - 1]) ||
                                "SM".equals(this.battleField[i][xSPos - 1]) ||
                                "CR".equals(this.battleField[i][xSPos - 1]) ||
                                "DS".equals(this.battleField[i][xSPos - 1]))) {
                            String horizontalShip1 = "H" + i + (xSPos - 1);
                            if (this.existingLocation.toString().contains(horizontalShip1)) {

                                /*
                                 *  revert the mapping for the current ship if any horizontal ship is
                                 *  going to touch current ship placement.
                                 */
                                for (;i >= ySPos; i--){
                                    this.battleField[i][xSPos] = "~";

                                    /*
                                     * remove the coordinates of the current ship from the history of the
                                     * coordinates of ship being saved with prefix H for horizontal placement
                                     * and V for Vertical placement of the ship.
                                     */
                                    this.existingLocation.delete(this.existingLocation.length() - 4,
                                            this.existingLocation.length());
                                }
                                return LOCATION.TOO_CLOSE;
                            } else {
                                this.battleField[i][xSPos] = ship;
                                this.existingLocation.append("V").append(i).append(xSPos).append(" ");
                            }

                        }else {
                            this.battleField[i][xSPos] = ship;
                            this.existingLocation.append("V").append(i).append(xSPos).append(" ");
                        }
                    }
                } else {
                    for (int i = ySPos; i <= yEPos; i++) {
                        this.battleField[i][xSPos] = ship;
                        this.existingLocation.append("V").append(i).append(xSPos).append(" ");
                    }
                }

                return LOCATION.RIGHT_LOCATION;
            } else {
                return LOCATION.WRONG_LENGTH;
            }
        } else {
            return LOCATION.WRONG_LOCATION;
        }
    }


    /**
     * check if the shot fired hit the target or missed.
     * @param shot one coordinate of the mapped passed.
     * @return return whether its a HIT or MISS
     */
    protected SHOT fireShot(String shot) {
        int xPos = Integer.parseInt(String.valueOf(shot.charAt(1)));
        /*
         *  if the coordinate length is equal to 3 take the last two character
         */
        if (shot.length() == 3) {
            String temp = String.valueOf(shot.charAt(1)) + shot.charAt(2);
            xPos = Integer.parseInt(temp);
        }

        int yPos = (VERTICAL_POS.valueOf(String.valueOf(shot.charAt(0)))).value;

        if ("AC".equals(this.battleField[yPos][xPos])) {
            --AircraftCarrierHit;
            this.battleField[yPos][xPos] = "X";
            if (AircraftCarrierHit == 0) {
                return SHOT.SANK;
            }
            return SHOT.HIT;
        } else if ("BS".equals(this.battleField[yPos][xPos])) {
            --BattleshipHit;
            this.battleField[yPos][xPos] = "X";
            if (BattleshipHit == 0) {
                return SHOT.SANK;
            }
            return SHOT.HIT;
        } else if ("SM".equals(this.battleField[yPos][xPos])) {
            --SubmarineHit;
            this.battleField[yPos][xPos] = "X";
            if (SubmarineHit == 0) {
                return SHOT.SANK;
            }
            return SHOT.HIT;
        } else if ("CR".equals(this.battleField[yPos][xPos])) {
            --CruiserHit;
            this.battleField[yPos][xPos] = "X";
            if (CruiserHit == 0) {
                return SHOT.SANK;
            }
            return SHOT.HIT;
        } else if ("DS".equals(this.battleField[yPos][xPos])) {
            --DestroyerHit;
            this.battleField[yPos][xPos] = "X";
            if (DestroyerHit == 0) {
                return SHOT.SANK;
            }
            return SHOT.HIT;
        }else if ("X".equals(this.battleField[yPos][xPos])) {
            return SHOT.HIT;
        } else {
            this.battleField[yPos][xPos] = "M";
            return SHOT.MISS;
        }
    }


    /**
     *  display the battlefield
     */
    protected void displayBattleField(){
        for(int i = 0; i < battleField.length; i++) {
            if (i == 0) {
                System.out.print(" ");
            } else {
                System.out.print(battleField[i][0]);
            }

            for (int j = 1; j < battleField[0].length; j++) {
                if (i == 0) {
                    System.out.print(" " + battleField[i][j]);
                } else {
                    System.out.print(" " + (!"~".equals(battleField[i][j]) ? "O" : battleField[i][j]));
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
