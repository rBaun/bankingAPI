package com.rbaun.banking.service.customer.specification;

import com.rbaun.banking.model.customer.Customer;
import com.rbaun.banking.service.specification.Specification;

public interface CustomerSpecification extends Specification<Customer> {

    @Override
    default CustomerSpecification and(Specification<Customer> other) {
        return customer -> this.isSatisfiedBy(customer) && other.isSatisfiedBy(customer);
    }

    @Override
    default CustomerSpecification or(Specification<Customer> other) {
        return customer -> this.isSatisfiedBy(customer) || other.isSatisfiedBy(customer);
    }

    @Override
    default CustomerSpecification not() {
        return customer -> !this.isSatisfiedBy(customer);
    }

}
