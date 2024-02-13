package com.rbaun.banking.controller.v1;

import com.rbaun.banking.model.customer.Customer;

public interface BaseControllerAPI {
    String ROOT_URL = "/v1";

    /**
     * Get the username of the currently logged in user
     * @return the username
     */
    String getLoggedInUsername();

    Customer getLoggedInCustomer();
}
