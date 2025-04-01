import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseListener{

    private boolean[][] grid;

    public DrawPanel()
    {
        this.addMouseListener(this);
        grid = new boolean[30][40];
        randomSetGrid();
    }

    public void randomSetGrid()
    {
        long time = System.currentTimeMillis();
        while(true)
        {
            if (System.currentTimeMillis() >= time + 1000)
            {
                grid = new boolean[30][40];
                for(int row = 0; row < 30; row++)
                {
                    for(int col = 0; col < 40; col++)
                    {
                        if(Math.random() <= 0.3)
                        {
                            grid[row][col] = true;
                        }
                    }
                }
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int i = 0; i < 40; i++)
        {
            for(int j = 0; j < 30; j++)
            {
                g.drawRect(10 + (25 * i), 10 + (25 * j), 20, 20);
                Graphics2D g2 = (Graphics2D) g;
                if(grid[j][i])
                {
                    g2.setColor(Color.RED);
                    g2.fillRect(10 + (25 * i), 10 + (25 * j), 20, 20);
                    g2.setColor(Color.BLACK);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        randomSetGrid();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}