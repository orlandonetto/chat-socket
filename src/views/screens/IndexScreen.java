package views.screens;

import controllers.ClientController;
import controllers.ServerController;
import models.Usuario;
import utils.Current;
import views.Screen;
import views.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class IndexScreen extends JPanel implements Screen, ActionListener, FocusListener {
    private static final long serialVersionUID = 1L;

    private JButton bServidor, bCliente;
    private JTextField tNome;
    private JTextField tPortaCliente;
    private JTextField tIpCliente;
    private JTextField tPortaServidor;

    public IndexScreen() {
        setSize(410, 350);
        setLayout(null);
        setBackground(Color.BLUE);

        JLabel lTitulo = new JLabel();
        lTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lTitulo.setFont(new Font("Serif", Font.BOLD, 30));
        lTitulo.setForeground(Color.WHITE);
        lTitulo.setText("Blue Chat");
        lTitulo.setBounds(15, 11, 373, 30);
        add(lTitulo);

        tNome = new JTextField("Digite seu nome");
        tNome.setBounds(154, 90, 144, 30);
        tNome.addFocusListener(this);
        add(tNome);

        tIpCliente = new JTextField("Digite o ip do Host");
        tIpCliente.setBounds(41, 153, 118, 30);
        tIpCliente.addFocusListener(this);
        add(tIpCliente);

        tPortaCliente = new JTextField("Digite a porta");
        tPortaCliente.setBounds(70, 188, 89, 30);
        tPortaCliente.addFocusListener(this);
        add(tPortaCliente);

        tPortaServidor = new JTextField("Digite a porta");
        tPortaServidor.setBounds(299, 189, 89, 30);
        tPortaServidor.addFocusListener(this);
        add(tPortaServidor);

        bServidor = new JButton("Iniciar um Chat");
        bServidor.addActionListener(this);
        bServidor.setBounds(244, 229, 144, 50);
        add(bServidor);

        bCliente = new JButton("Entrar em um Chat");
        bCliente.addActionListener(this);
        bCliente.setBounds(15, 229, 144, 50);
        add(bCliente);

        JLabel lNome = new JLabel("Nome:");
        lNome.setForeground(Color.WHITE);
        lNome.setFont(new Font("Arial Black", Font.BOLD, 14));
        lNome.setBounds(95, 99, 54, 14);
        add(lNome);

        JLabel lIpCliente = new JLabel("IP:");
        lIpCliente.setForeground(Color.WHITE);
        lIpCliente.setFont(new Font("Arial Black", Font.BOLD, 14));
        lIpCliente.setBounds(15, 155, 23, 22);
        add(lIpCliente);

        JLabel lPorta = new JLabel("Porta:");
        lPorta.setForeground(Color.WHITE);
        lPorta.setFont(new Font("Arial Black", Font.BOLD, 14));
        lPorta.setBounds(244, 191, 54, 22);
        add(lPorta);

        JLabel lIp = new JLabel("Ip da M�quina:");
        lIp.setForeground(Color.WHITE);
        lIp.setFont(new Font("Arial Black", Font.BOLD, 14));
        lIp.setBounds(89, 292, 124, 22);
        add(lIp);

        JLabel lIpMaquina = new JLabel("127.0.0.1");
        lIpMaquina.setBackground(Color.WHITE);
        lIpMaquina.setForeground(Color.ORANGE);
        lIpMaquina.setFont(new Font("Arial Black", Font.BOLD, 14));
        lIpMaquina.setBounds(220, 292, 124, 22);
        add(lIpMaquina);

        JLabel lPortaCliente = new JLabel("Porta:");
        lPortaCliente.setForeground(Color.WHITE);
        lPortaCliente.setFont(new Font("Arial Black", Font.BOLD, 14));
        lPortaCliente.setBounds(15, 190, 54, 22);
        add(lPortaCliente);
    }

    @Override
    public void fillComponents() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bServidor)) {

            // Verifying name.
            if (!verifyName(tNome.getText()))
                return;


            try {
                Integer.parseInt(tPortaServidor.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(tPortaServidor, "Valor de Porta incorreto.");

                return;
            }

            if (tPortaServidor.getText().length() < 1) {
                JOptionPane.showMessageDialog(tPortaServidor, "Digite um valor para a porta");

                return;
            }

            // Initializing Currents
            Current.PORT = Integer.parseInt(tPortaServidor.getText());
            Current.USUARIO = new Usuario(tNome.getText());
            Current.IP_SERVIDOR = "127.0.0.1";
            Current.IP_LOCAL = "127.0.0.1";

            ServerController.getInstance();
            ScreenManager.getInstance().setCurrent(1);
        }

        if (e.getSource().equals(bCliente)) {

            // Verifying name.
            if (!verifyName(tNome.getText())) {
                return;
            }

            // IP
            String ip = tIpCliente.getText();
            if (ip.equals("Digite o ip do Host") || ip.length() < 1) {
                JOptionPane.showMessageDialog(tIpCliente, "Digite o ip corretamente.");

                return;
            }

            // Port
            int porta;
            try {
                porta = Integer.parseInt(tPortaCliente.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(tPortaCliente, "Valor de Porta incorreto.");

                return;
            }

            // Initializing Currents
            Current.PORT = porta;
            Current.USUARIO = new Usuario(tNome.getText());
            Current.IP_SERVIDOR = ip;
            Current.IP_LOCAL = "127.0.0.1";

            ClientController.getInstance().connect(ip, porta);
            ScreenManager.getInstance().setCurrent(2);
        }
    }

    private Boolean verifyName(String name) {
        if (name.length() < 1 || name.equals("Digite seu nome")) {
            JOptionPane.showMessageDialog(tNome, "Digite um nome.");

            return false;
        }

        if (name.length() > 20) {
            JOptionPane.showMessageDialog(tNome, "O m�ximo de caracteres � 20.");

            return false;
        }
        return true;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource().equals(tNome)) {
            if (tNome.getText().equals("Digite seu nome"))
                tNome.setText("");
        }

        if (e.getSource().equals(tPortaServidor)) {
            if (tPortaServidor.getText().equals("Digite a porta"))
                tPortaServidor.setText("");
        }

        if (e.getSource().equals(tIpCliente)) {
            if (tIpCliente.getText().equals("Digite o ip do Host"))
                tIpCliente.setText("");
        }

        if (e.getSource().equals(tPortaCliente)) {
            if (tPortaCliente.getText().equals("Digite a porta"))
                tPortaCliente.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource().equals(tNome)) {
            if (tNome.getText().length() < 1)
                tNome.setText("Digite seu nome");
        }

        if (e.getSource().equals(tPortaServidor)) {
            if (tPortaServidor.getText().length() == 0)
                tPortaServidor.setText("Digite a porta");
        }

        if (e.getSource().equals(tIpCliente)) {
            if (tIpCliente.getText().length() == 0)
                tIpCliente.setText("Digite o ip do Host");
        }

        if (e.getSource().equals(tPortaCliente)) {
            if (tPortaCliente.getText().length() == 0)
                tPortaCliente.setText("Digite a porta");
        }
    }

}
