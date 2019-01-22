package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Ship {
	 String name;

	@JsonProperty private List<Square> occupiedSquares;

	public Ship() {
		occupiedSquares = new ArrayList<>();
	}
	
	public Ship(String kind) {
		this.name = kind;
	}
	public String getShipName(Ship ship){
		return ship.name;
	}
	public void setShipName(String name, Ship ship){
		ship.name = name;
	}

	public void setOccupiedSquares(Square tile){
		this.occupiedSquares.add(tile);
	}

	public List<Square> getOccupiedSquares() {
		return this.occupiedSquares;
	}
}
