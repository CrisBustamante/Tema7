import java.sql.*;
import java.util.Scanner;

public class PilotsCRUD {

    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        menu();
    }

    public static void showMenu(){
        System.out.print("\n" +
                "   ---MENÚ---\n" +
                "1.Crear Piloto\n" +
                "2.Leer Piloto\n" +
                "3.Leer Pilotos\n" +
                "4.Actualizar Piloto\n" +
                "5.Borrar Piloto\n" +
                "6.Ver Calificación Piloto\n" +
                "7.Ver Calificación Constructores\n" + //"
                "8.Salir\n" +
                "Elige tu opción: ");
    }
    public static void menu(){
        String urlLink = "postgreprogramacion.cqkb9kgwwlnt.us-east-1.rds.amazonaws.com/f12006";
        String user = "eloy";
        String passwd = "eloy1234";
        int option;
        do {
            showMenu();
            option = in.nextInt();
            switch (option){
                case 1:
                    insertPilot(urlLink, user, passwd);
                    break;
                case 2:
                    readPilot(urlLink, user, passwd);
                    break;
                case 3:
                    readPilots(urlLink, user, passwd);
                    break;
                case 4:
                    updatePilot();
                    break;
                case 5:
                    deletePilot();
                    break;
                case 6:
                    showPilotClassification();
                    break;
                case 7:
                    showBuildersClassification();
                    break;
                case 8:
                    System.out.println("\nHas salido del programa.");
                    break;
                default:
                    System.out.println("Selecciona una opción válida porfavor.");
            }
        } while (option != 8);
    }

    public static Pilot createPilot(){
        System.out.print("  - ID Piloto: ");
        int driverID = in.nextInt();
        System.out.print("  - Nombre Piloto: ");
        String name = in.next().toLowerCase().trim();
        System.out.print("  - Apellido Piloto: ");
        String surname = in.next().toLowerCase().trim();
        System.out.print("  - Nacionalidad Piloto: ");
        String nationality = in.next().toLowerCase().trim();
        System.out.print("  - ID Constructor: ");
        int constructorID = in.nextInt();
        return new Pilot(driverID, name, surname, nationality, constructorID);
    }
    public static void insertPilot(String url, String user, String passwd){
        Pilot driverToInsert = createPilot();
        try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
            String sql = "INSERT TO drivers (driverid, forename, surname, nationality, constructorid) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(driverToInsert.getDriverID()));
            statement.setString(2, driverToInsert.getName());
            statement.setString(3, driverToInsert.getSurname());
            statement.setString(4, driverToInsert.getNationality());
            statement.setString(5, String.valueOf(driverToInsert.getConstructorID()));
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0){
                System.out.println("El registro se ha realizado correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: No se ha podido realizar el registro correctamente.");
        }
    }
    public static void readPilot(String url, String user, String passwd){
        System.out.print("Dime el nombre del piloto a buscar: ");
        String name = in.next().toLowerCase().trim();
        try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
            String sql = "SELECT * FROM drivers WHERE drivers.forename = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int driverid = resultSet.getInt("driverid");
                String forename = resultSet.getString("forename");
                String surname = resultSet.getString("surname");
                String nationality = resultSet.getString("nationality");
                int constructorid = resultSet.getInt("constructorid");
                System.out.println(STR."""
                 - ID: \{driverid}
                 - Nombre: \{forename}
                 - Apellido: \{surname}
                 - Nacionalidad: \{nationality}
                 - ID del Constructor: \{constructorid}
                """);
            }
        } catch (SQLException e) {
            System.out.println("No se ha podido encontrar el piloto deseado.");
        }
    }
    public static void  readPilots(String url, String user, String passwd){
        try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
            String sql = "SELECT * FROM drivers";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            System.out.println("  ***** PILOTOS *****  ");
            while (resultSet.next()) {
                int driverid = resultSet.getInt("driverid");
                String forename = resultSet.getString("forename");
                String surname = resultSet.getString("surname");
                String nationality = resultSet.getString("nationality");
                int constructorid = resultSet.getInt("constructorid");
                System.out.println(STR."""
                 - ID: \{driverid}
                 - Nombre: \{forename}
                 - Apellido: \{surname}
                 - Nacionalidad: \{nationality}
                 - ID del Constructor: \{constructorid}
                """);
            }
        } catch (SQLException e) {
            System.out.println("No se ha podido encontrar el piloto deseado.");
        }
    }
    public static void updatePilot(){}
    public static void deletePilot(){}
    public static void showPilotClassification(){}
    public static void showBuildersClassification(){}
}