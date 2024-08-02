/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import java.awt.Font;
import principal.herramientas.CargadorRecursos;

/**
 *
 * @author Samuel Tezen
 */
public class Constantes {
    public static final int LADO_SSPRITE = 32;
    public static final int LADO_TILE = 32;
    
    public static int ANCHO_JUEGO = 683;
    public static int ALTO_JUEGO = 384;
    
    public static final int ANCHO_PANTALLA_COMPLETA = 1366;
    public static final int ALTO_PANTALLA_COMPLETA = 768;
    
    public static double FACTOR_ESCALADO_X = (double)ANCHO_PANTALLA_COMPLETA/ (double)ANCHO_JUEGO;
    public static double FACTOR_ESCALADO_Y = (double) ALTO_PANTALLA_COMPLETA /(double) ALTO_JUEGO;
    
    public static int CENTROX = ANCHO_JUEGO /2;
    public static int CENTROY = ALTO_JUEGO /2;
    
    public static int APS = 0;
    public static int FPS = 0;
    
    public static Font fuentePixeles = CargadorRecursos.cargarFuente("/res/fuentes/px10.ttf");
}
