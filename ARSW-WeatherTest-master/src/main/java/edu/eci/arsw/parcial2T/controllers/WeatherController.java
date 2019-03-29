package edu.eci.arsw.parcial2T.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author cristian
 */
@RestController
@RequestMapping(value = "/weather")
public class WeatherController {

    private static final String USER_AGENT = "Mozilla/5.0";
    
    
    /**
     * Controlador peticiones GET
     * @param city
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{city}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> recursoGetAllCinemas(@PathVariable String city){
        Object jo = null;
        try {
            String GET_URL = "https://samples.openweathermap.org/data/2.5/weather?q="+city+"&appid=b1b15e88fa797225412429c1c50c122a1";
            URL obj = new URL(GET_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
        
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            JSONParser parser = new JSONParser(response.toString());
            
            jo = parser.parse();
            }            
	    	
			return new ResponseEntity<>(jo,HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        Logger.getLogger(WeatherController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error Cinemas no encontrados",HttpStatus.NOT_FOUND);
	    }        
	}

}