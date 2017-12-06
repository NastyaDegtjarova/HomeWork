package net.proselyte.hibernate.controller;

import java.io.Closeable;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Nastya on 06.12.2017.
 */
public class AbstractController implements Closeable {
    private Scanner scan;

    public AbstractController() {
        this.scan = new Scanner(System.in);
    }

    public Scanner getScan() {
        return scan;
    }

    @Override
    public void close() throws IOException {
        try {
            if (scan != null) {
                scan.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
