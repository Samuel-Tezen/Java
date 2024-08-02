/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.mapas;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import principal.Constantes;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

/**
 *
 * @author Samuel Tezen
 */
public class Mapa {  
  
    private final int ancho;
    private final int alto;
    private int[] sprites;
    private final String [] partes;
    
    public ArrayList <Rectangle> areasColision = new ArrayList<Rectangle>();
    private Rectangle zonaSalida;
    private final Sprite[] paleta;
    private final Point puntoSalida;
    private String siguienteMapa;

    
    private boolean[] colisiones;
    private final int MargenX = Constantes.ANCHO_JUEGO/ 2 - 32 /2;
    private final int MargenY = Constantes.ALTO_JUEGO/2 - 32/2;
    private final Point posicionInicial;

    
    
    
    public Mapa(final String ruta){
         String contenido = CargadorRecursos.leerArchivoTexto(ruta);
         
         partes = contenido.split("\\*");
         ancho = Integer.parseInt(partes[0]);
         alto = Integer.parseInt(partes[1]);
         
         String hojasUtilizadas = partes[2];
         String [] hojasSeparadas = hojasUtilizadas.split(",");
         
         String paletaEntera = partes[3];
         String[] partesPaleta = paletaEntera.split("\\#");
         
         //asignar aqui
         paleta = setSprites(partesPaleta, hojasSeparadas);
         //
         
         
         String colisonesEnteras = partes[4];
         String [] colisionesTexto;
         colisiones = extraerColisiones(colisonesEnteras);
         
         String spritesEnteros = partes[5];
         String[] cadenasSprites = spritesEnteros.split(" ");
         
         sprites = extraerSprites(cadenasSprites);
         
         String posicion = partes[6];
         
         String[]posiciones= posicion.split("-");
         posicionInicial = new Point();
         posicionInicial.x = Integer.parseInt(posiciones[0]) * Constantes.LADO_SSPRITE;
         posicionInicial.y = Integer.parseInt(posiciones[1]) * Constantes.LADO_SSPRITE;
         
         String salida = partes[7];
         String[]datosSalida = salida.split("-");
         puntoSalida = new Point();
         puntoSalida.x = Integer.parseInt(datosSalida[0]);
         puntoSalida.y = Integer.parseInt(datosSalida[1]);
         siguienteMapa = datosSalida[2];
         
         zonaSalida = new Rectangle();
    }
    
    private Sprite[] setSprites(final String[] partesPaleta, final String[]hojasSeparadas){
        Sprite[] paleta = new Sprite[partesPaleta.length];
        HojaSprites hoja = new HojaSprites("/res/imagenes/texturas/mapas/" + hojasSeparadas[0] + ".png", 32, true);
        
        for (int i = 0; i < partesPaleta.length; i++) {
            String spriteTemporal = partesPaleta[i];
            
            String[] partesSprite = spriteTemporal.split("-");
                    
           int indicePaleta = Integer.parseInt(partesSprite[0]);
           
           int indiceSpriteHoja = Integer.parseInt(partesSprite[2]);
           
           paleta[indicePaleta] = hoja.getSprite(indiceSpriteHoja);  
           
        }
        
        return paleta;
    } 
    public void escribirArray(String[] hojasSeparadas){
        for (int i = 0; i < hojasSeparadas.length; i++) {
            System.out.println(partes[i]);
        }
    }
    public void probarMapa(){
        System.out.println("" + ancho);
        System.out.println("" + alto);
    }
    private boolean[] extraerColisiones(final String cadenaColisiones){
        
        boolean[] colisiones = new boolean[cadenaColisiones.length()];
        for (int i = 0; i < cadenaColisiones.length(); i++) {
            if (cadenaColisiones.charAt(i) == '0') {
                colisiones[i] = false;
            }else{
                colisiones[i] = true;
            }                                   
        }
        
        return colisiones;
    }
    private int[] extraerSprites(final String[] cadenaSprites){
        ArrayList<Integer> sprites = new ArrayList<Integer>();
        
        for (int i = 0; i < cadenaSprites.length; i++) {
            if (cadenaSprites[i].length() == 2){
                sprites.add(Integer.parseInt(cadenaSprites[i]));
                
            }else{
                String uno = "";
                String dos = "";
                
                String err = cadenaSprites[i];
                
                uno += err.charAt(0);
                uno += err.charAt(1);
                
                dos += err.charAt(2);
                dos += err.charAt(3);
                
                sprites.add(Integer.parseInt(uno));
                sprites.add(Integer.parseInt(dos));
                
            }
        }
        int[] vectorSprites = new int[sprites.size()];
        for (int i = 0; i < sprites.size(); i++) {
            vectorSprites[i] = sprites.get(i);
        }
        
        return vectorSprites;
    }
    public void dibujar(final Graphics g, final int posicionX, final int posicionY){
        
        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho; x++) {
                BufferedImage imagen =paleta[sprites[x+y*this.ancho]].getImagen();
                int puntoX = x * Constantes.LADO_SSPRITE - posicionX + MargenX;
                int puntoY = y * Constantes.LADO_SSPRITE - posicionY + MargenY; 
                DibujoDebug.dibujarImagen(g, imagen, puntoX, puntoY);
                
            }
        }
    }
    public void actualizar(final int posicionX, final int posicionY){       
        actualizarAreasColision(posicionX, posicionY);
        actualizarZonaSalida(posicionX, posicionY);
        
    }
    private void actualizarZonaSalida(final int posicionX, final int posicionY){
        
          int puntoX = ((int)puntoSalida.getX()) * Constantes.LADO_SSPRITE - posicionX + MargenX;
          int puntoY = ((int)puntoSalida.getY()) * Constantes.LADO_SSPRITE - posicionY + MargenY; 
                 
          zonaSalida = new Rectangle(puntoX, puntoY, Constantes.LADO_SSPRITE, Constantes.LADO_SSPRITE);
    }
    private void actualizarAreasColision(final int posicionX, final int posicionY){
        if (!areasColision.isEmpty()) {
            areasColision.clear();
        }
        
        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho; x++) {
                int puntoX = x * Constantes.LADO_SSPRITE - posicionX + MargenX;
                int puntoY = y * Constantes.LADO_SSPRITE - posicionY + MargenY; 
                if (colisiones[x+y*this.ancho]) {
                    final Rectangle r = new Rectangle(puntoX, puntoY, Constantes.LADO_SSPRITE, Constantes.LADO_SSPRITE);
                    areasColision.add(r);
                }
 
            }
        }
        
    }
    public int getAlto(){
        return alto;
    }
    public int getAncho(){
        return ancho;
    }
    public Sprite getSritePaleta(final int indice){
        return paleta[indice];
    }
    public Sprite getSritePaleta(final int x, final int y){
        return paleta[x+y*this.ancho];
        
    }
    public Sprite[] getPaleta(){
        return paleta;
    }
    public Rectangle getBordes(final int posicionX, final int posicionY, int anchoJugador, int altoJugador){
        int x = MargenX - posicionX + anchoJugador;
        int y = MargenY - posicionY + altoJugador;
        int ancho = this.ancho*Constantes.LADO_SSPRITE - anchoJugador * 2;
        int alto = this.alto*Constantes.LADO_SSPRITE - altoJugador * 2;
        
        return new Rectangle(x,y,ancho,alto);
    }

    public Rectangle getZonaSalida() {
        return zonaSalida;
    }
    public Point getPuntoSalida() {
        return puntoSalida;
    }

    public String getSiguienteMapa() {
        return siguienteMapa;
    }
    public Point getPosicionInicial() {
        return posicionInicial;
    }
}
