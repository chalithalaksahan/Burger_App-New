
package BurgerPackage;

import java.io.BufferedReader;
import java.io.File;
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
    
    public static Burger searchOrder(String orderId){
        try {
             BufferedReader br =new BufferedReader(new FileReader("Burger.txt"));
                String line=br.readLine();
                BurgerList burgerList = new BurgerList();
                while(line!=null){
                   String[] rowData = line.split(",");
                   Burger burger = new Burger(rowData[0],rowData[1],rowData[2],Integer.parseInt(rowData[3]),Integer.parseInt(rowData[4]));
                   burgerList.add(burger);
                    line=br.readLine();
                }
                br.close();
                for (int i = 0; i < burgerList.size(); i++) {
                    Burger burger = burgerList.get(i);
                    if(burger.getOrderId().equals(orderId)){
                     
                        return burger;
                    }
                
            }
         } catch (IOException ex) {
            
         }
       
         return null;
    }
    public static BurgerList importBurgers(){
         try {
             BufferedReader br =new BufferedReader(new FileReader("Burger.txt"));
                String line=br.readLine();
                BurgerList burgerList = new BurgerList();
                
                while(line!=null){
                   String[] rowData = line.split(",");
                   Burger burger = new Burger(rowData[0],rowData[1],rowData[2],Integer.parseInt(rowData[3]),Integer.parseInt(rowData[4]));
                   burgerList.add(burger);
                    line=br.readLine();
                    
                }
                br.close();
               
                return burgerList;
            
         } catch (IOException ex) {
            
         }
       
         return null;
    }
    
    public static boolean updateBurger(BurgerList burgerList) throws IOException{
        new File("Burger.txt").delete(); 
        try (FileWriter fw = new FileWriter("Burger.txt",true)) {
            for (int i = 0; i < burgerList.size(); i++) {
                Burger burger = burgerList.get(i);
                fw.write(burger.toString()+"\n");
            }
        }
         return true;
    }
}
