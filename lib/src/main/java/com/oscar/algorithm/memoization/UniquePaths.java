package com.oscar.algorithm.memoization;

public class UniquePaths {

    public static final String OBSTACLE = "O";
    public static final String VALID = "V";

    /**
     * This method solves the typical path-finding problem where a path is a sequence of cells whose movement is
     * restricted to one direction on the y-axis or one direction on the x-axis (i.d. down or right). The path will
     * start at the top left corner of the grid and end at the bottom left corner. Cells containing the string
     * 'obstacle' can't be traversed, so the path has to go around them.
     *
     * @param grid       non-null 2D grid with obstacles containing at least 1 row and 1 column. Assume are rows are the same
     *                   size (length is uniform across all rows)
     * @param currentRow starting point in the y axis
     * @param currentCol starting point in the x axis
     * @return number of unique paths that can be taken to reach the bottom right corner of the grid
     */
    public int findUniquePaths(String[][] grid, int currentRow, int currentCol) {
        int nRows = grid.length;
        int nCols = grid[0].length;

        // Base case - at the end
        if (currentRow == nRows - 1 && currentCol == nCols - 1) {
            return 1;
        }

        int rightCount = 0;
        int downCount = 0;
        for (int row = currentRow; row < nRows; row++) {
            for (int col = currentCol; col < nCols; col++) {
                // Go right
                if (isValidCell(grid, currentRow, currentCol + 1)) {
                    rightCount = findUniquePaths(grid, currentRow, currentCol + 1);
                }
                // Go down
                if (isValidCell(grid, currentRow + 1, currentCol)) {
                    downCount = findUniquePaths(grid, currentRow + 1, currentCol);
                }
            }
        }

        return rightCount + downCount;
    }

    /**
     * This method solves the typical path-finding problem where a path is a sequence of cells whose movement is
     * restricted to one direction on the y-axis or one direction on the x-axis (i.d. down or right). The path will
     * start at the top left corner of the grid and end at the bottom left corner. Cells containing the string
     * 'obstacle' can't be traversed, so the path has to go around them. This method uses a cache to remember
     * previously calculated results.
     *
     * @param grid       non-null 2D grid with obstacles containing at least 1 row and 1 column. Assume are rows are the same
     *                   size (length is uniform across all rows)
     * @param currentRow starting point in the y axis
     * @param currentCol starting point in the x axis
     * @param cache      2D array used to store previously calculated paths
     * @return number of unique paths that can be taken to reach the bottom right corner of the grid
     */
    public int findUniquePathsMemoized(String[][] grid, int currentRow, int currentCol, Integer[][] cache) {
        int nRows = grid.length;
        int nCols = grid[0].length;

        // Base case - at the end
        if (currentRow == nRows - 1 && currentCol == nCols - 1) {
            return 1;
        }

        int rightCount = 0;
        int downCount = 0;
        for (int row = currentRow; row < nRows; row++) {
            for (int col = currentCol; col < nCols; col++) {
                // Go right
                if (isValidCell(grid, currentRow, currentCol + 1)) {
                    if (cache[currentRow][currentCol + 1] == null) {
                        rightCount = findUniquePathsMemoized(grid, currentRow, currentCol + 1, cache);
                        cache[currentRow][currentCol + 1] = rightCount;
                    }
                    rightCount = cache[currentRow][currentCol + 1];
                }
                // Go down
                if (isValidCell(grid, currentRow + 1, currentCol)) {
                    if (cache[currentRow + 1][currentCol] == null) {
                        downCount = findUniquePathsMemoized(grid, currentRow + 1, currentCol, cache);
                        cache[currentRow + 1][currentCol] = downCount;
                    }
                    downCount = cache[currentRow + 1][currentCol];
                }
            }
        }

        return rightCount + downCount;
    }

    private boolean isValidCell(String[][] grid, int targetRow, int targetCol) {
        int nRows = grid.length;
        int nCols = grid[0].length;

        boolean outOfBoundsRow = targetRow >= nRows;
        boolean outOfBoundsCol = targetCol >= nCols;

        if (!outOfBoundsCol && !outOfBoundsRow) {
            return !grid[targetRow][targetCol].equalsIgnoreCase(OBSTACLE);
        }
        return false;
    }
}
