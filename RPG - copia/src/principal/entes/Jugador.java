/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.control.GestorControles;
import principal.herramientas.DibujoDebug;
import principal.mapas.Mapa;
import principal.sprites.HojaSprites;

/**
 *
 * @author Samuel Tezen
 */
public class Jugador {

    private double posicionX;
    private double posicionY;
    private boolean enMovimiento;
    private double velocidad = 2;
    private int direccion;
    private final HojaSprites hs;
    private BufferedImage imagen;
    private final int ANCHO_JUGADOR = 21;
    private final int ALTO_JUGADOR = 16;
    private final Rectangle LIMITE_ARRIBA = new Rectangle(Constantes.CENTROX + 6, Constantes.CENTROY + 30, ANCHO_JUGADOR, 1);
    private final Rectangle LIMITE_ABAJO = new Rectangle(Constantes.CENTROX + 4, Constantes.CENTROY + 45, ANCHO_JUGADOR, 1);
    private final Rectangle LIMITE_IZQUIERDA = new Rectangle(Constantes.CENTROX + 4, Constantes.CENTROY + 30, 1, 16);
    private final Rectangle LIMITE_DERECHA = new Rectangle(Constantes.CENTROX + 26, Constantes.CENTROY + 30, 1, 16);

    private int animacion;
    private int estado;
    public int resistencia = 600;
    private int recuperacion = 0;
    private final int recuperacionM = 200;
    private boolean recuperado = true;

    private Mapa mapa;

    public Jugador(Mapa mapa) {

        this.posicionX = mapa.getPosicionInicial().getX();
        this.posicionY = mapa.getPosicionInicial().getY();
        

        enMovimiento = false;
        direccion = 0;
        hs = new HojaSprites("/res/imagenes/texturas/personajes/bob.png", 64, false);

        imagen = hs.getSprite(0).getImagen();

        animacion = 0;
        estado = 0;
        this.mapa = mapa;
    }

    public void actualizar() {
        cambiarAnimacionEstado();
        enMovimiento = false;
        determinaDireccion();
        animar();
        correr();
    }

    public void dibujar(Graphics g) {
        final int centroX = Constantes.ANCHO_JUEGO / 2 - Constantes.LADO_SSPRITE / 2;
        final int centroY = Constantes.ALTO_JUEGO / 2 - Constantes.LADO_SSPRITE / 2;
        
        DibujoDebug.dibujarImagen(g, imagen, centroX, centroY);
        DibujoDebug.dibujarString(g, "Resistencia: " + resistencia ,200, 10, Color.blue);
//        g.drawRect(LIMITE_ARRIBA.x, LIMITE_ARRIBA.y,LIMITE_ARRIBA.width, LIMITE_ARRIBA.height );
//        g.drawRect(LIMITE_ABAJO.x, LIMITE_ABAJO.y,LIMITE_ABAJO.width, LIMITE_ABAJO.height );
//        g.drawRect(LIMITE_IZQUIERDA.x, LIMITE_IZQUIERDA.y,LIMITE_IZQUIERDA.width, LIMITE_IZQUIERDA.height );
//        g.drawRect(LIMITE_DERECHA.x, LIMITE_DERECHA.y,LIMITE_DERECHA.width, LIMITE_DERECHA.height );
//        

    }

    private void correr() {
        if (GestorControles.teclado.shift.isPulsada() && resistencia > 0) {
            velocidad = 5;
            recuperado = false;
            recuperacion = 0;

        } else {

            velocidad = 2;
            if (!recuperado && recuperacion < recuperacionM) {
                recuperacion++;
            }
            if (recuperacion == recuperacionM && resistencia < 600) {
                resistencia++;

            }
            if (resistencia <= 0) {
                resistencia = 0;
            }

        }

    }

    private void animar() {
        if (!enMovimiento) {
            estado = 0;
            animacion = 0;

        }
        imagen = hs.getSprite(estado, direccion).getImagen();
    }

    private void cambiarAnimacionEstado() {
        if (animacion < 70) {
            animacion++;
        } else {
            animacion = 0;
        }
        if (animacion < 10) {
            estado = 1;
        } else if (animacion < 20) {
            estado = 2;
        } else if (animacion < 30) {
            estado = 3;
        } else if (animacion < 40) {
            estado = 4;
        } else if (animacion < 50) {
            estado = 5;
        } else if (animacion < 60) {
            estado = 6;
        } else {
            estado = 7;
        }

    }

    private void determinaDireccion() {
        final int velocidadX = evaluarVelocidadX();
        final int velocidadY = evaluarVelocidadY();

        if (velocidadX == 0 && velocidadY == 0) {
            return;
        }
        if ((velocidadX != 0 && velocidadY == 0) || (velocidadX == 0 && velocidadY != 0)) {
            mover(velocidadX, velocidadY);
        } else {
            //izquierda y arriba
            if (velocidadX == -1 && velocidadY == -1) {
                if (GestorControles.teclado.izquierda.getUltimaPulsacion() > GestorControles.teclado.arriba.getUltimaPulsacion()) {
                    mover(velocidadX, 0);

                } else {
                    mover(0, velocidadY);
                }
            }
            //izquierda y abajo
            if (velocidadX == -1 && velocidadY == 1) {
                if (GestorControles.teclado.izquierda.getUltimaPulsacion() > GestorControles.teclado.abajo.getUltimaPulsacion()) {
                    mover(velocidadX, 0);

                } else {
                    mover(0, velocidadY);
                }
            }
            //derecha y arriba
            if (velocidadX == 1 && velocidadY == -1) {
                if (GestorControles.teclado.derecha.getUltimaPulsacion() > GestorControles.teclado.arriba.getUltimaPulsacion()) {
                    mover(velocidadX, 0);

                } else {
                    mover(0, velocidadY);
                }
            }
            //derecha y abajo
            if (velocidadX == 1 && velocidadY == 1) {
                if (GestorControles.teclado.derecha.getUltimaPulsacion() > GestorControles.teclado.abajo.getUltimaPulsacion()) {
                    mover(velocidadX, 0);

                } else {
                    mover(0, velocidadY);
                }
            }
        }
    }

    private int evaluarVelocidadX() {
        int velocidadX = 0;
        if (GestorControles.teclado.izquierda.isPulsada() && !GestorControles.teclado.derecha.isPulsada()) {
            velocidadX = -1;
        } else if (!GestorControles.teclado.izquierda.isPulsada() && GestorControles.teclado.derecha.isPulsada()) {
            velocidadX = 1;
        }
        return velocidadX;
    }

    private int evaluarVelocidadY() {
        int velocidadY = 0;
        if (GestorControles.teclado.arriba.isPulsada() && !GestorControles.teclado.abajo.isPulsada()) {
            velocidadY = -1;
        } else if (!GestorControles.teclado.arriba.isPulsada() && GestorControles.teclado.abajo.isPulsada()) {
            velocidadY = 1;
        }
        return velocidadY;

    }

    private void mover(int velocidadX, int velocidadY) {
        enMovimiento = true;
        cambiarDireccion(velocidadX, velocidadY);
        if (!fueraMapa(velocidadX, velocidadY)) {
            if (velocidadX == -1 && !colisionIzquierda(velocidadX)) {
                posicionX += velocidadX * velocidad;
                restarResistencia();
                return;
            }
            if (velocidadX == 1 && !colisionDerecha(velocidadX)) {
                posicionX += velocidadX * velocidad;
                restarResistencia();
                return;
            }
            if (velocidadY == 1 && !colisionAbajo(velocidadY)) {
                posicionY += velocidadY * velocidad;
                restarResistencia();
                return;
            }
            if (velocidadY == -1 && !colisionArriba(velocidadY)) {
                posicionY += velocidadY * velocidad;
                restarResistencia();

                return;
            }

        }

    }

    private void restarResistencia() {
        if (GestorControles.teclado.shift.isPulsada()) {
            resistencia--;
        }
    }

    private boolean colisionArriba(int velocidadY) {
        for (int r = 0; r < mapa.areasColision.size(); r++) {
            final Rectangle area = mapa.areasColision.get(r);

            int origenX = area.x;
            int origenY = area.y + velocidadY * (int) velocidad + 3 * (int) velocidad;
            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SSPRITE, Constantes.LADO_SSPRITE);
            if (LIMITE_ARRIBA.intersects(areaFutura)) {
                return true;
            }
        }

        return false;
    }

    private boolean colisionAbajo(int velocidadY) {
        for (int r = 0; r < mapa.areasColision.size(); r++) {
            final Rectangle area = mapa.areasColision.get(r);

            int origenX = area.x;
            int origenY = area.y + velocidadY * (int) velocidad - 3 * (int) velocidad;
            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SSPRITE, Constantes.LADO_SSPRITE);
            if (LIMITE_ABAJO.intersects(areaFutura)) {
                return true;
            }
        }

        return false;
    }

    private boolean colisionIzquierda(int velocidadX) {
        for (int r = 0; r < mapa.areasColision.size(); r++) {
            final Rectangle area = mapa.areasColision.get(r);

            int origenX = area.x + velocidadX * (int) velocidad + 3 * (int) velocidad;
            int origenY = area.y;
            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SSPRITE, Constantes.LADO_SSPRITE);
            if (LIMITE_IZQUIERDA.intersects(areaFutura)) {
                return true;
            }
        }

        return false;
    }

    private boolean colisionDerecha(int velocidadX) {
        for (int r = 0; r < mapa.areasColision.size(); r++) {
            final Rectangle area = mapa.areasColision.get(r);

            int origenX = area.x + velocidadX * (int) velocidad - 3 * (int) velocidad;
            int origenY = area.y;
            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SSPRITE, Constantes.LADO_SSPRITE);
            if (LIMITE_DERECHA.intersects(areaFutura)) {
                return true;
            }
        }

        return false;
    }

    private boolean fueraMapa(final int velocidadX, final int velocidadY) {

        int posicionFuturaX = (int) posicionX + velocidadX * (int) velocidad;
        int posicionFuturaY = (int) posicionY + velocidadY * (int) velocidad;

        final Rectangle bordesMapa = mapa.getBordes(posicionFuturaX, posicionFuturaY, ANCHO_JUGADOR, ALTO_JUGADOR);

        final boolean fuera;

        if (LIMITE_ARRIBA.intersects(bordesMapa)
                || LIMITE_ABAJO.intersects(bordesMapa)
                || LIMITE_IZQUIERDA.intersects(bordesMapa)
                || LIMITE_DERECHA.intersects(bordesMapa)) {

            fuera = false;
        } else {
            fuera = true;
        }
        return fuera;
    }

    private void cambiarDireccion(final int velocidadX, final int velocidadY) {
        if (velocidadX == 1) {
            direccion = 3;

        } else if (velocidadX == -1) {
            direccion = 1;
        }

        if (velocidadY == 1) {
            direccion = 2;
        } else if (velocidadY == -1) {
            direccion = 0;
        }
    }

    public void setPosicionX(double posicionX) {
        this.posicionX = posicionX;
    }

    public void setPosicionY(double posicionY) {
        this.posicionY = posicionY;
    }

    public double getPosicionX() {
        return posicionX;
    }

    public double getPosicionY() {
        return posicionY;
    }

    public Rectangle getLIMITE_ARRIBA() {
        return LIMITE_ARRIBA;
    }

    public Rectangle getLIMITE_ABAJO() {
        return LIMITE_ABAJO;
    }

    public Rectangle getLIMITE_IZQUIERDA() {
        return LIMITE_IZQUIERDA;
    }

    public Rectangle getLIMITE_DERECHA() {
        return LIMITE_DERECHA;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }
    
    
}
