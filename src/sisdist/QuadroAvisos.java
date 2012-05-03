
package sisdist;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Vitor
 */
public class QuadroAvisos extends UnicastRemoteObject implements IQuadroAvisos {
    
    private static IServidorAvisos servidor;
    private static QuadroGUI janela;
    
    public QuadroAvisos() throws RemoteException {
        
    }
    
    public void notificar(String aviso) throws RemoteException {
        janela.mostrarAviso(aviso);
    }
    
    public void enviarAviso(String aviso) throws RemoteException {
        servidor.setAviso(aviso);
    }
        
    public static void main(String[] args) {

        try {
            servidor = (IServidorAvisos) Naming.lookup("rmi://localhost:1099/ServidorAvisos");
            IQuadroAvisos q = new QuadroAvisos();
            servidor.setQuadro(q);
            janela = new QuadroGUI((QuadroAvisos) q);
            janela.setVisible(true);
            
        } catch( MalformedURLException e ) {  
            System.out.println();  
            System.out.println( "MalformedURLException: " + e.toString() );  
        } catch( RemoteException e ) {  
            System.out.println();  
            System.out.println( "RemoteException: " + e.toString() );  
        } catch( NotBoundException e ) {  
            System.out.println();  
            System.out.println( "NotBoundException: " + e.toString() );  
        } catch( Exception e ) {  
            System.out.println();  
            System.out.println( "Exception: " + e.toString() );  
        } 
    }
}
