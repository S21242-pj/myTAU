package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

import static org.example.Game.printBoard;
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
        assertFalse(board[playerY][playerX] == OBSTACLE);
        assertFalse(board[stopY][stopX] == OBSTACLE);
    }

    @Test
    public void testPlayerInputValidation() {
        // test valid input
        assertTrue(isValidMove('w'));
        assertTrue(isValidMove('a'));
        assertTrue(isValidMove('s'));
        assertTrue(isValidMove('d'));

        // test invalid input
        assertFalse(isValidMove('z'));
    }

    private boolean isValidMove(char move) {
        return move == 'w' || move == 'a' || move == 's' || move == 'd';
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
        assertTrue(board[playerY][playerX] == START);
        assertTrue(board[stopY][stopX] == STOP);
        assertTrue(playerX != stopX || playerY != stopY);
    }

    @Test
    public void testPlayerBoundsValidation() {
        char[][] board = new char[5][5];
        int playerX = 2, playerY = 2;
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

    // test if player can move north
    playerY--; // 2 1
    assertTrue(playerY >= 0);

    // test if player can move west
    playerX--; // 1 1
    assertTrue(playerX >= 0);

    // test if player can move south
    playerY++;
    playerY++; // 1 3
    assertTrue(playerY < 5);

    // test if player can move east
    playerX++;
    playerX++;// 3 3
    assertTrue(playerX < 5);

    // test if player cannot move out of bounds
    playerX++;
    playerX++;
    assertFalse(playerX < 5);
    playerY++;
    assertFalse(playerY < 5);
    playerX--;
    playerX--;
    assertFalse(playerX < 0);
    playerY--;
    assertFalse(playerY < 0);
}


}
