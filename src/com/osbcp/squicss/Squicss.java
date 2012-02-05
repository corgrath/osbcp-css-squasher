package com.osbcp.squicss;

import java.util.List;

import org.apache.commons.io.IOUtils;

import com.osbcp.cssparser.CSSParser;
import com.osbcp.cssparser.Rule;

public class Squicss {

	protected static Squicss oParser;

	public static void main(final String[] args) throws Exception {

		String contents = IOUtils.toString(Squicss.class.getResourceAsStream("loopia.css"));

		//		InputSource source = new InputSource(new InputStreamReader(stream));

		//		Squicss squicss = new Squicss();
		optimized(contents);

	}

	public static void optimized(final String css) throws Exception {

		//		Opti2 logic = new Opti2();

		List<Rule> rules = CSSParser.parse(css);

		//		for (Rule rule : rules) {
		//
		//			for (Selector selector : rule.getSelectors()) {
		//
		//				Set<PropertyValue> values = new HashSet<PropertyValue>();
		//
		//				for (PropertyValue pv : rule.getPropertyValues()) {
		//					values.add(pv);
		//				}
		//
		//				logic.register(selector, values);
		//
		//			}
		//
		//		}

		//		InputSource source = new InputSource(reader);
		//		CSSOMParser parser = new CSSOMParser();
		//		CSSStyleSheet stylesheet = parser.parseStyleSheet(source, null, null);
		//
		//		CSSRuleList ruleList = stylesheet.getCssRules();
		//
		//		for (int i = 0; i < ruleList.getLength(); i++) {
		//			CSSRule rule = ruleList.item(i);
		//			if (rule instanceof CSSStyleRule) {
		//				CSSStyleRule styleRule = (CSSStyleRule) rule;
		//
		//				String selectorString = styleRule.getSelectorText();
		//
		//				for (String select2 : selectorString.split(",")) {
		//
		//					Selector selector = new Selector(select2.trim());
		//					//				System.out.println("selector:" + i + ": " + selector);
		//					CSSStyleDeclaration styleDeclaration = styleRule.getStyle();
		//
		//					Set<PropertyValue> values = new HashSet<PropertyValue>();
		//
		//					for (int j = 0; j < styleDeclaration.getLength(); j++) {
		//
		//						String property = styleDeclaration.item(j);
		//						String value = styleDeclaration.getPropertyCSSValue(property).getCssText();
		//						//					System.out.println("property: " + property);
		//						//					System.out.println("value: " + value);
		//						//					System.out.println("priority: " + styleDeclaration.getPropertyPriority(property));
		//
		//						PropertyValue propertyValue = new PropertyValue(property, value);
		//
		//						values.add(propertyValue);
		//
		//					}
		//
		//					logic.register(selector, values);
		//
		//				}
		//
		//			}// end of StyleRule instance test
		//		} // end of ruleList loop

		for (Rule rule : rules) {
			System.out.println(rule.toString());
		}

		//		Set<Rule> rules2 = logic.getRules();

		print2(rules);

	}

	public static void print2(List<Rule> rules) {

		while (true) {

			SquashDuplicatedRulesLogic squasher = new SquashDuplicatedRulesLogic();
			rules = squasher.packDuplicates(rules);
			int fixed = squasher.fixedDuplications();
			System.out.println("fixed=" + fixed);
			//			print(rules);

			Erik erik = new Erik();
			rules = erik.hello(rules);
			int meh = erik.getFixed();
			System.out.println("meh=" + meh);
			//			print(rules);
			//

			if (fixed == 0 && meh == 0) {
				//			if (fixed == 0) {
				break;
			}

		}

		print(rules);

	}

	private static void print(final List<Rule> rules) {

		for (Rule rule : rules) {
			System.out.println(rule.toString());
		}

	}

	//	public static void main(final String[] args) throws IOException {
	//		
	//		Opti2 logic = new Opti2();
	//		
	//		// cssfile accessed as a resource, so must be in the pkg (in src dir).
	//		InputStream stream = Squicss.class.getResourceAsStream("loopia.css");
	//		InputSource source = new InputSource(new InputStreamReader(stream));
	//		CSSOMParser parser = new CSSOMParser();
	//		CSSStyleSheet stylesheet = parser.parseStyleSheet(source, null, null);
	//		
	//		CSSRuleList ruleList = stylesheet.getCssRules();
	//		
	//		for (int i = 0; i < ruleList.getLength(); i++) {
	//			CSSRule rule = ruleList.item(i);
	//			if (rule instanceof CSSStyleRule) {
	//				CSSStyleRule styleRule = (CSSStyleRule) rule;
	//				
	//				String selectorString = styleRule.getSelectorText();
	//				
	//				for (String select2 : selectorString.split(",")) {
	//					
	//					Selector selector = new Selector(select2.trim());
	//					//				System.out.println("selector:" + i + ": " + selector);
	//					CSSStyleDeclaration styleDeclaration = styleRule.getStyle();
	//					
	//					Set<PropertyValue> values = new HashSet<PropertyValue>();
	//					
	//					for (int j = 0; j < styleDeclaration.getLength(); j++) {
	//						
	//						String property = styleDeclaration.item(j);
	//						String value = styleDeclaration.getPropertyCSSValue(property).getCssText();
	//						//					System.out.println("property: " + property);
	//						//					System.out.println("value: " + value);
	//						//					System.out.println("priority: " + styleDeclaration.getPropertyPriority(property));
	//						
	//						PropertyValue propertyValue = new PropertyValue(property, value);
	//						
	//						values.add(propertyValue);
	//						
	//					}
	//					
	//					logic.register(selector, values);
	//					
	//				}
	//				
	//			}// end of StyleRule instance test
	//		} // end of ruleList loop
	//		
	//		logic.print2();
	//		
	//	}
}
