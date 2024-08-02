
package principal.maquinaEstado.estados.juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.control.GestorControles;
import principal.control.Raton;
import principal.entes.Jugador;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;
import principal.interfaz_usuario.InterfazUsuario;
import principal.mapas.Mapa;
import principal.maquinaEstado.EstadoJuego;
import principal.sprites.HojaSprites;

/**
 *
 * @author Samuel Tezen
 */
public class GestorJuego implements EstadoJuego{
    private GestorMapa gm;
    
    Mapa mapa;
    Jugador jugador;
    public GestorJuego(){
        iniciarMapa("/res/texto/2.txt");
        iniciarJugador();
    }
    private void  recargarJuego(){
        final String ruta = "/res/texto/"+mapa.getSiguienteMapa();
        iniciarMapa(ruta);
        jugador.setMapa(mapa);
        jugador.setPosicionX(mapa.getPosicionInicial().getX());
        jugador.setPosicionY(mapa.getPosicionInicial().getY());
        
    }
    private void iniciarMapa(final String ruta){
        mapa = new Mapa(ruta);
    }
    private void iniciarJugador(){
        jugador = new Jugador(mapa);
    }
    public void actualizar() {
        if (jugador.getLIMITE_ARRIBA().intersects(mapa.getZonaSalida())) {
            recargarJuego();
            
        }
        jugador.actualizar();
        mapa.actualizar((int)jugador.getPosicionX(), (int)jugador.getPosicionY());
       
    }
    
    
    public void dibujar(Graphics g) {
        mapa.dibujar(g, (int)jugador.getPosicionX(), (int)jugador.getPosicionY());
        jugador.dibujar(g);               
                              
        DibujoDebug.dibujarString(g, "X: " + jugador.getPosicionX(), 10, 10, Color.white);
        DibujoDebug.dibujarString(g, "Y: " + jugador.getPosicionY(), 10, 30,Color.white);
//        g.fillRect((int)mapa.getZonaSalida().getX(), (int)mapa.getZonaSalida().getY(), (int)mapa.getZonaSalida().getWidth(), (int)mapa.getZonaSalida().getHeight());
        
        DibujoDebug.dibujarString(g, "FPS: " + Constantes.FPS, 60, 10, Color.white);
        
        DibujoDebug.dibujarString(g, "APS: " + Constantes.APS, 60, 30, Color.white);
        
        InterfazUsuario.dibujarBarraResistencia(g, jugador.resistencia);
        DibujoDebug.dibujarString(g, "RatonX: "+Raton.posicionl.x, 10, 80, Color.white);
        DibujoDebug.dibujarString(g, "RatonY: "+Raton.posicionl.y, 10, 70, Color.white);
         
        
        
    }
    
    
}
