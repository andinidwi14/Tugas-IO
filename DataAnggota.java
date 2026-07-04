import java.io.BufferedReader;   
import java.io.BufferedWriter;   
import java.io.File;             
import java.io.FileReader;       
import java.io.FileWriter;       
import java.io.IOException;      
import java.io.PrintWriter;      
import java.util.ArrayList;      
import java.util.List;           

public class DataAnggota {

    // Nama file txt tempat data anggota disimpan, dibuat private karena hanya dipakai di dalam class ini
    private String namaFile = "anggota.txt";

    private boolean cekIdSudahAda(String id) {
        List<Anggota> daftarAnggota = bacaAnggota();
        // memanfaatkan method bacaAnggota() yang sudah ada untuk mengambil seluruh data di file

        for (Anggota a : daftarAnggota) {
            if (a.getId().equalsIgnoreCase(id)) {
                return true; 
            }
        }
        return false;
    }

    public void simpanAnggota(Anggota anggota) {

        if (cekIdSudahAda(anggota.getId())) {
            // jika id sudah pernah tersimpan sebelumnya, batalkan proses simpan
            System.out.println("Data dengan ID " + anggota.getId() + " (" + anggota.getNama() + ") sudah ada di file, tidak disimpan ulang.");
            return; // keluar dari method, baris di bawah tidak dijalankan
        }

        // try-with-resources -> otomatis menutup file setelah selesai dipakai, walau terjadi error
        try (
            FileWriter fw = new FileWriter(namaFile, true);  
            BufferedWriter bw = new BufferedWriter(fw);       
            PrintWriter pw = new PrintWriter(bw)               
        ) {
            pw.println(anggota.toFileFormat());
            // menulis 1 baris ke file dengan format "id,nama,jenis,mataKuliah", lalu pindah baris baru

            System.out.println("Data " + anggota.getNama() + " (" + anggota.getJenis() + ") berhasil disimpan ke file.");
        } catch (IOException e) {
            // dijalankan hanya jika terjadi error saat menulis file
            System.out.println("Gagal menyimpan data " + anggota.getNama() + ": " + e.getMessage());
        }
    }

    public List<Anggota> bacaAnggota() {
        List<Anggota> daftarAnggota = new ArrayList<>();
        // wadah kosong yang nanti diisi dengan objek Anggota hasil pembacaan file

        File file = new File(namaFile);
        // membuat referensi ke file "anggota.txt" untuk dicek keberadaannya

        if (!file.exists()) {
            return daftarAnggota; 
        }

        try (
            FileReader fr = new FileReader(file);     
            BufferedReader br = new BufferedReader(fr)  
        ) {
            String baris; 

            while ((baris = br.readLine()) != null) {

                String[] data = baris.split(",");
                
                if (data.length == 4) {
                    String id = data[0];           
                    String nama = data[1];         
                    String jenis = data[2];        
                    String mataKuliah = data[3];   

                    Anggota anggota = new Anggota(id, nama, jenis, mataKuliah);

                    daftarAnggota.add(anggota);
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca data: " + e.getMessage());
        }

        return daftarAnggota;
    }

    public void tampilkanAnggota(List<Anggota> daftarAnggota) {

        if (daftarAnggota.isEmpty()) {
            System.out.println("Belum ada data anggota.");
            return; 
        }

       
        for (Anggota a : daftarAnggota) {

            String keterangan;
            if (a.getJenis().equalsIgnoreCase("Mahasiswa")) {
                keterangan = "Mahasiswa, sedang mengambil mata kuliah " + a.getMataKuliah();
            } else if (a.getJenis().equalsIgnoreCase("Dosen")) {
                keterangan = "Dosen, mengajar mata kuliah " + a.getMataKuliah();
            } else if (a.getJenis().equalsIgnoreCase("Karyawan")) {
                keterangan = "Karyawan, bekerja di bagian administrasi";
            } else {
                keterangan = "Jenis anggota tidak diketahui";
            }

            System.out.println("ID: " + a.getId() + " | Nama: " + a.getNama() + " -> " + keterangan);
        }
    }
}
