import java.util.ArrayList;

public class ReadMonthReportCSV {

    private final ArrayList<ArrayList<MonthObject>> reports;

    public ReadMonthReportCSV() {
        this.reports = new ArrayList<>();
    }

    public ArrayList<ArrayList<MonthObject>> getReports(){
        return reports;
    }

    public void readData(String path, int monthNum) {
        String fileDataCSV = ReportReader.ReadFileContentsOrNull(path);
        String[] lines = fileDataCSV.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] data = lines[i].split(",");
            MonthObject obj = new MonthObject(String.valueOf(data[0]), Boolean.valueOf(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
            if (reports.size() < monthNum) {
                reports.add(new ArrayList<>());
            }
            reports.get(monthNum - 1).add(obj);
        }
    }

    public void processingMonthlyReports(int month) {
        int maxProfit = 0;
        int maxCost = 0;
        String nameMaxProfit = "";
        String nameMaxCost = "";

        System.out.println(monthNamelist[month - 1]);
        for (int m = 0; m < reports.get(month - 1).size(); m++) {
            MonthObject obj = reports.get(month - 1).get(m);
            if (obj.getIsExpense()) {
                int Cost = obj.getQuantity() * obj.getSumOfOne();
                if (maxCost < Cost) {
                    maxCost = Cost;
                    nameMaxCost = obj.getItemName();
                }
            } else {
                int profit = obj.getQuantity() * obj.getSumOfOne();
                if (profit > maxProfit) {
                    maxProfit = profit;
                    nameMaxProfit = obj.getItemName();
                }
            }
        }
        System.out.println("Самая большая трата: " + nameMaxCost + " " + maxCost);
        System.out.println("Самый прибыльный товар: " + nameMaxProfit + " " + maxProfit);
    }

    public void printReport(int month) {
        if (!reports.isEmpty()) {
            for (int i = 1; i <= month; i++) {
                processingMonthlyReports(i);
            }
        } else {
            System.out.println("Отчеты не найдены. Сначала считайте отчеты");
        }
    }

    public int[][] summMonthReport(int NumberOfMonth) {
        int[][] summMonthReport = new int[NumberOfMonth][2];
        for (int monthNumber = 1; monthNumber <= NumberOfMonth; monthNumber++) {
            int summCoast = 0;
            int summProfit = 0;
            for (int m = 0; m < reports.get(monthNumber - 1).size(); m++) {
                if (reports.get(monthNumber - 1).get(m).getIsExpense()) {
                    summCoast += reports.get(monthNumber - 1).get(m).getQuantity() * reports.get(monthNumber - 1).get(m).getSumOfOne();
                } else {
                    summProfit += reports.get(monthNumber - 1).get(m).getQuantity() * reports.get(monthNumber - 1).get(m).getSumOfOne();
                }
            }
            summMonthReport[monthNumber - 1][0] = summProfit;
            summMonthReport[monthNumber - 1][1] = summCoast;
        }
        return summMonthReport;
    }

    static String[] monthNamelist = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};

    public static String getMonthNameListI(int i) {
        return monthNamelist[i];
    }

    public String[] getMonthNameList() {
        return monthNamelist;
    }

}