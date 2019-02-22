package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

	@JsonProperty private List<Ship> ships;
	@JsonProperty private List<Result> attacks;

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Board() {
		ships = new ArrayList<>();
		attacks = new ArrayList<>();
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
		if (ships.size() >= 3) {
			return false;
		}
		if (ships.stream().anyMatch(s -> s.getKind().equals(ship.getKind()))) {
			return false;
		}
		final var placedShip = new Ship(ship.getKind());
		placedShip.place(y, x, isVertical);
		if (ships.stream().anyMatch(s -> s.overlaps(placedShip))) {
			return false;
		}
		if (placedShip.getOccupiedSquares().stream().anyMatch(s -> s.isOutOfBounds())) {
			return false;
		}
		ships.add(placedShip);
		return true;
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Result attack(int x, char y) {
		var attackLocation = new Square(x,y);
		//System.out.println("SHIPS: " + this.ships.get(0).getOccupiedSquares().get(0));
		Result attackResult = attack(new Square(x, y));
		int cap0 = ships.get(0).getCap();
		int cap1 = ships.get(1).getCap();
		int cap2 = ships.get(2).getCap();
		if(this.ships.get(0).getOccupiedSquares().get(cap0).equals(attackLocation) && !this.ships.get(0).getOccupiedSquares().get(cap0).isHit()){
			System.out.println("NO ATTACK 1: " + attackResult);
			return attackResult;
		}
		else if(this.ships.get(1).getOccupiedSquares().get(cap1).equals(attackLocation) && !this.ships.get(1).getOccupiedSquares().get(cap1).isHit()){
			System.out.println("NO ATTACK 2: " + attackResult);
			return attackResult;
		}
		else if(this.ships.get(2).getOccupiedSquares().get(cap2).equals(attackLocation) && !this.ships.get(2).getOccupiedSquares().get(cap2).isHit()){
			System.out.println("NO ATTACK 3: " + attackResult);
			return attackResult;
		}
		else {
			System.out.println("ATTACK RECORDED: " + attackResult);
			attacks.add(attackResult);
			return attackResult;
		}
	}

	private Result attack(Square s) {
		if (attacks.stream().anyMatch(r -> r.getLocation().equals(s))) {
			var attackResult = new Result(s);
			attackResult.setResult(AtackStatus.INVALID);
			return attackResult;
		}
		var shipsAtLocation = ships.stream().filter(ship -> ship.isAtLocation(s)).collect(Collectors.toList());
		if (shipsAtLocation.size() == 0) {
			var attackResult = new Result(s);
			return attackResult;
		}
		var hitShip = shipsAtLocation.get(0);
		var attackResult = hitShip.attack(s.getRow(), s.getColumn());
		if (attackResult.getResult() == AtackStatus.SUNK) {
			if (ships.stream().allMatch(ship -> ship.isSunk())) {
				attackResult.setResult(AtackStatus.SURRENDER);
			}
		}
		return attackResult;
	}

	List<Ship> getShips() {
		return ships;
	}
}
