/*
 * Copyright 2012-2013 the original author or authors.
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

package de.app.tradestory.elasticsearch;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "content", type = "content", shards = 1, replicas = 0, refreshInterval = "-1")
public class Content {

	@Id
	private String id;

	private String text;

	private String teaser;
	
	private String author;

	public Content() {
	}

	public Content(String teaser, String text, String author) {
		this.text = text;
		this.teaser = teaser;
		this.author = author;
	}

	public String getShortText(){
		return StringUtils.abbreviate(text, 200);
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String firstName) {
		this.text = firstName;
	}

	public String getTeaser() {
		return this.teaser;
	}

	public void setTeaser(String lastName) {
		this.teaser = lastName;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getAuthor() {
		return author;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", text=" + text + ", teaser=" + teaser + "]";
	}

	

}
