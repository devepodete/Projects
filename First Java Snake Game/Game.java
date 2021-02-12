import java.util.Scanner;
import java.util.Random;

public class Game implements Runnable {
    private Random randomGen;

    private GameField gameField;
    private GameState gameState;
    private Snake snake;

    private Pair food;

    public Game(int rows, int columns) {
        gameState = GameState.STOCK;
        randomGen = new Random();
        snake = new Snake();
        gameField = new GameField(rows, columns);
        food = new Pair(0,0);
        System.out.println("Game initialized");
    }

    private void updateGameField() {
        Pair snakeHead = snake.getHead();
        Direction snakeDirection = snake.getCurrentDirection();
        boolean increaseLength = false;

        switch (snakeDirection) {
            case UP:
                if (snakeHead.y == 0) {
                    gameState = GameState.OVER;
                    return;
                }
                if (gameField.at(snakeHead.y-1, snakeHead.x) == Cell.FOOD) {
                    increaseLength = true;
                }
                break;
            case DOWN:
                if (snakeHead.y == gameField.getRows()-1) {
                    gameState = GameState.OVER;
                    return;
                }
                if (gameField.at(snakeHead.y+1, snakeHead.x) == Cell.FOOD) {
                    increaseLength = true;
                }
                break;
            case LEFT:
                if (snakeHead.x == 0) {
                    gameState = GameState.OVER;
                    return;
                }
                if (gameField.at(snakeHead.y, snakeHead.x-1) == Cell.FOOD) {
                    increaseLength = true;
                }
                break;
            case RIGHT:
                if (snakeHead.x == gameField.at(snakeHead.y).length-1) {
                    gameState = GameState.OVER;
                    return;
                }
                if (gameField.at(snakeHead.y, snakeHead.x+1) == Cell.FOOD) {
                    increaseLength = true;
                }
                break;
        }

        System.out.println("Moving snake...");
        System.out.println("Snake before move:" + snake.toString());
        snake.move(increaseLength);
        System.out.println("Snake after move:" + snake.toString());


        System.out.println("Clearing board...");
        for (int i = 0; i < gameField.getRows(); i++) {
            for (int j = 0; j < gameField.at(i).length; j++) {
                gameField.set(i, j, Cell.EMPTY);
            }
        }

        System.out.println("Setting snake...");
        Pair[] snakePositions = snake.getBodyCellsPositions();
        for (Pair p : snakePositions) {
            gameField.set(p.y, p.x, Cell.SNAKE);
        }


        if (increaseLength) {
            System.out.println("Setting food...");
            int foodX, foodY;

            do {
                foodX = Math.abs(randomGen.nextInt() % gameField.getColumns());
                foodY = Math.abs(randomGen.nextInt() % gameField.getRows());
            } while (snake.hasBodyAtPosition(foodX, foodY));

            food.x = foodX;
            food.y = foodY;
        }
        gameField.set(food.x, food.y, Cell.FOOD);
    }

    @Override
    public void run() {
        gameState = GameState.RUNNING;
        Direction dir = null;
        Scanner scanner = new Scanner(System.in);

        while (gameState != GameState.OVER) {
            System.out.print("Move: ");
            String move = scanner.next();

            switch (move) {
                case "u" -> dir = Direction.UP;
                case "d" -> dir = Direction.DOWN;
                case "l" -> dir = Direction.LEFT;
                case "r" -> dir = Direction.RIGHT;
            }

            System.out.println("dir = " + dir);
            System.out.println("Snake head: " + snake.getHead());

            snake.setCurrentDirection(dir);
            updateGameField();
            gameField.printGameField();
        }

        System.out.println("Game over. Snake crashed.");
    }

}
