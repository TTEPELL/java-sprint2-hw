public class YearlyReport {

    private static int[][] yearReport;   //массив данных годового отчета. [месяц][доходы, расходы]
    private static boolean dataRead;
    private static int yearNumber;

    public static boolean getDataRead() {
        return dataRead;
    }

    public static void readYearData(String path, int yearNum, int NumberofMonth) {
        yearReport = new int[NumberofMonth][2];
        String fileDataCSV = ReportReader.ReadFileContentsOrNull(path);
        if (fileDataCSV != null) {
            String[] lines = fileDataCSV.split("\n");
            for (int i = 1; i < lines.length; i++) {
                String[] data = lines[i].split(",");
                int mountfNumber = Integer.parseInt(data[0]);
                int sum = Integer.parseInt(data[1]);
                boolean isCoast = Boolean.parseBoolean(data[2]);

                if (isCoast) {
                    yearReport[mountfNumber - 1][1] = sum;  //записываем в столбик к расходам
                } else {
                    yearReport[mountfNumber - 1][0] = sum;  //записываем в столбик к доходам
                }
            }
        }
        dataRead = true;
        yearNumber = yearNum;
    }

    public static int[][] getYearReport() {
        return yearReport;
    }

    public static void processingYearReport() {
        if (dataRead) {
            int averageСosts = 0;
            int averageProfit = 0;

            System.out.println("Отчет за " + yearNumber + "год:");
            for (int i = 0; i < yearReport.length; i++) {
                int monthProfit = yearReport[i][0] - yearReport[i][1];
                System.out.println("прибыль за " + ReadMonthReportCSV.getMonthNameListI(i) + " :" + monthProfit);
                averageСosts += yearReport[i][1];
                averageProfit += yearReport[i][0];
            }
            System.out.println("Среднегодовой расход за месяц: " + averageСosts / yearReport.length);
            System.out.println("Среднегодовой доход за месяц: " + averageProfit / yearReport.length);
        } else {
            System.out.println("Данные не загружены. Считайте годовой отчет");
        }
    }

}

