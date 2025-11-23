package hospital.management;

/**
 * Base class representing a person in the hospital management system.
 * This class contains common properties shared by all hospital members
 * such as patients, doctors, and staff.
 */
public class Person {
    private String name;
    private int age;
    private String contactDetails;

    /**
     * Constructor to create a Person object.
     *
     * @param name           The name of the person
     * @param age            The age of the person
     * @param contactDetails The contact details of the person
     */
    public Person(String name, int age, String contactDetails) {
        this.name = name;
        this.age = age;
        this.contactDetails = contactDetails;
    }

    /**
     * Gets the name of the person.
     *
     * @return The name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the age of the person.
     *
     * @return The age of the person
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the person.
     *
     * @param age The age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the contact details of the person.
     *
     * @return The contact details of the person
     */
    public String getContactDetails() {
        return contactDetails;
    }

    /**
     * Sets the contact details of the person.
     *
     * @param contactDetails The contact details to set
     */
    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    /**
     * Displays the basic information of the person.
     */
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Contact: " + contactDetails);
    }
}