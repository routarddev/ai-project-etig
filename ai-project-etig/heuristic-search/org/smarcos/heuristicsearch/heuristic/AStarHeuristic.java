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
 * Funció heurística que pren la casella amb menor temps.
 */
public class AStarHeuristic {

	/*
	 * f(n)=g(n)+h(n), on g(n) és el cost per arribar al node h(n) és el cost
	 * d'anar al node objectiu
	 */

	/**
	 * HEURÍSTICA A estrella 1 Heurística que mira el temps
	 * 
	 * @param Casella
	 *            c
	 * @return: temps.
	 */
	public int getHeuristicaA1(BoardSquare current, BoardSquare arrival) {
		int hValue = 0;
		Heuristic heuristic = new Heuristic();

		hValue = 1 * heuristic.heuristicByDistance(current, arrival);
		/*
		 * el temps mínim serà la distància fins al destí si, en el millor dels
		 * casos, només passem per carretera.
		 */

		return (current.getTime() + hValue);
	}

	/**
	 * HEURÍSTICA A ESTRELLA 2 Heurística que mira el temps
	 * 
	 * @param Casella
	 *            c
	 * @return: temps.
	 */
	public int getHeuristicaA2(BoardSquare current, BoardSquare arrival) {
		int hValue = 0;
		Heuristic heuristic = new Heuristic();

		hValue = heuristic.heuristicByDistance(current, arrival);

		// descomptem la casella actual
		return (2 * hValue - 1);
	}

	/**
	 * HEURÍSTICA 3 Heurística qur tria el camí amb menor temps i més curt, és a
	 * dir, no es pot tornar enrera.
	 */
	public int getHeuristicaA3(BoardSquare[][] boardMap, BoardSquare current,
			BoardSquare arrival) {

		int result = 1000, middleVal = 0;

		ArrayList<BoardSquare> nextList = new ArrayList<BoardSquare>();
		Successor successor = new Successor();

		successor.extractSuccessors(current, nextList, boardMap);

		Heuristic h = new Heuristic();
		Iterator<BoardSquare> it;
		it = nextList.iterator();
		if (!it.hasNext()) {
			System.out.println("No hi ha successors" + "\n");
		} else {
			middleVal = 0;
			// Anem mirant els successors
			while (it.hasNext()) {
				BoardSquare seg = (BoardSquare) it.next();

				middleVal = h
						.compareTimeUnits(seg.getTime(), current.getTime());
				middleVal += h.heuristicByDistance(seg, arrival);

				if (middleVal < result)
					result = middleVal;
			}
		}

		return (result + 1 * h.heuristicByDistance(current, arrival));
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
	/*
	 * public int getHeuristica3(Casella c, Casella d) {
	 * 
	 * return this.getHeuristica1(c) + this.getHeuristica2(c, d); }
	 */

	/**
	 * Compara dos temps i en retorna el menor.
	 * 
	 * @param ut1
	 *            : unitat de temps.
	 * @param ut2
	 *            : unitat de temps.
	 * @return: mín(ut1, ut2) --> menor temps.
	 */
	public int compateTimeUnits(int ut1, int ut2) {
		if (ut1 <= ut2)
			return ut1;
		else
			return ut2;
	}

}