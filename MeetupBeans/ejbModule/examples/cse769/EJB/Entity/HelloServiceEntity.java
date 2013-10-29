package examples.cse769.EJB.Entity;
import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "MessageTable")
public class HelloServiceEntity implements Serializable{
	@Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	public Long getId() {return id;}

	public void setId(Long newID) { this.id = newID;}
	private String theMessage;
	public String getTheMessage() {
		return theMessage;
	}
	public void setTheMessage(String message) {
		    this.theMessage = message;
	}
}
