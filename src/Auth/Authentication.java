package Auth;

import java.util.HashMap;
import java.util.Scanner;

public class Authentication {
    Scanner input = new Scanner(System.in);
    int pilih = 0;
    int banyakLogin = 0; // variabel data
    int banyakLogout = 0;

    HashMap<String, String> tabelAkun = new HashMap(); // objek
    HashMap<String, String> tabelSesiLogin = new HashMap<>();

    public boolean registerAkun(String email, String password) {
        System.out.println("\t\t Registrasi Akun");
        System.out.print("Masukkan E-mail : ");
        email = input.next();
        System.out.print("Masukkan Password : ");
        password = input.next();

        // periksa jiak email ada yg sama di data akun dan cek string karaker

        if (tabelAkun.containsKey(email) == false && email.endsWith("@umm.ac.id")) {
            tabelAkun.put(email, password);
            System.out.println("E-mail Berhasil Terdaftar");
            System.out.println("");
            loginAkun(email, password);
        } else {
            System.out.println("E-mail telah terdaftar atau Email tidak sesuai");
            registerAkun(email, password);
        }

        return false;
    }

    public boolean loginAkun(String email, String password) {
        System.out.println("\t\tPilihan Menu : ");
        System.out.println("\t***Gunakan Domain @umm.ac.id***");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Pilih : ");
        pilih = input.nextInt();
        switch (pilih) {
            case 1:
                System.out.println("\t\t Login ");
                System.out.print("Masukkan E-mail : ");
                email = input.next();
                System.out.print("Masukkan Password : ");
                password = input.next();

                // cek jika email dan password ada di tabel akun
                if (tabelAkun.containsKey(email) == true && tabelAkun.containsValue(password) == true) {
                    tabelSesiLogin.put(email, password); // simpan sesion di sesi login
                    banyakLogin += 1; // simpan banyak login
                    pilihan(email, password);
                } else {
                    System.out.println("Login Gagal");
                    loginAkun(email, password);
                    break;
                }
            case 2:
                registerAkun(email, password);
                break;
            case 3:
                System.exit(0);
            default:
                break;

        }
        return false;
    }

    public boolean hapusAkun(String email, String pastikanPassword) {
        System.out.println("\t\t Hapus Akun ");
        System.out.print("Masukkan E-mail : ");
        email = input.next();

        if (tabelAkun.containsKey(email) == true) { // cek email jika ada di tabel akun
            if (tabelSesiLogin.containsKey(email) == true) {
                System.out.println("Akun sedang digunakan"); // cek kodisi email jika digunain
                hapusAkun(email, pastikanPassword);
            }
            tabelAkun.remove(email);
            System.out.println("Berhasil Hapus Akun");
            pilihan(email, pastikanPassword);
        } else {
            System.out.println("Akun Tidak Ada"); // kondisi jika tidak ada di tabel akun
            hapusAkun(email, pastikanPassword);
        }
        return false;
    }

    public int totalEmail() {
        int count = 0; // data
        for (String key : tabelAkun.keySet()) { // mengamil key di hashmap
            if (key.endsWith("@umm.ac.id")) {
                count++;
            } else {
                count = count;
            }
        }
        System.out.println("Total Akun dengan E-mail UMM : " + count);
        return 0;
    }

    public boolean logoutAkun(String email) {
        System.out.println("\t\t Logout ");
        System.out.print("Masukkan E-mail : ");
        email = input.next();

        if (tabelSesiLogin.containsKey(email) == true) { // cek email
            tabelSesiLogin.remove(email);
            banyakLogout += 1; // simpan longout
            System.out.println("Logout Berhsil");
        } else {
            System.out.println("Logout Gagal");
            logoutAkun(email);
        }
        return false;
    }

    public int totalLogout() {
        System.out.println("Akun Logout : " + banyakLogout);
        return 0;
    }

    public int totalLogin() {
        System.out.println("Akun Login : " + banyakLogin);
        return 0;
    }

    public int totalAuth() {
        System.out.println("Total yang Sedang Login");
        System.out.println("Total : " + tabelSesiLogin.size());
        return 0;
    }

    public void pilihan(String email, String password) {
        System.out.println("\t\tMenu");
        System.out.println("1. Total Akun yang Login ");
        System.out.println("2. Total Akun yang Logout");
        System.out.println("3. Total Akun yang sedang Login");
        System.out.println("4. Total Akun dengan E-mail UMM");
        System.out.println("5. Hapus Akun");
        System.out.println("6. Logout");
        System.out.print("choose : ");
        pilih = input.nextInt();
        switch (pilih) {
            case 1:
                totalLogin();
                pilihan(email, password);
                break;
            case 2:
                totalLogout();
                pilihan(email, password);
                break;
            case 3:
                totalAuth();
                pilihan(email, password);
                break;
            case 4:
                totalEmail();
                pilihan(email, password);
                break;
            case 5:
                hapusAkun(email, password);
                break;
            case 6:
                logoutAkun(email);
                loginAkun(email, password);
                break;
            default:
                break;
        }

    }
}

/*
 * public boolean dataAkun() { // data Akun tabelAkun.put("labit@umm.ac.id",
 * "labitUwoke"); tabelAkun.put("laboranlab@umm.ac.id", "Lab_oyisam");
 * tabelAkun.put("instrukturlab@umm.ac.id", "instrukturkece"); return false; }
 */
