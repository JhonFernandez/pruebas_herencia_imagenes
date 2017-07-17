package models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by Jhon on 3/7/2017.
 */
@Entity
public class Host extends User implements Serializable{

    private String hostFuntion;

    public Host() {
    }

    public Host(String email, String firstName, String lastName, String password, Date birthdate, String hostFuntion) {
        super(email, firstName, lastName, password, birthdate);
        this.hostFuntion = hostFuntion;
    }

    public String getHostFuntion() {
        return hostFuntion;
    }

    public void setHostFuntion(String hostFuntion) {
        this.hostFuntion = hostFuntion;
    }

    @Override
    public String toString() {
        return "Host{" +
                "hostFuntion='" + hostFuntion + '\'' +
                super.toString()+
                '}';
    }
}
