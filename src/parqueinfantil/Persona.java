/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueinfantil;

import java.util.concurrent.BrokenBarrierException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class Persona extends Thread {
    
    private final int ID;
    private final int edad;
    private int escogerAtrac;
    private String atraccion;
    private boolean esperandoSubir,juegoOn;
    private ParqueInfantil park;

    public int getID() {
        return ID;
    }

    public int getEdad() {
        return edad;
    }
    

    public Persona(int ID, int edad, String atraccion, boolean esperandoSubir,ParqueInfantil park) {
        this.ID = ID;
        this.edad = 2+(edad%10);
        this.atraccion = atraccion;
        this.esperandoSubir = esperandoSubir;
        this.park=park;
        this.juegoOn=true;
    }

    @Override
    public void run() {
        
       while(juegoOn){ // cambiar por algun 
           try{
           try{
               sleep(200+(int)(1800*Math.random())); //pensar antes de elegir una de las atracciones  
           }catch(InterruptedException ex){}
           

               escogerAtrac= decidir();
               switch(1){//escogerAtrac
                   case 0: //columpio
                       park.entraColumpios(this);
                       park.salirColumpios(this);
                       break;
                   case 1: // tobogan
                       park.entraTobogan(this);
                       park.salirTobogan(this);
                       System.out.println("Salida del tobogan completada");
                       break;
                   case 2: // tiovivo
                       park.entraTiovivo(this); //cyclicbarrier
                       park.salirTiovivo(this);
                       break;
               }
               if(park.estaCerrado()){
               juegoOn=false;
           }
           
           
       }catch(Exception ex){//catch(BrokenBarrierException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    }

    private int decidir() {
        int i= (int)(Math.random()*3);
        return i;
    }

    
    
    
    
    
}
