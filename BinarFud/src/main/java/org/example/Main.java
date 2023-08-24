package org.example;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        SetQuery setQuery = new SetQuery();
        List<String> makanan = setQuery.getMenuData("nama_makanan");
        List<String > harga = setQuery.getMenuData("harga");
        String username = "";
        boolean stat = false;

        do {
            switch (menu.loginMenu()) {
                case 1 :
                    clearConsole();
                    String pilihan = menu.login();
                    stat = login(!pilihan.equals(""));
                    username = pilihan;
                    break;
                case 2 :
                    clearConsole();
                    daftar(menu.daftar());
                    stat = true;
                    break;
                case 3 :
                    clearConsole();
                    menu.closeScanner();
                    return;
                default:
                    clearConsole();
                    wrongOption();
                    stat = true;
                    break;
            }
        } while (stat);

        do {
            Integer pilihan = menu.mainMenu();
            if (pilihan.equals(99)) {
                do {
                    switch (menu.konfirPemb(username)){
                        case 1 :
                            clearConsole();
                            confirm(menu.konfirmasi(username));
                            stat = false;
                            break;
                        case 2 :
                            clearConsole();
                            stat = false;
                            break;
                        case 0 :
                            clearConsole();
                            menu.closeScanner();
                            return;
                        default:
                            clearConsole();
                            wrongOption();
                            stat = true;
                            break;
                    }
                } while (stat);
                stat = true;
            } else if (pilihan.equals(0)) {
                clearConsole();
                menu.closeScanner();
                return;
            } else if ((pilihan <= makanan.size()) && (pilihan > 0)) {
                clearConsole();
                quantity(menu.tambahPesanan(username, pilihan));
                stat = true;
            } else {
                clearConsole();
                wrongOption();
                stat = true;
            }
        } while (stat);

    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static boolean login(boolean loginParameter) {
        if (loginParameter) {
            System.out.println("Login Berhasil!");
            return false;
        } else {
            System.out.println("Login Gagal!");
            return true;
        }
    }

    public static void daftar(boolean daftarParameter) {
        if (daftarParameter) {
            System.out.println("Daftar Berhasil!");
        } else {
            System.out.println("Daftar Gagal!\nUsername sudah digunakan!");
        }
    }

    public static void wrongOption() {
        System.out.println("Pilihan tidak tersedia");
    }

    public static void quantity(boolean quantityParameter) {
        if (quantityParameter) {
            System.out.println("Pemesanan berhasil!");
        }
    }

    public static void confirm(boolean confirmParameter) {
        if (confirmParameter) {
            System.out.println("Pembayaran Berhasil!");
        }
    }
}
