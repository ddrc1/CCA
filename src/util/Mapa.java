package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Mapa {

	public static List<Setor> montaSetores(String arquivo) throws Exception{

        List<Setor> setores = new ArrayList<Setor>();
        BufferedReader in = new BufferedReader(new FileReader(arquivo));
        String line = null;
        String[] arrayAtrinutos = null;

        HashMap<Long, Setor> hashSetores = new HashMap<Long, Setor>();

        line = in.readLine();
        while(in.ready()){
            line = in.readLine();
            arrayAtrinutos = line.split(";");
            Setor sc = new Setor();
            sc.setId(Long.parseLong(arrayAtrinutos[0].trim()));
            sc.setPopulacao(Integer.parseInt(arrayAtrinutos[1]));
            sc.setDensidadePopulacional(Double.parseDouble(arrayAtrinutos[2]));

            String[] centroid = arrayAtrinutos[3].split(" ");
            sc.setCentro(new PontoLatLng(Double.parseDouble(centroid[0]), Double.parseDouble(centroid[1])));

            sc.setArea(Double.parseDouble(arrayAtrinutos[4]));

            sc.setStringObjectPolygon(arrayAtrinutos[5]);

            setores.add(sc);
            hashSetores.put(sc.getId(), sc);
        }
        in.close();

        return setores;
    }

}
