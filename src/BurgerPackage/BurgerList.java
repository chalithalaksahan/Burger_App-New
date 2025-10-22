
package BurgerPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Node{
    private Burger burger;
    private Node next;
    public Node(Burger burger){
        this.burger=burger;
    }
}

class BurgerList {

    private Burger[] burgerArray;
    private int nextIndex;
    private final double loadFact;
    private final int initSize;

    public BurgerList() {
        nextIndex = 0;
        loadFact = 0.5;
        initSize = 100;
        burgerArray = new Burger[initSize];
    }

    public BurgerList(int initSize, double loadFact) {
        burgerArray = new Burger[initSize];
        this.loadFact = loadFact;
        nextIndex = 0;
        this.initSize = initSize;
    }

    public void add(Burger burger) {
        if (nextIndex >= burgerArray.length) {
            extendsArray();
        }
        burgerArray[nextIndex++] = burger;
    }

    public void addLast(Burger burger) {
        add(burger);
    }

    public void addFirst(Burger burger) {
        add(0, burger);
    }

    public void add(int index, Burger burger) {
        if (index >= 0 && index <= nextIndex) {
            for (int i = nextIndex - 1; i >= index; i--) {
                burgerArray[i + 1] = burgerArray[i];
            }
            burgerArray[index] = burger;
            nextIndex++;
        }
    }

    public void removeFirst() {
        remove(0);
    }

    public void removeLast() {
        remove(nextIndex - 1);
    }

    public void remove(int index) {
        if (index >= 0 && index < nextIndex) {
            for (int i = index; i < nextIndex - 1; i++) {
                burgerArray[i] = burgerArray[i + 1];
            }
            nextIndex--;
        }
    }

    public Burger get(int index) {
        return index >= 0 && index < nextIndex ? burgerArray[index] : null;
    }

    public void printList() {
        System.out.print("[");
        for (int i = 0; i < nextIndex; i++) {
            System.out.print(burgerArray[i] + ", ");
        }
        System.out.println(isEmpty() ? "empty]" : "\b\b]");
    }

    private void extendsArray() {
        Burger[] tempBurgerArray = new Burger[(int) (burgerArray.length * (loadFact + 1))];
        for (int i = 0; i < burgerArray.length; i++) {
            tempBurgerArray[i] = burgerArray[i];
        }
        burgerArray = tempBurgerArray;
    }

    public boolean isEmpty() {
        return nextIndex <= 0;
    }

    public int size() {
        return nextIndex;
    }

    public boolean contains(Burger burger) {
        return indexOf(burger) != -1;
    }

    public int indexOf(Burger burger) {
        for (int i = 0; i < nextIndex; i++) {
            if (burgerArray[i] == burger) {
                return i;
            }
        }
        return -1;
    }

    public Burger[] toArray() {
        Burger[] tempDataArray = new Burger[nextIndex];
        for (int i = 0; i < nextIndex; i++) {
            tempDataArray[i] = burgerArray[i];
        }
        return tempDataArray;
    }

    public void clear() {
        nextIndex = 0;
        burgerArray = new Burger[initSize];
    }
    
    
    
    
    public String generateBurgerId(){
        String lastId = "O0001";
        try {
             String PrepairId = String.format("%d",Burger.DELIVERED);
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
    
    public String searchCustomerId(String customerId){
        for (Burger burger : burgerArray) {
            if(burger.getCustomerId().equalsIgnoreCase(customerId)){
                return burger.getCustomerName();
            }
        }
        return null;
    }
    public String getTotal(int OrderQty){
        double total =  (OrderQty*Burger.burgerPrice);
        String Total =  String.format("%.2f", total);
      return Total;
    }
    public boolean search(Burger[] ar,String id){
		for (int i = 0; i <ar.length ; i++){
			if(ar[i].getCustomerId().equalsIgnoreCase(id)){
				return true;
			}
		}
		return false;
		
    }
    public Burger[] findBestCustomer() {
    Burger[] dra = new Burger[0];
    
    for (int i = 0; i < burgerArray.length; i++) {
        if (!search(dra, burgerArray[i].getCustomerId())) {
            Burger[] tempBurgerArray = new Burger[dra.length + 1];
            for (int j = 0; j < dra.length; j++) {
                tempBurgerArray[j] = dra[j];
            }
            Burger newBurger = new Burger(
                burgerArray[i].getCustomerId(),
                burgerArray[i].getCustomerName(),
                0 
            );
            tempBurgerArray[dra.length] = newBurger;
            dra = tempBurgerArray;
        }
    }
    
  
    for (int i = 0; i < dra.length; i++) {
        int totalQuantity = 0;
        for (int j = 0; j < burgerArray.length; j++) {
            if (dra[i].getCustomerId().equalsIgnoreCase(burgerArray[j].getCustomerId())) {
                totalQuantity += burgerArray[j].getOrderQty();
            }
        }
        dra[i].setOrderQty(totalQuantity); 
    }

  
    for (int i = 0; i < dra.length - 1; i++) {
        for (int j = 0; j < dra.length - i - 1; j++) {
            if (dra[j].getOrderQty() < dra[j + 1].getOrderQty()) { 
                Burger tempBurger = dra[j];
                dra[j] = dra[j + 1];
                dra[j + 1] = tempBurger;
            }
        }
    }
     
    return dra;
    }
}
