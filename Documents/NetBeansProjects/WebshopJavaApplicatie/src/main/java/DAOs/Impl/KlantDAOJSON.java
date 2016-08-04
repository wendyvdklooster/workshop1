/*
 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs.Impl;

import DAOs.Interface.KlantDAOInterface;
import POJO.Klant;
import POJO.Klant.KlantBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import Factory.DaoFactory;
import org.apache.commons.validator.routines.EmailValidator;
/**
 *
 * @author Wendy
 */
public class KlantDAOJSON implements KlantDAOInterface {

    @Override
    public ArrayList<Klant> findAllKlanten() {
        
    ArrayList<Klant> klantenLijst = new ArrayList<>();
        Object obj = new Object();
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Anne\\Documents\\Programmeren\\Workshops\\workshop1\\JsonProef"));
            // aparte try-catch voor logger anders vangt hij hem niet
            try {
                obj = parser.parse(reader);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
     
        JSONObject KlantDatabase = (JSONObject)obj;
        
        // de gegevens van de klanten ophalen en in jsonArray zetten
        
            JSONArray klantenIn = (JSONArray)(KlantDatabase.get("klanten"));
            for (int i = 0; i < klantenIn.size(); i++) {
                JSONObject klantIn = (JSONObject)(klantenIn.get(i));
                long klantId = (long)(klantIn.get("klant_id"));
                String voornaam = (String)(klantIn.get("voornaam"));
                String achternaam = (String)(klantIn.get("achternaam"));
                String tussenvoegsel = (String)(klantIn.get("tussenvoegsel"));
                String email = (String)(klantIn.get("email"));
                
                // maak klantbuilder aan en maak klant van gegevens.
                KlantBuilder klantBuilder = new KlantBuilder();
                klantBuilder.klantId((int)(klantId));
                klantBuilder.voornaam(voornaam);
                klantBuilder.achternaam(achternaam);
                klantBuilder.tussenvoegsel(tussenvoegsel);
                klantBuilder.email(email);
                
                Klant klant = new Klant();
                klant = klantBuilder.build();
                klantenLijst.add(klant);
            }
        
        return klantenLijst;
    /**
    JSONParser parser = new JSONParser();
    
    try {
        File file = new File("C:\\Users\\Anne\\Documents\\Programmeren\\Workshops\\workshop1\\JsonProef");
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNext()) {
            JSONObject klantObject;
            try {
                klantObject = (JSONObject)(parser.parse(scanner.nextLine()));
                klantenLijst.add(klantObject);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                    Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    catch (FileNotFoundException ex)  {
        System.out.println(ex.toString());
    }
        return klantenLijst;
        */
    }

    @Override
    public Klant findByKlantId(int klantId) {
        
   ArrayList<Klant> klantenLijst = new ArrayList<>();
        Object obj = new Object();
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Anne\\Documents\\Programmeren\\Workshops\\workshop1\\JsonProef"));
            // aparte try-catch voor logger anders vangt hij hem niet
            try {
                obj = parser.parse(reader);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
     
        JSONObject KlantDatabase = (JSONObject)obj;
        
        // de gegevens van de klanten ophalen en in jsonArray zetten
        Klant klant = new Klant();
        JSONArray klantenIn = (JSONArray)(KlantDatabase.get("klanten"));
            for (int i = 0; i < klantenIn.size(); i++) {
                JSONObject klantIn = (JSONObject)(klantenIn.get(i));
                int klantIdInput = (int)((long)(klantIn.get("klant_id")));
                
                // als klant id overeenkomt met gezochte, dan klant bouuwen.
                if (klantIdInput == klantId) {
                    String voornaam = (String)(klantIn.get("voornaam"));
                    String achternaam = (String)(klantIn.get("achternaam"));
                    String tussenvoegsel = (String)(klantIn.get("tussenvoegsel"));
                    String email = (String)(klantIn.get("email"));
                   
                    // maak klantbuilder aan en maak klant van gegevens.
                    KlantBuilder klantBuilder = new KlantBuilder();
                    klantBuilder.klantId((int)(klantId));
                    klantBuilder.voornaam(voornaam);
                    klantBuilder.achternaam(achternaam);
                    klantBuilder.tussenvoegsel(tussenvoegsel);
                    klantBuilder.email(email);
                
                    klant = klantBuilder.build();
                }
                else {
                    System.out.println("Klant kan niet gevonden worden in de database.");
                }
            }
        return klant;
    }
    
    

    @Override
    public Klant findByVoorNaamAchterNaam(String voorNaam, String achterNaam) {
        Object obj = new Object();
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Anne\\Documents\\Programmeren\\Workshops\\workshop1\\JsonProef"));
            // aparte try-catch voor logger anders vangt hij hem niet
            try {
                obj = parser.parse(reader);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
     
        JSONObject KlantDatabase = (JSONObject)obj;
        
        // de naam en achternaam van de klant ophalen en in jsonArray zetten
        Klant klant = new Klant();
        JSONArray klantenIn = (JSONArray)(KlantDatabase.get("klanten"));
            for (int i = 0; i < klantenIn.size(); i++) {
                JSONObject klantIn = (JSONObject)(klantenIn.get(i));
                String voornaam = (String)(klantIn.get("voornaam"));
                String achternaam = (String)(klantIn.get("achternaam"));
                
                // als overeenkomt met gezochte namen dan klant ophalen
                    voornaam = (String)(klantIn.get("voornaam"));
                    achternaam = (String)(klantIn.get("achternaam"));
                    int klantId = (int)((long)(klantIn.get("klant_id")));
                    String tussenvoegsel = (String)(klantIn.get("tussenvoegsel"));
                    String email = (String)(klantIn.get("email"));
                   
                    // maak klantbuilder aan en maak klant van gegevens.
                    KlantBuilder klantBuilder = new KlantBuilder();
                    klantBuilder.klantId(klantId);
                    klantBuilder.voornaam(voornaam);
                    klantBuilder.achternaam(achternaam);
                    klantBuilder.tussenvoegsel(tussenvoegsel);
                    klantBuilder.email(email);
                    klant = klantBuilder.build();
                    
                    //if (klant.getAchternaam().equals(voorNaam) && klant.getVoornaam().equals(achterNaam)) {
                    if (klant.getVoornaam().equals(voorNaam) && klant.getAchternaam().equals(achterNaam)) {
                        return klant;
                    }
                else {
                    System.out.println("Klant kan niet gevonden worden in de database.");
                }
                
            }
            /**catch (IOException ex) {
                 System.out.println(ex.toString());       
            }
        */
    
            return klant;
    }   
    

    @Override
    public Klant findByEmail(String email) {
       Object obj = new Object();
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Anne\\Documents\\Programmeren\\Workshops\\workshop1\\JsonProef"));
            // aparte try-catch voor logger anders vangt hij hem niet
            try {
                obj = parser.parse(reader);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
     
        JSONObject KlantDatabase = (JSONObject)obj;
        
        // de naam en achternaam van de klant ophalen en in jsonArray zetten
        Klant klant = new Klant();
        JSONArray klantenIn = (JSONArray)(KlantDatabase.get("klanten"));
            for (int i = 0; i < klantenIn.size(); i++) {
                JSONObject klantIn = (JSONObject)(klantenIn.get(i));
                int klantId = (int)((long)(klantIn.get("klant_id")));
                String voornaam = (String)(klantIn.get("voornaam"));
                String achternaam = (String)(klantIn.get("achternaam"));
                String tussenvoegsel = (String)(klantIn.get("tussenvoegsel"));
                String emailInput = (String)(klantIn.get("email"));
                   
                // maak klantbuilder aan en maak klant van gegevens.
                KlantBuilder klantBuilder = new KlantBuilder();
                klantBuilder.klantId(klantId);
                klantBuilder.voornaam(voornaam);
                klantBuilder.achternaam(achternaam);
                klantBuilder.tussenvoegsel(tussenvoegsel);
                klantBuilder.email(email);
                klant = klantBuilder.build();
                    
                if (klant.getEmail().equals(emailInput)) {
                    return klant;
                }
                else {
                    System.out.println("Klant kan niet gevonden worden in de database.");
                }
        }
        return klant;
                
    }

    @Override
    public Klant insertKlant(Klant klant) {
        
        // eerst alle klanten ophalen in een lijst
        ArrayList<Klant> klantenLijst = new ArrayList<>();
        Object obj = new Object();
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Anne\\Documents\\Programmeren\\Workshops\\workshop1\\JsonProef"));
            // aparte try-catch voor logger anders vangt hij hem niet
            try {
                obj = parser.parse(reader);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
        int nieuweKlantId;
        JSONObject KlantDatabase = (JSONObject)obj;
        if (KlantDatabase != null) {
          
        
        // de gegevens van de klanten ophalen en in jsonArray zetten
        JSONArray klantenIn = (JSONArray)(KlantDatabase.get("klanten"));
            for (int i = 0; i < klantenIn.size(); i++) {
                JSONObject klantIn = (JSONObject)(klantenIn.get(i));
                long klantId = (long)(klantIn.get("klant_id"));
                String voornaam = (String)(klantIn.get("voornaam"));
                String achternaam = (String)(klantIn.get("achternaam"));
                String tussenvoegsel = (String)(klantIn.get("tussenvoegsel"));
                String email = (String)(klantIn.get("email"));
                
                // maak klantbuilder aan en maak klant van gegevens.
                KlantBuilder klantBuilder = new KlantBuilder();
                klantBuilder.klantId((int)(klantId));
                klantBuilder.voornaam(voornaam);
                klantBuilder.achternaam(achternaam);
                klantBuilder.tussenvoegsel(tussenvoegsel);
                klantBuilder.email(email);
                
                Klant klantInput = new Klant();
                klantInput = klantBuilder.build();
                klantenLijst.add(klantInput);
            }
        
        // check wat laatste klantId is om volgende te maken
        Klant laatsteKlant = klantenLijst.get(klantenLijst.size()-1);
        int laatsteKlantId = laatsteKlant.getKlantId();
        nieuweKlantId = ++laatsteKlantId;
        
        }
        else {
            nieuweKlantId = 1;
        }
        Klant nieuweKlant = new Klant();
        String voornaam = klant.getVoornaam();
        String achternaam = klant.getAchternaam();        
        String tussenvoegsel = klant.getTussenvoegsel();
        String email = klant.getEmail(); 
        
        // create klant en zet het in de arrayList
        KlantBuilder klantBuilder = new KlantBuilder();
        klantBuilder.klantId(nieuweKlantId);
        klantBuilder.voornaam(voornaam);
        klantBuilder.achternaam(achternaam);
        klantBuilder.tussenvoegsel(tussenvoegsel);
        klantBuilder.email(email);
        
        nieuweKlant = klantBuilder.build();
        klantenLijst.add(nieuweKlant);
        
        // loop door de arraylist om alle klanten in jsonformat te zetten
        JSONArray klantenOutput = new JSONArray();
        for (int i = 0; i <klantenLijst.size(); i++) {
            
            Klant klantOutput = klantenLijst.get(i);
            JSONObject klantObject = new JSONObject();
            klantObject.put("klant_id", klantOutput.getKlantId());
            klantObject.put("voornaam", klantOutput.getVoornaam());
            klantObject.put("achternaam", klantOutput.getAchternaam());
            klantObject.put("tussenvoegsel", klantOutput.getTussenvoegsel());
            klantObject.put("email", klantOutput.getEmail());
            
            klantenOutput.add(klantObject);
        }    
        JSONObject nieuweKlantDatabase = new JSONObject();
        nieuweKlantDatabase.put("klanten", klantenOutput);
        
        // gebruik parser om de nieuwe database naar json bestand te sturen
        File jsonFile = new File("C:\\Users\\Anne\\Documents\\Programmeren\\Workshops\\workshop1\\JsonProef");
        try (PrintWriter output = new PrintWriter(jsonFile)) {
          output.write(nieuweKlantDatabase.toString());
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
               
        return klant;
    }
    

    @Override
    public boolean deleteByKlantId(int klantId) {
        
        boolean isDeleted = false;
        ArrayList<Klant> klantenLijst = new ArrayList<>();
        Object obj = new Object();
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Anne\\Documents\\Programmeren\\Workshops\\workshop1\\JsonProef"));
            // aparte try-catch voor logger anders vangt hij hem niet
            try {
                obj = parser.parse(reader);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
     
        JSONObject klantDatabase = (JSONObject)obj;
        
        // de gegevens van de klanten ophalen en in jsonArray zetten
        Klant klant = new Klant();
        JSONArray klantenIn = (JSONArray)(klantDatabase.get("klanten"));
            for (int i = 0; i < klantenIn.size(); i++) {
                JSONObject klantIn = (JSONObject)(klantenIn.get(i));
                int klantIdInput = (int)((long)(klantIn.get("klant_id")));
                
                // als klant id overeenkomt met gezochte, dan klant verwijderen.
                if (klantIdInput == klantId) {
                    
                    klantIn.remove("klant_id");
                    klantIn.remove("voornaam");
                    klantIn.remove("achternaam");
                    klantIn.remove("tussenvoegsel");
                    klantIn.remove("email");
                    klantenIn.remove(klantIn);
                    
                    isDeleted = true;
                }
            }
        klantDatabase.put("klanten", klantenIn);
        // gebruik parser om de nieuwe database naar json bestand te sturen
        File jsonFile = new File("C:\\Users\\Anne\\Documents\\Programmeren\\Workshops\\workshop1\\JsonProef");
        try (PrintWriter output = new PrintWriter(jsonFile)) {
          output.write(klantDatabase.toString());
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
       return isDeleted;
    }
    

    @Override
    public boolean deleteByKlantNaam(String achternaam, String tussenvoegsel, String voornaam) {
        
        boolean isDeleted = false;
        Object obj = new Object();
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Anne\\Documents\\Programmeren\\Workshops\\workshop1\\JsonProef"));
            // aparte try-catch voor logger anders vangt hij hem niet
            try {
                obj = parser.parse(reader);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
     
        JSONObject klantDatabase = (JSONObject)obj;
        
        // de gegevens van de klanten ophalen en in jsonArray zetten
        JSONArray klantenIn = (JSONArray)(klantDatabase.get("klanten"));
            for (int i = 0; i < klantenIn.size(); i++) {
                JSONObject klantIn = (JSONObject)(klantenIn.get(i));
                int klantIdInput = (int)((long)(klantIn.get("klant_id")));
                String klantVoornaam = (String)(klantIn.get("voornaam"));
                String klantAchternaam = (String)(klantIn.get("achternaam"));
                String klantTussenvoegsel = (String)(klantIn.get("tussenvoegsel"));
                
                
                // als klant id overeenkomt met gezochte, dan klant verwijderen.
                if (klantVoornaam.equals(voornaam) && klantTussenvoegsel.equals(tussenvoegsel) 
                        && klantAchternaam.equals(achternaam)) {
                    
                    klantIn.remove("klant_id");
                    klantIn.remove("voornaam");
                    klantIn.remove("achternaam");
                    klantIn.remove("tussenvoegsel");
                    klantIn.remove("email");
                    klantenIn.remove(klantIn);
                    
                    isDeleted = true;
                }
            }
        klantDatabase.put("klanten", klantenIn);
        // gebruik parser om de nieuwe database naar json bestand te sturen
        File jsonFile = new File("C:\\Users\\Anne\\Documents\\Programmeren\\Workshops\\workshop1\\JsonProef");
        try (PrintWriter output = new PrintWriter(jsonFile)) {
          output.write(klantDatabase.toString());
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
       return isDeleted;
    }

    
    @Override
    public int deleteAll() {
        
        Object obj = new Object();
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Anne\\Documents\\Programmeren\\Workshops\\workshop1\\JsonProef"));
            // aparte try-catch voor logger anders vangt hij hem niet
            try {
                obj = parser.parse(reader);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
        int count = 0;
        JSONObject klantDatabase = (JSONObject)obj;
        
        // de gegevens van de klanten ophalen en in jsonArray zetten
        JSONArray klantenIn = (JSONArray)(klantDatabase.get("klanten"));
            for (int i = 0; i < klantenIn.size(); i++) {
                JSONObject klantIn = (JSONObject)(klantenIn.get(i));
                // van alle klanten alles verwijderen
                    
                    klantIn.remove("klant_id");
                    klantIn.remove("voornaam");
                    klantIn.remove("achternaam");
                    klantIn.remove("tussenvoegsel");
                    klantIn.remove("email");
                    klantenIn.remove(klantIn);
                    count++;
                }
    
        klantDatabase.put("klanten", klantenIn);
        // gebruik parser om de nieuwe database naar json bestand te sturen
        File jsonFile = new File("C:\\Users\\Anne\\Documents\\Programmeren\\Workshops\\workshop1\\JsonProef");
        try (PrintWriter output = new PrintWriter(jsonFile)) {
          output.write(klantDatabase.toString());
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
       return count;
        
    }

    
    @Override
    public Klant updateGegevens(Klant klant) {
        // lijst ophalen
        Object obj = new Object();
        try {    
            JSONParser parser = new JSONParser();
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Anne\\Documents\\Programmeren\\Workshops\\workshop1\\JsonProef"));
            // aparte try-catch voor logger anders vangt hij hem niet
            try {
                obj = parser.parse(reader);
            } 
            catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(KlantDAOJSON.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
        JSONObject KlantDatabase = (JSONObject)obj;
        
        //get details van update klant
        int klantId = klant.getKlantId();
        String voornaam = klant.getVoornaam();
        String achternaam = klant.getAchternaam();        
        String tussenvoegsel = klant.getTussenvoegsel();
        String email = klant.getEmail();   
        
        
        JSONArray klantenIn = (JSONArray)(KlantDatabase.get("klanten"));
            for (int i = 0; i < klantenIn.size(); i++) {
                JSONObject klantIn = (JSONObject)(klantenIn.get(i));
                int klantIdInput = (int)((long)(klantIn.get("klant_id")));
                
                // als klant id overeenkomt met gezochte, dan klant updaten.
                if (klantIdInput == klantId) {
                    klantIn.put("voornaam", voornaam);
                    klantIn.put("achternaam", achternaam);
                    klantIn.put("tusssenvoegsel", tussenvoegsel);
                    klantIn.put("email", email);
                }
            } 
        
        // schrijf gewijzigde lijst weer in json bestand
        KlantDatabase.put("klanten", klantenIn);
        // gebruik parser om de nieuwe database naar json bestand te sturen
        File jsonFile = new File("C:\\Users\\Anne\\Documents\\Programmeren\\Workshops\\workshop1\\JsonProef");
        try (PrintWriter output = new PrintWriter(jsonFile)) {
          output.write(KlantDatabase.toString());
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
        
         return klant;  
        
    }

    
}
