package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PetStoreData {
    private Long petStoreId;
    private String petStoreName;
    private String petStoreAddress;
    private String petStoreCity;
    private String petStoreState;
    private String petStoreZip;
    private String petStorePhone;
    private Set<PetStoreCustomer> customers = new HashSet<>();
    private Set<PetStoreEmployee> employees = new HashSet<>();

    public PetStoreData(PetStore petStore) {
        this.petStoreId = petStore.getPetStoreId();
        this.petStoreName = petStore.getPetStoreName();
        this.petStoreAddress = petStore.getPetStoreAddress();
        this.petStoreCity = petStore.getPetStoreCity();
        this.petStoreState = petStore.getPetStoreState();
        this.petStoreZip = petStore.getPetStoreZip();
        this.petStorePhone = petStore.getPetStorePhone();

        for (Customer customer : petStore.getCustomers()) {
            customers.add(new PetStoreCustomer(customer));
        }

        for (Employee employee : petStore.getEmployees()) {
            employees.add(new PetStoreEmployee(employee));
        }
    }

    @Data
    @NoArgsConstructor
    public static class PetStoreCustomer {
        private Long customerId;
        private String customerFirstName;
        private String customerLastName;
        private String customerEmail;

        public PetStoreCustomer(Customer customer) {
            this.customerId = customer.getCustomerId();
            this.customerFirstName = customer.getCustomerFirstName();
            this.customerLastName = customer.getCustomerLastName();
            this.customerEmail = customer.getCustomerEmail();
        }
    }

    @Data
    @NoArgsConstructor
    public static class PetStoreEmployee {
        private Long employeeId;
        private String employeeFirstName;
        private String employeeLastName;
        private String employeePhone;
        private String employeeJobTitle;

        public PetStoreEmployee(Employee employee) {
            this.employeeId = employee.getEmployeeId();
            this.employeeFirstName = employee.getEmployeeFirstName();
            this.employeeLastName = employee.getEmployeeLastName();
            this.employeePhone = employee.getEmployeePhone();
            this.employeeJobTitle = employee.getEmployeeJobTitle();
        }
    }
}