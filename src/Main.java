import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ReadMonthReportCSV ReadMonthReportCSV = new ReadMonthReportCSV();
        int NumberOfMonth = 3;

        while (true) {
            printMenu();
            int userInput = scanner.nextInt();
            if (userInput == 2517) {
                break;
            }
            switch (userInput) {
                case 1: {
//считать все месяцные отчеты
                    for (int i = 1; i <= NumberOfMonth; i++) {
                        ReadMonthReportCSV.readData("resources/m.20210" + i + ".csv", i);
                    }
                    break;
                }
                case 2:
//считать годовой отчет
                    System.out.println("за какой год считать отчет? введите год в формате YYYY");
                    userInput = scanner.nextInt();
                    YearlyReport.readData("resources/y." + userInput + ".csv", userInput, NumberOfMonth);
                    break;
                case 3:
//сверить отчеты
                    if (YearlyReport.dataRead && (!ReadMonthReportCSV.reports.isEmpty())) {
                        int[][] summMonthlyReport = ReadMonthReportCSV.summMonthReport(NumberOfMonth);
                        СomparisonOfReports.compare(summMonthlyReport, YearlyReport.YearReport, NumberOfMonth, YearlyReport.monthName);
                    } else {
                        if ((!YearlyReport.dataRead) && ReadMonthReportCSV.reports.isEmpty()) {
                            System.out.println("Годовой и месячные отчеты не найдены. Загрузите отчеты.");
                        } else if (ReadMonthReportCSV.reports.isEmpty()) {
                            System.out.println("Месячный отчет не найден. Загрузите отчет.");
                        } else {
                            System.out.println("Годовой отчет не найден. Загрузите отчет.");
                        }
                    }
                    break;
                case 4:
//вывести онформацию о всех месячных отчетах
                    ReadMonthReportCSV.printReport(NumberOfMonth);
                    break;

                case 5:
//вывести информацию о годовом отчете
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
        System.out.println("2517 - Завершить работу программы");
    }

}


