/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.control;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;

/**
 *
 * @author Samuel Tezen
 */
public class Raton extends MouseAdapter {
    private final Cursor cursor;
    private Point posicion;
    public static Point posicionl;
    public Raton(final SuperficieDibujo sd){
        Toolkit configuracion = Toolkit.getDefaultToolkit();
        BufferedImage icono = CargadorRecursos.cargarImagenCompatibleTraslucida("/res/imagenes/texturas/iconos/pointer.png");
        
        Point punto = new Point(0,0);
        this.cursor = configuracion.createCustomCursor(icono, punto, "cursor");
        posicion = new Point();
        posicionl = new Point();
        actualizarPosicion(sd);
        
    }
    public void actualizar(final SuperficieDibujo sd){
        actualizarPosicion(sd);
    }
    public void dibujar(final Graphics g){    
    }
    public Cursor getCursor(){
        return this.cursor;
    }
    
    private void actualizarPosicion(final SuperficieDibujo sd){
        final Point posicionInicial = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(posicionInicial,sd);       
        posicion.setLocation(posicionInicial.getX(),posicionInicial.getY());
        posicionl.setLocation(posicionInicial.getX(),posicionInicial.getY());
    }
    
}
