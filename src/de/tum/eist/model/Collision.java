package de.tum.eist.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class Collision {

	private Car winningCar;
	private Car loosingCar;

	public Collision() {

	}

	public Collision(Car winningCar, Car loosingCar) {
		super();
		this.winningCar = winningCar;
		this.loosingCar = loosingCar;
	}

	public Car getWinningCar() {
		return winningCar;
	}

	public void setWinningCar(Car winningCar) {
		this.winningCar = winningCar;
	}

	public Car getLoosingCar() {
		return loosingCar;
	}

	public void setLoosingCar(Car loosingCar) {
		this.loosingCar = loosingCar;
	}

	@Override
	public String toString() {
		return "Collision [winningCar=" + winningCar + ", loosingCar=" + loosingCar + "]";
	}

}
