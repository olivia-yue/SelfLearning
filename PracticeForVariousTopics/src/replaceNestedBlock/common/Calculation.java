package replaceNestedBlock.common;

import replaceNestedBlock.by.commandPattern.AddCommand;
import replaceNestedBlock.by.commandPattern.Command;
import replaceNestedBlock.by.commandPattern.DivideCommand;
import replaceNestedBlock.by.commandPattern.PerformCalculation;
import replaceNestedBlock.by.enums.Operator;
import replaceNestedBlock.by.factoryClass.OperatorFactory;
import replaceNestedBlock.by.ruleEngine.Expression;
import replaceNestedBlock.by.ruleEngine.RuleEngine;

public class Calculation {
	
	public static int calculationUsingFactory(String operator, int a, int b) {
		Operation targerOperation = OperatorFactory.getOperation(operator)
				.orElseThrow(() -> new IllegalArgumentException("Illegal Operator!"));
		return targerOperation.apply(a, b);
	}
	
	public static int calculationUsingEnums(Operator operator, int a, int b) {
		return operator.apply(a,b);
	}
	
	public static int calculationUsingCommandPattern(Command command) {
		PerformCalculation perform = new PerformCalculation();
		return perform.calcalate(command);
	}
	
	public static int calculationUsingRuleEngine(Expression expression) {
		RuleEngine engine = new RuleEngine();
		return engine.process(expression);
	}
	
	public static void main(String args[]) {
		int output;
		output = calculationUsingFactory("add", 5 ,6);
		System.out.println(output);
		output = calculationUsingFactory("multiply",3,5);
		System.out.println(output);
		try{
			calculationUsingFactory("divide",3,5);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		output = calculationUsingEnums(Operator.ADD, 5, 8);
		System.out.println(output);
		output = calculationUsingEnums(Operator.valueOf("DIVEDE"), 7, 3);
		System.out.println(output);
		output = calculationUsingEnums(Operator.SUBTRACT, 4, 8);
		System.out.println(output);
		try {
			output = calculationUsingEnums(Operator.valueOf("multiply"),5, 9);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		Command addCommand = new AddCommand(5,7);
		output = calculationUsingCommandPattern(addCommand);
		System.out.println(output);
		Command divideCommand = new DivideCommand(6,4);
		output = calculationUsingCommandPattern(divideCommand);
		System.out.println(output);
		try {
			divideCommand = new DivideCommand(3,0);
			output = calculationUsingCommandPattern(divideCommand);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		Expression expression = new Expression(1,5,Operator.valueOf("ADD"));
		output = calculationUsingRuleEngine(expression);
		System.out.println(output);
		expression = new Expression(1,5,Operator.DIVEDE);
		output = calculationUsingRuleEngine(expression);
		System.out.println(output);
	}

}
