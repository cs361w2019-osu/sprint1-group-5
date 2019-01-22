package cs361.battleships.models;

import java.util.ArrayList;
import java.util.List;

public class Board {

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	private List<Ship> ships;
	public Board() {
		ships = new ArrayList<>();
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
					ship.setOccupiedSquares(tile1);
					Square tile2 = new Square();
					tile2.setRow(x);
					y = (char) (y + 1);
					tile2.setColumn(y);
					ship.setOccupiedSquares(tile2);
					System.out.printf("Battleship placed at x: %d y: %c.", x, y);

					return true;
				} else {
					return false;
				}
			}
			else{
				if(0 <= x && x <= 8 && 0 <= y2 && 9 >= y ){
					// add position to list
					ship.setOccupiedSquares(tile1);
					Square tile2 = new Square();
					tile2.setRow(x+1);
					tile2.setColumn(y);
					ship.setOccupiedSquares(tile2);
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
					ship.setOccupiedSquares(tile1);
					Square tile2 = new Square();
					tile2.setRow(x);
					y = (char) (y + 1);
					tile2.setColumn(y);
					ship.setOccupiedSquares(tile2);
					Square tile3 = new Square();
					tile3.setRow(x);
					y = (char)(y2 + 2);
					tile3.setColumn(y);
					ship.setOccupiedSquares(tile3);

					System.out.printf("Battleship origin placed at x: %d y: %c.", x, y);
					return true;
				} else {
					return false;
				}
			}
			else{
				if(0 <= x && x <= 7 && 0 <= y2 && 9 >= y ){
					// add position to list
					ship.setOccupiedSquares(tile1);
					Square tile2 = new Square();
					tile2.setRow(x+1);
					tile2.setColumn(y);
					ship.setOccupiedSquares(tile2);
					Square tile3 = new Square();
					tile3.setRow(x+2);
					tile3.setColumn(y);
					ship.setOccupiedSquares(tile3);

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
					ship.setOccupiedSquares(tile1);
					Square tile2 = new Square();
					tile2.setRow(x);
					y = (char) (y + 1);
					tile2.setColumn(y);
					ship.setOccupiedSquares(tile2);
					Square tile3 = new Square();
					tile3.setRow(x);
					y = (char)(y2 + 2);
					tile3.setColumn(y);
					ship.setOccupiedSquares(tile3);
					Square tile4 = new Square();
					tile4.setRow(x);
					y = (char)(y2 + 2);
					tile4.setColumn(y);
					ship.setOccupiedSquares(tile4);
					System.out.printf("Battleship placed at x: %d y: %c.", x, y);
					return true;
				} else {
					return false;
				}
			}
			else{
				if(0 <= x && x <= 6 && 0 <= y2 && 9 >= y ){
					// add position to list
					ship.setOccupiedSquares(tile1);
					Square tile2 = new Square();
					tile2.setRow(x+1);
					tile2.setColumn(y);
					ship.setOccupiedSquares(tile2);
					Square tile3 = new Square();
					tile3.setRow(x+2);
					tile3.setColumn(y);
					ship.setOccupiedSquares(tile3);
					Square tile4 = new Square();
					tile4.setRow(x+3);
					tile4.setColumn(y);
					ship.setOccupiedSquares(tile4);
					System.out.printf("Battleship placed at x: %d y: %c.", x,y);

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
		return null;
	}

	public List<Ship> getShips() {
		return this.ships;
	}

	public void setShips(Ship ship) {
		this.ships.add(ship);
	}

	public List<Result> getAttacks() {
		//TODO implement
		return null;
	}

	public void setAttacks(List<Result> attacks) {
		//TODO implement
	}
}
