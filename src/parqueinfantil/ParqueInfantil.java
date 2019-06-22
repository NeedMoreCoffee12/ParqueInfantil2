/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueinfantil;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import javax.swing.JTextField;

/**
 *
 * @author david
 */
public class ParqueInfantil {
    private Semaphore tobogan;
    private Semaphore columpios;
    private ListaThreads cola_col;//Colas de los columpios
    private ListaThreads en_tobogan;
    private CyclicBarrier tioVivo;
    private ListaThreads cola_tio;
    private ListaThreads cola_tobogan;
    private JTextField ColaTobogan;
    private JTextField ColaColumpios;
    private JTextField ColaTiovivo;
    private JTextField EnTobogan;
    private JTextField EnColumpios;
    private JTextField EnTiovivo;
    private boolean detenerTobogan;
    private boolean salir;
    private float tiem_escaleras_tob;
    private float tiem_tobogan;

    public ParqueInfantil(JTextField ColaTobogan,JTextField ColaColumpios,JTextField ColaTiovivo,JTextField EnTobogan,JTextField EnColumpios,JTextField EnTiovivo) {
    
    this.ColaTobogan=ColaTobogan;
    this.tobogan= new Semaphore(1,true);
    this.columpios= new Semaphore(3,true);
    this.tioVivo=new CyclicBarrier(5);
    cola_col = new ListaThreads(ColaColumpios);
    cola_tobogan = new ListaThreads(ColaTobogan);
    en_tobogan= new ListaThreads(EnTobogan);
    this.detenerTobogan=false;
    this.salir=false;
    this.tiem_escaleras_tob=(float) 1.2;
    this.tiem_tobogan=(float) 0.5;
    this.EnTobogan= EnTobogan;
    this.EnColumpios=EnColumpios;
    this.EnTiovivo=EnTiovivo;
}

    public boolean estaCerrado() {
        return false;        
    }

    public void entraColumpios(Persona aThis) {
        //try{
        
        //} catch(InterruptedException ex){
            System.out.println("Error entrar columpios");
        //}
    }

    void salirColumpios(Persona aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void entraTobogan(Persona aThis) {
        try{
        //monitorTobogan();
        cola_tobogan.meter(aThis); // Tambien modifica el jText adscrito a la ListaThread
        tobogan.acquire(); 
        cola_tobogan.sacar(aThis);
        realizar_actividad(tiem_escaleras_tob); //tiempo de espera escalera
        if(!salir){
            //monitorTobogan();
           
            System.out.println("Bajando el tobogan");
            System.out.println("ID: "+aThis.getID()+" edad: "+aThis.getEdad());
            EnTobogan.setText(Integer.toString(aThis.getID())); //He tenido que darle "generate method getID" cuando ya existia
            realizar_actividad(tiem_tobogan);//Espera
        }
        
        
        } catch(InterruptedException ex){
            System.out.println("Error entrar tobogan");
        }
        //El semaforo de uno, se vuelve a abrir en el metodo salirCarniceria que usan los hilos Persona
    }
    
        public void realizar_actividad(float t){
        try {
            t=t*1000;
            Thread.sleep((int)t);
        } catch (InterruptedException ex) {
        }
   }
    public synchronized void monitorTobogan(){
        while(detenerTobogan){
            try{
            wait();
        } catch (InterruptedException ex){
            
        }
    }
    }

    void salirTobogan(Persona aThis) {
        tobogan.release();
    }

    void entraTiovivo(Persona aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void salirTiovivo(Persona aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
}
