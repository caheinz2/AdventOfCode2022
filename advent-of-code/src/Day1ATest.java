import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1ATest {

    @Test
    public void findsMaximumCalories() {
        int[][] input = {{}};
        assertEquals(0, findMaximumRowSum(input));

        int[][] input2 = {{1}};
        assertEquals(1, findMaximumRowSum(input2));

        int[][] input3 = {{1,2}};
        assertEquals(3, findMaximumRowSum(input3));

        int[][] input4 = {{}, {1,2}};
        assertEquals(3, findMaximumRowSum(input4));

        int[][] input5 = {{}, {1,2}, {9, 9, 9}, {}, {5, 9}};
        assertEquals(9+9+9, findMaximumRowSum(input5));
    }

    @Test
    public void parseInput() {
        try(StringReader reader = new StringReader("")) {
            int[][] input = {{}};
            assertArrayEquals(input, parseReaderContents(reader, 1, 0));
        }

        try(StringReader reader = new StringReader("1\n2\n3\n")) {
            int[][] input = {{1,2,3}};
            assertArrayEquals(input, parseReaderContents(reader, 1, 3));
        }

        try(StringReader reader = new StringReader("1\n2\n3\n\n4\n5\n6\n")) {
            int[][] input = {{1,2,3},{4,5,6}};
            assertArrayEquals(input, parseReaderContents(reader, 2, 3));
        }
    }

    @Test
    public void main() throws IOException {
        try(Reader reader = new FileReader("./data/day1A.txt")) {
            int[][] parsedInput = parseReaderContents(reader, 254, 20);
            int answer = findMaximumRowSum(parsedInput);
            System.out.println("Answer: " + answer);
        }
    }

    public int findMaximumRowSum(int[][] input) {
        int max = 0;
        for(int i = 0; i < input.length; i++) {
            int curMax = sumRow(input, i);
            max = Integer.max(max, curMax);
        }
        return max;
    }

    private int sumRow(int[][] input, int rowNumber) {
        return Arrays.stream(input[rowNumber]).sum();
    }

    public int[][] parseReaderContents(Reader reader, int numRows, int numCols) {
        int[][] retVal = new int[numRows][numCols];
        int rowIdx = 0;
        try(BufferedReader bufferedReader = new BufferedReader(reader)) {
            while(rowIdx < numRows) {
                retVal[rowIdx] = parseReaderRowContents(bufferedReader, numCols);
                rowIdx += 1;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return retVal;
    }

    private int[] parseReaderRowContents(BufferedReader bufferedReader, int numCols) throws IOException {
        int[] retVal = new int[numCols];
        String curCol;
        int colIdx = 0;
        while((curCol = bufferedReader.readLine()) != null) {
            if(curCol.isEmpty()) {
                break;
            }
            retVal[colIdx] = Integer.parseInt(curCol);
            colIdx += 1;
        }
        return retVal;
    }
}
