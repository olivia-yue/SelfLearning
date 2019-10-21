package replaceNestedBlock.by.ruleEngine;

import java.util.ArrayList;
import java.util.List;

public class RuleEngine {
	private static List<Rule> rules = new ArrayList<>();
	static {
		rules.add(new AddRule());
		rules.add(new SubtractRule());
	}
	public Integer process(Expression expression) {
		Rule rule = rules
				.stream()
				.filter(r -> r.evaluate(expression))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Expression does not match any rules!"));
		return rule.getResult();
		}
}
