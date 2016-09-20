package boymustafa.com.myapplication.SignUp;

/**
 * Created by Boy on 17/09/2016.
 */
public class UserTypeModel {

    private int id;
    private String type;

    public UserTypeModel() {
        this.id = 0;
        this.type = "";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
