package replaceNestedBlock.by.ruleEngine;

import replaceNestedBlock.by.enums.Operator;

public class Expression {
	private Integer a;
	private Integer b;
	private Operator operator;
	
	public Expression(int a, int b, Operator operator) {
		this.a = a;
		this.b =b;
		this.operator = operator;
	}
	public Integer getA() {
		return a;
	}
	public void setA(Integer a) {
		this.a = a;
	}
	public Integer getB() {
		return b;
	}
	public void setB(Integer b) {
		this.b = b;
	}
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
}
