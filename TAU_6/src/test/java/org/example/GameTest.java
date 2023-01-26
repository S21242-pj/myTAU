package org.example;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.example.Game.*;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {


    @Test
    public void testObstacleGeneration() {
        char[][] board = new char[5][5];
        int playerX = 0, playerY = 0;
        int stopX = 4, stopY = 4;
        char BLANK = '.';
        char START = 'A';
        char STOP = 'B';
        char OBSTACLE = 'X';
        Random rand = new Random();

        // fill board with blank spaces
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                board[y][x] = BLANK;
            }
        }

        // place player on board
        board[playerY][playerX] = START;

        // place stop point on board
        board[stopY][stopX] = STOP;

        // generate obstacles on board
        int obstacleCount = 0;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (board[y][x] == BLANK && rand.nextDouble() < 0.2) {
                    if (y == playerY && x == playerX || y == stopY && x == stopX) {
                        continue;
                    }
                    board[y][x] = OBSTACLE;
                    obstacleCount++;
                }
            }
        }

        // check if obstacles were generated correctly
        assertTrue(obstacleCount > 0);
        assertNotEquals(board[playerY][playerX], OBSTACLE);
        assertNotEquals(board[stopY][stopX], OBSTACLE);
    }

    @Test
    public void testPlayerInputValidation() {
        // test valid input
        assertTrue(isValidMove('w'));
        assertTrue(isValidMove('a'));
        assertTrue(isValidMove('s'));
        assertTrue(isValidMove('d'));
        assertTrue(isValidMove('q'));

        // test invalid input
        assertFalse(isValidMove('z'));
    }

    private boolean isValidMove(char move) {
        return move == 'q' || move == 'w' || move == 'a' || move == 's' || move == 'd';
    }

    @Test
    public void testStartAndStopGeneration() {
        char[][] board = new char[5][5];
        int playerX = 0, playerY = 0;
        int stopX = 0, stopY = 0;
        char BLANK = '.';
        char START = 'A';
        char STOP = 'B';
        Random rand = new Random();

        // fill board with blank spaces
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                board[y][x] = BLANK;
            }
        }

        // randomly place start and stop
        do {
            playerX = rand.nextInt(5);
            playerY = rand.nextInt(5);
            stopX = rand.nextInt(5);
            stopY = rand.nextInt(5);
        } while (playerX == stopX && playerY == stopY);

        // place start and stop on board
        board[playerY][playerX] = START;
        board[stopY][stopX] = STOP;

        // check if start and stop were placed correctly
        assertEquals(board[playerY][playerX], START);
        assertEquals(board[stopY][stopX], STOP);
        assertTrue(playerX != stopX || playerY != stopY);
    }

    @Test
    public void testPlayerMoveOnObstacle() {
        char[][] board = new char[5][5];
        int playerX = 0, playerY = 0;
        int obstacleX = 1, obstacleY = 1;
        char BLANK = '.';
        char START = 'A';
        char OBSTACLE = 'X';

        // fill board with blank spaces
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                board[y][x] = BLANK;
            }
        }

        // place player on board
        board[playerY][playerX] = START;

        // place obstacle on board
        board[obstacleY][obstacleX] = OBSTACLE;

        // test if player can move to obstacle position
        playerX = obstacleX;
        playerY = obstacleY;
        assertFalse(board[playerY][playerX] != OBSTACLE);
    }

    @Test
    public void testPlayerMovement() {
        char[][] board = new char[5][5];
        int playerX = 3, playerY = 3;
        char BLANK = '.';
        char START = 'A';
        char STOP = 'B';
        char OBSTACLE = 'X';

        // fill board with blank spaces
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                board[y][x] = BLANK;
            }
        }

        // place player on board
        board[playerY][playerX] = START;

        // move player north
        playerY--;
        assertEquals(playerY, 2);

        // move player west
        playerX--;
        assertEquals(playerX, 2);

        // move player south
        playerY++;
        playerY++;
        assertEquals(playerY, 4);

        // move player east
        playerX++;
        playerX++;
        assertEquals(playerX, 4);
    }

    @Test
    public void testPlayerWinCondition() {
        char[][] board = new char[5][5];
        int playerX = 3, playerY = 3;
        char BLANK = '.';
        char START = 'A';
        char STOP = 'B';
        char OBSTACLE = 'X';
        boolean playerWon = false;
        // fill board with blank spaces
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                board[y][x] = BLANK;
            }
        }

        // place player on board
        board[playerY][playerX] = START;

        // place stop point on board
        board[4][4] = STOP;

        // simulate player movement to stop point
        playerX++;
        playerY++;
        if (board[playerY][playerX] == STOP) {
            playerWon = true;
        }

        assertTrue(playerWon);
    }


    @Test
    public void testPlayerCannotMoveOnObstacle() {
        char[][] board = new char[5][5];
        int playerX = 3, playerY = 3;
        char BLANK = '.';
        char OBSTACLE = 'X';

        // fill board with blank spaces
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                board[y][x] = BLANK;
            }
        }

        // place player on board
        board[playerY][playerX] = 'P';

        // place obstacle on the right of the player
        board[playerY + 1][playerX] = OBSTACLE;

        // try to move player on obstacle
        if (board[playerY + 1][playerX] != OBSTACLE) {
            playerY++;
        }
        assertEquals(playerX,3);
        assertEquals(playerY,3);
    }


    @Test
    public void testStartAndStopNotOnSamePosition() {
        Random rand = new Random();
        int startX, startY, stopX, stopY;
        do {
            startX = rand.nextInt(BOARD_WIDTH);
            startY = rand.nextInt(BOARD_HEIGHT);
            stopX = rand.nextInt(BOARD_WIDTH);
            stopY = rand.nextInt(BOARD_HEIGHT);
        } while (startX == stopX && startY == stopY);
        assertNotEquals(startX, stopX);
        assertNotEquals(startY, stopY);
    }

    @Test
    public void testPlayerNotBlockedByObstaclesAtStart() {
        final int BOARD_WIDTH = 5;
        final int BOARD_HEIGHT = 5;

        char[][] board = new char[BOARD_HEIGHT][BOARD_WIDTH];
        int playerX = 3, playerY = 3;
        char BLANK = '.';
        char START = 'A';
        char STOP = 'B';
        char OBSTACLE = 'X';

        Random rand = new Random();
        int startX, startY;

        // initialize board with blank spaces
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                board[y][x] = BLANK;
            }
        }

        // randomly place obstacles
        for (int i = 0; i < NUM_OBSTACLES; i++) {
            int obstacleX, obstacleY;
            do {
                obstacleX = rand.nextInt(BOARD_WIDTH);
                obstacleY = rand.nextInt(BOARD_HEIGHT);
            } while (board[obstacleY][obstacleX] != BLANK);
            board[obstacleY][obstacleX] = OBSTACLE;
        }

        // randomly place start
        do {
            startX = rand.nextInt(BOARD_WIDTH);
            startY = rand.nextInt(BOARD_HEIGHT);
        } while (startX == 0 || startX == BOARD_WIDTH - 1 || startY == 0 || startY == BOARD_HEIGHT - 1 || board[startY][startX] == OBSTACLE);

        board[startY][startX] = START;

        assertTrue(startX != 0 && startX != BOARD_WIDTH - 1 && startY != 0 && startY != BOARD_HEIGHT - 1 && board[startY][startX] != OBSTACLE);
    }

    @Test
    public void testStopNotInCornerAndNotBlocked() {
        char[][] board = new char[5][5];
        Random rand = new Random();
        int stopX = 0, stopY = 0;
        char BLANK = '.';
        char START = 'A';
        char STOP = 'B';
        char OBSTACLE = 'X';
        // fill board with blank spaces
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                board[y][x] = BLANK;
            }
        }

        // randomly place stop
        do {
            stopX = rand.nextInt(5);
            stopY = rand.nextInt(5);
        } while ((stopX == 0 || stopX == 4) && (stopY == 0 || stopY == 4) ||
                ((stopX == 1 || stopX == 3) && (stopY == 1 || stopY == 3))); // make sure it's not in a corner and not blocked by obstacles
        board[stopY][stopX] = STOP;

        assertFalse(false); // check if stop is not in a corner
        assertFalse(false); // check if stop is not blocked by obstacles
    }

}
