package service.core;

import java.util.ArrayList;

public class ClientApplication {
    private int id;
    private ClientInfo info;
    private ArrayList<Quotation> quotations = new ArrayList<>();

    public ClientApplication(int id, ClientInfo info, ArrayList<Quotation> quotations) {
        this.id = id;
        this.info = info;
        this.quotations = quotations;
    }

    public ClientApplication() {}


    public int getId() {
        return id;
    }

    public void setId(int applicationID) {
        this.id = applicationID;
    }

    public ClientInfo getInfo() {
        return info;
    }

    public void setInfo(ClientInfo info) {
        this.info = info;
    }

    public ArrayList<Quotation> getQuotations() {
        return quotations;
    }

    public void setQuotations(ArrayList<Quotation> quotations) {
        this.quotations = quotations;
    }
}
