public class СomparisonOfReports {

    public static void compare(int[][] MonthlyReport, int[][] YaerReport, int NumberOfMonth, String[] monthName) {
        int Error = 0;
        for (int i = 0; i < NumberOfMonth; i++) {
            if (!((MonthlyReport[i][0] == YaerReport[i][0]) && (MonthlyReport[i][1] == YaerReport[i][1]))) {
                System.out.println("Ошибка в отчете за месяц: " + monthName[i]);
                Error += 1;
            }
        }
        if (Error == 0) {
            System.out.println("Ошибок в отчетах нет");
        }
    }

}
