package com.locationbasedfoodieservice.rawrestaurant.scheduler;

public enum DataType {
	KIMBOB("Genrestrtlunch"),
	CAFE("Genrestrtcate"),
	CHINESE("Genrestrtchifood"),
	JAPANESE("Genrestrtjpnfood"),
	SOUP("Genrestrtsoup"),
	FAST_FOOD("Genrestrtfastfood");

	private String url;

	DataType(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

}
