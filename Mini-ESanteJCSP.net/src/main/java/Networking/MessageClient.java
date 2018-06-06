/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking;

import com.xpertsoft.mini.esante.Model.PrescriptionDetail;
import com.xpertsoft.mini.esante.Model.Prescriptionentet;
import java.util.List;
import org.jcsp.net2.tcpip.TCPIPNodeAddress;

/**
 *
 * @author Soufiane
 */
public class MessageClient implements java.io.Serializable {
    private String Solicitation;
    private Prescriptionentet entet;
    private List<PrescriptionDetail> detail;
    private int TypeRequest;
    public static final int SOLICITATION = 0;
    public static final int PRESCRIPTION = 1;

    public MessageClient(String Solicitation, TCPIPNodeAddress adressRetour) {
        this.Solicitation = Solicitation;
        this.adressRetour = adressRetour;
        this.TypeRequest=SOLICITATION;
    }
    private TCPIPNodeAddress adressRetour;

    public TCPIPNodeAddress getAdressRetour() {
        return adressRetour;
    }

    public void setAdressRetour(TCPIPNodeAddress adressRetour) {
        this.adressRetour = adressRetour;
    }
    public MessageClient(Prescriptionentet entet, List<PrescriptionDetail> detail) {
        this.entet = entet;
        this.detail = detail;
        this.TypeRequest=PRESCRIPTION;
    }

    public int getTypeRequest() {
        return TypeRequest;
    }

    public void setTypeRequest(int TypeRequest) {
        this.TypeRequest = TypeRequest;
    }



    public String getSolicitation() {
        return Solicitation;
    }

    public void setSolicitation(String Solicitation) {
        this.Solicitation = Solicitation;
    }

    public Prescriptionentet getEntet() {
        return entet;
    }

    public void setEntet(Prescriptionentet entet) {
        this.entet = entet;
    }

    public List<PrescriptionDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<PrescriptionDetail> detail) {
        this.detail = detail;
    }
    
}
