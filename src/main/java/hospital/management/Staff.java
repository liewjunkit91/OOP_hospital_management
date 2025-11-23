package hospital.management;

/**
 * Represents a staff member in the hospital management system.
 * Inherits from Person and includes staff-specific attributes
 * such as role and department.
 */
public class Staff extends Person {
    private String role;
    private String department;

    /**
     * Constructor to create a Staff object.
     *
     * @param name           The name of the staff member
     * @param age            The age of the staff member
     * @param contactDetails The contact details of the staff member
     * @param role           The role of the staff member
     * @param department     The department where the staff member works
     */
    public Staff(String name, int age, String contactDetails, String role, String department) {
        super(name, age, contactDetails);
        this.role = role;
        this.department = department;
    }

    /**
     * Gets the role of the staff member.
     *
     * @return The role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the staff member.
     *
     * @param role The role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the department of the staff member.
     *
     * @return The department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the department of the staff member.
     *
     * @param department The department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Displays the staff information including inherited Person details.
     */
    @Override
    public void displayInfo() {
        System.out.println("===== Staff Information =====");
        super.displayInfo();
        System.out.println("Role: " + role);
        System.out.println("Department: " + department);
    }
}
