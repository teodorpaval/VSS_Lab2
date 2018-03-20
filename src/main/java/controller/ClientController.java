package controller;

import java.util.ArrayList;
import java.util.Objects;

import repository.DataManager;
import model.*;

public class ClientController {
	private DataManager _dataManager;
    
    public ClientController(){
        _dataManager = new DataManager();
    }
    
    private String ValidateClient(String name, String address, String id){
        if(!name.equals("") && !address.equals("") && !name.equals(" ")){
            for(int i=0;i<name.length();i++){
                if((!Character.isUpperCase(name.charAt(i))) && (!Character.isLowerCase(name.charAt(i))) && (!Character.isSpaceChar(name.charAt(i)))){
                    return "Invalid character: " + name.charAt(i);
                }
            }
            return null;
        }else{
            return "Name or address cannot be empty!";
        }
    }
    
    public String AddClient(String name, String address, String id){
        //validation
        String valid;
        if((valid = ValidateClient(name, address,id)) != null){
            return valid;
        }
        Client c = new Client(name, address,id);
        //uniqueness
        for(int j=0; j<_dataManager.Clients.size(); j++){
            if(_dataManager.Clients.get(j).idClient == id){
                return "ClientID not unique!";
            }
        }
        try{
            _dataManager.Clients.add(c);
            _dataManager.SaveChanges();
            return "Client Added Successfully: " + c.toString();
        }catch(Exception ex){
            return ex.getMessage();
        }
    }
    
    public String AddClientIndex(String idClient, int year, int month, float toPay){
        if(year > 0){
            if(month > 0 && month <= 12){
                if(toPay >= 0){
                    //validate client attributes
                        //check if client exist
                        Boolean exist = false;
                        for(int i=0; i<_dataManager.Clients.size(); i++){
                            if(Objects.equals(_dataManager.Clients.get(i).idClient, idClient)){
                                exist = true;
                                break;
                            }
                        }
                        if(exist){
                            Issue i = new Issue(idClient, year, month, toPay, 0);
                            //uniqueness
                            for(int j=0; j<_dataManager.Issues.size(); j++){
                                if(_dataManager.Issues.get(j).ClientId == idClient && _dataManager.Issues.get(j).Month == month){
                                    return "Monthly index already exists!";
                                }
                            }
                        
                            _dataManager.Issues.add(i);
                            _dataManager.SaveChanges();
                            return "Index added successfully: " + i.toString();
                        }else{
                            return "Client does not exist!";
                        }
                }else{
                    return "Money to pay can't be less than 0!";
                }
            }else{
                return "Invalid Month!";
            }
        }else{
            return "Year can't be 0 or less!";
        }
    }
    
    public String ListIssue(String c){
        String s = "";
        for(Issue i: _dataManager.Issues){
            if(Objects.equals(c, i.ClientId)){
            //System.out.println("AAAAAAAAAAAAA");
                s += i.prettyPrint() + "\n";
            }
        }
        return s;
    }
    
}
