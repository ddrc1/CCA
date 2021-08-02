package util;


import java.util.ArrayList;
import java.util.List;



public class Setor {

    private Long id;
    private PontoLatLng centro;
    private List<PontoLatLng> pontosLatLng = new ArrayList<PontoLatLng>();
    private Integer populacao = 0;
//    private Integer crimes2019 = 0;
//    private Integer crimes2020 = 0;
    private Integer idCluster;
    private Double densidade = 0.0;
    private Double area;
    public static Double tamanhoPixelEmMetros;
    public String stringObjectPolygon;

    public List<PontoLatLng> getPontosLatLng() {
        return pontosLatLng;
    }

    public void setPontosLatLng(List<PontoLatLng> pontosLatLng) {
        this.pontosLatLng = pontosLatLng;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PontoLatLng getCentro() {
        return centro;
    }

    public void setStringObjectPolygon(String stringObjectPolygon) {
        this.stringObjectPolygon = stringObjectPolygon;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public void setCentro(PontoLatLng centro) {
        this.centro = centro;
    }

    public Integer getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
    }

//    public Integer getCrimes2019() {
//        return crimes2019;
//    }
//
//    public void setCrimes2019(Integer crimes2019) {
//        this.crimes2019 = crimes2019;
//    }
//
//    public Integer getCrimes2020() {
//        return crimes2020;
//    }
//
//    public void setCrimes2020(Integer crimes2020) {
//        this.crimes2020 = crimes2020;
//    }

    public Integer getIdCluster() {
        return idCluster;
    }

    public void setIdCluster(Integer idCluster) {
        this.idCluster = idCluster;
    }

    public Double getDensidadePopulacional() {
        // pessoa por km2
        return densidade;
    }

    public void setDensidadePopulacional(Double densidade) {
        this.densidade = densidade;
    }

}
