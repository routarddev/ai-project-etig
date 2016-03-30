package org.smarcos.heuristicsearch.main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

import org.smarcos.heuristicsearch.heuristic.Heuristic;
import org.smarcos.heuristicsearch.literal.Constants;
import org.smarcos.heuristicsearch.literal.SquareTypeEnum;
import org.smarcos.heuristicsearch.model.BoardMap;
import org.smarcos.heuristicsearch.model.BoardSquare;
import org.smarcos.heuristicsearch.util.Pending;
import org.smarcos.heuristicsearch.util.Successor;


/**
 * @author Sandra Marcos Bonet
 *
 */

public class BestFirstMain {
	
	/**
	 * Algorisme BEST FIRST
	 * amb heurística "method getHeuristica1"
	 * @throws CloneNotSupportedException 
	 */ 
	
	public static void main(String[] args) throws CloneNotSupportedException {
		
		// Guardem els elements tractats en una taula de hash
		Hashtable <Integer, BoardSquare> tractats = new Hashtable<Integer, BoardSquare>();
		
		// Guardem els elements pendents en una llista d'estats ordenada per h
		ArrayList <Pending> pends = new ArrayList<Pending> ();
		
		// Per retornar el camí, tindrem una llista
		ArrayList <BoardSquare> cami = new ArrayList<BoardSquare>();
		
		// Tenim una matriu de 10x10 caselles:
		BoardSquare[][] mapa = new BoardSquare[10][10];
		
		// Variables del programa
		BoardSquare c, sucx;
		boolean trobat = false;
		Heuristic heur = new Heuristic();

		Iterator<BoardSquare> it1, it2;
		
		BoardMap tauler = new BoardMap();
		
		// Omplim el tauler per tenir les dades
		tauler.fillBoardMap(mapa);
		
		
		// Cerquem la sortida dins el mapa
//		BoardSquare start = (BoardSquare) tauler.findBoardSquare(mapa, Constants.START_SQUARE);
		BoardSquare start = new BoardSquare(SquareTypeEnum.START, Constants.BOARD_SIZE, Constants.BOARD_SIZE);
		
		Pending pendingStatus = new Pending(null, 0);
		
		// Tenim el primer element (la sortida) a pendents
		pendingStatus.setState(start);
		
		/* HEURÍSTICA 1 */
		// state.setHeuristica(0);
		
		// NOMÉS PER A LES HEURÍSTIQUES 2 I 3
		// Cerquem el desti dins el mapa		
//		BoardSquare arrival = (BoardSquare) tauler.findBoardSquare(mapa, Constants.ARRIVAL_SQUARE);
		BoardSquare arrival = new BoardSquare(SquareTypeEnum.ARRIVAL, Constants.BOARD_SIZE, Constants.BOARD_SIZE);
		
		/* HEURÍSTICA 2 */
		pendingStatus.setHeuristicValue(heur.heuristicByDistance( pendingStatus.getState(), arrival));
		
		/* HEURÍSTICA 3 */
		// state.setHeuristica( heuristicValue.getHeuristica3(tauler, mapa, state.getEstat(), d) );
		
		pends.add(pendingStatus);
		
		int index = 0;
		while ( !(trobat)&& pends!=null ) {
			
			pendingStatus = (Pending) pends.get(0).clone(); 			
			
			cami.add( pendingStatus.getState() );
			
			// Elim_Primer(pends); 
			pends.remove(0); 
			
			// Si ( estat_actual.tipus == "desti" ) llavors trobat=true;
			if ( pendingStatus.getState().getName().equals(Constants.ARRIVAL_SQUARE) ) {
				trobat=true;
			}
		    else {
		    	
		    	ArrayList<BoardSquare> successors = new ArrayList<BoardSquare>();
		    	
		    	Successor suc = new Successor();
		    	
				suc.extractSuccessors(pendingStatus.getState(), successors, mapa);
		    	
		    	/* PER TOT successor x de e fer */
		    	it1 = successors.iterator();
				
				if ( !it1.hasNext() )  {
					System.out.println("No hi ha successors"+"\n");
				}
				else {
					// Anem mirant els successors
					while( it1.hasNext() ) {
						sucx = (BoardSquare)it1.next();
						
						Pending estatx = new Pending(null, 0);
						estatx.setState(sucx);
						
						/* HEURÍSTICA 1 */
			    		// estatx.setHeuristica( heuristicValue.getHeuristica1(sucx));
			    		
			    		/* HEURÍSTICA 2 */
			    		estatx.setHeuristicValue( heur.heuristicByDistance(sucx, arrival) );
			    		
			    		/* HEURÍSTICA 3 */
			    		// estatx.setHeuristica( heuristicValue.getHeuristica3(tauler, mapa, estatx.getEstat(), d) );
			    				
			    		// Si x no pertany a tractats ni a pendents
				    	if( !tractats.containsValue(sucx) && !pends.contains(estatx) )
				    	{
				    		// Afegir_ordenat( pends, (x, h(x)) );
				    		pends.add( estatx );
				    		
				    		Collections.sort( (ArrayList<Pending>)pends );
				    	}
					}
				}
										
				tractats.put(index, pendingStatus.getState());
				
				index++;
		    }
		}
		
		// Finalitzem
		int temps=0;
		if (trobat) {
			it2 = cami.iterator();
			index = 0;
			if ( !it2.hasNext() )  {
				System.out.println("LLista Buida."+"\n");
			}
			else {
				// Anem escrivint el camí
				System.out.println("El camí és el següent:"+"\n");
				System.out.println("PAS i: TIPUS : Pos : ut");
				System.out.println("-----------------------");
				while( it2.hasNext() ) {
					c = (BoardSquare)it2.next();
					System.out.println("Pas "+index+":"+" "
							+c.getName()+" "+
							+c.getPosf()+" "+
							+c.getPosc()+" "+
							+c.getTime());
					
					temps += c.getTime();
					
					index++;
				}
				System.out.println("\n"+"En total s'han explorat "+index+" nodes."+"\n");
				System.out.println("\n"+"Temps total: "+(temps-11)+" ut."+"\n");
			}
		}
		else {
			// No existeix camí
			System.out.println("No existeix camí!");
		}
			
	}
}	