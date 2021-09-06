package Domain;

public class User {

    private String name;
    private String password;
    private short areaCode;
    private int authorized;

    /**
     * generic constructor, used to create objects
     */
    public User() {
    }

    /**
     * Used to search Users
     *
     * @param name 's ID
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Used to INSERT new User or UPDATE Users
     *
     * @param name 's person
     * @param password 's password
     * @param areaCode 's work area
     */
    public User(String name, String password, short areaCode, int authorized) {
        this.name = name;
        this.password = password;
        this.areaCode = areaCode;
        this.authorized = authorized;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the areaCode
     */
    public short getAreaCode() {
        return areaCode;
    }

    /**
     * @param areaCode the areaCode to set
     */
    public void setAreaCode(short areaCode) {
        this.areaCode = areaCode;
    }

    public int getAuthorized() {
        return authorized;
    }

    public void setAuthorized(int authorized) {
        this.authorized = authorized;
    }
}
