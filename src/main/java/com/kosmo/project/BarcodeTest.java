package com.kosmo.project;

import java.io.File;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class BarcodeTest {

	public static void main(String[] args) {
		try {
			for (int i = 0; i < 10; i++) {
				String timestamp = System.currentTimeMillis() + "";
				Barcode barcode = BarcodeFactory.createCode128(timestamp);
				File file = new File("C:/" + timestamp + ".jpg");
				BarcodeImageHandler.saveJPEG(barcode, file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
