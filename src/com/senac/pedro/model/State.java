package com.senac.pedro.model;

import java.util.ArrayList;
import java.util.List;

public class State {
    private Integer id;
    private String name;
    private String abbreviation;
    private List<City> cities = new ArrayList<>();

    public State() {

    }

    public State(Integer id, String name, String abbreviation) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
