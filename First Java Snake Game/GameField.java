import java.util.Arrays;

public class GameField {
    private Cell[][] field;

    public GameField(int rows, int columns) {
        field = new Cell[rows][columns];
        for (int i = 0; i < field.length; i++) {
            Arrays.fill(field[i], Cell.EMPTY);
        }
    }

    public Cell[] at(int i) {
        return field[i];
    }

    public Cell at(int i, int j) {
        return this.at(i)[j];
    }

    public void set(int i, int j, Cell value) {
        field[i][j] = value;
    }

    public int getRows() {
        return field.length;
    }

    public int getColumns() {
        return field[0].length;
    }

    public void printGameField() {
        for (Cell[] row : field) {
            for (Cell elem : row) {
                char toPrint;
                switch (elem) {
                    case FOOD:
                        toPrint = '*';
                        break;
                    case SNAKE:
                        toPrint = '#';
                        break;
                    case EMPTY:
                        toPrint = '.';
                        break;
                    default:
                        toPrint = 'x';
                }
                System.out.print(toPrint);
            }
            System.out.println();
        }
    }
}
