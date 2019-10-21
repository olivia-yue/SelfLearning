package replaceNestedBlock.by.ruleEngine;

public interface Rule {
	boolean evaluate(Expression expression);
	Integer getResult();
}
