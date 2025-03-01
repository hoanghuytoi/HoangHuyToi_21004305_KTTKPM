package com.journaldev.singleton;

public class ThreadSafeSingleton {
    // Biến static lưu trữ instance của Singleton
    private static volatile ThreadSafeSingleton instance;

    // Constructor private để ngăn chặn việc tạo instance từ bên ngoài
    private ThreadSafeSingleton() {}

    // Phương thức lấy instance với Double-Checked Locking
    public static ThreadSafeSingleton getInstance() {
        if (instance == null) { // Kiểm tra lần đầu
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) { // Kiểm tra lần hai
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }

    // Phương thức hiển thị thông báo
    public void showMessage() {
        System.out.println("Hello from ThreadSafeSingleton!");
    }

    // Phương thức main để kiểm tra hoạt động của Singleton
    public static void main(String[] args) {
        ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance();
        singleton.showMessage();
    }
}
