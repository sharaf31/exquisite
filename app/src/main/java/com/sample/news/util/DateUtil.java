package com.sample.news.util;

import com.sample.news.exception.ArticleException;
import org.springframework.beans.factory.annotation.Value;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by sharaf on 4/23/18.
 */
public final class  DateUtil {

    public static final String DATE_FORMAT="dd/MM/yyyy";

    @Value("${msg.err.date}")
    private static String ERROR_MSG;

    public static Date convertStringToFormattedDate(String dateInString) throws ArticleException{
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        Date date=null;
        try {
            date = formatter.parse(dateInString);

        } catch (ParseException e) {
            throw new ArticleException(ERROR_MSG+DATE_FORMAT);
        }
        return  date;
    }


    public static String convertDateToFormattedString(Date date){
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.format(date);


    }
}
