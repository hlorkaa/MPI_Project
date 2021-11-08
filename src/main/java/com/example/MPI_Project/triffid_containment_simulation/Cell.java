package com.example.MPI_Project.triffid_containment_simulation;

import java.util.ArrayList;
import java.util.Random;

public class Cell {
    private final int size = 5;
    private final int[][] space = new int[size][size];
    private final int[] triffidPosition = new int[2];
    private boolean triffidEscaped = false;
    private  boolean doorIsOpen = false;

    /*
     0 = empty space
     1 = triffid position
     2 = wall
     3 = closed door
     4 = opened door
    */

    public Cell() {
        for (int x = 0; x < space.length; x++) {
            for (int y = 0; y < space.length; y++) {
                if (x == 0 || x == space.length-1 || y == 0 || y == space.length-1) {
                    this.space[x][y] = 2; // wall
                }
            }
        }

        /*
        2 2 2 2 2
        2 0 0 0 2
        2 0 0 0 2 <-- now cell looks like this
        2 0 0 0 2
        2 2 2 2 2
        */
        Random random = new Random();
        int randomX = random.nextInt(3) + 1;
        int randomY = random.nextInt(3) + 1;

        this.space[randomX][randomY] = 1; // drop triffid on the random place inside the cell
        triffidPosition[0] = randomX; triffidPosition[1] = randomY; // saving his position to avoid searching for it on each update
    }

    public void update() {
        if (!triffidEscaped) {
            Random random = new Random();
            int random_chance = random.nextInt(10);
            if (!doorIsOpen) { // if door is closed, open it with 10% chance
                if (random_chance < 1) {
                    doorIsOpen = true;
                    space[0][size - (int)(size/2)] = 0;
                }
            }
            else {
                if (random_chance < 3) { // if door is opened, close it with 30% chance
                    doorIsOpen = false;
                    space[0][size - (int)(size/2)] = 0;
                }
            }

            moveTriffid();
        }

    }

    void moveTriffid() {
        int currentX = triffidPosition[0];
        int currentY = triffidPosition[1];

        if (currentX == 0) {
            triffidEscaped = true;
            return;
        }

        Random random = new Random();
        int chooseAxis = random.nextInt(2); // up-down or left-right
        int direction = random.nextInt(3) - 3; // -1, 0, 1

        if (chooseAxis == 0) {                          // moving in x-axis
            if (space[currentX + direction][currentY] == 2) { // if wall
                return;
            }
            space[currentX][currentY] = 0; // triffid went away from previous position
            space[currentX + direction][currentY] = 1;
        } else {                                        // moving in y-axis
            if (space[currentX][currentY + direction] != 0) { // if wall
                return;
            }
            space[currentX][currentY] = 0; // triffid went away from previous position
            space[currentX][currentY + direction] = 1;
        }
    }

    public int[] triffidPosition() {
        return triffidPosition;
    }

    public boolean isOpen() {
        return doorIsOpen;
    }

    public boolean isEmpty() {
        return triffidEscaped;
    }
}
