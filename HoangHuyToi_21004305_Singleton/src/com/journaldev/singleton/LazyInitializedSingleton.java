package com.journaldev.singleton;

public class LazyInitializedSingleton {
    // Biến lưu trữ instance duy nhất của Singleton
    private static volatile LazyInitializedSingleton instance;

    // Constructor private để ngăn tạo đối tượng từ bên ngoài
    private LazyInitializedSingleton() {}

    // Phương thức công khai để lấy instance duy nhất của Singleton
    public static LazyInitializedSingleton getInstance() {
        if (instance == null) {
            synchronized (LazyInitializedSingleton.class) {
                if (instance == null) {
                    instance = new LazyInitializedSingleton();
                }
            }
        }
        return instance;
    }

    // Phương thức hiển thị thông báo
    public void showMessage() {
        System.out.println("Hello from LazyInitializedSingleton!");
    }

    // Phương thức main để kiểm tra hoạt động của Singleton
    public static void main(String[] args) {
        LazyInitializedSingleton singleton = LazyInitializedSingleton.getInstance();
        singleton.showMessage();
    }
}
