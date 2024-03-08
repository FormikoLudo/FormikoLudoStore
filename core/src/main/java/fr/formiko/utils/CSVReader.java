package fr.formiko.utils;

import fr.formiko.model.field.Domain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.lang.Integer;

public class CSVReader{

    /**
       The path to the csv file
     */
    private String path;

    /**
       The data in the csv file
     */
    private ArrayList<String[]> data;

    public CSVReader(String path){
	this.path = path;
	data = new ArrayList<>();
    }

    /**
       Reads the data in the csv file
     */
    public void readData(){
	try {

	    //get data from csv file
	    BufferedReader br = new BufferedReader(new FileReader(path));

	    //the line of data
	    String line = new String();

	    //while it has not reached end of file
	    while((line = br.readLine()) != null){

		//store the data in a string array
		String[] values = line.split(",");

		//add the splitted values in the data array
		data.add(values);
	    }

	} catch(FileNotFoundException e){
	    e.printStackTrace();
	} catch(IOException e){
	    e.printStackTrace();
	}
    }

    /**
       Gets the domains in the csv array
       @retrun an ArrayList of Domain containing the domains in the csv file
     */
    public ArrayList<Domain> getDomainArrayFromData(){

	//initializes the list of domains
	ArrayList<Domain> domainsList = new ArrayList<>();

	//for every entry in the csv file (except the titles entry)
	for(int i = 1; i < data.size(); i++){
	    String[] value = data.get(i);

	    //adds a new domain from the csv file to the data array
	    domainsList.add(new Domain(value[1], Integer.parseInt(value[0]),Integer.parseInt(value[2]),Integer.parseInt(value[3]),Integer.parseInt(value[4]),Integer.parseInt(value[5])));
	}
	return domainsList;
    }

    public ArrayList<String []> getData()
    {
            return data;
    }
}
