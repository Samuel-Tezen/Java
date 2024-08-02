
package principal.graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import principal.Constantes;
import principal.control.GestorControles;
import principal.control.Raton;
import principal.control.Teclado;
import principal.herramientas.DibujoDebug;
import principal.maquinaEstado.GestorEstados;

/**
 *
 * @author Samuel Tezen
 */
public class SuperficieDibujo extends Canvas{
    private int ancho;
    private int alto;
    private static int posicionRaton;
    private Raton raton;
    
    public SuperficieDibujo(final int ancho, final int alto){
     this.ancho  = ancho;
     this.alto = alto;
     addKeyListener(GestorControles.teclado);
     this.raton = new Raton(this);
         
     setIgnoreRepaint(true);
     setCursor(raton.getCursor());
     setPreferredSize(new Dimension (ancho, alto));     
     setFocusable(true);
     requestFocus();
     
 }
    public void actualizar(){
        raton.actualizar(this);
       
    }
    public void dibujar(final GestorEstados ge){
        BufferStrategy buffer = getBufferStrategy();
        if (buffer == null) {
            createBufferStrategy(3);
            return;
        }
        
        Graphics2D g = (Graphics2D)buffer.getDrawGraphics();
        DibujoDebug.reiniciar();
        
        g.setFont(Constantes.fuentePixeles);
        
        DibujoDebug.dibujarRectanguloRelleno(g, 0, 0, Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA, Color.black);
        
        if (Constantes.FACTOR_ESCALADO_X != 1.0 ||Constantes.FACTOR_ESCALADO_Y != 1.0) {
             g.scale(Constantes.FACTOR_ESCALADO_X, Constantes.FACTOR_ESCALADO_Y);
        }
       raton.dibujar(g);
        ge.dibujar(g);
        DibujoDebug.dibujarString(g, "OPF: " + DibujoDebug.getObjetosDibujados(), 10, 90);
        
        Toolkit.getDefaultToolkit().sync();        
        g.dispose();
        buffer.show();
        
    }
   
    
    public int getAncho(){
        return ancho;
    }
    public int getAlto(){
        return alto;
    }
   
}
