public class YearlyReport {

    private static int[][] YearReport;
    private static boolean dataRead;
    private static int yearNumber;

    public static boolean getDataRead() {
        return dataRead;
    }

    public static void readYearData(String path, int yearNum, int NumberofMonth) {
        YearReport = new int[NumberofMonth][2];
        String fileDataCSV = readFileContentsOrNull.readFileContentsOrNull(path);
        if (fileDataCSV != null) {
            String[] lines = fileDataCSV.split("\n");
            for (int i = 1; i < lines.length; i++) {
                String[] data = lines[i].split(",");
                int mountfNumber = Integer.parseInt(data[0]);
                int sum = Integer.parseInt(data[1]);
                boolean isCoast = Boolean.valueOf(data[2]);

                if (isCoast) {
                    YearReport[mountfNumber - 1][1] = sum;  //записываем в столбик к расходам
                } else {
                    YearReport[mountfNumber - 1][0] = sum;  //записываем в столбик к доходам
                }
            }
        }
        dataRead = true;
        yearNumber = yearNum;
    }

    public static int[][] getYearReport() {
        return YearReport;
    }

    public static void processingYearReport() {
        if (dataRead) {
            int averageСosts = 0;
            int averageProfit = 0;

            System.out.println("Отчет за " + yearNumber + "год:");
            for (int i = 0; i < YearReport.length; i++) {
                int monthProfit = YearReport[i][0] - YearReport[i][1];
                System.out.println("прибыль за " + ReadMonthReportCSV.getMonthNameListI(i) + " :" + monthProfit);
                averageСosts += YearReport[i][1];
                averageProfit += YearReport[i][0];
            }
            System.out.println("Среднегодовой расход за месяц: " + averageСosts / YearReport.length);
            System.out.println("Среднегодовой доход за месяц: " + averageProfit / YearReport.length);
        } else {
            System.out.println("Данные не загружены. Считайте годовой отчет");
        }
    }

}

