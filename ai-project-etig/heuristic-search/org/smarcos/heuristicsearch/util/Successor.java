package org.smarcos.heuristicsearch.util;

import java.util.ArrayList;

import org.smarcos.heuristicsearch.literal.Constants;
import org.smarcos.heuristicsearch.model.BoardSquare;

/**
 * @author Sandra Marcos Bonet
 * 
 */

public class Successor {

	public Successor() {
		super();
	}

	/**
	 * Fills a list with the possible successors (max four) of a determined
	 * item: if i<9: (i+1, j); if j<9: (i, j+1); if i>0: (i-1, j); if j>0: (i,
	 * j-1);
	 * 
	 * @param item
	 *            : current item.
	 * @param boardSquareList
	 *            : the list to fill.
	 * @param: map of the board.
	 */

	public void extractSuccessors(BoardSquare item,
			ArrayList<BoardSquare> boardSquareList, BoardSquare[][] boardMap) {

		if (boardMap == null) {
			System.out.println("ERROR: uninitialized board!\n");
		} else {
			try {
				// if i<9: (i+1, j)
				if (item.getPosf() < Constants.BOARD_SIZE - 1) {
					boardSquareList.add(boardMap[item.getPosf() + 1][item
							.getPosc()]);
				}
				// if j<9: (i, j+1)
				if (item.getPosc() < Constants.BOARD_SIZE - 1) {
					boardSquareList
							.add(boardMap[item.getPosf()][item.getPosc() + 1]);
				}
				// if i>0: (i-1, j)
				if (item.getPosf() > 0) {
					boardSquareList.add(boardMap[item.getPosf() - 1][item
							.getPosc()]);
				}
				// if j>0: (i, j-1)
				if (item.getPosc() > 0) {
					boardSquareList
							.add(boardMap[item.getPosf()][item.getPosc() - 1]);
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("ERROR: Incorrect access to the board map");
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

}
