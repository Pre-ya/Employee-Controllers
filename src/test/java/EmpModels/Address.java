package EmpModels;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Address {

    private int id;
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    public Address(){

    }
    public Address(String streetAddress, String city, String state, String country,String postalCode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
    }
}
