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

    public void doOneBrick() {
        if (!bricks.isEmpty()) {
            Brick b = bricks.removeFirst();
            for(int row = 0; row < brickLayout.length; row++)
            {
                if(row != brickLayout.length - 1)
                {
                    for(int i = 0; i < b.getEnd() - b.getStart() + 1; i++)
                    {
                        if(brickLayout[row + 1][b.getStart() + i] == 1)
                        {
                            for(int j = 0; j < b.getEnd() - b.getStart() + 1; j++)
                            {
                                brickLayout[row][b.getStart() + j] = 1;
                            }
                            return;
                        }
                    }
                }
                else
                {
                    for(int j = 0; j < b.getEnd() - b.getStart() + 1; j++)
                    {
                        brickLayout[row][b.getStart() + j] = 1;
                    }
                    break;
                }
            }
        }
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