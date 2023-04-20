
package co.edu.univalle.vista;

import java.awt.BorderLayout;
import java.awt.Container;
/*import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;*/
import java.awt.event.*;
import javax.swing.JDesktopPane;
// import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 *
 * @author Admin+
 */
public class Ventana extends JFrame {
    
    private JLabel lblTitulo;
    private JLabel lblStateBar;
    private JTextField txtDatos;
   
    private Container contenedorPpal;
    
    public Ventana(){
        iniciarComponentes();   // Declarar los controles
        setSize(350,150);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Manejo de Eventos");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    private void iniciarComponentes(){
        lblTitulo = new JLabel("Digitación : ");
        txtDatos = new JTextField(30);
        lblStateBar = new JLabel("fsdfd");
                                
        
        //btnCalcularArea.addActionListener(adminsitradorEventos);
                                
        contenedorPpal = getContentPane();        
        
        jMenuBar = new JMenuBar();
        jMenuFile = new JMenu();        
        jMenuOptions = new JMenu();
        jMenuHelp = new JMenu();
        jMenuItemSalir = new JMenuItem();
        jMenuItemAbout = new JMenuItem();
        jMenuItemEnrrollment = new JMenuItem();
        jMenuItemPerson = new JMenuItem();
        jDesktopPane = new JDesktopPane();        
                
        jMenuFile.setText("File");        
        jMenuItemSalir.setText("Salir");        
                        
        jMenuOptions.setText("Options"); 
        jMenuItemEnrrollment.setText("Enrollment");
        jMenuItemPerson.setText("Person");
        
        jMenuHelp.setText("Help");
        jMenuItemAbout.setText("About");        
            
        jMenuFile.add(jMenuItemSalir);
        jMenuOptions.add(jMenuItemEnrrollment);
        jMenuOptions.add(jMenuItemPerson);
        jMenuHelp.add(jMenuItemAbout);    
       
        jMenuBar.add(jMenuFile);
        jMenuBar.add(jMenuOptions);
        jMenuBar.add(jMenuHelp);
        
        setJMenuBar(jMenuBar);     
                
        
        contenedorPpal.add(jDesktopPane, BorderLayout.CENTER);
        
        jDesktopPane.add(lblTitulo);                                                     
        jDesktopPane.add(txtDatos); 
        
        lblTitulo.setBounds(100, 100, 70 ,30);
        txtDatos.setBounds(100, 140, 150 ,30);
        
        ManejadoraEventos manejadorEventos = new ManejadoraEventos();
        jMenuItemSalir.addActionListener(manejadorEventos);                
        jMenuItemAbout.addActionListener(manejadorEventos); 
        jMenuItemEnrrollment.addActionListener(manejadorEventos); 
        jMenuItemPerson.addActionListener(manejadorEventos);
        
        contenedorPpal.addMouseListener(new ManejadoraDeMouse());
        contenedorPpal.addMouseMotionListener(new ManejadoraDeMouseMotion());
        contenedorPpal.addMouseWheelListener(new ManejadoraDeMouseMotion());
        lblTitulo.addMouseMotionListener(new ManejadoraDeMouseMotion());
        txtDatos.addKeyListener(new ManejadorDeKeyboard());        
        
        contenedorPpal.add(lblStateBar, BorderLayout.SOUTH);
        txtDatos.requestFocusInWindow();
    }    
    
    private class ManejadoraEventos implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jMenuItemSalir){
                int response = JOptionPane.showConfirmDialog(null, 
                        "¿Realmente desea salir?", 
                        "Confirm Action", 
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION){
                    System.exit(0);
                }                
            }else if( e.getSource() == jMenuItemAbout){
                JOptionPane.showMessageDialog(null, "Interfaces Gráficas 1.0");
            }else if( e.getSource() == jMenuItemEnrrollment){
                JOptionPane.showMessageDialog(null, "Enrrollment",
                        "Information", JOptionPane.INFORMATION_MESSAGE);
            }else if( e.getSource() == jMenuItemPerson){
                JOptionPane.showMessageDialog(null, "Person",
                        "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }        
    }
    
    class ManejadoraDeMouse implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            
            lblStateBar.setText("Boton : " + e.getButton());
            
            if(e.getClickCount() == 1 && e.getButton() == 1){
                lblStateBar.setText("Se realizó click izquierdo");
            }else if(e.getClickCount() == 2 && e.getButton() == 1){
               int x = e.getX();
               int y = e.getY();            
               lblTitulo.setBounds(x, y, 70 ,30);
               lblStateBar.setText("Se realizó doble click izquierdo");
            }else{
                lblStateBar.setText("Realizó doble click derecho");
            }
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
    
    class ManejadoraDeMouseMotion implements MouseMotionListener, MouseWheelListener{

        @Override
        public void mouseDragged(MouseEvent e) {
            lblStateBar.setText("Hizo Mouse Dragged");           
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            lblStateBar.setText("X : " + e.getX() + " | Y: " + e.getY());
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            int x = lblTitulo.getX();
            int y = lblTitulo.getY();
            if(e.getPreciseWheelRotation() == 1){
                lblTitulo.setBounds(x, y + 10, 70 ,30); 
            }else {
                lblTitulo.setBounds(x, y - 10, 70 ,30);
            }
        }
    }
    
    class ManejadorDeKeyboard implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getSource() == txtDatos && e.getKeyChar() == KeyEvent.VK_ENTER){
                 lblStateBar.setText("Presionaste enter");
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            lblStateBar.setText("Presionó una tecla : " 
                    + (char)e.getKeyCode() 
                    + " | Code : " + e.getKeyCode() );
        }

        @Override
        public void keyReleased(KeyEvent e) {
            lblStateBar.setText(lblStateBar.getText() + " | Liberó una tecla");
        }
        
    }
    
    
    private JMenu jMenuFile;
    private JMenu jMenuOptions;
    private JMenu jMenuHelp;

    private JMenuBar jMenuBar;
    private JMenuItem jMenuItemSalir;
    private JMenuItem jMenuItemAbout;
    private JMenuItem jMenuItemEnrrollment;
    private JMenuItem jMenuItemPerson;
    private JDesktopPane jDesktopPane;
 }

