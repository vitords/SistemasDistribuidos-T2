
package sisdist;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vitor
 */
public class ServidorAvisos extends UnicastRemoteObject implements IServidorAvisos {

    private ArrayList<IQuadroAvisos> quadros;
    
    public ServidorAvisos() throws RemoteException {
        
        quadros = new ArrayList<IQuadroAvisos>(100);
        
    }
    
    public void setAviso(String aviso) throws RemoteException {
        
        Iterator<IQuadroAvisos> it = quadros.iterator();
        
        while (it.hasNext()) {
            IQuadroAvisos quadro = it.next();
            try {
                quadro.notificar(aviso);
            } catch (RemoteException ex) {
                it.remove();
                System.out.println();
                System.out.println("Um quadro nao foi encontrado. Removendo referencia no servidor...");
                System.out.println("Quadros cadastrados: " + quadros.size());
                System.out.println("---------------------");
            }
        }
        
       /*for(IQuadroAvisos q : quadros) {
            try{
                q.notificar(aviso);
            } catch (RemoteException e) {
                quadros.remove(q);
                System.out.println();
                System.out.println("Um quadro nao foi encontrado. Removendo referencia no servidor...");
                System.out.println("Quadros cadastrados: " + quadros.size());
                System.out.println("---------------------");
            }
        }*/
        
    }
    
    public void setQuadro(IQuadroAvisos q) throws RemoteException {
        
        quadros.add(q);
        System.out.println();
        System.out.println("Novo quadro cadastrado no servidor.");
        System.out.println("Quadros cadastrados: " + quadros.size());
        System.out.println("---------------------");
        
    }
    
    public static void main(String[] args) {
        
        try {
            System.out.println();
            System.out.println("Criando RMI Registry...");
            LocateRegistry.createRegistry(1099);
            System.out.println("Iniciando servidor...");
            IServidorAvisos server = new ServidorAvisos();
            Naming.bind("rmi://localhost:1099/ServidorAvisos", server);
            System.out.println("Servidor iniciado.");
            System.out.println("---------------------");
        } catch (AlreadyBoundException ex) {
            System.out.println("Erro ao iniciar o servidor: " + ex.getMessage());
        } catch (MalformedURLException ex) {
            System.out.println("Erro ao iniciar o servidor: " + ex.getMessage());
        } catch(RemoteException ex) {
            System.out.println("Erro ao iniciar o servidor: " + ex.getMessage());
        }
        
    }
    
}
