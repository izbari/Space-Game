import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.HeadlessException;
import java.io.IOException;
import java.awt.Component;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class GameScreen extends JFrame
{
    public static void main(final String[] args) throws IOException {
        final GameScreen ekran = new GameScreen("Space Game");
        ekran.setResizable(false);
        ekran.setFocusable(false);
        ekran.setSize(800, 600);
        ekran.setDefaultCloseOperation(3);
        final Game oyun = new Game();
        oyun.requestFocus();
        oyun.addKeyListener(oyun);
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);
        ekran.add(oyun);
        ekran.setVisible(true);
    }

    public GameScreen(final String title) throws HeadlessException {
        super(title);
    }

    private void initComponents() {
        this.setDefaultCloseOperation(3);
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, 32767));
        this.pack();
    }
}