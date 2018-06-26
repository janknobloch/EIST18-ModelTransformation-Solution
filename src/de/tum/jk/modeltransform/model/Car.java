package de.tum.jk.modeltransform.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class Car {
	private String iconUrl;

	public Car() {

	}

	public Car(String iconUrl) {
		super();
		this.iconUrl = iconUrl;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	@Override
	public String toString() {
		return "Car [iconUrl=" + iconUrl + "]";
	}

}
