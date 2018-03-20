package model;

import java.util.Objects;

public class Issue {
	//public Client Client;
	public String ClientId;
    public int Year;
    public int Month;
    public float ToPay;
    public float Paid;
    
    public Issue(){
        //this.Client = new Client();
        this.ClientId = "";
        this.Year = 0;
        this.Month = 0;
        this.ToPay = 0.0f;
        this.Paid = 0.0f;
    }
    
    public Issue(Issue issue){
        //this.Client = issue.Client;
        this.ClientId = issue.ClientId;
        this.Year = issue.Year;
        this.Month = issue.Month;
        this.ToPay = issue.ToPay;
        this.Paid = issue.Paid;
    }
    
    public Issue(String clientId, int year, int month, float toPay, float paid){
        //this.Client = client;
        this.ClientId = clientId;
        this.Year = year;
        this.Month = month;
        this.ToPay = toPay;
        this.Paid = paid;
    }
    

    
    @Override 
    public boolean equals(Object object){
        if(object == null) return false;
        if(!(object instanceof Issue)) return false;
        Issue i = (Issue)object;
        if(i.ClientId == null) return false;
        if((i.Month == this.Month) && (i.Year == this.Year) && (i.ClientId == this.ClientId)) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.ClientId);
        hash = 71 * hash + this.Year;
        hash = 71 * hash + this.Month;
        hash = 71 * hash + Float.floatToIntBits(this.ToPay);
        hash = 71 * hash + Float.floatToIntBits(this.Paid);
        return hash;
    }

    @Override
    public String toString() {
        return ClientId +
                "," + Year +
                "," + Month +
                "," + ToPay +
                "," + Paid;
    }

    public String prettyPrint() {
        return "Client ID: " + ClientId +
                "; Date: " + Month + "." + Year +
                "; Amount: " + ToPay +
                "; Paid: " + Paid;
    }
}
