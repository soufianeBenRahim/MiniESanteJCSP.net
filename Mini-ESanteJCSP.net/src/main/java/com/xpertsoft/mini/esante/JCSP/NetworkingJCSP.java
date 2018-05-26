package com.xpertsoft.mini.esante.JCSP;

import com.xpertsoft.mini.esante.Model.PrescriptionDetail;
import com.xpertsoft.mini.esante.Model.Prescriptionentet;

import com.xpertsoft.mini.esante.gui.menu;


import java.util.List;


public class NetworkingJCSP {


    private menu menu;
    public NetworkingJCSP(menu _menu) {
        menu=_menu;
    }

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

    public void SendPrescription(Prescriptionentet p,List<PrescriptionDetail> detail) {
      
    }
     
    public void SendSollicitation(String Message) {
     
    }

}
