public class Pair {
    public int x, y;

    public Pair (int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Pair copy() {
        return new Pair(x, y);
    }
}
