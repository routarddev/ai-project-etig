package org.smarcos.heuristicsearch.heuristic;

import java.util.ArrayList;
import java.util.Iterator;

import org.smarcos.heuristicsearch.model.BoardSquare;
import org.smarcos.heuristicsearch.util.Successor;

/**
 * @author Sandra Marcos Bonet
 *
 */

/**
 * Diverses funcions heurístiques.
 */
public class Heuristic {
	
	public Heuristic() {
		
	}
	

	/**
	 * HEURÍSTICA 1 Heurística que mira el temps
	 * 
	 * @param Casella
	 *            c
	 * @return: temps.
	 */
	public int heuristicByTime(BoardSquare boardSquare) {
		return boardSquare.getSquare().getTimeUnit();
	}

	/**
	 * HEURÍSTICA 2 Retorna la distància entre la casella actual i la casella
	 * destí.
	 * 
	 * @param x
	 *            : coordenada y de la posició actual.
	 * @param y
	 *            : coordenada y de la posició destí.
	 * @return distància: distància entre la casella actual y el destí.
	 */
	public int heuristicByDistance(BoardSquare current, BoardSquare arrival) {
		int x = getDistance(current.getPosf(), arrival.getPosf());
		int y = getDistance(current.getPosc(), arrival.getPosc());

		return (x + y);
	}

	/**
	 * HEURÍSTICA 3 Heurística qur tria el camí amb menor temps i més curt, és a
	 * dir, no es pot tornar enrera.
	 */
	public int heuristicByShortestPathAndTime(BoardSquare[][] boardMap,
			BoardSquare current, BoardSquare arrival) {

		int result = 1000, middle = 0;

		ArrayList<BoardSquare> nextList = new ArrayList<BoardSquare>();
		Successor successor = new Successor();

		successor.extractSuccessors(current, nextList, boardMap);

		Iterator<BoardSquare> it;
		it = nextList.iterator();
		if (!it.hasNext()) {
			System.out.println("No hi ha successors" + "\n");
		} else {
			middle = 0;
			// Anem mirant els successors
			while (it.hasNext()) {
				BoardSquare next = (BoardSquare) it.next();

				middle = compareTimeUnits(next.getSquare().getTimeUnit(), current.getSquare().getTimeUnit());
				middle += heuristicByDistance(next, arrival);

				if (middle < result)
					result = middle;
			}
		}
		return result;
	}
	

	/*
	 * HEURÍSTICA 3 Heurística qur tria el camí amb menor temps i més curt, és a
	 * dir, no es pot tornar enrera.
	 * 
	 * @param Casella c
	 * 
	 * @param Casella destí
	 * 
	 * @return: enter.
	 */
	public int heuristicV3(BoardSquare current, BoardSquare arrival) 
	{ 
		return heuristicByTime(current) + heuristicByDistance(current, arrival); 
	}
	
	

	/**
	 * Compara dos temps i en retorna el menor.
	 * 
	 * @param ut1
	 *            : unitat de temps.
	 * @param ut2
	 *            : unitat de temps.
	 * @return: mín(ut1, ut2) --> menor temps.
	 */
	public int compareTimeUnits(int ut1, int ut2) {
		if (ut1 <= ut2)
			return ut1;
		else
			return ut2;
	}

	/**
	 * Retorna la distància entre dos punts.
	 * 
	 * @param xa
	 *            : coordenada x de la posició actual.
	 * @param xd
	 *            : coordenada x de la posició destí.
	 * @return distx: distància entre el punts xa i xd.
	 */
	public int getDistance(int current, int arrival) {
		return Math.abs(arrival - current);
	}

}