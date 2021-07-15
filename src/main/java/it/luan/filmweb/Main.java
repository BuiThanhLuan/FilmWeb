package it.luan.filmweb;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("/home/luanit/OneDrivePersonal/Pictures/Saved pictures");
        File[] files = file.listFiles();

        for (int i = 0; i < files.length; i++) {
            String name = files[i].getName().substring(7);
            files[i].renameTo(new File("/home/luanit/OneDrivePersonal/Pictures/" + name));
        }
    }
}
