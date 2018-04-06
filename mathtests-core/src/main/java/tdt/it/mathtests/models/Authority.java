package tdt.it.mathtests.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Created by Tu Nguyen on 2018-03-25.
 */

@Entity
@Table(name="M_AUTHORITY")
public class Authority implements GrantedAuthority , Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1923533144129921455L;

	@Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NAME")
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return this.name;
    }
    
    @Override
    public boolean equals(Object obj) {
    	// TODO Auto-generated method stub
    	if (this == obj)
    		return true;
    	if (obj == null)
    		return false;
    	if (!this.getClass().equals(obj.getClass()))
    		return false;
    	Authority other = (Authority) obj;
    	return this.id == other.id;
    }
}
