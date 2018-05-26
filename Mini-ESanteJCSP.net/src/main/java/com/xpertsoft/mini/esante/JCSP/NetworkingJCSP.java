package com.xpertsoft.mini.esante.JCSP;

import com.xpertsoft.mini.esante.Model.PrescriptionDetail;
import com.xpertsoft.mini.esante.Model.Prescriptionentet;

import com.xpertsoft.mini.esante.gui.menu;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkingJCSP {


    private menu menu;
    public NetworkingJCSP(menu _menu) {
        menu=_menu;
    }

    public String IPAnnuaire;
    public String IPServiceDestant;
public void EndRecive(){
   
       
      
}
    public void Recive() {
     
    }
   public String GetIPUser(String Psudo) {
        
  
       return null;
    }
    public boolean Connect(String name, String pass) {
        boolean result = false;
      
        return result;
    }
   public boolean Deconnect(String name, String pass) {
        
     
      return false;
    }
    public void GetAnnuere() {
       
    }

    public void GetServiceDestant() {
       
    }

     public void SendPrescription(Prescriptionentet p,List<PrescriptionDetail> detail) {
        try {
            GetServiceDestant();
            
        } catch (Exception ex) {
            Logger.getLogger(NetworkingJCSP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
        public void SendSollicitation(String Message) {
        try {
            GetServiceDestant();
            
        } catch (Exception ex) {
            Logger.getLogger(NetworkingJCSP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
