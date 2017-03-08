package anellcomunicacions_isidro;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*@author Isidro*/

public class Programa extends Thread {
    
    int num;
    int port;
    boolean ultim;

    public Programa(ThreadGroup elsProgrames, int num, boolean ultim, int port) {
        super(elsProgrames, "");
        this.num = num;
        this.ultim = ultim;
        this.port = port+num;
    }

    @Override
    public void run() {
        
        System.out.println("Comença el programa "+num);
        
        if (num == 1) {
            
            try {
                
            //ENVIAR DATOS
            
            System.out.println("1: Vaig a enviar un missatge al segón programa.");
            
            DatagramSocket socket = new DatagramSocket ();
            
            String msg = "HOLA";
            InetAddress addr = InetAddress.getByName("localhost");
            
            System.out.println("1: Creant el datagrama del missatge.");
            DatagramPacket datagram = new DatagramPacket (
                msg.getBytes(), //Dades
                msg.getBytes().length, //Tamany
                addr, //IP del servidor
                port+1); //Port del servidor
            sleep(1000);
            System.out.println("1: Enviant missatge al segón programa.");
            socket.send(datagram);
            
            socket.close();
            System.out.println("1: el missatge s'ha enviat correctament, ara esperaré una resposta.");
            
            //RECIBIR DATOS
            
            InetSocketAddress addrS = new InetSocketAddress("localhost", port);
        
            DatagramSocket datagramSocket = new DatagramSocket(addrS);
            
            byte [] mensaje = new byte[25];
            
            DatagramPacket datagrama = new DatagramPacket (mensaje, mensaje.length);
            
            datagramSocket.receive(datagrama);
            System.out.println("1: Missatge rebut: "+ new String(mensaje));
            
            datagramSocket.close();
            
        
            }
            //Error en la creación del socket
            catch (SocketException ex) {
                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
            } 
            //Error al acceder al servidor
            catch (UnknownHostException ex) {
                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
            } 
            //Error al transmitir los datos
            catch (IOException ex) {
                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else {
            
            try {

                //RECIBIR DATOS
                System.out.println(num+": Estic a la espera de rebre un missatge.");
                
                InetSocketAddress addrS = new InetSocketAddress("localhost", port);
        
                DatagramSocket datagramSocket = new DatagramSocket(addrS);

                byte [] mensaje = new byte[25];

                DatagramPacket datagrama = new DatagramPacket (mensaje, mensaje.length);

                System.out.println(num+": esperant connexions.");
                datagramSocket.receive(datagrama);
                String msg = new String(mensaje);
                System.out.println(num+": Missatge rebut: "+ msg);

                datagramSocket.close();
                

                 //ENVIAR DATOS
                
                int portServidor;
                if (ultim) {
                    portServidor = 5001;
                }
                else {
                    portServidor = port+1;
                }
                
                System.out.println(num+": Vaig a enviar un missatge.");

                DatagramSocket socket = new DatagramSocket ();
                
                InetAddress addr = InetAddress.getByName("localhost");

                System.out.println(num+": Creant el datagrama del missatge.");
                DatagramPacket datagram = new DatagramPacket (
                    msg.getBytes(), //Dades
                    msg.getBytes().length, //Tamany
                    addr, //IP del servidor
                    portServidor); //Port del servidor

                sleep(1000);
                System.out.println(num+": Enviant el missatge.");
                socket.send(datagram);

                socket.close();
                System.out.println(num+": el missatge s'ha enviat correctament.");
                
            }
            //Error en la creación del socket
            catch (SocketException ex) {
                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
            } 
            //Error al acceder al servidor
            catch (UnknownHostException ex) {
                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
            } 
            //Error al transmitir los datos
            catch (IOException ex) {
                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
            }
        } //end if
        
        System.out.println(num+": He acabat i vaig a tancarme.");
        
    }
    
}
