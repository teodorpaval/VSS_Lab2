package repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import model.*;

public class DataManager {
	private final static String fileClient = "client.txt";
    private final static String fileIssue = "issue.txt";
    public ArrayList<Client> Clients;
    public ArrayList<Issue> Issues;
    
    public DataManager(){
        Clients = new ArrayList<Client>();
        Issues = new ArrayList<Issue>();
        
        LoadClient();
        LoadIssues();
    }
    
    private void LoadClient(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileClient)); 
            String line;
            line = br.readLine();
            while(line != null){
                List<String> lineData = Arrays.asList(line.split(","));
                String id = lineData.get(2);
                String name = lineData.get(0);
                String address = lineData.get(1);
                Clients.add(new Client(name, address, id));
                //System.out.println(id);
                line = br.readLine();
            }
            br.close();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
    private void LoadIssues(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileIssue));
            String line;
            Boolean b = true;
            String id;
            String sYear;
            String sMonth;
            String sToPay;
            String sPaid;
            line = br.readLine();
            while(line != null){
                List<String> lineData = Arrays.asList(line.split(","));
                id = lineData.get(0);
                sYear = lineData.get(1);
                sMonth = lineData.get(2);
                sToPay = lineData.get(3);
                sPaid = lineData.get(4);
                Issues.add(new Issue(id,
                        Integer.parseInt(sYear),
                        Integer.parseInt(sMonth),
                        Float.parseFloat(sToPay),
                        Float.parseFloat(sPaid)));
                System.out.println(id);
                line = br.readLine();
            }
            br.close();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public void SaveChanges(){
        try{
            File fClient = new File(fileClient);
            File fIssue = new File(fileIssue);
            FileOutputStream pwClient = new FileOutputStream(fClient, false);
            FileOutputStream pwIssue = new FileOutputStream(fIssue, false);
            String s;
            s = "";
            for(Client c : Clients) {
                s += c.toString() + System.getProperty("line.separator");
            }
            pwClient.write(s.getBytes());
            s = "";
            for(Issue iss : Issues){
                s += iss.toString() + System.getProperty("line.separator");
            }
            pwIssue.write(s.getBytes());
            pwClient.close();
            pwIssue.close();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public ArrayList<Issue> getInvoicesList()
	{
		return Issues;	
	}
    public ArrayList<Client> getClientsList()
	{
		return Clients;	
	}
}
