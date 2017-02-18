/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.app.tradestory;

import java.io.File;
import java.io.IOException;

import jxl.WorkbookSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.app.tradestory.elasticsearch.Content;
import de.app.tradestory.elasticsearch.ContentRepository;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@SpringBootApplication
public class SearchApplication implements CommandLineRunner {

	@Autowired
	private ContentRepository repository;

	@Override
	public void run(String... args) throws Exception {
		importContent();
		//fetchAllContent();
		
	}

	private void importContent() {

		repository.deleteAll();
		saveContent();
	}

	private void saveContent() {

		Workbook workbook = null;
		try {

			WorkbookSettings ws = new WorkbookSettings();
			ws.setEncoding("Cp1252");
			workbook = Workbook.getWorkbook(new File("src/main/resources/content1.xls"), ws);

			Sheet sheet = workbook.getSheet(0);

			int rows = sheet.getRows();

			for (int index = 1; index < rows; index++) {
				Cell[] row = sheet.getRow(index);
				String title = row[0].getContents();
				String text = row[1].getContents();
				String author = row[2].getContents();
				System.out.println("saving content");
				this.repository.save(new Content(title, text, author));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} finally {

			if (workbook != null) {
				workbook.close();
			}

		}

		//this.repository.save(new Content("Ein guter Deutscher in Buchenwald", "Alice"));
		//this.repository.save(new Content("Smith", "Bob"));
	}

	private void fetchAllContent() {
		System.out.println("Content found with findAll():");
		System.out.println("-------------------------------");
		for (Content customer : this.repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SearchApplication.class, "--debug");
	}

}
