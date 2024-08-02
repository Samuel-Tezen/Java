/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import principal.control.GestorControles;
import principal.graficos.SuperficieDibujo;
import principal.graficos.Ventana;
import principal.maquinaEstado.GestorEstados;

/**
 *
 * @author Samuel Tezen
 */
public class GestorPrincipal {
    private boolean enFuncionamiento = false;
    private final String titulo;
    private int ancho, alto;
    private SuperficieDibujo sd;
    private Ventana ventana;
    private GestorEstados ge;
    
    private GestorPrincipal(final String titulo, final int ancho, final int alto){
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
        
    }
    
    public static void main (String args[]){
        
        GestorPrincipal gp = new GestorPrincipal("Juego", Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);
       
        gp.iniciarJuego();
        gp.buclePrincipal();
    }

    private void iniciarJuego() {
        enFuncionamiento = true;
        inicializar();
        
    }

    private void buclePrincipal() {
        int aps = 0;
        int fps = 0;
        
        final int NS_POR_SEGUNDO = 1000000000;
        final byte APS_OBJETIVO = 60 ;
        
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();
        double tiempoTranscurrido;
        double delta = 0;

        

        while (enFuncionamiento) {
            
            final long inicioBucle = System.nanoTime();
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

            while (delta >= 1) {
                this.actualizar();
                aps++;
                
                
                delta--;
                
            }
                this.mostrar();
                fps++;
                
            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
//            APS = "APS:" + aps;
                Constantes.FPS = fps;
                Constantes.APS = aps;
//            FPS = "FPS:" + fps;                                 
                aps = 0;
                
                fps = 0;
                
                referenciaContador = System.nanoTime();
            }
        }

    }
    

    private void inicializar() {
        sd = new SuperficieDibujo(ancho, alto);
        ventana = new Ventana(titulo, sd);
        ge = new GestorEstados();
        
    }
    private void actualizar(){
 
        
        ge.actualizar();
        sd.actualizar();
        
    }
    private void mostrar(){
        sd.dibujar(ge);

    }
}
