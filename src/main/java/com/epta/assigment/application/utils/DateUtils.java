package com.epta.assigment.application.utils;

import com.epta.assigment.infraestructure.exception.BadRequestException;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DateUtils {

    private static final int MONTH_OF_YEAR = 12;

    public static void isValidMonth(String month) {

        List<String> monthNames = getMonths();
        String tempMonth = month.toUpperCase();
        int index = monthNames.indexOf(tempMonth);

        if (index == -1){
            throw new BadRequestException("Date is wrong");
        }
    }

    public static Month getMonth(String month) {

        List<String> monthNames = getMonths();
        String tempMonth = month.toUpperCase();
        int index = monthNames.indexOf(tempMonth);

        if (index == -1){
            throw new BadRequestException("Date is wrong");
        }else{
            index++;
            return Month.of(index);
        }
    }

    public static List<String> getMonths() {

        List<String> monthNames = new ArrayList<>(MONTH_OF_YEAR);
        for ( int i = 1 ; i <= MONTH_OF_YEAR ; i++ )
        {
            monthNames.add(Month.of(i).getDisplayName(TextStyle.SHORT,Locale.US).toUpperCase());
        }
        return monthNames;
    }
}
