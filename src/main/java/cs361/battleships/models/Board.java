package cs361.battleships.models;

import java.util.List;

public class Board {


	private Board enemy;
	private List<Result> attackList;

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Board() {
		// TODO Implement
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
		// TODO Implement
		return false;
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
				res.setLocation(null);
				res.setShip(null);
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

		return res;
	}

	public List<Ship> getShips() {
		//TODO implement
		return null;
	}

	public void setShips(List<Ship> ships) {
		//TODO implement
	}

	public List<Result> getAttacks() {
		return attackList;
	}

	public void setAttacks(List<Result> attacks) {
		attackList = attacks;
	}
}
