import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Survey {
    public void Survey(Statement statement) {
        System.out.println("서베이");
        // 이름 휴대폰 입력 확인
        System.out.print("이름 : ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.print("휴대폰 번호 : ");
        String phone_Number = scanner.nextLine();

        Commons commons = new Commons();
        String strDate = commons.getGeneratorID();
        String query = "INSERT INTO users_list(USERS_UID,PHONE,NAME)" +
                "values('" + strDate + "', '" + phone_Number + "','" + name + "')";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 설문과 답항 내용 출력
        query = "SELECT * FROM questions_list " +
                "ORDER BY ORDERS";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.print(resultSet.getString("ORDERS") + ". ");
                System.out.println(resultSet.getString("QUESTIONS"));
                String uid = resultSet.getString("QUESTIONS_UID");

                query = "SELECT example_list.EXAMPLE_UID,example_list.EXAMPLE,example_list.ORDERS" +
                        "FROM answers INNER JOIN example_list" +
                        "ON answers.EXAMPLE_UID = example_list.EXAMPLE_UID" +
                        "WHERE QUESTIONS_UID = '" + uid + "'" +
                        "ORDER BY ORDERS; ";
                ResultSet resultSet2 = statement.executeQuery(query);
                ArrayList arrayList= new ArrayList<>();
                while (resultSet2.next()) {
                    System.out.print(resultSet2.getString("ORDERS") + ". ");
                    System.out.println(resultSet2.getString("EXAMPLE"));
                    arrayList.add(resultSet2)
                }
                //설문자 답 받기
                System.out.println("응답 번호 : ");
                String answer = scanner.nextLine();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
