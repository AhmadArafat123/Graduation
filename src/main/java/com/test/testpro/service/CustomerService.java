package com.test.testpro.service;

import com.test.testpro.model.Customer;
import com.test.testpro.repository.CustomerRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@EnableTransactionManagement
@EnableRetry
@Transactional(isolation = Isolation.SERIALIZABLE)
public class CustomerService {

    private final CustomerRepository userRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    public CustomerService(CustomerRepository userRepository, ElasticsearchOperations elasticsearchOperations) {
        this.userRepository = userRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }
    public Optional<Customer> getUser(long id){
        return userRepository.findById(id);
    }

    public void save(Customer user) {
        userRepository.save(user);
    }

    public void updateUser(long id) {
        Optional<Customer> user = userRepository.findById(id);
        if(user.isPresent()){
            Customer u = user.get();
            u.setCity("Nablus");

        }

    }

    public Customer createUser(Customer user) {
        userRepository.save(user);
        return user;
    }

    public String deleteUser(long id) {
        Optional<Customer> user=userRepository.findById(id);
        if (user.isPresent()){
            userRepository.delete(user.get());
            return "User deleted";
        }
        return "User not found";
    }
    //////////////////////////////////////////////
    public List<String> fetchSuggestions(String query) {
        query=query.toLowerCase(Locale.ROOT);

        QueryBuilder queryBuilder = QueryBuilders
                .wildcardQuery("name", query+"*");
        System.out.println("QueryBul "+queryBuilder);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .withPageable(PageRequest.of(0, 5))
                .build();
        System.out.println("sear "+searchQuery);

        SearchHits<Customer> searchSuggestions =
                elasticsearchOperations.search(searchQuery,
                        Customer.class,
                        IndexCoordinates.of("user"));
        System.out.println("searSuq "+searchSuggestions);

        List<String> suggestions = new ArrayList<>();

        searchSuggestions.getSearchHits().forEach(searchHit-> suggestions.add(searchHit.getContent().getUserName()));
        System.out.println(suggestions);
        return suggestions;
    }
}


