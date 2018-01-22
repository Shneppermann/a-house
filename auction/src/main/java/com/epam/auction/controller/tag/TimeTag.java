package com.epam.auction.controller.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Custom tag that prints a date
 */
public class TimeTag extends TagSupport {

    private static final Logger LOGGER = LogManager.getLogger(TimeTag.class);
    private static final long serialVersionUID = 1350593774114515056L;
    private String mFormat;

    public void setFormat(String pFormat) {
        mFormat = pFormat;
    }


    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            Date today = new Date();
            SimpleDateFormat dateFormatter = new SimpleDateFormat(mFormat);
            out.print(dateFormatter.format(today));

        } catch (IOException exception) {
            LOGGER.error(exception.getMessage() + exception);
            throw new JspException(exception.getMessage() + exception);
        }
        return EVAL_BODY_INCLUDE;
    }


    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}

