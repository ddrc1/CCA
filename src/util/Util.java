package util;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class Util {

    public static double distancia(double lat1, double lon1, double lat2,  double lon2,
                                   double el1, double el2) {

        final int R = 6371; // Raio da terra

        Double latDistance = deg2rad(lat2 - lat1);
        Double lonDistance = deg2rad(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convertendo para metros

        double height = 0;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);// aplicando pitagoras
        return Math.sqrt(distance);//retorna o comprimento da hipotenusa.
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public static void gerarDistribruicaoPl(List<Integer> valores) throws Exception{

        DataOutputStream distribution = new DataOutputStream(new FileOutputStream("./saida/distribuicao.txt"));

        Collections.sort(valores);

        int degreeAnt = valores.get(0);

        int count = 0;

        for (Integer valor : valores) {
            if(degreeAnt != valor){
                distribution.writeBytes(degreeAnt+","+count);
                distribution.writeBytes("\n");
                degreeAnt = valor;

                count = 1;
            }else{
                count ++;
            }
        }

        distribution.writeBytes(degreeAnt+","+count);

        distribution.close();
    }
}
