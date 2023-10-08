package appTroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class appTroll2 extends JFrame implements ActionListener {
    private int numeroAleatorio;
    private int intentos = 0;
    private JTextField campoTexto;
    private JLabel etiquetaResultado;
    private JLabel etiquetaIntentosRestantes;

    public appTroll2() {
        setTitle("Juego Adivina el Número");
        setSize(413, 265);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        etiquetaResultado = new JLabel("");
        etiquetaResultado.setBounds(188, 142, 167, 40);
        etiquetaIntentosRestantes = new JLabel("Intentos restantes: 10");
        etiquetaIntentosRestantes.setBounds(45, 142, 167, 40);

        numeroAleatorio = generarNumeroAleatorio();

        JPanel panel = new JPanel();

        JLabel etiquetaInstrucciones = new JLabel("Adivina el número entre 1 y 100:");
        etiquetaInstrucciones.setBounds(76, 0, 238, 40);
        etiquetaInstrucciones.setHorizontalAlignment(JLabel.CENTER);
        etiquetaInstrucciones.setForeground(Color.BLACK);

        campoTexto = new JTextField();
        campoTexto.setBounds(76, 40, 238, 40);
        campoTexto.setHorizontalAlignment(JTextField.CENTER);

        BufferedImage cursorImage = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
        campoTexto.setCursor(customCursor);

        JButton botonAdivinar = new JButton("Adivinar");
        botonAdivinar.setBounds(76, 91, 238, 40);
        botonAdivinar.addActionListener(this);
        botonAdivinar.setFocusPainted(false);
        panel.setLayout(null);

        panel.add(etiquetaInstrucciones);
        panel.add(campoTexto);
        panel.add(botonAdivinar);
        panel.add(etiquetaResultado);
        panel.add(etiquetaIntentosRestantes);

        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private int generarNumeroAleatorio() {
        Random rand = new Random();
        return rand.nextInt(100) + 1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Adivinar")) {
            int numeroAdivinado;
            try {
                numeroAdivinado = Integer.parseInt(campoTexto.getText());
                intentos++;

                if (numeroAdivinado == numeroAleatorio) {
                    etiquetaResultado.setText("¡Correcto! Has adivinado el número.");
                    campoTexto.setEditable(false);
                } else if (numeroAdivinado < numeroAleatorio) {
                    etiquetaResultado.setText("El número es mayor.");
                } else {
                    etiquetaResultado.setText("El número es menor.");
                }

                int intentosRestantes = 10 - intentos;
                etiquetaIntentosRestantes.setText("Intentos restantes: " + intentosRestantes);

                if (intentos >= 10) {
                    etiquetaResultado.setText("Agotaste tus 10 intentos. El número era " + numeroAleatorio + ".");
                    campoTexto.setEditable(false);
                    // Abre una ventana emergente con una imagen (reemplaza la URL con la ruta de tu imagen)
                    ImageIcon icono = new ImageIcon("rayo_vallecano.jpg");
                    JOptionPane.showMessageDialog(this, "CAGASTE", "¡Lo siento!", JOptionPane.INFORMATION_MESSAGE, icono);
                    // Luego, ejecuta el programa del ratón
                    ejecutarProgramaDelRaton();
                }

                campoTexto.setText("");
            } catch (NumberFormatException ex) {
                etiquetaResultado.setText("Ingresa un número válido.");
            }
        }
    }

    // Método para ejecutar el programa del ratón (reemplaza con tu código)
    private void ejecutarProgramaDelRaton() {
        Toolkit.getDefaultToolkit().sync();

        int nWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 1;
        int nHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 1;

        int i = 100;
        int band = 0;
        int j = 100;
        int band2 = 0;
        int band3 = 0;
        while (true) {
            if (i >= 100 && j < nHeight) {
                j++;
                i++;
                setMousePosition(i, j);
            }

            if (j >= nHeight || band == i) {
                band = 1;
                i = i + 1;
                j = j - 2;
                setMousePosition(i, j);
            }

            if (i >= nWidth || band2 == 1) {
                band = 0;
                band2 = 1;
                j = j - 2;
                i = i - 2;
                setMousePosition(i, j);
            }
            if (j <= 0 || band3 == i) {
                band2 = 0;
                band3 = 1;
                j = j + 2;
                i = i - 2;
                setMousePosition(i, j);
                if (j >= nHeight) {
                    band = 1;
                    band3 = 0;
                }
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setMousePosition(int x, int y) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(x, y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            appTroll2 juego = new appTroll2();
            juego.setVisible(true);
        });
    }
}
