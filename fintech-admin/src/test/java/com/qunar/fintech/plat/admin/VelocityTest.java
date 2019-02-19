package com.qunar.fintech.plat.admin;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class VelocityTest {

	private static final String domain = "src/main/webapp/WEB-INF/templates/common/generator/domain.java.vm";

	@Test
	public void readTest() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(domain)));
			String data = null;
			try {
				while((data = br.readLine())!=null)
                {
                    System.out.println(data);
                }
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
