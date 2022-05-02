import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ReadMonthReportCSV ReadMonthReportCSV = new ReadMonthReportCSV();
        int NumberOfMonth = 3;

        while (true) {
            printMenu();
            int userInput = scanner.nextInt();
            if (userInput == 0) {  //блок выхода по команде. В прошлый раз изменил команду в меню, но не поменял в условии
                break;
            }
            switch (userInput) {
                case 1: {
                    for (int i = 1; i <= NumberOfMonth; i++) {
                        ReadMonthReportCSV.readData("resources/m.20210" + i + ".csv", i);
                    }
                    break;
                }
                case 2:
                    System.out.println("за какой год считать отчет? введите год в формате YYYY");
                    userInput = scanner.nextInt();
                    YearlyReport.readYearData("resources/y." + userInput + ".csv", userInput, NumberOfMonth);
                    break;
                case 3:
                    if (YearlyReport.getDataRead() && (!ReadMonthReportCSV.reports.isEmpty())) {
                        int[][] summMonthlyReport = ReadMonthReportCSV.summMonthReport(NumberOfMonth);
                        СomparisonOfReports.compare(summMonthlyReport, YearlyReport.getYearReport(), NumberOfMonth, ReadMonthReportCSV.getMonthNameList());
                    } else {
                        if ((!YearlyReport.getDataRead()) && ReadMonthReportCSV.reports.isEmpty()) {
                            System.out.println("Годовой и месячные отчеты не найдены. Загрузите отчеты.");
                        } else if (ReadMonthReportCSV.reports.isEmpty()) {
                            System.out.println("Месячный отчет не найден. Загрузите отчет.");
                        } else {
                            System.out.println("Годовой отчет не найден. Загрузите отчет.");
                        }
                    }
                    break;
                case 4:
                    ReadMonthReportCSV.printReport(NumberOfMonth);
                    break;
                case 5:
                    YearlyReport.processingYearReport();
                    break;
                default:
                    System.out.println("Извините, такой команды пока нет");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Завершить работу программы");
    }
}


