package com.journaldev.singleton;

public class EagerInitializedSingleton {
    // Constructor private để ngăn tạo đối tượng từ bên ngoài
    private EagerInitializedSingleton() {}

    // Lớp nội bộ giúp giữ instance của Singleton
    private static class SingletonHelper {
        // Instance duy nhất của Singleton
        private static final EagerInitializedSingleton INSTANCE = new EagerInitializedSingleton();
    }

    // Phương thức công khai để lấy instance duy nhất của Singleton
    public static EagerInitializedSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    // Phương thức hiển thị thông báo
    public void showMessage() {
        System.out.println("Hello World from Singleton!");
    }

    // Phương thức main để kiểm tra hoạt động của Singleton
    public static void main(String[] args) {
        EagerInitializedSingleton singleton = EagerInitializedSingleton.getInstance();
        singleton.showMessage();
    }
}
