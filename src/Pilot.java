
public class Pilot {
    private int driverID;
    private String name;
    private String surname;
    private String nationality;
    private int constructorID;

    public Pilot(int driverID, String name, String surname, String nationality, int constructorID) {
        this.driverID = driverID;
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.constructorID = constructorID;
    }

    public int getDriverID() {
        return driverID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNationality() {
        return nationality;
    }

    public int getConstructorID() {
        return constructorID;
    }
}