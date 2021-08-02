package util;


public class PontoLatLng {
    public double lat,lng;

    public PontoLatLng(){

    }

    public PontoLatLng(double lat, double lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public boolean equals(Object obj) {

        PontoLatLng p = (PontoLatLng)obj;

        return p.lat == this.lat && p.lng == this.lng ;
    }
}
