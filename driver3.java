import java.util.Scanner;
public class driver3 {
        public static void main(String[] args) {
        String[] logs = {
            "10:05 alice Hello there",
            "10:10 bob How are you?",
            "10:15 charlie Good morning",
            "10:20 malformed"
        };
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();
        String result = chatfilter.filterLogs(logs, keyword);
        System.out.println(result);
        sc.close();
    }
}
    

