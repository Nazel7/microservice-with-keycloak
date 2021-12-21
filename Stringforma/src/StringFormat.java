import java.time.LocalDate;

public class StringFormat {
    public static void main(String[] args) {

        StringFormat.formatAccountOpenDateDBQueryStrin("13-09-10T00:00:00.000");
    }

    public static void formatAccountOpenDateDBQueryString(String accountOpeningDbDateString) {
        accountOpeningDbDateString = "2013-09-10T00:00:00.000";
        String day= accountOpeningDbDateString.substring(8,10);
        String month = accountOpeningDbDateString.substring(5,7);
        String year = accountOpeningDbDateString.substring(2,4);



        if (month.equalsIgnoreCase("01")){
            month = "Jan";
        }
        if (month.equalsIgnoreCase("02")){
            month = "Feb";
        }
        if (month.equalsIgnoreCase("03")){
            month = "Mar";
        }
        if (month.equals("04")){
            month = "Apr";
        }
        if (month.equals("05")){
            month = "May";
        } if (month.equals("06")){
            month = "Jun";
        }
        if (month.equals("07")){
            month = "Jul";
        }
        if (month.equals("08")){
            month = "Aug";
        }
        if (month.equals("09")){
            month = "Sep";
        }
        if (month.equals("10")){
            month = "Oct";
        }
        if (month.equals("11")){
            month = "Nov";
        }
        if (month.equals("12")){
            month = "Dec";
        }

        String ddMMyy = day.concat("-").concat(month).concat("-").concat(year);
        System.out.println(ddMMyy);
        int shortYear= Integer.parseInt(ddMMyy.substring(7, 9));
        String shortYearWithUpset= String.valueOf(shortYear + 1);
        System.out.println(ddMMyy.substring(0, 4).toUpperCase().concat(ddMMyy.substring(4, 7).toLowerCase()
                .concat(LocalDate.now().toString().substring(0, 2).concat(shortYearWithUpset))));

    }

    public static String formatAccountOpenDateDBQueryStrin(String accountOpeningDbDateString) {

        // Date format of family yyyy-MM-dd:HH:mm:ss.ms
        String day= accountOpeningDbDateString.substring(8,10);
        String month = accountOpeningDbDateString.substring(5,7);
        String year = accountOpeningDbDateString.substring(0,4);


        if (year.contains("-")){
           day = accountOpeningDbDateString.substring(6,8);
           month = accountOpeningDbDateString.substring(3,5);
           year = accountOpeningDbDateString.substring(0,2);
        }

        if (month.equalsIgnoreCase("01")){
            month = "Jan";
        }
        if (month.equalsIgnoreCase("02")){
            month = "Feb";
        }
        if (month.equalsIgnoreCase("03")){
            month = "Mar";
        }
        if (month.equals("04")){
            month = "Apr";
        }
        if (month.equals("05")){
            month = "May";
        } if (month.equals("06")){
            month = "Jun";
        }
        if (month.equals("07")){
            month = "Jul";
        }
        if (month.equals("08")){
            month = "Aug";
        }
        if (month.equals("09")){
            month = "Sep";
        }
        if (month.equals("10")){
            month = "Oct";
        }
        if (month.equals("11")){
            month = "Nov";
        }
        if (month.equals("12")){
            month = "Dec";
        }

        String dateMonthFragment = day.concat("-").concat(month).concat("-");
        String ddMMyy = dateMonthFragment.concat(year);
        if (year.length() > 2){
            int yearInt = Integer.parseInt(year);
            String yearWithUpset = String.valueOf(yearInt + 1);
            ddMMyy = dateMonthFragment.concat(yearWithUpset);
            System.out.println(ddMMyy);
            return ddMMyy;
        }

        // for date of input family yy-MM-dd
        int shortYear= Integer.parseInt(ddMMyy.substring(7, 9));
        String shortYearWithUpset= String.valueOf(shortYear + 1);

        System.out.println(ddMMyy.substring(0, 4).toUpperCase().concat(ddMMyy.substring(4, 7).toLowerCase()
                .concat(LocalDate.now().toString().substring(0, 2).concat(shortYearWithUpset))));
        return ddMMyy.substring(0, 4).toUpperCase().concat(ddMMyy.substring(4, 7).toLowerCase()
                .concat(LocalDate.now().toString().substring(0, 2).concat(shortYearWithUpset)));

    }
}
