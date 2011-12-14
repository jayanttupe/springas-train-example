package cn.com.oceansoft.flex4.server.http.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date: 11-12-12
 * Time: 下午3:42
 * Description: 公共 dataBiner 类，进行指定类型的自动转换
 */
public class CommonBindingInitializer implements WebBindingInitializer {

    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String MONTH_PATTERN = "yyyy-MM";

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

}
