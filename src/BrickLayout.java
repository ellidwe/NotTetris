import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BrickLayout {

    private ArrayList<Brick> bricks;
    private int[][] brickLayout;
    private int cols;

    public BrickLayout(String fileName, int cols, boolean dropAllBricks) {
        this.cols = cols;
        ArrayList<String> fileData = getFileData(fileName);
        bricks = new ArrayList<Brick>();
        for (String line : fileData) {
            String[] points = line.split(",");
            int start = Integer.parseInt(points[0]);
            int end = Integer.parseInt(points[1]);
            Brick b = new Brick(start, end);
            bricks.add(b);
        }
        brickLayout = new int[bricks.size()][cols];
        if (dropAllBricks) {
            while (bricks.size() != 0) {
                doOneBrick();
            }
        }
    }

    public void doOneBrick() { //not working
        if (!bricks.isEmpty())
        {
            Brick b = bricks.removeFirst();
            for(int row = 0; row < brickLayout.length; row++)
            {
                if(row != brickLayout.length - 1) //if is not on bottom row
                {
                    int[] belowArr = new int[b.getEnd() - b.getStart() + 1];
                    for(int i = 0; i < belowArr.length; i++)
                    {
                        belowArr[i] = brickLayout[row + 1][b.getStart() + i];
                    }

                    if(contains1(belowArr))
                    {
                        for(int j = 0; j < b.getEnd() - b.getStart() + 1; j++)
                        {
                            brickLayout[row][b.getStart() + j] = 1;
                        }
                    }
                    else
                    {
                        for(int j = 0; j < b.getEnd() - b.getStart() + 1; j++)
                        {
                            brickLayout[row + 1][b.getStart() + j] = 1;
                        }
                    }
                }
                else
                {
                    for(int j = 0; j < b.getEnd() - b.getStart() + 1; j++)
                    {
                        brickLayout[row][b.getStart() + j] = 1;
                    }
                }
                if(row != 0)
                {
                    for(int j = 0; j < b.getEnd() - b.getStart() + 1; j++)
                    {
                        brickLayout[row - 1][b.getStart() + j] = 0;
                    }
                }
            }
        }
        for(int row = 0; row < brickLayout.length; row++)
        {
            for(int col = 0; col < brickLayout[0].length; col++)
            {
                System.out.print(brickLayout[row][col]);
            }
            System.out.println();
        }
    }

    public void doOneBrickAndDrop()
    {
        for(int row = brickLayout.length - 2; row >= 0; row--) //iterate up 2d arr starting from 2nd to last row
        {
            for(int col = 0; col < brickLayout[0].length; col++) //iterates from left to right
            {
                if(brickLayout[row][col] == 1) //if beginning of brick detected
                {
                    int start = col;
                    int length = 1;
                    col++;
                    while(brickLayout[row][col] == 1) //until end of brick detected
                    {
                        length++;
                        col++;
                    }

                    int[] belowArr = new int[length];
                    for(int i = 0; i < belowArr.length; i++) //get section 1 row below current section
                    {
                        belowArr[i] = brickLayout[row + 1][start + i];
                    }

                    if(!contains1(belowArr)) //drop brick
                    {
                        for(int i = 0; i < length; i++) //add 1s to where brick is to be moved
                        {
                            brickLayout[row + 1][start + i] = 1;
                        }
                        for(int i = 0; i < length; i++) //remove 1s where brick currently is located
                        {
                            brickLayout[row][start + i] = 0;
                        }
                    }
                }
            }
        }
        if (!bricks.isEmpty())
        {
            Brick b = bricks.removeFirst();
            for (int i = 0; i < b.getEnd() - b.getStart() + 1; i++)
            {
                brickLayout[0][b.getStart() + i] = 1;
            }
        }
    }

    public static boolean contains1(int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] == 1)
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getFileData(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }
        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        return fileData;
    }

    public void printBrickLayout() {
        for (int r = 0; r < brickLayout.length; r++) {
            for (int c = 0; c < brickLayout[0].length; c++) {
                System.out.print(brickLayout[r][c] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkBrickSpot(int r, int c) {
        if (brickLayout[r][c] == 1) {
            return true;
        }
        else {
            return false;
        }
    }
}