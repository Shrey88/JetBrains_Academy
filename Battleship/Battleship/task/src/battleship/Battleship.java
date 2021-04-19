package battleship;

class Battleship {

    /**
     *  help in validating the ship cells with the start and end coordinates and iterate coordinate input in case of
     *  wrong length, wrong location or ship placed too close.
     */
    enum SHIPS { AIRCRAFT_CARRIER(5, "Aircraft Carrier"), BATTLESHIP(4, "Battleship"), SUBMARINE(3, "Submarine"), CRUISER(3, "Cruiser"), DESTROYER(2, "Destroyer");
        private final int value;
        private final String model;

        SHIPS(int value, String model) {
            this.value = value;
            this.model = model;
        }

        protected int getCells() {
            return this.value;
        }

        protected String getModel() {
            return this.model;
        }
    }

    /**
     *  taking coordinates for 5 ships Aircraft Carrier, Battleship, Submarine.
     *  Cruiser, Destroyer
     *  if the coordinates provided are of wrong length or wrong location or it's too close
     *  to another ship it will iterate again taking the coordinates for the same ship again
     */
    protected void placeBattleShip(BattleField battleField) {
        boolean allShipsPlaced = false;
        SHIPS currentShip = SHIPS.AIRCRAFT_CARRIER;
        boolean coordinateMapped = true;
        do {
            if (coordinateMapped) {
                battleField.displayBattleField();
            }

            switch(currentShip) {
                case AIRCRAFT_CARRIER:
                    coordinateMapped = battleField.mapCoordinates(currentShip);
                    if (coordinateMapped) {
                        currentShip = SHIPS.BATTLESHIP;
                    }
                    break;
                case BATTLESHIP:
                    coordinateMapped = battleField.mapCoordinates(currentShip);
                    if (coordinateMapped) {
                        currentShip = SHIPS.SUBMARINE;
                    }
                    break;
                case SUBMARINE:
                    coordinateMapped = battleField.mapCoordinates(currentShip);
                    if (coordinateMapped) {
                        currentShip = SHIPS.CRUISER;
                    }
                    break;
                case CRUISER:
                    coordinateMapped = battleField.mapCoordinates(currentShip);
                    if (coordinateMapped) {
                        currentShip = SHIPS.DESTROYER;
                    }
                    break;
                case DESTROYER:
                    coordinateMapped = battleField.mapCoordinates(currentShip);
                    if (coordinateMapped) {
                        battleField.displayBattleField();
                        allShipsPlaced = true;
                    }
                    break;

                default:
                    break;
            }
        } while (!allShipsPlaced);
    }
}
