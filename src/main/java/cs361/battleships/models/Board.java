package cs361.battleships.models;

import java.util.List;

public class Board {


	private Board enemy;
	private List<Result> attackList;

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	private List<Ship> ships;
	private List<Square> placedTiles;
	public Board() {
		ships = new ArrayList<>();
		placedTiles = new ArrayList<>();
	}


	//setter for enemy
	public void setEnemy(Board enemy) {
		this.enemy = enemy;
	}


	public void setReservedTiles(Square tile){
		this.placedTiles.add(tile);
	}

	public List<Square> getReservedTiles() {
		return this.placedTiles;
	}
	public boolean canPlaceShip ( Square tile){

		for (int i = 0; i < getReservedTiles().size(); i++){
			if (getReservedTiles().get(i).equals(tile)){
				return false;
			}
		}
		return true;
	}
  
	/*
        DO NOT change the signature of this method. It is used by the grading scripts.
         */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
		int y2 = y - 65;
		//new square
		Square tile1 = new Square();
		//set new square
		tile1.setRow(x);
		tile1.setColumn(y);

		if(ship.name.equals("Minesweeper")){
			if (isVertical) {
				if (0 <= x && x <= 9 && 0 <= y2 && 8 >= y) {
					if (this.canPlaceShip(tile1)){
						ship.setOccupiedSquares(tile1);
						this.setReservedTiles(tile1);
					}

					Square tile2 = new Square();
					tile2.setRow(x);
					y = (char) (y + 1);
					tile2.setColumn(y);
					if(this.canPlaceShip(tile2)){
						ship.setOccupiedSquares(tile2);
						this.setReservedTiles(tile2);
					}

					System.out.printf("Battleship placed at x: %d y: %c.", x, y);
					return true;
				} else {
					System.out.print("Battleship cannot be placed here.");
					return false;
				}
			}
			else{
				if(0 <= x && x <= 8 && 0 <= y2 && 9 >= y ){
					// add position to list
					if(this.canPlaceShip(tile1)){
						ship.setOccupiedSquares(tile1);
						this.setReservedTiles(tile1);
					}

					Square tile2 = new Square();
					tile2.setRow(x+1);
					tile2.setColumn(y);
					if(this.canPlaceShip(tile2)){
						ship.setOccupiedSquares(tile2);
						this.setReservedTiles(tile2);
					}

					System.out.printf("Battleship origin placed at x: %d y: %c.", x,y);
					return true;
				}
				else{
					System.out.print("Battleship cannot be placed here.");
					return false;
				}
			}
		}
		else if(ship.name.equals("Destroyer")){
			if (isVertical) {
				if (0 <= x && x <= 9 && 0 <= y2 && 7 >= y) {
					// add position to list
					if (this.canPlaceShip(tile1)){
						ship.setOccupiedSquares(tile1);
						this.setReservedTiles(tile1);
					}

					Square tile2 = new Square();
					tile2.setRow(x);
					y = (char) (y + 1);
					tile2.setColumn(y);
					if(this.canPlaceShip(tile2)){
						ship.setOccupiedSquares(tile2);
						this.setReservedTiles(tile2);
					}

					Square tile3 = new Square();
					tile3.setRow(x);
					y = (char)(y2 + 2);
					tile3.setColumn(y);
					if(this.canPlaceShip(tile3)){
						ship.setOccupiedSquares(tile3);
						this.setReservedTiles(tile3);
					}

					System.out.printf("Battleship origin placed at x: %d y: %c.", x, y);
					return true;
				} else {
					System.out.print("Battleship cannot be placed here.");
					return false;
				}
			}
			else{
				if(0 <= x && x <= 7 && 0 <= y2 && 9 >= y ){
					// add position to list
					if(this.canPlaceShip(tile1)){
						ship.setOccupiedSquares(tile1);
						this.setReservedTiles(tile1);
					}

					Square tile2 = new Square();
					tile2.setRow(x+1);
					tile2.setColumn(y);
					if(this.canPlaceShip(tile2)){
						ship.setOccupiedSquares(tile2);
						this.setReservedTiles(tile2);
					}

					Square tile3 = new Square();
					tile3.setRow(x+2);
					tile3.setColumn(y);
					if(this.canPlaceShip(tile3)) {
						ship.setOccupiedSquares(tile3);
						this.setReservedTiles(tile3);
					}

					System.out.printf("Battleship placed at x: %d y: %c.", x,y);
					return true;
				}
				else{
					System.out.print("Battleship cannot be placed here.");
					return false;
				}
			}
		}
		else if(ship.name.equals("Battleship")){
			if (isVertical) {
				if (0 <= x && x <= 9 && 0 <= y2 && 6 >= y) {
					// add position to list
					if (this.canPlaceShip(tile1)){
						ship.setOccupiedSquares(tile1);
						this.setReservedTiles(tile1);
					}

					Square tile2 = new Square();
					tile2.setRow(x);
					y = (char) (y + 1);
					tile2.setColumn(y);
					if(this.canPlaceShip(tile2)){
						ship.setOccupiedSquares(tile2);
						this.setReservedTiles(tile2);
					}

					Square tile3 = new Square();
					tile3.setRow(x);
					y = (char)(y2 + 2);
					tile3.setColumn(y);
					if(this.canPlaceShip(tile3)){
						ship.setOccupiedSquares(tile3);
						this.setReservedTiles(tile3);
					}

					Square tile4 = new Square();
					tile4.setRow(x);
					y = (char)(y2 + 2);
					tile4.setColumn(y);
					if(this.canPlaceShip(tile4)){
						ship.setOccupiedSquares(tile4);
						this.setReservedTiles(tile4);
					}
					System.out.printf("Battleship placed at x: %d y: %c.", x, y);
					return true;
				} else {
					System.out.print("Battleship cannot be placed here.");
					return false;
				}
			}
			else{
				if(0 <= x && x <= 6 && 0 <= y2 && 9 >= y ){
					// add position to list
					if(this.canPlaceShip(tile1)){
						ship.setOccupiedSquares(tile1);
						this.setReservedTiles(tile1);
					}

					Square tile2 = new Square();
					tile2.setRow(x+1);
					tile2.setColumn(y);
					if(this.canPlaceShip(tile2)){
						ship.setOccupiedSquares(tile2);
						this.setReservedTiles(tile2);
					}

					Square tile3 = new Square();
					tile3.setRow(x+2);
					tile3.setColumn(y);
					if(this.canPlaceShip(tile3)) {
						ship.setOccupiedSquares(tile3);
						this.setReservedTiles(tile3);
					}

					Square tile4 = new Square();
					tile4.setRow(x+3);
					tile4.setColumn(y);
					if(this.canPlaceShip(tile4)){
						ship.setOccupiedSquares(tile4);
						this.setReservedTiles(tile4);
					}

					System.out.printf("Battleship origin placed at x: %d y: %c.", x,y);
					return true;
				}
				else{
					System.out.print("Battleship cannot be placed here.");
					return false;
				}
			}
		}
		else{
			System.out.print("No ship selected.");
			return false;
		}
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Result attack(int x, char y) {
		//TODO Implement
		Result res = new Result();
		res.setResult(AtackStatus.MISS);
		//Check if this attack has been tried before, INVALID if so
		for(int i = 0; i < attackList.size(); i++)
		{
			if (attackList.get(i).getLocation().getRow() == x && attackList.get(i).getLocation().getColumn() == y) {
				res.setResult(AtackStatus.INVALID);
				Square loc = new Square(x, y);
				res.setLocation(loc);
				res.setShip(null);
				attackList.add(res);
				return res;
			}
		}

		//check enemy ships for a hit, set status to HIT temporarily if matching square is found
		for(int i = 0; i < enemy.getShips().size(); i++) {
			for(int j = 0; j < enemy.getShips().get(i).getOccupiedSquares().size(); j++){
				if(enemy.getShips().get(i).getOccupiedSquares().get(j).getRow() == x && enemy.getShips().get(i).getOccupiedSquares().get(j).getColumn() == y)
					res.setResult(AtackStatus.HIT);
					res.setShip(enemy.getShips().get(i));
					res.setLocation(enemy.getShips().get(i).getOccupiedSquares().get(j));
			}
		}

		int shipHitCount = 0;

		//check for sink case by looking at previous hits
		if(res.getResult().equals(AtackStatus.HIT))
		{
			shipHitCount = 1;
			for(int i = 0; i < attackList.size(); i++)
			{
				if(attackList.get(i).getShip() != null){
					//check for equality of this result's ship to previous attack's ship result
					if(attackList.get(i).getShip().equals(res.getShip()) && attackList.get(i).getResult().equals(AtackStatus.HIT)){
						shipHitCount++;
					}
				}

			}
		}
		else if(res.getResult().equals(AtackStatus.MISS))
		{
			Square loc = new Square(x,y);
			res.setLocation(loc);
			res.setShip(null);
			return res;
		}
		if(shipHitCount == res.getShip().getOccupiedSquares().size())
			res.setResult(AtackStatus.SUNK);


		//check for surrender case by looking at previous sinks
		int sinkCount = 0;
		if(res.getResult().equals(AtackStatus.SUNK)) {
			sinkCount = 1;
			for(int i = 0; i < attackList.size(); i++) {
				if(attackList.get(i).getResult().equals(AtackStatus.SUNK)) {
					sinkCount++;
				}
			}
		}
		if(sinkCount == enemy.getShips().size())
			res.setResult(AtackStatus.SURRENDER);

		attackList.add(res);
		return res;
	}

	public List<Ship> getShips() {
		return this.ships;
	}

	public void setShips(Ship ship) {
		this.ships.add(ship);
	}

	public List<Result> getAttacks() {
		return attackList;
	}

	public void setAttacks(List<Result> attacks) {
		attackList = attacks;
	}
}
