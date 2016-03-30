package org.smarcos.heuristicsearch.util;

import org.smarcos.heuristicsearch.model.BoardSquare;

/**
 * @author Sandra Marcos Bonet
 * 
 */

public class Pending implements Cloneable, Comparable<Object> {
	
	/**
	 * Pending class consisting on two attributes: state (BoardSquare type) and
	 * heuristicValue (int type)
	 */
	public BoardSquare state;
	public int heuristicValue = 0;

	public Pending(BoardSquare state, int heuristicValue) {
		this.state = state;
		this.heuristicValue = heuristicValue;
	}

	public BoardSquare getState() {
		return state;
	}

	public void setState(BoardSquare state) {
		this.state = state;
	}

	public int getHeuristicValue() {
		return heuristicValue;
	}

	public void setHeuristicValue(int heuristicValue) {
		this.heuristicValue = heuristicValue;
	}

	public boolean equals(Object otherP) {

		/* Comprovem si els objectes són idèntics */
		if (this == otherP)
			return true;

		/*
		 * Retornem fals si paràmetre explícit és nul.
		 */
		if (otherP == null)
			return false;

		/*
		 * Si les classes no coincideixen, aleshores no poden ser iguals.
		 */
		if (getClass() != otherP.getClass())
			return false;

		Pending obj = (Pending) otherP;

		/* comprovem si els camps tenen valors idèntics. */
		return (this.state.equals(obj.getState()) && (this.heuristicValue == obj.heuristicValue));
	}

	public Object clone() throws CloneNotSupportedException {
		Pending o = null;

		try {
			o = (Pending) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace(System.err);
		}

		// Must clone references:
		o.state = (BoardSquare) o.state.clone();

		return o;
	}

	/**
	 * Will be used to sort pending squares depending on the heuristic function
	 */
	public int compareTo(Object p) {

		Pending otherP = (Pending) p;

		if (otherP == null)
			return 1;
		if (this.equals(otherP)
				|| (this.heuristicValue == otherP.heuristicValue)) {
			return 0;
		}
		return (otherP.heuristicValue < this.heuristicValue) ? 1 : -1;
	}

}