package views;

import javax.swing.*;

public class MainScreen extends JFrame {
    private static final long serialVersionUID = 1L;

    public static final Integer WIDTH = 410;
    public static final Integer HEIGTH = 350;

    private JPanel jpanel;

    private static MainScreen instance;

    private MainScreen() {
        initComponents();
        fillComponents();
    }

    public static MainScreen getInstance() {
        if (instance == null)
            instance = new MainScreen();
        return instance;
    }

    private void initComponents() {
        jpanel = new JPanel();
    }

    private void fillComponents() {
        setTitle("Chat - Padr�o Internacional");
        setSize(WIDTH, HEIGTH);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(jpanel);
        updateComponents();
    }

    public void updateComponents() {
        // Necess�rio para limpar a tela.
    	remove(jpanel);
        jpanel.removeAll();

        jpanel = ScreenManager.getInstance().getCurrentScreen();
        jpanel.setBounds(0, 0, WIDTH, HEIGTH);

        add(jpanel);

        setVisible(false);
        setVisible(true);
    }

}