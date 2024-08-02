/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.maquinaEstado;

import java.awt.Graphics;
import principal.maquinaEstado.estados.juego.GestorJuego;

/**
 *
 * @author Samuel Tezen
 */
public class GestorEstados {
    private EstadoJuego[] estados;
    private EstadoJuego estadoActual;
    
    public GestorEstados(){
        iniciarEstados();
        iniciarEstadoActual();
    }

    private void iniciarEstados() {
        estados = new EstadoJuego[1];
        estados[0] = new GestorJuego();
        //Añadir e iniciar los demas estados a medida que se crean
        
    }

    private void iniciarEstadoActual() {
        estadoActual = estados[0];
    }
    
    public void actualizar(){
        estadoActual.actualizar();
    }
    public void dibujar(final Graphics g){
        estadoActual.dibujar(g);
    }
    public void cambiarEstadoActual(final int nuevoEstado){
        estadoActual = estados[nuevoEstado];       
    }
    public EstadoJuego getEstadoActual(){
        return estadoActual;
    }
    
}
