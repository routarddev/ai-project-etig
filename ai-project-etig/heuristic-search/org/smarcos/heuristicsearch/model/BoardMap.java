package org.smarcos.heuristicsearch.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.smarcos.heuristicsearch.literal.Constants;
import org.smarcos.heuristicsearch.literal.SquareTypeEnum;

/**
 * @author Sandra Marcos Bonet
 * 
 */

public class BoardMap {

	public BoardMap() {
		super();
	}

	/**
	 * Acció per omplir el tauler.
	 * 
	 * @param mapa
	 *            : matriu 10x10.
	 */
	public void fillBoardMap(BoardSquare[][] boardMap) {

		// Inicialment el tauler està buit
		for (int i = 0; i < Constants.BOARD_SIZE; i++) {
			for (int j = 0; j < Constants.BOARD_SIZE; j++) {
				boardMap[i][j] = new BoardSquare(SquareTypeEnum.EMPTY, i, j);
			}
		}

		System.out.println(new File(".").getAbsoluteFile());

		// Llegim les dades de l'arxiu i anem
		// omplint el tauler
		String fileName = "heuristic-search/files/problemaIA.txt";
		FileReader fileReader;
		BufferedReader bufferedReader;
		String line;

		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			line = bufferedReader.readLine();
			while (line != null) {

				StringTokenizer st = new StringTokenizer(line, ";");

				String posx = st.nextToken();
				Integer x1 = new Integer(posx);
				int x = x1.intValue();

				String posy = st.nextToken();
				Integer y1 = new Integer(posy);
				int y = y1.intValue();

				String type = st.nextToken();
				BoardSquare boardSquare = null;
				if (type.toLowerCase().equals(Constants.ROAD_SQUARE)) {
					boardSquare = new BoardSquare(SquareTypeEnum.ROAD, x, y);
				} else if (type.toLowerCase().equals(Constants.START_SQUARE)) {
					boardSquare = new BoardSquare(SquareTypeEnum.START, x, y);
				} else if (type.toLowerCase().equals(Constants.ARRIVAL_SQUARE)) {
					boardSquare = new BoardSquare(SquareTypeEnum.ARRIVAL, x, y);
				} else if (type.toLowerCase().equals(Constants.RIVER_SQUARE)) {
					boardSquare = new BoardSquare(SquareTypeEnum.RIVER, x, y);
				} else if (type.toLowerCase().equals(Constants.MOUNTAIN_SQUARE)) {
					boardSquare = new BoardSquare(SquareTypeEnum.MOUNTAIN, x, y);
				}

				if (boardSquare != null)
					boardMap[x][y] = boardSquare;

				line = bufferedReader.readLine();
			}
			// Tanquem el fitxer
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println("Impossible obrir l'arxiu per llegir");
		}

		// Treiem per pantalla la situació actual del tauler
		for (int i = 0; i < Constants.BOARD_SIZE; i++) {
			for (int j = 0; j < Constants.BOARD_SIZE; j++) {
				System.out.println("board square " + i + " " + j + ": "
						+ boardMap[i][j].getName() + " "
						+ boardMap[i][j].getTime() + " "
						+ boardMap[i][j].getPosf() + " "
						+ boardMap[i][j].getPosc());
			}
		}

	}

	/**
	 * Acció per buscar una casella determinada.
	 * 
	 * @param mapa
	 *            : coordenada x de la posició actual.
	 * @param s
	 *            : String on guardem el tipus de la casella a buscar.
	 */
	public BoardSquare findBoardSquare(BoardSquare[][] boardSquareMap,
			String boardType) {
		int i = 0, j = 0;
		boolean finish = false, found = false;
		BoardSquare square = null;

		while (i < Constants.BOARD_SIZE && !finish) {
			j = 0;
			while (j < Constants.BOARD_SIZE && !finish) {

				if (boardSquareMap[i][j].getSquare().getName().toLowerCase()
						.equals(boardType)) {
					finish = true;
					square = (BoardSquare) (boardSquareMap[i][j]).clone();
					found = true;
				}
				j++;
			}
			i++;
		}

		if (!found) {
			System.out.println("This item does not exist");
			return null;
		} else
			return square;
	}

}