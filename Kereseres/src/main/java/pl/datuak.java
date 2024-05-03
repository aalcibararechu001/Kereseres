package pl;

public class datuak {
    private String izena;
    private String orduak;
    
    public String getIzena() {
        return izena;
    }
    public void setIzena(String izena) {
        this.izena = izena;
    }
    public String getOrduak() {
        return orduak;
    }
    public void setOrduak(String orduak) {
        this.orduak = orduak;
    }
    public datuak(String izena, String orduak) {
        this.izena = izena;
        this.orduak = orduak;
    }
    
}
