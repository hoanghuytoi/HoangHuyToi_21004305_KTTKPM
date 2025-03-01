package com.journaldev.singleton;

public class StaticBlockSingleton {
    private static StaticBlockSingleton instance;

    private StaticBlockSingleton() {}

    // Khối static để khởi tạo instance với xử lý ngoại lệ
    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating singleton instance");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }

    // Phương thức hiển thị thông báo
    public void showMessage() {
        System.out.println("Hello from StaticBlockSingleton!");
    }

    // Phương thức main để chạy chương trình
    public static void main(String[] args) {
        StaticBlockSingleton singleton = StaticBlockSingleton.getInstance();
        singleton.showMessage();
    }
}
