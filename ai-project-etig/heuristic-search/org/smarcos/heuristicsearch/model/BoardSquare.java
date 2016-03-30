package org.smarcos.heuristicsearch.model;

import java.io.Serializable;

import org.smarcos.heuristicsearch.literal.SquareTypeEnum;

public class BoardSquare implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SquareTypeEnum square;
	private int posf;
	private int posc;

	/**
	 * @param square
	 *            : square type
	 * @param posf
	 *            : x coordinate
	 * @param posc
	 *            : y coordinate
	 */
	public BoardSquare(SquareTypeEnum square, int posf, int posc) {
		this.square = square;
		this.posf = posf;
		this.posc = posc;
	}

	public SquareTypeEnum getSquare() {
		return square;
	}

	public int getPosf() {
		return this.posf;
	}

	public int getPosc() {
		return this.posc;
	}

	public void setPosf(int posf) {
		this.posf = posf;
	}

	public void setPosc(int posc) {
		this.posc = posc;
	}

	public String getName() {
		return this.square.getName();
	}

	public int getTime() {
		return this.square.getTimeUnit();
	}

	@Override
	public String toString() {
		return this.square.getName() + " " + this.getPosf() + " "
				+ this.getPosc() + " " + this.square.getTimeUnit();
	}
	
	public Object clone() {
		BoardSquare o = null;
		
		try {
			o = (BoardSquare) super.clone();
 		} catch(CloneNotSupportedException e) {
			e.printStackTrace(System.err);
		}
		
		return o;
	}
	
}
