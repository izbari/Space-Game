
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Desktop;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.io.File;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, ActionListener
{
    Timer timer;
    private int gecen_sure;
    private int harcanan_ates;
    private BufferedImage image;
    private BufferedImage resim;
    private ArrayList<Fire> atesler;
    private int atesdirY;
    private int topX;
    private int topdirX;
    private int uzayGemisiX;
    private int dirUzayX;

    public boolean kontrolEt() {
        for (final Fire Fire : this.atesler) {
            if (new Rectangle(Fire.getX(), Fire.getY(), 10, 20).intersects(new Rectangle(this.topX, 0, 20, 20))) {
                return true;
            }
        }
        return false;
    }

    public Game() {
        this.timer = new Timer(5, this);
        this.gecen_sure = 0;
        this.harcanan_ates = 0;
        this.atesler = new ArrayList<Fire>();
        this.atesdirY = 2;
        this.topX = 0;
        this.topdirX = 2;
        this.uzayGemisiX = 0;
        this.dirUzayX = 20;
        try {
            this.image = ImageIO.read(new FileImageInputStream(new File("b.png")));
        }
        catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setBackground(Color.BLACK);
        this.timer.start();
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        this.gecen_sure += 5;
        g.setColor(Color.red);
        g.fillOval(this.topX, 0, 20, 20);
        g.drawImage(this.image, this.uzayGemisiX, 490, this.image.getWidth() / 5, this.image.getHeight() / 5, this);
        for (final Fire Fire : this.atesler) {
            if (Fire.getY() < 0) {
                this.atesler.remove(Fire);
            }
        }
        g.setColor(Color.BLUE);
        for (final Fire Fire : this.atesler) {
            g.fillRect(Fire.getX(), Fire.getY(), 10, 20);
        }
        if (this.kontrolEt()) {
            this.timer.stop();
            final String message = "Kazand\u0131n\u0131z...\nHarcanan Fire : " + this.harcanan_ates + "\nGe\u00e7en s\u00fcre : " + this.gecen_sure / 1000.0;
            JOptionPane.showMessageDialog(this, message);
            JOptionPane.showMessageDialog(this, "You won!");
            final File f = new File("a.jpg");
            final Desktop dt = Desktop.getDesktop();
            try {
                dt.open(f);
            }
            catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(2000);
        }
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        final int c = e.getKeyCode();
        if (c == 37) {
            if (this.uzayGemisiX <= 0) {
                this.uzayGemisiX = 0;
            }
            else {
                this.uzayGemisiX -= this.dirUzayX;
            }
        }
        else if (c == 39) {
            if (this.uzayGemisiX >= 750) {
                this.uzayGemisiX = 750;
            }
            else {
                this.uzayGemisiX += this.dirUzayX;
            }
        }
        else if (c == 17) {
            this.atesler.add(new Fire(this.uzayGemisiX + 25, 500));
            ++this.harcanan_ates;
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        for (final Fire Fire : this.atesler) {
            Fire.setY(Fire.getY() - this.atesdirY);
        }
        this.topX += this.topdirX;
        if (this.topX >= 750) {
            this.topdirX = -this.topdirX;
        }
        if (this.topX <= 0) {
            this.topdirX = -this.topdirX;
        }
        this.repaint();
    }
}
