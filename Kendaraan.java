public abstract class Kendaraan {
    private String kodeKendaraan;
    private String namaKendaraan;
    private double hargaSewaPerHari;
    private boolean isTersedia;

    public Kendaraan(String kodeKendaraan, String namaKendaraan, double hargaSewaPerHari){
        this.kodeKendaraan = kodeKendaraan;
        this.namaKendaraan = namaKendaraan;
        this.hargaSewaPerHari = hargaSewaPerHari;
        this.isTersedia = true;
    }

    public String getKodeKendaraan(){
        return kodeKendaraan;
    }

    public String getNamaKendaraan(){
        return namaKendaraan;
    }

    public double getHargaSewaPerHari(){
        return hargaSewaPerHari;
    }

    public boolean isTersedia(){
        return isTersedia;
    }

    public void setKodeKendaraan(String kodeKendaraan) {
        this.kodeKendaraan = kodeKendaraan;
    }

    public void setNamaKendaraan(String namaKendaraan) {
        this.namaKendaraan = namaKendaraan;
    }

    public void setHargaSewaPerHari(double hargaSewaPerHari) {
        this.hargaSewaPerHari = hargaSewaPerHari;
    }

    public void setTersedia(boolean tersedia) {
        isTersedia = tersedia;
    }

    public abstract void tampilInfo();

    public abstract double hitungBiayaDasar(int lamaSewa);
}
