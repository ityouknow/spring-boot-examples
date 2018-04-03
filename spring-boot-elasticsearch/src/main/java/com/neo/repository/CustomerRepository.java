
package com.neo.repository;

import com.neo.model.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {

	public Customer findByUserName(String userName);

	public int  deleteByUserName(String userName);

	public List<Customer> findByAddress(String address);

}
