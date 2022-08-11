package model;

import java.util.ArrayList;
import java.util.Optional;

public class Cart {
    public Cart(){
        list= new ArrayList<Product>();
    }

    public void addProduct(Product p,int quantita){
    Optional<Product> opt=find(p.getId());
    if(opt.isPresent()){
        opt.get().setTotal(opt.get().getTotal()+quantita);
    }else{
        p.setTotal(quantita);
        list.add(p);
    }
    }

    public Optional<Product> find(int id){
        return list.stream().filter(pr->pr.getId()==id).findFirst();
    }


    public ArrayList<Product> getList() {
        return list;
    }

    public void setList(ArrayList<Product> list) {
        this.list = list;
    }

    public double getTotale() {
        double sum=0;
        for (Product p: list) {
            sum+=(p.getTotal()*p.getPrice());
        }
        return sum;
    }


    public void setTotale(double totale) {
        this.totale = totale;
    }

    public static ArrayList<Product> list;
    public double totale=0;

    public boolean removeProduct(int removeId) {
        return list.removeIf(pr->pr.getId()==removeId) ;
    }
}
