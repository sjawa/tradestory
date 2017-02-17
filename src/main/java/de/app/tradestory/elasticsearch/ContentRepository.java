package de.app.tradestory.elasticsearch;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
interface ContentRepository {

	void deleteAll();

	void save(Content content);

	List<Content> findAll();

	List<Content> find(String query);

}
