
package BurgerPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BurgerController {
    public static String generateBurgerId(){
        String lastId = "O0001";
        try {           
              BufferedReader br =new BufferedReader(new FileReader("Burger.txt"));           
              String line=br.readLine();             
              while(line!=null){                
                  String[] rowData = line.split(",");
                    if(line.length()>=16){
                    lastId=String.format("O%04d",(Integer.parseInt(rowData[0].substring(1,5)))+1);
                  }
                 line = br.readLine();                 
              }
               br.close();
         } catch (IOException ex) {
         }
        return lastId;
    }
    public static String isCustomerExist(String customerId){
        try {
             BufferedReader br =new BufferedReader(new FileReader("Burger.txt"));
            
              String line=br.readLine();
              
              while(line!=null){
                 
                  String[] rowData = line.split(",");
                    if(line.length()>=16){
                    if(line.substring(6,16).equals(customerId)){
                        return rowData[2];
                    }
                  }
                 line = br.readLine();
              
              }
               br.close();
         
         } catch (IOException ex) {
         }
        return "null";
    }
    public static boolean addNewBurger(Burger burger) throws IOException{
        try (FileWriter fw = new FileWriter("Burger.txt",true)) {
            fw.write(burger.toString()+"\n");
        }
         return true;
    }
}
