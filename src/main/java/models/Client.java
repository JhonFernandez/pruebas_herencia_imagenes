package models;

import javax.persistence.Entity;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Jhon on 3/7/2017.
 */
@Entity
public class Client extends User implements Serializable {

    private String clientFuntion;

    public Client() {
    }

    public Client(String email, String firstName, String lastName, String password, Date birthdate, String clientFuntion) {
        super(email, firstName, lastName, password, birthdate);
        this.clientFuntion = clientFuntion;
    }

    public String getClientFuntion() {
        return clientFuntion;
    }

    public void setClientFuntion(String clientFuntion) {
        this.clientFuntion = clientFuntion;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientFuntion='" + clientFuntion + '\''+
                super.toString()+
                '}';
    }
}
