package com.neo.repository;


import com.neo.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository repository;

    @Test
    public void saveCustomers() {
        repository.save(new Customer("Alice", "北京"));
        repository.save(new Customer("Bob", "北京"));
        repository.save(new Customer("neo", "西安"));
        repository.save(new Customer("summer", "烟台"));
    }

    @Test
    public void deleteCustomers() {
//        repository.deleteAll();
        repository.deleteByUserName("neo");
    }

    @Test
    public void updateCustomers() {
        Customer customer= repository.findByUserName("summer");
        System.out.println(customer);
        customer.setAddress("北京市海淀区西直门");
        repository.save(customer);
        Customer xcustomer=repository.findByUserName("summer");
        System.out.println(xcustomer);
    }

    @Test
    public void fetchAllCustomers() {
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();
    }

    @Test
    public void fetchIndividualCustomers() {
        System.out.println("Customer found with findByUserName('summer'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByUserName("summer"));

        System.out.println("Customers found with findByAddress(\"北京\"):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByAddress("北京")) {
            System.out.println(customer);
        }
    }

}
