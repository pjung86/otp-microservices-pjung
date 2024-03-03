package com.pjung.coreservice;

import com.pjung.coreservice.util.Base64Utility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreServiceApplication.class, args);

		Base64Utility base64Utility = new Base64Utility();

		String encoded = base64Utility.Base64Encoder("teszt.cecilia@otpmobil.com&3000&E68560872BDB2DF2FFE7ADC091755378");
		System.out.println("Encoded string " + encoded);

		String decoded = base64Utility.Base64Decoder(encoded);
		System.out.println("Decoded string: " + decoded);
	}
}
