/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.control;

/**
 *
 * @author Samuel Tezen
 */
public class Tecla {
    private boolean pulsada = false;

    public boolean isPulsada() {
        return pulsada;
    }

    public long getUltimaPulsacion() {
        return ultimaPulsacion;
    }
    private long ultimaPulsacion = System.nanoTime();
    
    public void teclaPulsada(){
        pulsada = true;
    }
    public void teclaLiberada(){
        pulsada = false;
    }
    
}
