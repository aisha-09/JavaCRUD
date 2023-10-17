import java.sql.*;

public class Main {
    public static final String CREATETABLEQUERY="DROP TABLE Employee IF EXISTS; create table Employee(empId INTEGER , empName varchar2(50), empSalary NUMBER )";
    public static final String INSERTQUERY1="INSERT INTO EMPLOYEE VALUES (1,'Bob',100)";
    public static final String INSERTQUERY2="INSERT INTO EMPLOYEE VALUES (2,'COCO',200)";
    public static final String INSERTQUERY3="INSERT INTO EMPLOYEE VALUES (3,'DAVE',300)";

    public static final String SELECTQUERY="SELECT * FROM EMPLOYEE";
    public static final String UPDATEQUERY = "update EMPLOYEE set empName='CRUST' where empId=2";
    public static final String DELETEQUERY = "delete from EMPLOYEE where empSalary=100";

    public static void main(String[] args)  {
        try
        {
            int i=1;
            Connection connection= DriverManager.getConnection("jdbc:h2:./db","root","password");
            Statement statement= connection.createStatement();
            statement.execute(CREATETABLEQUERY);
            statement.executeUpdate(INSERTQUERY1);
            statement.executeUpdate(INSERTQUERY2);
            statement.executeUpdate(INSERTQUERY3);
            ResultSet resultSet=statement.executeQuery(SELECTQUERY);
            while (resultSet.next())
            {
                System.out.println("My Emp "+i++ +" ID:-"+resultSet.getInt("empId")+"\t"+"NAME:-"
                        +resultSet.getString(2)
                        +" salary:-"+ resultSet.getFloat(3));
            }

           //update table
            int count = statement.executeUpdate(UPDATEQUERY);

            System.out.println(count+"rows affected");
            ResultSet resultSet1=statement.executeQuery(SELECTQUERY);
            int k=1;
            while (resultSet1.next())
            {
                System.out.println("My Emp "+k++ +" ID:-"+resultSet1.getInt("empId")+"\t"+"NAME:-"
                        +resultSet1.getString(2)
                        +" salary:-"+ resultSet1.getFloat(3));

            }
            count = statement.executeUpdate(DELETEQUERY);

            System.out.println(count+"rows affected");

            ResultSet resultSet2=statement.executeQuery(SELECTQUERY);
            int j=1;
            while (resultSet2.next())
            {
                System.out.println("My Emp "+j++ +" ID:-"+resultSet2.getInt("empId")+"\t"+"NAME:-"
                        +resultSet2.getString(2)
                        +" salary:-"+ resultSet2.getFloat(3));

            }
        }
        catch (SQLException sqlException)
        {
            System.out.println("SQLException handler"+sqlException.getMessage());
        }
    }
}