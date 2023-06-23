package com.unir.apirest_operaciones.data;

import java.util.List;
import java.util.Optional;

import com.unir.apirest_operaciones.model.customers.customer.Customer;
import com.unir.apirest_operaciones.model.rentals.rental.Rental;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;
import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class DataAccessRepository {

    private final ICustomerRepository cRepository;
    private final IRentalRepository rRepository;
    private final ElasticsearchOperations elasticClient;

    private final String[] textSearchFieldsClient = { "client.search", "client.search._2gram", "client.search._3gram" };
    private final String[] textSearchFieldsMovie = { "movie.search", "movie.search._2gram", "movie.search._3gram" };
    private final String[] textSearchFieldsNombre = { "Nombre.search", "Nombre.search._2gram", "Nombre.search._3gram" };
    private final String[] textSearchFieldsApellido = { "Apellido.search", "Apellido.search._2gram", "Apellido.search._3gram" };

    public Customer save(Customer customer) {
        return cRepository.save(customer);
    }
    public Rental save(Rental rental) { return rRepository.save(rental);  }

    public Boolean delete(Customer customer) {
        cRepository.delete(customer);
        return Boolean.TRUE;
    }
    public Boolean delete(Rental rental) {
        rRepository.delete(rental);
        return Boolean.TRUE;
    }

    public List<Customer> findCustomer(String nombre, String apellido, String documento) {

        BoolQueryBuilder querySpec = QueryBuilders.boolQuery();

        if (!StringUtils.isEmpty(nombre)) {
            querySpec.must(QueryBuilders.multiMatchQuery(nombre, textSearchFieldsNombre).type(Type.BOOL_PREFIX));
        }

        if (!StringUtils.isEmpty(apellido)) {
            querySpec.must(QueryBuilders.multiMatchQuery(apellido, textSearchFieldsApellido).type(Type.BOOL_PREFIX));
        }

        if (!StringUtils.isEmpty(documento)) {
            querySpec.must(QueryBuilders.termQuery("documento", documento));
        }

        //para que coincida con todos
        if (!querySpec.hasClauses()) {
            querySpec.must(QueryBuilders.matchAllQuery());
        }

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(querySpec);

        Query query = nativeSearchQueryBuilder.build();
        SearchHits<Customer> result = elasticClient.search(query, Customer.class);

        return result.getSearchHits().stream().map(SearchHit::getContent).toList();
    }

    public List<Rental> findRentals(String client, String movie) {

        BoolQueryBuilder querySpec = QueryBuilders.boolQuery();

        if (!StringUtils.isEmpty(client)) {
            querySpec.must(QueryBuilders.multiMatchQuery(client, textSearchFieldsClient).type(Type.BOOL_PREFIX));
        }

        if (!StringUtils.isEmpty(movie)) {
            querySpec.must(QueryBuilders.multiMatchQuery(movie, textSearchFieldsMovie).type(Type.BOOL_PREFIX));
        }

        //para que coincida con todos
        if (!querySpec.hasClauses()) {
            querySpec.must(QueryBuilders.matchAllQuery());
        }

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(querySpec);

        Query query = nativeSearchQueryBuilder.build();
        SearchHits<Rental> result = elasticClient.search(query, Rental.class);

        return result.getSearchHits().stream().map(SearchHit::getContent).toList();
    }


    public Optional<Rental> findById(String id) {
        return rRepository.findById(id);
    }public Optional<Customer> findCustomerById(String id) {
        return cRepository.findById(id);
    }

}

