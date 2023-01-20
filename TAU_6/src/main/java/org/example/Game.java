package org.example;

import java.util.Random;
import java.util.Scanner;

public class Game {
    static final int BOARD_WIDTH = 5;
    static final int BOARD_HEIGHT = 5;
    static final int NUM_OBSTACLES = 5;
    static final char START = 'A';
    static final char STOP = 'B';
    static final char OBSTACLE = 'X';
    static final char BLANK = '.';

    public static void main(String[] args) {
        char[][] board = new char[BOARD_HEIGHT][BOARD_WIDTH];
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        int playerX = 0;
        int playerY = 0;

        // initialize board
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                board[i][j] = BLANK;
            }
        }

        // randomly place start and stop
        int startX, startY, stopX, stopY;
        do {
            startX = rand.nextInt(BOARD_WIDTH);
            startY = rand.nextInt(BOARD_HEIGHT);
            stopX = rand.nextInt(BOARD_WIDTH);
            stopY = rand.nextInt(BOARD_HEIGHT);
        } while (startX == stopX && startY == stopY); // make sure they're not on the same spot
        board[startY][startX] = START;
        board[stopY][stopX] = STOP;
        playerX = startX;
        playerY = startY;

        // randomly place obstacles
        for (int i = 0; i < NUM_OBSTACLES; i++) {
            int obstacleX, obstacleY;
            do {
                obstacleX = rand.nextInt(BOARD_WIDTH);
                obstacleY = rand.nextInt(BOARD_HEIGHT);
            } while (board[obstacleY][obstacleX] != BLANK ||
                    (Math.abs(obstacleX - startX) <= 1 && Math.abs(obstacleY - startY) <= 1));
        board[obstacleY][obstacleX] = OBSTACLE;
        }

        // print initial board and instructions
        printBoard(board, playerX, playerY);

        while (true) {
            // get input from user
            char input = scanner.next().charAt(0);

            // move player based on input
            switch (input) {
                case 'w':
                    if (playerY > 0 && board[playerY - 1][playerX] != OBSTACLE) {
                        playerY--;
                    }
                    break;
                case 'a':
                    if (playerX > 0 && board[playerY][playerX - 1] != OBSTACLE) {
                        playerX--;
                    }
                    break;
                case 's':
                    if (playerY < BOARD_HEIGHT - 1 && board[playerY + 1][playerX] != OBSTACLE) {
                        playerY++;
                    }
                    break;
                case 'd':
                    if (playerX < BOARD_WIDTH - 1 && board[playerY][playerX + 1] != OBSTACLE) {
                        playerX++;
                    }
                    break;
                case 'q':
                    System.exit(0);
                    break;
                default:
                    break;
            }

            // check if player won
            if (board[playerY][playerX] == STOP) {
                System.out.println("YOU WIN!");
                System.exit(0);
            }

            // print updated board
            printBoard(board, playerX, playerY);
        }
    }

    public static void printBoard ( char[][] board, int playerX, int playerY) {
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (i == playerY && j == playerX) {
                    System.out.print('P');
                } else {
                    System.out.print(board[i][j]);
                }
                System.out.print(" ");
            }
            System.out.println();

        }
        System.out.println("Use arrow keys to move. Press \"q\" to quit.");
    }
}