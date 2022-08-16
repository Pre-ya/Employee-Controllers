package EmpModels;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Phonenumber {

    private int id;
    private String type;
    private String number;

    public Phonenumber(){
    }

    public Phonenumber(String type, String number) {
        this.type = type;
        this.number = number;
    }
}
