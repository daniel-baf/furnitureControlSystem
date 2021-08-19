package Domain;

public class User {

    private String name;
    private String password;
    private short areaCode;

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
    public User(String name, String password, short areaCode) {
        this.name = name;
        this.password = password;
        this.areaCode = areaCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(short areaCode) {
        this.areaCode = areaCode;
    }
}
