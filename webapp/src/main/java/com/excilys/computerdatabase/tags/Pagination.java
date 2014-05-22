package com.excilys.computerdatabase.tags;


import java.io.Writer;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class Pagination extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String uri;
    private int currPage;
    private int totalPages;
    private int maxLinks = 10;
    private String lang;
    
    @Autowired
    private MessageSource messageSource;
	
    private Writer getWriter() {
        JspWriter out = pageContext.getOut();
        return out;
    }
	
    public int doStartTag() throws JspException{
    	Writer out = getWriter();
    	Locale locale = new Locale(lang,"");
    	String previous = "Previous";
    	String next = "Next";
    	if(messageSource!=null){
    		System.out.println(lang+" et locale: "+locale.getDisplayLanguage());
    		previous = messageSource.getMessage("previous", null, locale);
        	next = messageSource.getMessage("next", null, locale);
    	}
    			
    	boolean lastPage = currPage == (totalPages/10);
    	int pgStart = Math.max(currPage - maxLinks / 2, 0);
    	int pgEnd = pgStart + maxLinks;
    	if (pgEnd > (totalPages/10) + 1) {
    		int diff = pgEnd - (totalPages/10);
    		pgStart -= diff - 1;
    		if (pgStart < 1)
    			pgStart = 1;
    		pgEnd = (totalPages/10) + 1;
    	}

    	try {
    		out.write("<ul class=\"paginatorList\">");

    		if (currPage > 0)
    			out.write(constructLink(currPage - 1, previous, "paginatorPrev"));

    		for (int i = pgStart; i < pgEnd; i++) {
    			if (i == currPage)
    				out.write("<li class=\"paginatorCurr"+ (lastPage && i == (totalPages/10) ? " paginatorLast" : "")  +"\">"+ currPage + "</li>");
    			else
    				out.write(constructLink(i));
    		}

    		if (!lastPage)
    			out.write(constructLink(currPage + 1, next, "paginatorNext paginatorLast"));

    		out.write("</ul>");

    	} catch (java.io.IOException ex) {
    		throw new JspException("Error in Paginator tag", ex);
    	}
    	//ne pas evaluer le corps de la balise
    	return SKIP_BODY;
    }
	
	public int doEndTag(){
		
		//evaluer le reste e la jsp preferer skip page si redirection dans la taglib
		return EVAL_PAGE;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getMaxLinks() {
		return maxLinks;
	}

	public void setMaxLinks(int maxLinks) {
		this.maxLinks = maxLinks;
	}
	
	private String constructLink(int page) {
        return constructLink(page, String.valueOf(page), null);
    }
 
    private String constructLink(int page, String text, String className) {
        StringBuilder link = new StringBuilder("<li");
        if (className != null) {
            link.append(" class=\"");
            link.append(className);
            link.append("\"");
        }
        link.append(">")
            .append("<a href=\"")
            .append(uri.replace("##", String.valueOf(page)))
            .append("\">")
            .append(text)
            .append("</a></li>");
        
        return link.toString();
    }

}
