package com.senac.pedro;

import com.senac.pedro.model.City;
import com.senac.pedro.model.State;
import com.senac.pedro.utils.Database;
import com.senac.pedro.utils.StateCityParser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        testParser();
    }

    private static void testParser() {
        StateCityParser parser = new StateCityParser();

        List<State> states = parser.read();
        for(State state: states) {
            System.out.println(state.getName());

            for(City city: state.getCities()) {
                System.out.println("\t" + city.getName());
            }
        }

        parser.write(states);
    }

    private static void TestDatabaseReadWrite() {
        Database db = new Database("/Users/rafagan/Desktop/Aula domingo/atividade-estados/data/data.csv");
        List<List<String>> data = db.readData();
        db.saveData(data);
    }
}


// TODO: Montar servlet
// TODO: Ler arquivo CSV de dentro do Servlet
// TODO: Executar GET e POST dos dados no servlet para salvar informações do servidor
// TODO: UI JSF