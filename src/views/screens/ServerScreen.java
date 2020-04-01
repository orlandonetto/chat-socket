package views.screens;

import views.Screen;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerScreen extends JPanel implements Screen, ActionListener {
    private static final long serialVersionUID = 1L;

    private JTextArea textAreaLogs;
    private JButton btnLimpar;

    public ServerScreen() {
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBackground(Color.RED);
        setSize(410, 350);
        setLayout(null);

        JLabel lblServidor = new JLabel("Servidor Iniciado");
        lblServidor.setForeground(Color.WHITE);
        lblServidor.setHorizontalAlignment(SwingConstants.CENTER);
        lblServidor.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblServidor.setBounds(10, 11, 385, 25);
        add(lblServidor);

        JLabel lblLogs = new JLabel("Logs");
        lblLogs.setForeground(Color.WHITE);
        lblLogs.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblLogs.setBounds(10, 37, 62, 25);
        add(lblLogs);

        textAreaLogs = new JTextArea();
        textAreaLogs.setEditable(false);
        textAreaLogs.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(textAreaLogs);
        scrollPane.setBounds(10, 66, 385, 244);
        add(scrollPane);

        btnLimpar = new JButton("Limpar Logs");
        btnLimpar.setBounds(261, 37, 134, 25);
        btnLimpar.addActionListener(this);
        add(btnLimpar);
    }

    @Override
    public void fillComponents() {

    }

    public void setTextAreaLog(String log) {
        textAreaLogs.setText(textAreaLogs.getText() + log + "\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnLimpar))
            textAreaLogs.setText("");
    }
}