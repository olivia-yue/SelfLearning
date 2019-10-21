package replaceNestedBlock.by.factoryClass;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import replaceNestedBlock.common.Operation;

public class OperatorFactory {
	
	static Map<String, Operation> operationMap = new HashMap<>();
	static {
		operationMap.put("add", new Addition());
		operationMap.put("multiply", new Multiplication());
	}
	
	static public Optional<Operation> getOperation(String operator){
		return Optional.ofNullable(operationMap.get(operator));
	}

}
