
package BurgerPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



class BurgerList {

    private Node first;

    public boolean add(int index,Burger burger) {
        if(index>=0 && index<=size()){
            Node node = new Node(burger);
            if(index==0){
               node.next=first;
                first=node;
            }else{
            int count=0;
            Node temp = first;
            while(count<index-1){
                temp=temp.next;
            }
            node.next=temp.next;
            temp.next=node;
            }
               return true;
        }
        return false;
    }
    public boolean addFirst(Burger burger){
        return add(0,burger);
    }
    public boolean addLast(Burger burger){
        return add(size(),burger);
    }
    public boolean add(Burger burger){
        return addLast(burger);
    }
    public boolean remove(int index){
        if(index>=0 && index<size()){
            int count =0;
            Node temp = first;
            while(count<index-1){
                count++;
                temp=temp.next;
            }
            temp.next=temp.next.next;
            return true;
        }
        return false;
    }
    public boolean removeFirst(){
        return remove(0);
    }
    public boolean removeLast(){
        return remove(size()-1);
    }
    public int size(){
        int count=0;
        Node temp=first;
        while(temp!=null){
            temp=temp.next;
            count++;
        }
        return count;
    }
    
    public void printBurgers(){
        System.out.print("{");
        Node temp=first;
        while(temp!=null){
            Burger burger = temp.burger;
            System.out.print(burger.toString()+", ");
            temp=temp.next;
        }
        System.out.println(isEmpty() ? "Empty" : "\b\b}");
    }
    public boolean isEmpty(){
        return first==null;
    }
    public Burger get(int index){
        if(index>=0 && index<size()){
            int count=0;
            Node temp=first;
            while(count<index){
                count++;
                temp=temp.next;
            }
            return temp.burger;
        }
        return null;
    }
    public Burger getFirst(){
        return get(0);
    }
    public Burger getLast(){
        return get(size()-1);
    }
    public int indexOf(Burger burger){
        int index = 0;
        Node temp = first;
        while(temp!=null){
            if(burger.getOrderId().equals(temp.burger.getOrderId())){
                return index;
            }
            index++;
            temp=temp.next;
        }
        return -1;
    }
    public boolean contains(Burger burger){
        return indexOf(burger)!=-1;
    }
    public boolean remove(Burger burger){
        int index=indexOf(burger);
        return remove(index);
    }
    class Node{
        private Burger burger;
        private Node next;
        
        public Node(Burger burger){
            this.burger=burger;
        }
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
    
//    public String searchCustomerId(String customerId){
//        for (Burger burger : burgerArray) {
//            if(burger.getCustomerId().equalsIgnoreCase(customerId)){
//                return burger.getCustomerName();
//            }
//        }
//        return null;
//    }
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
//    public Burger[] findBestCustomer() {
//    Burger[] dra = new Burger[0];
//    
//    for (int i = 0; i < burgerArray.length; i++) {
//        if (!search(dra, burgerArray[i].getCustomerId())) {
//            Burger[] tempBurgerArray = new Burger[dra.length + 1];
//            for (int j = 0; j < dra.length; j++) {
//                tempBurgerArray[j] = dra[j];
//            }
//            Burger newBurger = new Burger(
//                burgerArray[i].getCustomerId(),
//                burgerArray[i].getCustomerName(),
//                0 
//            );
//            tempBurgerArray[dra.length] = newBurger;
//            dra = tempBurgerArray;
//        }
//    }
//    
//  
//    for (int i = 0; i < dra.length; i++) {
//        int totalQuantity = 0;
//        for (int j = 0; j < burgerArray.length; j++) {
//            if (dra[i].getCustomerId().equalsIgnoreCase(burgerArray[j].getCustomerId())) {
//                totalQuantity += burgerArray[j].getOrderQty();
//            }
//        }
//        dra[i].setOrderQty(totalQuantity); 
//    }
//
//  
//    for (int i = 0; i < dra.length - 1; i++) {
//        for (int j = 0; j < dra.length - i - 1; j++) {
//            if (dra[j].getOrderQty() < dra[j + 1].getOrderQty()) { 
//                Burger tempBurger = dra[j];
//                dra[j] = dra[j + 1];
//                dra[j + 1] = tempBurger;
//            }
//        }
//    }
//     
//    return dra;
//    }
}
