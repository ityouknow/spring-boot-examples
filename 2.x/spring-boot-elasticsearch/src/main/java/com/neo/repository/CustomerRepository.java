
package com.neo.repository;

import com.neo.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {
	public List<Customer> findByAddress(String address);
	public Customer findByUserName(String userName);
	public int  deleteByUserName(String userName);
	public Page<Customer> findByAddress(String address, Pageable pageable);


}
