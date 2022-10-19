package com.neo.service;

import com.neo.model.Customer;

import java.util.List;

public interface CustomersInterface {

    public List<Customer> searchCity(Integer pageNumber, Integer pageSize, String searchContent);


}
