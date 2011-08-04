package com.skynet.spms.modules.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skynet.spms.modules.entity.PreDefineElement;

public class FilterTool {
	
	private Logger log = LoggerFactory.getLogger(FilterTool.class);

	private final List<Filter> filterList=new ArrayList<Filter>();
	
	private final boolean isAll;
	public FilterTool(Element dataElem,PreDefineElement preDefine){
		if(dataElem==null){
			isAll=true;			
			return;
		}
		
		String containType = dataElem.attributeValue("default");
		
		isAll=!("none".equals(containType));		
		
		for (Object ext : dataElem.elements()) {
			Element filterElem = (Element) ext;

			boolean isInclude=true;
			if (filterElem.getName().equals("include")) {
				isInclude=true;
			} else if (filterElem.getName().equals("exclude")) {
				isInclude=false;
			} else {
				// do nothing.
				continue;
			}
			
//			String prefix=filterElem.attributeValue("prefix");
					
			Filter filter=null;
			
			Element regElem = filterElem.element("regexp");
			if(regElem!=null){
				filter= new RegExpFilter(filterElem);
			}else{
				String text=StringUtils.trim(filterElem.getText());
				if("*".equals(text)){
					filter=new AllFilter(filterElem);
				}else{
					filter= new LineFilter(filterElem,preDefine);
				}
			} 	
			filter.isInclude=isInclude;			
			filterList.add(filter);
		}
	}
	
	public boolean doValid(String field){
		log.debug("filter field:" + field);
		boolean sign = isAll;
		for (Filter filter : filterList) {			
			sign=filter.isValid(field, sign);
		}
		log.debug("filter result:" + sign);
		return sign;
	}
	
	public Set<String> doFilter(Set<String> fieldSet) {
		Set<String> filterResult=new HashSet<String>();
		if(isAll){
			filterResult.addAll(fieldSet);
		}
		for (Iterator<String> iter = fieldSet.iterator(); iter.hasNext();) {
			String field = iter.next();
			
			if (doValid(field)) {
				filterResult.add(field);
			}else{
				filterResult.remove(field);
			}
		}
		return filterResult;

	}

	private static class AllFilter extends Filter{

		public AllFilter(Element elem) {
			super(elem);
		}

		@Override
		protected boolean isValid(String val) {
			return true;
		}
		
	}

	private static class LineFilter extends Filter {
		
		
		protected LineFilter(Element elem,PreDefineElement preDefine) {
			super(elem);
			String ref = elem.attributeValue("ref");
			
			
			if (StringUtils.isNotBlank(ref)) {				
				set.addAll(preDefine.getPreDefRuleByName(ref));				
			}
			String text=elem.getText();			
			
			if(StringUtils.isNotBlank(text)){
				set.addAll(SplitUtil.getSplitStr(text));
			}
		}

		private final Set<String> set=new LinkedHashSet<String>() ;

		public boolean isValid(String val) {
			return  set.contains(val);
		}

	}

	private static class RegExpFilter extends Filter {
		protected RegExpFilter(Element elem) {
			super(elem);
			String regexp = elem.elementText("regexp");
			pattern = Pattern.compile(regexp);
		}

		private final Pattern pattern;

		@Override
		public boolean isValid(String val) {
			
			return pattern.matcher(val).find();
		}

	}

	private static abstract class Filter {
	
		private boolean isInclude;
		
		private String prefix;
		
		public Filter(Element elem){
			prefix=elem.attributeValue("prefix");
			if(StringUtils.isBlank(prefix)){
				prefix="";
			}
		}

		boolean isValid(String val,boolean signNow){
			if(!(isInclude^signNow)){
				return signNow;
			}
			
			boolean isPre=val.startsWith(prefix);
			if(!isPre){
				return !isInclude;
			}
			val=StringUtils.substring(val, prefix.length());
			if(val.startsWith(".")){
				val=val.substring(1);
			}
			return !(isInclude^isValid(val));
		}
		protected abstract boolean isValid(String val);
		
	}

}
