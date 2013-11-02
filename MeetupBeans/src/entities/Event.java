package entities;
import java.io.*;
import javax.persistence.*;

/**
 * An event object as represented in the database.
 */
@Entity
@Table(name = "Events")
public class Event implements Serializable{
	
	/** Generated Serial Version UID. */
	private static final long serialVersionUID = -6559004705492527656L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column (name="NAME")
	private String name;
	
	@Column (name="DESCRIPTION")
	private String description;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long newID) { 
		this.id = newID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String newDescription) {
		description = newDescription;
	}
}
