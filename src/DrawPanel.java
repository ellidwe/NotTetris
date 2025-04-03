import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class DrawPanel extends JPanel implements MouseListener{

    private BrickLayout layout = new BrickLayout("src/bricks",40, false);

    public DrawPanel()
    {
        this.addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        long time = System.currentTimeMillis();

        while(!(System.currentTimeMillis() >= time + 100))
        {

        }
        time = System.currentTimeMillis();
        layout.doOneBrickAndDrop();
        for(int row = 0; row < 40; row++)
        {
            for(int col = 0; col < 30; col++)
            {
                g.drawRect(10 + (25 * row), 10 + (25 * col), 20, 20);
                Graphics2D g2 = (Graphics2D) g;
                if(layout.checkBrickSpot(col, row))
                {
                    g2.setColor(Color.RED);
                    g2.fillRect(10 + (25 * row), 10 + (25 * col), 20, 20);
                    g2.setColor(Color.BLACK);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

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