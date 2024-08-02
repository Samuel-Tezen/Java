/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.interfaz_usuario;

import java.awt.Color;
import java.awt.Graphics;
import principal.herramientas.DibujoDebug;

/**
 *
 * @author Samuel Tezen
 */
public class InterfazUsuario {
    public static void dibujarBarraResistencia(Graphics g, int resistencia){
        int ancho = 100*resistencia/600;
        Color azul = new Color(50, 121, 168);
//        g.setColor(Color.white);
//        g.drawRect(200, 15, 102, 22);
        DibujoDebug.dibujarRectangulo(g, 200, 15, 102, 22, Color.white);
//        g.setColor(azul);
//        g.fillRect(201, 16, ancho, 20);
        DibujoDebug.dibujarRectanguloRelleno(g, 201, 16, ancho, 20, azul);
    }
}
