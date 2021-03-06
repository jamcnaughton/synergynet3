package synergynet3.web.shared;

import java.io.Serializable;

/**  
 * Representation of a students. This class is comparable so that instances of it can be ordered by their name.
 */
public class Student implements Comparable<Student>, Serializable {
	
	/** Generated unique serial id for serialisation. */
	private static final long serialVersionUID = -2996637256646940175L;
	
	/** Name of the student. */	
	private String name = "New Student";
	 
	/** The device the student is currently logged in on.   */
	private String table = "none";
	
	/** The name of the class the student belongs to. */
	private String className = "none";
	
	/** The name of the colour to be used for any menu's representing the student. */
	private String colour = "white";
	
	/** Unique ID of the student. */
	private String studentID = "";

	/**
	 * Set the name of the student.
	 * 
	 * @param name The name of the student.
	 */
	public void setName(String name){
		this.name = name;
	}	
	
	/**
	 * Get the name of the student.
	 * 
	 * @return String representing the name of the student.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of the class the student belongs to.
	 * 
	 * @param className The name of the class the student belongs to.
	 */
	public void setClassName(String className){
		this.className = className;
	}	

	@Override
	public int compareTo(Student otherStudent) {
        int lastCmp = name.compareTo(otherStudent.getName());
        return (lastCmp != 0 ? lastCmp : name.compareTo(otherStudent.getName()));
	}

	/**
	 * Set the clustered network ID of device the student is logged in on.
	 * 
	 * @param table The clustered network ID of device the student is logged in on.
	 */
	public void setTable(String table) {
		this.table = table;
	}

	/**
	 * Get the clustered network ID of device the student is logged in on.
	 * 
	 * @return A String representing the clustered network ID of device the student is logged in on.
	 */
	public String getTable() {
		return table;
	}

	/**
	 * Get the name of the class the student belongs to.
	 * 
	 * @return A String representing the name of the class the student belongs to.
	 */
	public String getClassName() {
		return className;
	}
	
	/**
	 * Set the name of the colour to be used for any menu's representing the student.
	 * 
	 * @param colour The name of the colour to be used for any menu's representing the student.
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * Get the name of the colour to be used for any menu's representing the student.
	 * 
	 * @return A String representing the name of the colour to be used for any menu's representing the student.
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * Set the ID of the student.
	 * 
	 * @param studentID The ID of the student.
	 */
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	/**
	 * Get the ID of the student.
	 * 
	 * @return The ID of the student.
	 */
	public String getStudentID() {
		return studentID;
	}
		
}