import java.sql.Statement;
import java.util.Scanner;

import javax.sql.StatementEvent;

public class AppMain {
    public static void main(String[] args) throws Exception {
        // 설문 분기 여부
        System.out.println("설문을 참여 하시겠습니까?");

        Commons commons = new Commons();
        Statement statement = commons.getStatement();

        String flag = "";
        Scanner scanner = new Scanner(System.in);
        while (!flag.equals("Q")) {
            System.out.println("P.설문시작 | S.통계 | Q.종료");
            flag = scanner.nextLine();
            if (flag.equals("P")) { // 설문시작
                Survey survey = new Survey();
                survey.Survey(statement);

            } else if (flag.equals("S")) {
                Statistics statistics = new Statistics();
                statistics.Statistics(statement);

            }
        }
        System.out.println("종료");
    }
}
