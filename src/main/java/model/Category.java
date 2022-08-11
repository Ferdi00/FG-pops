package model;

import http.JsonSerializable;
import org.json.JSONObject;

public class Category implements JsonSerializable {

    public Category(){
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private int Id;
    private String nome;


    @Override
    public JSONObject toJson() {
        JSONObject object=new JSONObject();
        object.put("id",Id);
        object.put("label",nome);
        return object;
    }
}
