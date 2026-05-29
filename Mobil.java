public class Mobil extends Kendaraan {
    private int jumlahKursi;

    public Mobil(String kode, String nama, double harga, int jumlahKursi){
        super(kode, nama, harga);
        this.jumlahKursi = jumlahKursi;
    }

    public int getJumlahKursi(){
        return jumlahKursi;
    }

    public void setJumlahKursi(int jumlahKursi){
        this.jumlahKursi = jumlahKursi;
    }

    @Override
    public void tampilInfo(){
        System.out.println("[MOBIL] Kode: " + getKodeKendaraan() + " | Nama: " + getNamaKendaraan() + " | Kursi: " + jumlahKursi + " | Tarif: Rp" + (int)getHargaSewaPerHari() + "/hari" + " | Status: " + (isTersedia() ? "Tersedia" : "Disewa"));
    }

    @Override
    public double hitungBiayaDasar(int lamaSewa){
        double total = lamaSewa * getHargaSewaPerHari();

        if (jumlahKursi > 5) {
            total += 50000;
        }

        return total;
    }
}
