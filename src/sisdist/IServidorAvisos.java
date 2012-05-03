
package sisdist;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Vitor
 */
public interface IServidorAvisos extends Remote {
    
    public void setAviso(String aviso) throws RemoteException;
    public void setQuadro(IQuadroAvisos quadro) throws RemoteException;
    
}