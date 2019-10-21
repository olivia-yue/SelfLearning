package replaceNestedBlock.by.ruleEngine;

import replaceNestedBlock.by.enums.Operator;

public class SubtractRule implements Rule {

	private Integer result;
	@Override
	public boolean evaluate(Expression expression) {
		boolean evalResult = false;
		if(expression.getOperator() == Operator.SUBTRACT) {
			this.result = expression.getA() - expression.getB();
			evalResult = true;
		}
		return evalResult;
	}

	@Override
	public Integer getResult() {
		return this.result;
	}

}
