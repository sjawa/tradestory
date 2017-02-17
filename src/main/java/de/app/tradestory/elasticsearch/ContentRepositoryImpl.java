package de.app.tradestory.elasticsearch;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.util.List;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

@Repository
class ContentRepositoryImpl implements ContentRepository {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    
    void test() {
    	SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .build();
		elasticsearchTemplate.queryForList(searchQuery, String.class);
		
		
	}

    
    public void deleteAll() {
    	elasticsearchTemplate.deleteIndex(Content.class);
    }
    
    public void save(Content content) {
    	
    	IndexQuery indexQuery = new IndexQueryBuilder()
    			.withIndexName("content")
    			.withType("content")
    			.withObject(content)
    			.build();
		elasticsearchTemplate.index(indexQuery);
		elasticsearchTemplate.refresh(Content.class);
    }
    
    public List<Content> findAll() {
    	SearchQuery query = new NativeSearchQueryBuilder()
    			.withQuery(matchAllQuery())
    			.build();
    	return elasticsearchTemplate.queryForList(query, Content.class);
    }
    
    public List<Content> find(String query) {
    	
    	QueryStringQueryBuilder builder = queryStringQuery(query);
    	builder.defaultOperator(Operator.AND);
    	builder.defaultField("text");
    	
    	SearchQuery searchQuery = new NativeSearchQueryBuilder()
    		
    			.withQuery(builder)
    			.build();
    	return elasticsearchTemplate.queryForList(searchQuery, Content.class);
    }


}
