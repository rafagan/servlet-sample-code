package com.senac.pedro.utils;

import com.senac.pedro.model.City;
import com.senac.pedro.model.State;

import java.util.ArrayList;
import java.util.List;

public class StateCityParser {
    private String path = "/Users/rafagan/Desktop/Aula domingo/atividade-estados/data/data.csv2";
    private Database db = new Database(path);

    private String valueOrNull(List<String> line, int index) {
        return index < line.size() ? line.get(index) : null;
    }

    public List<State> read() {
        List<State> states = new ArrayList<>();
        List<List<String>> data = db.readData();
        String previousState = "";

        State stateData = null;

        for(int i = 0; i < data.size(); i++) {
            if(i == 0) continue;

            List<String> line = data.get(i);
            String state = valueOrNull(line, 0);

            if(state == null) continue;

            String abbreviation = valueOrNull(line, 1);
            String city = valueOrNull(line, 2);
            String population = valueOrNull(line, 3);
            String area = valueOrNull(line, 4);
            String density = valueOrNull(line, 5);
            String pib = valueOrNull(line, 6);

            if(!state.equals(previousState)) {
                stateData = new State(i, state, abbreviation);
                states.add(stateData);
                previousState = state;
            }

            City cityData = new City();
            cityData.setName(city);
            cityData.setPopulation(population != null ? Integer.parseInt(population) : null);
            cityData.setArea(area != null ? Integer.parseInt(area) : null);
            cityData.setDensity(density != null ? Integer.parseInt(density) : null);
            cityData.setPib(pib != null ? Integer.parseInt(pib) : null);

            stateData.getCities().add(cityData);

        }

        return states;
    }

    public void write(List<State> states) {
        List<List<String>> data = new ArrayList<>();

        List<String> firstLine = new ArrayList<>();
        firstLine.add("state");
        firstLine.add("abbreviation");
        firstLine.add("city");
        firstLine.add("population");
        firstLine.add("area");
        firstLine.add("density");
        firstLine.add("pib");
        data.add(firstLine);

        for(State state: states) {
            for(City city: state.getCities()) {
                List<String> line = new ArrayList<>();
                line.add(state.getName());
                line.add(state.getAbbreviation());

                line.add(city.getName());
                line.add(city.getPopulation() != null ? city.getPopulation().toString() : "");
                line.add(city.getArea() != null ? city.getArea().toString() : "");
                line.add(city.getDensity() != null ? city.getDensity().toString() : "");
                line.add(city.getPib() != null ? city.getPib().toString() : "");

                data.add(line);
            }
        }

        db.saveData(data);
    }
}
