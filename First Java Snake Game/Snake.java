import java.util.Arrays;

public class Snake {
    private Pair[] bodyCellsPositions;
    private Pair lastPositionBeforeSlide;
    private Direction currentDirection;

    public Snake() {
        bodyCellsPositions = new Pair[]{new Pair(0,0)};
        currentDirection = Direction.RIGHT;
    }

    public void move(boolean increaseLength) {
        Pair newHeadCord = getHead().copy();
        Direction dir = getCurrentDirection();
        lastPositionBeforeSlide = bodyCellsPositions[bodyCellsPositions.length-1].copy();

        if (increaseLength) {
            Pair[] newCells = new Pair[bodyCellsPositions.length+1];
            for (int i = 0; i < bodyCellsPositions.length; i++) {
                newCells[i] = bodyCellsPositions[i].copy();
            }
            bodyCellsPositions = Arrays.copyOf(newCells, newCells.length);
            bodyCellsPositions[bodyCellsPositions.length-1] = bodyCellsPositions[bodyCellsPositions.length-2].copy();
        }


        switch (dir) {
            case UP -> newHeadCord.y--;
            case DOWN -> newHeadCord.y++;
            case LEFT -> newHeadCord.x--;
            case RIGHT -> newHeadCord.x++;
        }


        for (int i = bodyCellsPositions.length-1; i > 0; i--) {
            bodyCellsPositions[i] = bodyCellsPositions[i-1].copy();
        }
        bodyCellsPositions[0] = newHeadCord;

    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction direction) {
        currentDirection = direction;
    }

    public Pair getHead() {
        return bodyCellsPositions[0].copy();
    }

    public Pair[] getBodyCellsPositions() {
        return Arrays.copyOf(bodyCellsPositions, bodyCellsPositions.length);
    }

    public boolean hasBodyAtPosition(int i, int j) {
        for (Pair p : bodyCellsPositions) {
            if (p.x == j && p.y == i) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Snake{" +
                "bodyCellsPositions=" + Arrays.toString(bodyCellsPositions) +
                ", lastPositionBeforeSlide=" + lastPositionBeforeSlide +
                ", currentDirection=" + currentDirection +
                '}';
    }
}
