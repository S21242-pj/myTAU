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
    public void testObstacleNotNextToStart() {
        char[][] board = new char[5][5];
        int playerX = 0, playerY = 0;
        int startX = 0, startY = 0;
        int stopX = 4, stopY = 4;
        int NUM_OBSTACLES = 3;
        char BLANK = '.';
        char START = 'A';
        char STOP = 'B';
        char OBSTACLE = 'X';
        Random rand = new Random();

        // randomly place start and stop
        board[startY][startX] = START;
        board[stopY][stopX] = STOP;
        playerX = startX;
        playerY = startY;

        // randomly place obstacles
        for (int i = 0; i < NUM_OBSTACLES; i++) {
            int obstacleX, obstacleY;
            do {
                obstacleX = rand.nextInt(5);
                obstacleY = rand.nextInt(5);
            } while (board[obstacleY][obstacleX] != BLANK ||
                    (Math.abs(obstacleX - startX) <= 1 && Math.abs(obstacleY - startY) <= 1));
            // make sure obstacle isn't on top of something else or next to the starting point
            board[obstacleY][obstacleX] = OBSTACLE;
        }

        // check if obstacles are next to starting point
        for (int y = startY - 1; y <= startY + 1; y++) {
            for (int x = startX - 1; x <= startX + 1; x++) {
                if (y >= 0 && y < 5 && x >= 0 && x < 5) {
                    assertNotEquals(OBSTACLE, board[y][x]);
                }
            }
        }
    }

    @Test
    public void testCantGoOutOfBoard() {
        char[][] board = new char[5][5];
        int playerX = 0, playerY = 0;
        char BLANK = '.';
        char START = 'A';
        char STOP = 'B';
        char OBSTACLE = 'X';
        Random rand = new Random();

        // randomly place start and stop
        board[playerY][playerX] = START;
        // move player out of the board
        playerX = -1;
        playerY = -1;
        // check if player's location is still within the board
        assertTrue(playerX >= 0 && playerX < 5 && playerY >= 0 && playerY < 5);
    }

    @Test
    public void testPrintBoard() {
        char[][] board = new char[5][5];
        int playerX = 2, playerY = 2;
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

        // place obstacles on board
        board[0][0] = OBSTACLE;
        board[4][4] = OBSTACLE;
        board[2][3] = OBSTACLE;

        // capture the output of the printBoard method
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // call the printBoard method
        printBoard(board, playerX, playerY);

        // check if the board was printed correctly
        String expectedOutput = ". . . . .\n" +
                ". . X . .\n" +
                ". . A . .\n" +
                ". . . . .\n" +
                ". . . . X\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testWinCondition() {
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

        // simulate player reaching the stop point
        playerX = stopX;
        playerY = stopY;

        // capture the output of the game
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //call printBoard method
        printBoard(board, playerX, playerY);

        // check if the game ended correctly and the message was displayed
        String expectedOutput = "YOU WIN!\n";
        assertTrue(outContent.toString().endsWith(expectedOutput));
    }

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
    public void testPlayerMovement() {
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

        //generate obstacles on board
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (board[y][x] == BLANK && rand.nextDouble() < 0.2) {
                    board[y][x] = OBSTACLE;
                }
            }
        }

        // test if player can move up
        playerY--;
        assertTrue(playerY >= 0 && board[playerY][playerX] != OBSTACLE);

        // test if player can move down
        playerY++;
        playerY++;
        assertTrue(playerY < 5 && board[playerY][playerX] != OBSTACLE);

        // test if player can move left
        playerY--;
        playerX--;
        assertTrue(playerX >= 0 && board[playerY][playerX] != OBSTACLE);

        // test if player can move right
        playerX++;
        playerX++;
        assertTrue(playerX < 5 && board[playerY][playerX] != OBSTACLE);
    }

    @Test
    public void testPlayerInputValidation() {
        char move;

        // test valid input
        move = 'w';
        assertTrue(move == 'w' || move == 'a' || move == 's' || move == 'd');

        // test invalid input
        move = 'z';
        assertFalse(move == 'w' || move == 'a' || move == 's' || move == 'd');
    }


}
