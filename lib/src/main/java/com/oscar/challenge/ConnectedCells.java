package com.oscar.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

/**
 * This class contains a solution to the following challenge:
 * Given a 2D grid with values. Find the largest group of contiguous values in the grid.
 * For example, the following grid:
 * | A | B | C |
 * | B | B | B |
 * | B | A | A |
 * Should return the group of coordinates containing B's: [(0,1), (1,0), (1,1), (1,2), (2,0)]
 * This solution uses breadth-first search to find the neighbour cells for any given coordinate
 */
public class ConnectedCells {

    private final String[][] grid;
    private final Map<Coordinate, Boolean> visited;

    public static class Coordinate {
        private final int row;
        private final int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public Coordinate getUp() {
            return new Coordinate(getRow() - 1, getCol());
        }

        public Coordinate getDown() {
            return new Coordinate(getRow() + 1, getCol());
        }

        public Coordinate getLeft() {
            return new Coordinate(getRow(), getCol() - 1);
        }

        public Coordinate getRight() {
            return new Coordinate(getRow(), getCol() + 1);
        }

        public Coordinate getUpLeft() {
            return new Coordinate(getRow() - 1, getCol() - 1);
        }

        public Coordinate getUpRight() {
            return new Coordinate(getRow() - 1, getCol() + 1);
        }

        public Coordinate getDownLeft() {
            return new Coordinate(getRow() + 1, getCol() - 1);
        }

        public Coordinate getDownRight() {
            return new Coordinate(getRow() + 1, getCol() + 1);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return row == that.row && col == that.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    public ConnectedCells(String[][] grid) {
        this.grid = grid;
        this.visited = new HashMap<>();
    }

    /**
     * This method iterates over the entire 2D grid and finds the largest group of contiguous coordinates
     * containing the same value
     *
     * @return list of contiguous coordinates of the same value. If there are multiple groups with the same value,
     * it returns the first largest group it finds
     */
    public List<Coordinate> findLargestSection() {

        List<Coordinate> largestSoFar = new ArrayList<>();

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                Coordinate currentCoord = new Coordinate(row, col);
                if (!visited.containsKey(currentCoord)) {
                    List<Coordinate> contiguousCells = bfs(grid[row][col], currentCoord);
                    if (contiguousCells.size() > largestSoFar.size()) {
                        largestSoFar.clear();
                        largestSoFar.addAll(contiguousCells);
                    }
                }
            }
        }
        return largestSoFar;
    }

    private List<Coordinate> bfs(String cellValue, Coordinate start) {

        List<Coordinate> contiguousCells = new ArrayList<>();

        Queue<Coordinate> neighboursToVisit = new LinkedList<>();
        neighboursToVisit.add(start);

        while (!neighboursToVisit.isEmpty()) {
            Coordinate current = neighboursToVisit.poll();
            if (!visited.containsKey(current)) {
                contiguousCells.add(current);
            }

            visited.put(current, Boolean.TRUE);
            for (Coordinate neighbour : getAllNeighbours(current)) {
                if (grid[neighbour.getRow()][neighbour.getCol()].equalsIgnoreCase(cellValue)) {
                    neighboursToVisit.add(neighbour);
                }
            }

        }

        return contiguousCells;
    }

    private List<Coordinate> getAllNeighbours(Coordinate current) {
        List<Coordinate> neighbours = new ArrayList<>();

        Coordinate up = current.getUp();
        Coordinate down = current.getDown();
        Coordinate left = current.getLeft();
        Coordinate right = current.getRight();
        Coordinate upLeft = current.getUpLeft();
        Coordinate upRight = current.getUpRight();
        Coordinate downLeft = current.getDownLeft();
        Coordinate downRight = current.getDownRight();

        if (isValid(up)) {
            neighbours.add(up);
        }
        if (isValid(down)) {
            neighbours.add(down);
        }
        if (isValid(left)) {
            neighbours.add(left);
        }
        if (isValid(right)) {
            neighbours.add(right);
        }
        if (isValid(upRight)) {
            neighbours.add(upRight);
        }
        if (isValid(upLeft)) {
            neighbours.add(upLeft);
        }
        if (isValid(downRight)) {
            neighbours.add(downRight);
        }
        if (isValid(downLeft)) {
            neighbours.add(downLeft);
        }

        return neighbours;
    }

    private boolean isValid(Coordinate coordinate) {
        boolean visited = this.visited.containsKey(coordinate);
        boolean validRow = coordinate.getRow() < this.grid.length && coordinate.getRow() >= 0;
        boolean validCol = coordinate.getCol() < this.grid[0].length && coordinate.getCol() >= 0;
        return !visited && validCol && validRow;
    }
}
