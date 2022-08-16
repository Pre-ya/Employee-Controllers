package EmpSteps;

import EmpModels.*;
import EmpUtils.Endpoints;
import EmpUtils.TestNGListeners;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class MyStepdefs {

    ObjectMapper objectMapper = new ObjectMapper();
    Employee employee;
    Address address;
    Phonenumber phonenumber;
    static Response response, delresponse;
    static Employee responseEmployee;


    @Given("Creating a Employee")
    public void creatingAEmployee() {
        JSONObject testData = (JSONObject) TestNGListeners.data.get("createEmployee");
        employee = new Employee(
                (String) testData.get("firstName"),
                (String) testData.get("lastName"),
                (Long) testData.get("age"),
                (Long) testData.get("noOfChildrens"),
                (Boolean) testData.get("spouse")
        );
        JSONObject address1 = (JSONObject) testData.get("address");
        address = new Address(
                (String) address1.get("streetAddress"),
                (String) address1.get("city"),
                (String) address1.get("state"),
                (String) address1.get("country"),
                (String) address1.get("postalCode"));
        employee.setAddress(address);

        JSONArray phoneNumber = (JSONArray) testData.get("phoneNumbers");
        JSONObject phoneNumber1 = (JSONObject) phoneNumber.get(0);
        phonenumber = new Phonenumber(
                (String) phoneNumber1.get("type"),
                (String) phoneNumber1.get("number"));
        employee.setPhoneNumbers(phoneNumber);

        JSONArray hobbies1 = (JSONArray) testData.get("hobbies");
        Arrays.asList((String) hobbies1.get(0), (String) hobbies1.get(1));
        employee.setHobbies(hobbies1);

        response = given()
                .body(employee)
                .when().post(Endpoints.EMPLOYEEENDPOINT)
                .then().body(matchesJsonSchemaInClasspath("employeeDetails_schema.json")).statusCode(201).extract().response();
    }

    @Then("the user is created")
    public void theUserIsCreated() throws JsonProcessingException {
        responseEmployee = objectMapper.readValue(response.asString(), Employee.class);
        Assert.assertEquals(employee.getFirstName(), responseEmployee.getFirstName());
        Assert.assertEquals(employee.getLastName(), responseEmployee.getLastName());
        Assert.assertEquals(employee.getAge(), responseEmployee.getAge());
        Assert.assertEquals(employee.getNoOfChildrens(), responseEmployee.getNoOfChildrens());
        Assert.assertEquals(employee.getAddress().getCity(), responseEmployee.getAddress().getCity());
        Assert.assertEquals(employee.getAddress().getStreetAddress(), responseEmployee.getAddress().getStreetAddress());
        Assert.assertEquals(employee.getAddress().getState(), responseEmployee.getAddress().getState());
        Assert.assertEquals(employee.getAddress().getPostalCode(), responseEmployee.getAddress().getPostalCode());
        Assert.assertEquals(employee.getAddress().getCountry(), responseEmployee.getAddress().getCountry());
        Assert.assertEquals(employee.getHobbies(), responseEmployee.getHobbies());

    }


    @When("creating a Employee with firstName Blank")
    public void creatingAEmployeeWithFirstNameBlank() {
        JSONObject testData = (JSONObject) TestNGListeners.data.get("EmployeeWithfirstNameBlank");
        employee = new Employee(
                (String) testData.get("firstName"),
                (String) testData.get("lastName"),
                (Long) testData.get("age"),
                (Long) testData.get("noOfChildrens"),
                (Boolean) testData.get("spouse")
        );
        JSONObject address1 = (JSONObject) testData.get("address");
        address = new Address(
                (String) address1.get("streetAddress"),
                (String) address1.get("city"),
                (String) address1.get("state"),
                (String) address1.get("country"),
                (String) address1.get("postalCode"));
        employee.setAddress(address);

        JSONArray phoneNumber = (JSONArray) testData.get("phoneNumbers");
        JSONObject phoneNumber1 = (JSONObject) phoneNumber.get(0);
        phonenumber = new Phonenumber(
                (String) phoneNumber1.get("type"),
                (String) phoneNumber1.get("number"));
        employee.setPhoneNumbers(phoneNumber);

        JSONArray hobbies1 = (JSONArray) testData.get("hobbies");
        Arrays.asList((String) hobbies1.get(0), (String) hobbies1.get(1));
        employee.setHobbies(hobbies1);

        response = given()
                .body(employee)
                .when().post(Endpoints.EMPLOYEEENDPOINT)
                .then().statusCode(400).extract().response();
    }


    @Then("Error is displayed when firstName is not given")
    public void errorIsDisplayedWhenFirstNameIsNotGiven() throws JsonProcessingException {
        ErrorObject errorObject = objectMapper.readValue(response.asString(), ErrorObject.class);
        Assert.assertEquals(errorObject.getMessage(), "First Name is required");
    }


    @When("creating a Employee with lastName Blank")
    public void creatingAEmployeeWithLastNameBlank() {
        JSONObject testData = (JSONObject) TestNGListeners.data.get("EmployeeWithlastNameBlank");
        employee = new Employee(
                (String) testData.get("firstName"),
                (String) testData.get("lastName"),
                (Long) testData.get("age"),
                (Long) testData.get("noOfChildrens"),
                (Boolean) testData.get("spouse")
        );
        JSONObject address1 = (JSONObject) testData.get("address");
        address = new Address(
                (String) address1.get("streetAddress"),
                (String) address1.get("city"),
                (String) address1.get("state"),
                (String) address1.get("country"),
                (String) address1.get("postalCode"));
        employee.setAddress(address);

        JSONArray phoneNumber = (JSONArray) testData.get("phoneNumbers");
        JSONObject phoneNumber1 = (JSONObject) phoneNumber.get(0);
        phonenumber = new Phonenumber(
                (String) phoneNumber1.get("type"),
                (String) phoneNumber1.get("number"));
        employee.setPhoneNumbers(phoneNumber);

        JSONArray hobbies1 = (JSONArray) testData.get("hobbies");
        Arrays.asList((String) hobbies1.get(0), (String) hobbies1.get(1));
        employee.setHobbies(hobbies1);

        response = given()
                .body(employee)
                .when().post(Endpoints.EMPLOYEEENDPOINT)
                .then().statusCode(400).extract().response();
    }


    @Then("Error is displayed when lastName is not given")
    public void errorIsDisplayedWhenLastNameIsNotGiven() throws JsonProcessingException {
        ErrorObject errorObject = objectMapper.readValue(response.asString(), ErrorObject.class);
        Assert.assertEquals(errorObject.getMessage(), "Last Name is required");
    }


    @When("creating a Employee with address Blank")
    public void creatingAEmployeeWithAddressBlank() {
        JSONObject testData = (JSONObject) TestNGListeners.data.get("EmployeewithAdressBlank");
        employee = new Employee(
                (String) testData.get("firstName"),
                (String) testData.get("lastName"),
                (Long) testData.get("age"),
                (Long) testData.get("noOfChildrens"),
                (Boolean) testData.get("spouse")
        );

        JSONArray phoneNumber = (JSONArray) testData.get("phoneNumbers");
        JSONObject phoneNumber1 = (JSONObject) phoneNumber.get(0);
        phonenumber = new Phonenumber(
                (String) phoneNumber1.get("type"),
                (String) phoneNumber1.get("number"));
        employee.setPhoneNumbers(phoneNumber);

        JSONArray hobbies1 = (JSONArray) testData.get("hobbies");
        Arrays.asList((String) hobbies1.get(0), (String) hobbies1.get(1));
        employee.setHobbies(hobbies1);

        response = given()
                .body(employee)
                .when().post(Endpoints.EMPLOYEEENDPOINT)
                .then().statusCode(400).extract().response();
    }

    @Then("Error is displayed when address is not given")
    public void errorIsDisplayedWhenAddressIsNotGiven() throws JsonProcessingException {
        ErrorObject errorObject = objectMapper.readValue(response.asString(), ErrorObject.class);
        Assert.assertEquals(errorObject.getMessage(), "Address is required");
    }

    @When("creating a Employee with phoneNumber Blank")
    public void creatingAEmployeeWithPhoneNumberBlank() {
        JSONObject testData = (JSONObject) TestNGListeners.data.get("EmployeewithphoneNumberBlank");
        employee = new Employee(
                (String) testData.get("firstName"),
                (String) testData.get("lastName"),
                (Long) testData.get("age"),
                (Long) testData.get("noOfChildrens"),
                (Boolean) testData.get("spouse")
        );
        JSONObject address1 = (JSONObject) testData.get("address");
        address = new Address(
                (String) address1.get("streetAddress"),
                (String) address1.get("city"),
                (String) address1.get("state"),
                (String) address1.get("country"),
                (String) address1.get("postalCode"));
        employee.setAddress(address);

        JSONArray hobbies1 = (JSONArray) testData.get("hobbies");
        Arrays.asList((String) hobbies1.get(0), (String) hobbies1.get(1));
        employee.setHobbies(hobbies1);

        response = given()
                .body(employee)
                .when().post(Endpoints.EMPLOYEEENDPOINT)
                .then().statusCode(400).extract().response();

    }

    @Then("Error is displayed when phoneNumber is not given")
    public void errorIsDisplayedWhenPhoneNumberIsNotGiven() throws JsonProcessingException {
        ErrorObject errorObject = objectMapper.readValue(response.asString(), ErrorObject.class);
        Assert.assertEquals(errorObject.getMessage(), "Phone Number is required");
    }


    @When("get the details of employee")
    public void getTheDetailsOfEmployee() {
        response = given()
                .when().get(Endpoints.EMPLOYEEENDPOINT)
                .then().statusCode(200).extract().response();
    }


    @When("get the details of employee with invalid Endpoint")
    public void getTheDetailsOfEmployeeWithInvalidEndpoint() {
            response = given()
                    .when().get(Endpoints.EMPLOYEEENDPOINT1)
                    .then().statusCode(404).extract().response();
    }

    @Then("Details of Employee displayed with invalid Endpoint")
    public void detailsOfEmployeeDisplayedWithInvalidEndpoint() throws JsonProcessingException {
        ErrorObject errorObject = objectMapper.readValue(response.asString(), ErrorObject.class);
        Assert.assertEquals(errorObject.getError(), "Not Found");
        Assert.assertEquals(errorObject.getPath(), "/employee");
    }

    @When("get the details of employee with ID")
    public void getTheDetailsOfEmployeeWithID() {
            response = given()
                    .when().get(Endpoints.EMPLOYEEENDPOINT + "/" + responseEmployee.getId())
                    .then().statusCode(200).extract().response();
        }


    @Then("Details of Employee displayed With ID")
    public void detailsOfEmployeeDisplayedWithID() throws JsonProcessingException  {
        responseEmployee = objectMapper.readValue(response.asString(), Employee.class);
        Assert.assertEquals(employee.getFirstName(), responseEmployee.getFirstName());
        Assert.assertEquals(employee.getLastName(), responseEmployee.getLastName());
        Assert.assertEquals(employee.getAge(), responseEmployee.getAge());
        Assert.assertEquals(employee.getNoOfChildrens(), responseEmployee.getNoOfChildrens());
        Assert.assertEquals(employee.getAddress().getCity(), responseEmployee.getAddress().getCity());
        Assert.assertEquals(employee.getAddress().getStreetAddress(), responseEmployee.getAddress().getStreetAddress());
        Assert.assertEquals(employee.getAddress().getState(), responseEmployee.getAddress().getState());
        Assert.assertEquals(employee.getAddress().getPostalCode(), responseEmployee.getAddress().getPostalCode());
        Assert.assertEquals(employee.getAddress().getCountry(), responseEmployee.getAddress().getCountry());
        Assert.assertEquals(employee.getHobbies(), responseEmployee.getHobbies());
    }

    @When("get the details of employees without using ID")
    public void getTheDetailsOfEmployeesWithoutUsingID() {
        response = given()
                .when().get(Endpoints.EMPLOYEEENDPOINT)
                .then().statusCode(200).extract().response();
    }

    @When("Update the user")
    public void updateTheUser() {
        JSONObject testData = (JSONObject) TestNGListeners.data.get("updateEmployee");
        employee = new Employee(
                (String) testData.get("firstName"),
                (String) testData.get("lastName"),
                (Long) testData.get("age"),
                (Long) testData.get("noOfChildrens"),
                (Boolean) testData.get("spouse")
        );
        JSONObject address1 = (JSONObject) testData.get("address");
        address = new Address(
                (String) address1.get("streetAddress"),
                (String) address1.get("city"),
                (String) address1.get("state"),
                (String) address1.get("country"),
                (String) address1.get("postalCode"));
        employee.setAddress(address);

        JSONArray phoneNumber = (JSONArray) testData.get("phoneNumbers");
        JSONObject phoneNumber1 = (JSONObject) phoneNumber.get(0);
        phonenumber = new Phonenumber(
                (String) phoneNumber1.get("type"),
                (String) phoneNumber1.get("number"));
        employee.setPhoneNumbers(phoneNumber);

        JSONArray hobbies1 = (JSONArray) testData.get("hobbies");
        Arrays.asList((String) hobbies1.get(0), (String) hobbies1.get(1));
        employee.setHobbies(hobbies1);

        response = given()
                .body(employee)
                .when().put(Endpoints.EMPLOYEEENDPOINT + "/" + responseEmployee.getId())
                .then().body(matchesJsonSchemaInClasspath("employeeDetails_schema.json")).statusCode(200).extract().response();
    }

    @Then("the user is updated")
    public void theUserIsUpdated() throws JsonProcessingException {
        responseEmployee = objectMapper.readValue(response.asString(), Employee.class);
        Assert.assertEquals(employee.getFirstName(), responseEmployee.getFirstName());
        Assert.assertEquals(employee.getLastName(), responseEmployee.getLastName());
        Assert.assertEquals(employee.getAge(), responseEmployee.getAge());
        Assert.assertEquals(employee.getNoOfChildrens(), responseEmployee.getNoOfChildrens());
        Assert.assertEquals(employee.getAddress().getCity(), responseEmployee.getAddress().getCity());
        Assert.assertEquals(employee.getAddress().getStreetAddress(), responseEmployee.getAddress().getStreetAddress());
        Assert.assertEquals(employee.getAddress().getState(), responseEmployee.getAddress().getState());
        Assert.assertEquals(employee.getAddress().getPostalCode(), responseEmployee.getAddress().getPostalCode());
        Assert.assertEquals(employee.getAddress().getCountry(), responseEmployee.getAddress().getCountry());
        Assert.assertEquals(employee.getHobbies(), responseEmployee.getHobbies());

    }

    @When("Updating a Employee without ID")
    public void updatingAEmployeeWithoutID() {
        JSONObject testData = (JSONObject) TestNGListeners.data.get("updateEmployee");
        employee = new Employee(
                (String) testData.get("firstName"),
                (String) testData.get("lastName"),
                (Long) testData.get("age"),
                (Long) testData.get("noOfChildrens"),
                (Boolean) testData.get("spouse")
        );
        JSONObject address1 = (JSONObject) testData.get("address");
        address = new Address(
                (String) address1.get("streetAddress"),
                (String) address1.get("city"),
                (String) address1.get("state"),
                (String) address1.get("country"),
                (String) address1.get("postalCode"));
        employee.setAddress(address);

        JSONArray phoneNumber = (JSONArray) testData.get("phoneNumbers");
        JSONObject phoneNumber1 = (JSONObject) phoneNumber.get(0);
        phonenumber = new Phonenumber(
                (String) phoneNumber1.get("type"),
                (String) phoneNumber1.get("number"));
        employee.setPhoneNumbers(phoneNumber);

        JSONArray hobbies1 = (JSONArray) testData.get("hobbies");
        Arrays.asList((String) hobbies1.get(0), (String) hobbies1.get(1));
        employee.setHobbies(hobbies1);

        response = given()
                .body(employee)
                .when().put(Endpoints.EMPLOYEEENDPOINT)
                .then().statusCode(405).extract().response();
    }


    @Then("Error is displayed when ID is not given")
    public void errorIsDisplayedWhenIDIsNotGiven() throws JsonProcessingException {
        ErrorObject errorObject = objectMapper.readValue(response.asString(), ErrorObject.class);
        Assert.assertEquals(errorObject.getError(), "Method Not Allowed");
        Assert.assertEquals(errorObject.getPath(), "/employees");
    }


    @When("Updating an Employee with firstName Blank")
    public void updatingAnEmployeeWithFirstNameBlank() {
        JSONObject testData = (JSONObject) TestNGListeners.data.get("EmployeefirstNameBlank");
        employee = new Employee(
                (String) testData.get("firstName"),
                (String) testData.get("lastName"),
                (Long) testData.get("age"),
                (Long) testData.get("noOfChildrens"),
                (Boolean) testData.get("spouse")
        );
        JSONObject address1 = (JSONObject) testData.get("address");
        address = new Address(
                (String) address1.get("streetAddress"),
                (String) address1.get("city"),
                (String) address1.get("state"),
                (String) address1.get("country"),
                (String) address1.get("postalCode"));
        employee.setAddress(address);

        JSONArray phoneNumber = (JSONArray) testData.get("phoneNumbers");
        JSONObject phoneNumber1 = (JSONObject) phoneNumber.get(0);
        phonenumber = new Phonenumber(
                (String) phoneNumber1.get("type"),
                (String) phoneNumber1.get("number"));
        employee.setPhoneNumbers(phoneNumber);

        JSONArray hobbies1 = (JSONArray) testData.get("hobbies");
        Arrays.asList((String) hobbies1.get(0), (String) hobbies1.get(1));
        employee.setHobbies(hobbies1);

        response = given()
                .body(employee)
                .when().put(Endpoints.EMPLOYEEENDPOINT + "/" + responseEmployee.getId())
                .then().statusCode(400).extract().response();
    }

    @Then("Error is displayed when firstName is not given While updating")
    public void errorIsDisplayedWhenFirstNameIsNotGivenWhileUpdating() throws JsonProcessingException {
        ErrorObject errorObject = objectMapper.readValue(response.asString(), ErrorObject.class);
        Assert.assertEquals(errorObject.getMessage(), "First Name is required");
    }

    @When("Updating an Employee with lastName Blank")
    public void updatingAnEmployeeWithLastNameBlank() {
        JSONObject testData = (JSONObject) TestNGListeners.data.get("EmployeelastNameBlank");
        employee = new Employee(
                (String) testData.get("firstName"),
                (String) testData.get("lastName"),
                (Long) testData.get("age"),
                (Long) testData.get("noOfChildrens"),
                (Boolean) testData.get("spouse")
        );
        JSONObject address1 = (JSONObject) testData.get("address");
        address = new Address(
                (String) address1.get("streetAddress"),
                (String) address1.get("city"),
                (String) address1.get("state"),
                (String) address1.get("country"),
                (String) address1.get("postalCode"));
        employee.setAddress(address);

        JSONArray phoneNumber = (JSONArray) testData.get("phoneNumbers");
        JSONObject phoneNumber1 = (JSONObject) phoneNumber.get(0);
        phonenumber = new Phonenumber(
                (String) phoneNumber1.get("type"),
                (String) phoneNumber1.get("number"));
        employee.setPhoneNumbers(phoneNumber);

        JSONArray hobbies1 = (JSONArray) testData.get("hobbies");
        Arrays.asList((String) hobbies1.get(0), (String) hobbies1.get(1));
        employee.setHobbies(hobbies1);

        response = given()
                .body(employee)
                .when().put(Endpoints.EMPLOYEEENDPOINT + "/" + responseEmployee.getId())
                .then().statusCode(400).extract().response();

    }

    @Then("Error is displayed when lastName is not given While updating")
    public void errorIsDisplayedWhenLastNameIsNotGivenWhileUpdating() throws JsonProcessingException {
        ErrorObject errorObject = objectMapper.readValue(response.asString(), ErrorObject.class);
        Assert.assertEquals(errorObject.getMessage(), "Last Name is required");
    }



    @When("Updating an Employee with phoneNumber Blank")
    public void updatingAnEmployeeWithPhoneNumberBlank() {
        JSONObject testData = (JSONObject) TestNGListeners.data.get("EmployeephoneNumberBlank");
        employee = new Employee(
                (String) testData.get("firstName"),
                (String) testData.get("lastName"),
                (Long) testData.get("age"),
                (Long) testData.get("noOfChildrens"),
                (Boolean) testData.get("spouse")
        );
        JSONObject address1 = (JSONObject) testData.get("address");
        address = new Address(
                (String) address1.get("streetAddress"),
                (String) address1.get("city"),
                (String) address1.get("state"),
                (String) address1.get("country"),
                (String) address1.get("postalCode"));
        employee.setAddress(address);

        JSONArray hobbies1 = (JSONArray) testData.get("hobbies");
        Arrays.asList((String) hobbies1.get(0), (String) hobbies1.get(1));
        employee.setHobbies(hobbies1);

        response = given()
                .body(employee)
                .when().put(Endpoints.EMPLOYEEENDPOINT + "/" + responseEmployee.getId())
                .then().statusCode(400).extract().response();


    }


    @Then("Error is displayed when phoneNumber is not given While updating")
    public void errorIsDisplayedWhenPhoneNumberIsNotGivenWhileUpdating() throws JsonProcessingException{
        ErrorObject errorObject = objectMapper.readValue(response.asString(), ErrorObject.class);
        Assert.assertEquals(errorObject.getMessage(), "Phone Number is required");

    }



    @When("Deleting an Employee without ID")
    public void deletingAnEmployeeWithoutID() {
        delresponse = given()
                .when().delete(Endpoints.EMPLOYEEENDPOINT)
                .then().statusCode(405).extract().response();
    }

    @Then("Error is displayed when ID is not given While Deleting a Employee")
    public void errorIsDisplayedWhenIDIsNotGivenWhileDeletingAEmployee() throws JsonProcessingException{
        ErrorObject errorObject = objectMapper.readValue(delresponse.asString(), ErrorObject.class);
        Assert.assertEquals(errorObject.getError(), "Method Not Allowed");
        Assert.assertEquals(errorObject.getPath(), "/employees");
    }

    @Then("the user is deleted")
    public void theUserIsDeleted() {
        delresponse = given()
                .when().delete(Endpoints.EMPLOYEEENDPOINT + "/" + responseEmployee.getId())
                .then().statusCode(200).extract().response();
    }

}

