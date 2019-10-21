package replaceNestedBlock.by.ruleEngine;

import replaceNestedBlock.by.enums.Operator;

public class AddRule implements Rule {

	private Integer result;
	
	@Override
	public boolean evaluate(Expression expression) {
		boolean evalResult = false;
		if(expression.getOperator() == Operator.ADD) {
			this.result = expression.getA() + expression.getB();
			evalResult = true;
		}
		return evalResult;
	}

	@Override
	public Integer getResult() {
		return this.result;
	}

}
