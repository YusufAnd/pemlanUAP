public class Motor extends Kendaraan{
    private String jenisTrasmisi;

    public Motor(String kode, String nama, double harga, String jenisTransmisi){
        super(kode, nama, harga);
        this.jenisTrasmisi = jenisTransmisi;
    }

    public String getJenisTransmisi(){
        return jenisTransmisi;
    }

    public void setJenisTransmisi(String jenisTransmisi){
        this.jenisTrasmisi = jenisTransmisi;
    }

    @Override
    public void tampilInfo(){
        System.out.println("[MOTOR] kode: " + getKodeKendaraan() + " | Nama: " + getNamaKendaraan() + " | Transmisi: " + jenisTrasmisi + " | Tarif: Rp" + (int)getHargaSewaPerHari() + "/hari" + " | Status: " + (isTersedia() ? "Tersedia" : "Disewa"));
    }

    @Override
    public double hitungBiayaDasar(int lamaSewa){
        double total = lamaSewa * getHargaSewaPerHari();

        if (jenisTransmisi.equalsIgnoreCase("Matik")) {
            total += (10000 * lamaSewa);
        }
        
        return total;
    }
}
