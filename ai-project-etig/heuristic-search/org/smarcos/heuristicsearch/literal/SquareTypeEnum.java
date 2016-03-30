package org.smarcos.heuristicsearch.literal;


public enum SquareTypeEnum {
	
	START(Constants.START_SQUARE, Constants.START_TU),
	ROAD(Constants.ROAD_SQUARE, Constants.ROAD_TU),
	EMPTY(Constants.EMPTY_SQUARE, Constants.EMPTY_SQUARE_TU),
	RIVER(Constants.RIVER_SQUARE, Constants.RIVER_TU),
	MOUNTAIN(Constants.MOUNTAIN_SQUARE, Constants.MOUNTAIN_TU),
	ARRIVAL(Constants.ARRIVAL_SQUARE, Constants.ARRIVAL_TU);

	private final String name;
	private final int timeUnit;
	
	/**
	 * @param name: square name
	 * @param timeUnit: time unit 
	 */
	private SquareTypeEnum(String name, int timeUnit) {
		this.name = name;
		this.timeUnit = timeUnit;
	}

	public String getName() {
		return name;
	}

	public int getTimeUnit() {
		return timeUnit;
	}
	
}
