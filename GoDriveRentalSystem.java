import java.util.ArrayList;
import java.util.Scanner;

public class GoDriveRentalSystem {
    private ArrayList<Kendaraan> daftarKendaraan;

    public GoDriveRentalSystem(){
        daftarKendaraan = new ArrayList<>();
    }

    public void tambahKendaraan(Kendaraan k){
        daftarKendaraan.add(k);
    }

    public void tampilkanDaftarKendaraan(){
        System.out.println("\n=== DAFTAR ARMADA GODRIVE ===");
        int nomor = 1;
        for (Kendaraan k : daftarKendaraan){
            System.out.print(nomor + ". ");
            k.tampilInfo();
            nomor++;
        }
    }

    public void sewaKendaraan(String kode, int lamaSewa, boolean vip) throws KendaraanTidakTersediaException {

        Kendaraan kendaraanDipilih = null;

        for (Kendaraan k : daftarKendaraan) {
            if (k.getKodeKendaraan().equalsIgnoreCase(kode)) {
                kendaraanDipilih = k;
                break;
            }
        }

        if (kendaraanDipilih == null || !kendaraanDipilih.isTersedia()) {
            throw new KendaraanTidakTersediaException(
                "Kendaraan dengan kode " + kode + " gagal disewa. Alasan: Kendaraan sedang disewa atau tidak ditemukan!"
            );
        }

        kendaraanDipilih.setTersedia(false);
        double biayaDasar = kendaraanDipilih.hitungBiayaDasar(lamaSewa);

        double diskonVIP = 0;
        double diskonLebih7Hari = 0;

        if (vip) {
            diskonVIP = biayaDasar * 0.10;
        }

        if (lamaSewa > 7) {
            diskonLebih7Hari = biayaDasar * 0.05;
        }

        double totalAkhir = biayaDasar - diskonVIP - diskonLebih7Hari;

        System.out.println("\n=== TRANSAKSI SEWA GODRIVE ===");
        System.out.println("Kendaraan Berhasil Disewa!");
        System.out.printf("Unit                    : %s (%s)%n", kendaraanDipilih.getNamaKendaraan(), kendaraanDipilih.getKodeKendaraan());
        System.out.printf("Lama Sewa               : %d hari%n", lamaSewa);

        double biayaHarian = lamaSewa * kendaraanDipilih.getHargaSewaPerHari();
        System.out.printf("Biaya Dasar Harian      : Rp %,.0f%n", biayaHarian);

        if (kendaraanDipilih instanceof Mobil){
            Mobil mobil = (Mobil) kendaraanDipilih;
            if (mobil.getJumlahKursi() > 5){
                System.out.printf("Tambahan Kursi (> 5): Rp 50,000%n");
            }
        } else if (kendaraanDipilih instanceof Motor){
            Motor motor = (Motor) kendaraanDipilih;
            if (motor.getJenisTransmisi().equalsIgnoreCase("Matik")){
                System.out.printf("Biaya Asuransi Matik: Rp %,.0f%n", 10000.0 * lamaSewa);
            }
        }

        if (vip) {
            System.out.printf("Diskon Member VIP (10%%) : -Rp %,.0f%n", diskonVIP);
        }

        if (lamaSewa > 7) {
            System.out.printf("Diskon > 7 Hari (5%%)    : -Rp %,.0f%n", diskonLebih7Hari);
        }

        System.out.println("--------------------------------");
        System.out.printf("TOTAL BIAYA AKHIR : Rp %,.0f%n", totalAkhir);
    }

    public void kembalikanKendaraan(String kode){
        for (Kendaraan k : daftarKendaraan){
            if (k.getKodeKendaraan().equalsIgnoreCase(kode)) {
                if (k.isTersedia()) {
                    System.out.println("[INFO] Kendaraan belum disewa.");
                    return;
                }

                k.setTersedia(true);

                System.out.println("[INFO] Kendaraan " + k.getNamaKendaraan() + " (" + kode + ") berhasil dikembalikan. Status: Tersedia.");
                return;
            }
        }
        System.out.println("[ERROR] Kendaraan tidak ditemukan.");
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        GoDriveRentalSystem sistem = new GoDriveRentalSystem();

        sistem.tambahKendaraan(new Mobil("MBL01", "Toyota Avanza", 350000, 7));
        sistem.tambahKendaraan(new Mobil("MBL02", "Daihatsu Sigra", 300000, 7));
        sistem.tambahKendaraan(new Mobil("MBL03", "Honda Brio", 280000, 5));

        sistem.tambahKendaraan(new Motor("MTR01", "Honda Vario", 80000, "Matik"));
        sistem.tambahKendaraan(new Motor("MTR02", "Yamaha NMAX", 100000, "Matik"));
        sistem.tambahKendaraan(new Motor("MTR03", "Kawasaki KLX", 90000, "Manual"));

        while (true){
            System.out.println("\n====== MENU GO DRIVE RENTAL SYSTEM ======");
            System.out.println("1. Tambah Kendaraan");
            System.out.println("2. Tampilkan Daftar Armada");
            System.out.println("3. Sewa Kendaraan");
            System.out.println("4. Kembalikan Kendaraan");
            System.out.println("5. Keluar");

            System.out.print("Pilih menu: ");

            int pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {
                case 1:
                    System.out.print("Masukkan jenis kendaraan (mobil/motor): ");
                    String jenis = input.nextLine();

                    System.out.print("Masukkan kode kendaraan: ");
                    String kode = input.nextLine();

                    System.out.print("Masukkan nama kendaraan: ");
                    String nama = input.nextLine();

                    System.out.print("Masukkan harga sewa per hari: ");
                    double harga = input.nextDouble();

                    input.nextLine();

                    if (jenis.equalsIgnoreCase("mobil")) {
                        System.out.print("Masukkan kapasitas kursi: ");
                        int kursi = input.nextInt();

                        input.nextLine();

                        Mobil mobil = new Mobil(kode, nama, harga, kursi);

                        sistem.tambahKendaraan(mobil);

                        System.out.println("[INFO] Kendaraan berhasil ditambahkan: " + mobil.getNamaKendaraan() + " (" + mobil.getKodeKendaraan() + ")");

                    } else if (jenis.equalsIgnoreCase("motor")){
                        System.out.print("Masukkan jenis transmisi: ");

                        String transmisi = input.nextLine();

                        Motor motor = new Motor(kode, nama, harga, transmisi);

                        sistem.tambahKendaraan(motor);

                        System.out.println("[INFO] Kendaraan berhasil ditambahkan: " + motor.getNamaKendaraan() + " (" + motor.getKodeKendaraan() + ")");

                    }
                    
                    break;
            
                case 2:
                    sistem.tampilkanDaftarKendaraan();

                    break;

                case 3:
                    try {
                        System.out.print("Masukkan kode kendaraan yang ingin disewa: ");
                        String kodeSewa = input.nextLine();

                        System.out.print("Masukkan durasi sewa (dalam hari): ");
                        int lamaSewa = input.nextInt();
                        
                        input.nextLine();

                        System.out.print("Apakah Anda Member VIP? (y/n): ");
                        String member = input.nextLine();

                        boolean vip = member.equalsIgnoreCase("y");

                        sistem.sewaKendaraan(kodeSewa, lamaSewa, vip);

                    } catch (KendaraanTidakTersediaException e){
                        System.out.println("Exception in thread \"main\" KendaraanTidakTersediaException: " + e.getMessage());
                        System.out.println("\tat GoDriveRentalSystem.sewaKendaraan(GoDriveRentalSystem.java:124)");
                        System.out.println("\tat Main.menuSewa(Main.java:90)");
                        System.out.println("\tat Main.main(Main.java:62)");
                    }

                    break;

                case 4: 
                    System.out.print("Masukkan kode kendaraan yang ingin dikembalikan: ");
                    String kodeKembali = input.nextLine();

                    sistem.kembalikanKendaraan(kodeKembali);

                    break;

                case 5:
                    System.out.print("Program selesai.");
                    System.exit(0);

                default:
                    System.out.print("Menu tidak valid.");
            }
        }
    }
}
