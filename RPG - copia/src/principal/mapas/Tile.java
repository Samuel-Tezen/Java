/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.mapas;

import java.awt.Rectangle;
import principal.sprites.Sprite;

/**
 *
 * @author Samuel Tezen
 */
public class Tile {
    private final Sprite sprite;
    private final int id;
    private boolean solido;
    
    public Tile(final Sprite sprite, final int id){
        this.sprite = sprite;
        this.id = id;
        this.solido = false;
    }
    
    public Tile(final Sprite sprite, final int id, final boolean solido){
        this.sprite = sprite;
        this.id = id;
        this.solido = solido;
    }
    
    public Sprite getSprite(){
        return sprite;
    }
    
    public int getId(){
        return id;
    }
       
    
    public void setSolido(final boolean x){
        this.solido = solido;
    }
    public Rectangle getLimites(final int x, final int y){        
        return new Rectangle(x,y, sprite.getAncho(),sprite.getAlto());
    }
    
}
