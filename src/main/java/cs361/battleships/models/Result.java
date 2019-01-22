package cs361.battleships.models;

public class Result {

	private AtackStatus result;
	private Ship ship;


	public AtackStatus getResult() {
		return this.result;
	}

	public void setResult(AtackStatus result) {
		this.result = result;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public Square getLocation() {
		//TODO implement
		return null;
	}

	public void setLocation(Square square) {
		//TODO implement
	}
}
