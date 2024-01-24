package com.rbaun.banking.controller.customer;

import com.rbaun.banking.controller.BaseControllerAPI;
import com.rbaun.banking.controller.customer.request.CreateCustomerRequest;
import com.rbaun.banking.controller.customer.request.LookupCustomerRequest;
import com.rbaun.banking.controller.customer.request.UpdateCustomerRequest;
import com.rbaun.banking.controller.customer.response.CustomerResponse;
import com.rbaun.banking.controller.customer.response.DeleteCustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Operations for the Customer")
@RequestMapping(CustomerControllerAPI.BASE_URL)
public interface CustomerControllerAPI extends BaseControllerAPI {

    String BASE_URL = ROOT_URL + "/customers";
    String GET_ALL_CUSTOMERS_URL = "/all";
    String GET_CUSTOMERS_BY_SEARCH_TERM_URL = "/{searchTerm}";
    String CREATE_CUSTOMER_URL = "/create";
    String DELETE_CUSTOMER_URL = "/delete/{id}";
    String UPDATE_CUSTOMER_URL = "/update/{id}";

    /**
     * Request a list of all the customers
     * @return @{@link ResponseEntity} with a list of @{@link CustomerResponse}
     */
    @Operation(
            summary = "Get a list of all customers",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of customers",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No customers found"
                    )
            }
    )
    @GetMapping(GET_ALL_CUSTOMERS_URL)
    ResponseEntity<List<CustomerResponse>> getAllCustomers();

    /**
     * Request to search for a customer by a search term
     * @param searchTerm - the search term to match customers by
     * @return @{@link ResponseEntity} with a list of @{@link CustomerResponse} matching the search term
     */
    @Operation(
            summary = "Get a list of customers by search term",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of customers",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No customers found"
                    )
            }
    )
    @GetMapping(GET_CUSTOMERS_BY_SEARCH_TERM_URL)
    ResponseEntity<List<CustomerResponse>> getCustomersBySearchTerm(@PathVariable String searchTerm);

    /**
     * Request to create a new customer
     * @param request - the @{@link CreateCustomerRequest} containing the information to create a new customer
     * @return @{@link ResponseEntity} with a @{@link CustomerResponse} containing the newly created customer
     */
    @Operation(
            summary = "Create a new customer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer created",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request such as missing required fields or invalid format of email or phone number"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Customer already exists with the same email, phone number, or social security number"
                    )
            }
    )
    @PostMapping(CREATE_CUSTOMER_URL)
    ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest request);

    /**
     * Request to delete a customer by id
     * @param request - the @{@link LookupCustomerRequest} containing the id of the customer to delete
     * @return @{@link ResponseEntity} with a @{@link DeleteCustomerResponse} containing the id of the deleted customer
     */
    @Operation(
            summary = "Delete a customer by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer deleted",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request"
                    )
            }
    )
    @DeleteMapping(DELETE_CUSTOMER_URL)
    ResponseEntity<DeleteCustomerResponse> deleteCustomer(@RequestBody LookupCustomerRequest request);

    /**
     * Request to update a customer by social security number
     * @param request - the @{@link UpdateCustomerRequest} containing the information to update the customer
     * @return @{@link ResponseEntity} with a @{@link CustomerResponse} containing the updated customer
     */
    @Operation(
            summary = "Update a customer by social security number",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer updated",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Customer already exists with the same email, phone number, or social security number"
                    )
            }
    )
    @PutMapping(UPDATE_CUSTOMER_URL)
    ResponseEntity<CustomerResponse> updateCustomer(@Valid @RequestBody UpdateCustomerRequest request);

}
