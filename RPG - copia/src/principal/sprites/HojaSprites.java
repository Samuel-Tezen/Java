/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.sprites;

import java.awt.image.BufferedImage;
import principal.herramientas.CargadorRecursos;

/**
 *
 * @author Samuel Tezen
 */
public class HojaSprites {
    final private int anchoHojaEnPixeles;
    private final int altoHojaEnPixeles;
    
    private final int anchoHojaEnSprites;
    private final int altoHojaEnSprites;
    
    private final int anchoSprites;
    private final int altoSprites;
    
    private final Sprite[] sprites;
    public HojaSprites(final String ruta, final int tamañoSprites, final boolean opacidad){
        final BufferedImage imagen;
        
        if (opacidad) {
          imagen = CargadorRecursos.cargarImagenCompatibleOpaca(ruta);
        }else{
          imagen = CargadorRecursos.cargarImagenCompatibleTraslucida(ruta);
        }
        anchoHojaEnPixeles = imagen.getWidth();
        altoHojaEnPixeles = imagen.getHeight();
        
        anchoHojaEnSprites =anchoHojaEnPixeles/tamañoSprites;
        altoHojaEnSprites = altoHojaEnPixeles/tamañoSprites;
        anchoSprites = tamañoSprites;
        altoSprites = tamañoSprites;
        
        sprites = new Sprite[anchoHojaEnSprites * altoHojaEnSprites];
                extraerSprite(imagen);
        
    }
        public HojaSprites(final String ruta, final int ancho, final int alto, final boolean opacidad){
        final BufferedImage imagen;
        
        if (opacidad) {
            imagen = CargadorRecursos.cargarImagenCompatibleOpaca(ruta);
        }else{
          imagen = CargadorRecursos.cargarImagenCompatibleTraslucida(ruta);
        }
        anchoHojaEnPixeles = imagen.getWidth();
        altoHojaEnPixeles = imagen.getHeight();
        
        anchoHojaEnSprites =anchoHojaEnPixeles/ancho;
        altoHojaEnSprites = altoHojaEnPixeles/alto;
        anchoSprites = ancho;
        altoSprites = alto;
        
        sprites = new Sprite[anchoHojaEnSprites * altoHojaEnSprites];
        extraerSprite(imagen);
    }
        
        private void extraerSprite(final BufferedImage imagen){
            for (int y = 0; y < altoHojaEnSprites; y++) {
                for (int x = 0; x < anchoHojaEnSprites; x++) {
                    final int posicionX = x*anchoSprites;
                    final int posicionY = y*altoSprites;
                    sprites[x+y*anchoHojaEnSprites] = new Sprite(imagen.getSubimage(posicionX, posicionY, anchoSprites, altoSprites));
                    
                    
                }
            }
        }
        
        public Sprite getSprite(final int indice){
            return sprites[indice];
            
        }
        public Sprite getSprite(final int x, final int y){
            return sprites[x+y*anchoHojaEnSprites];
        }
        
}
