
package sisdist;

/**
 *
 * @author Vitor
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IQuadroAvisos extends Remote {

    void notificar(String aviso) throws RemoteException;
    
}
