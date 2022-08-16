package EmpModels;

import lombok.Getter;

@Getter
public class ErrorObject {

    private String message;
    private String error;
    private String timestamp;
    private int status;
    private String path;
}
