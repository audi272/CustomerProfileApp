package com.encora.customer.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encora.customer.model.Customer;
import com.encora.customer.repository.CustomerRepository;
import com.encora.customer.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		return customer.orElse(null);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Long id, Customer customer) {
		Optional<Customer> existingCustomer = customerRepository.findById(id);
		if (existingCustomer.isPresent()) {
			customer.setId(id);
			return customerRepository.save(customer);
		} else {
			return null;
		}
	}

	@Override
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}
}
