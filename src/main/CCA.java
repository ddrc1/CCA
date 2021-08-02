package main;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import util.Mapa;
import util.PontoLatLng;
import util.Setor;
import util.Util;

public class CCA {

    private List<Setor> setores;
    private String pathSaida;
    private double l; // Em metros
    private double dMin;
    String cidade;

    public CCA(List<Setor> setores, double l, double dMin, String cidade, String pathSaida) {
        super();
        this.setores = setores;
        this.l = l;
        this.dMin = dMin;
        this.cidade = cidade;
        this.pathSaida = pathSaida;
    }

    public void generate() throws Exception {

        List<Cluster> clusters = new ArrayList<Cluster>();

        for (Setor setor : setores) {

            if(setor.getDensidadePopulacional()<dMin)
                continue;

            Cluster c = new Cluster();

            c.setores.put(setor.getId(), setor);

            for (Setor setor2 : setores) {

                if(!setor.getId().equals(setor2.getId())){

                    double distancia = Util.distancia(setor.getCentro().lng,setor.getCentro().lat, setor2.getCentro().lng,setor2.getCentro().lat, 0, 0);
                    if(distancia<l && setor2.getDensidadePopulacional() >= dMin) {
                        c.setores.put(setor2.getId(), setor2);
                    }
                }
            }

            boolean temSetor = false;

            for (Cluster cluster : clusters) {

                Set<Long> chaves = c.setores.keySet();

                for (Long chave : chaves) {

                    if(cluster.setores.containsKey(chave)){
                        temSetor = true;
                        break;
                    }
                }
                if(temSetor){
                    for (Long chave : chaves) {
                        if(!cluster.setores.containsKey(chave)){
                            cluster.setores.put(chave, c.setores.get(chave));
                        }
                    }
                    break;
                }
            }
            if(!temSetor){
                clusters.add(c);
            }
        }

        for (int i = 0; i < clusters.size(); i++) {
            for (int j = 0; j < clusters.size(); j++) {
                if(i!=j){
                    boolean temSetor = false;
                    Set<Long> chaves = clusters.get(i).setores.keySet();
                    for (Long chave : chaves) {
                        if(clusters.get(j).setores.containsKey(chave)){
                            temSetor = true;
                        }
                    }

                    if(temSetor){
                        for (Long chave : chaves) {
                            if(!clusters.get(j).setores.containsKey(chave)){
                                clusters.get(j).setores.put(chave, clusters.get(i).setores.get(chave));
                            }
                        }
                        clusters.get(i).setores = new HashMap<Long, Setor>();
                    }
                }
            }
        }

        String pathData = this.pathSaida + "/setores_cca_txt/";
        DataOutputStream outputSetores = new DataOutputStream(
                new FileOutputStream(pathData + "cca_" + (int) this.dMin + "_" + (int) l + ".csv"));
        outputSetores.writeBytes("SETORID;POP;DENSIDADE;CLUSTER;AREA;geometry\n");
        for (int i = 0; i < clusters.size(); i++) {
            Cluster a = clusters.get(i);
            if (a.setores.size() == 0)
                continue;
            Set<Long> chaves = a.setores.keySet();
            for (Long chave : chaves) {
                outputSetores.writeBytes(a.setores.get(chave).getId() + ";" +  a.setores.get(chave).getPopulacao() + ";" +
                        a.setores.get(chave).getDensidadePopulacional() + ";" + i + ";" + a.setores.get(chave).getArea() + ";" +
                        a.setores.get(chave).stringObjectPolygon + "\n");
            }
        }
        outputSetores.close();
    }

    public static void main(String[] args) throws Exception {
         String path = args[0];
         String cidade = args[1];
         int dmin = Integer.parseInt(args[2]);
         int dmax = Integer.parseInt(args[3]);
         int dinc = Integer.parseInt(args[4]);
         int lmin = Integer.parseInt(args[5]);
         int lmax = Integer.parseInt(args[6]);
         int linc = Integer.parseInt(args[7]);
         

        System.out.println(path + "/" + cidade + ".csv");
        
        List<Setor> setores = Mapa.montaSetores(path + "/" + cidade + ".csv");
        System.out.println("processesando "+ cidade + " : ");
        
        for (int d = dmin; d <= dmax; d += dinc) {
	     	for (int l = lmin; l <= lmax; l += linc) {
	     		System.out.println(l + " " + d);
	     		CCA cca = new CCA(setores, l, d, cidade, path);
	     		cca.generate();
	
	     	}
	
	     }
        
	     System.out.println("terminado");
    }

}

class Cluster {
    public HashMap<Long, Setor> setores = new HashMap<Long, Setor>();
}
