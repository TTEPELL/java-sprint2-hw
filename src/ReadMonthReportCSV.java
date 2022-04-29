import java.util.ArrayList;

public class ReadMonthReportCSV {
    public final ArrayList<MonthlyReport> reports;

    public ReadMonthReportCSV() {
        this.reports = new ArrayList<>();
    }

    public void readData(String path, int monthNum) {
        String fileDataCSV = readFileContentsOrNull.readFileContentsOrNull(path);
        String[] lines = fileDataCSV.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] data = lines[i].split(",");
            insertData(data, monthNum);
        }
    }

    private void insertData(String[] data, int monthNumber) {
        MonthObject obj = new MonthObject(String.valueOf(data[0]), Boolean.valueOf(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
        if (reports.size() < monthNumber) {
            reports.add(new MonthlyReport());
        }
        reports.get(monthNumber - 1).getObjects().add(obj);
    }

    public void processingMonthlyReports(int month) {
        int maxProfit = 0;
        int maxCost = 0;
        String nameMaxProfit = "";
        String nameMaxCost = "";
        String[] monthName = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
        System.out.println(monthName[month - 1]);
        for (int m = 0; m < reports.get(month - 1).getObjects().size(); m++) {
            if (reports.get(month - 1).getObjects().get(m).isexpense) {
                int Cost = reports.get(month - 1).getObjects().get(m).quantity * reports.get(month - 1).getObjects().get(m).sum_of_one;
                if (maxCost < Cost) {
                    maxCost = Cost;
                    nameMaxCost = reports.get(month - 1).getObjects().get(m).item_name;
                }
            } else {
                int profit = reports.get(month - 1).getObjects().get(m).quantity * reports.get(month - 1).getObjects().get(m).sum_of_one;
                if (profit > maxProfit) {
                    maxProfit = profit;
                    nameMaxProfit = reports.get(month - 1).getObjects().get(m).item_name;
                }
            }
        }
        System.out.println("Самая большая трата: " + nameMaxCost + " " + maxCost);
        System.out.println("Самый прибыльный товар: " + nameMaxProfit + " " + maxProfit);
    }

    public void printReport(int month) {
        if (!reports.isEmpty()){
            for (int i = 1; i <= month; i++) {
                processingMonthlyReports(i);
            }
        }else{
            System.out.println("Отчеты не найдены. Сначала считайте отчеты");
        }
    }

    public int[][] summMonthReport (int NumberOfMonth) {
        int[][] summMonthReport = new int[NumberOfMonth][2];
        for (int monthNumber = 1; monthNumber <= NumberOfMonth; monthNumber++) {
            int summCoast = 0;
            int summProfit = 0;
            for (int m = 0; m < reports.get(monthNumber - 1).getObjects().size(); m++) {
                if (reports.get(monthNumber - 1).getObjects().get(m).isexpense){
                    summCoast+= reports.get(monthNumber - 1).getObjects().get(m).quantity*reports.get(monthNumber - 1).getObjects().get(m).sum_of_one;
                }else{
                    summProfit += reports.get(monthNumber - 1).getObjects().get(m).quantity*reports.get(monthNumber - 1).getObjects().get(m).sum_of_one;
                }
            }
            summMonthReport[monthNumber-1][0]=summProfit;
            summMonthReport[monthNumber-1][1]=summCoast;
        } return summMonthReport;

    }

}