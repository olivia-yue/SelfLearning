package replaceNestedBlock.by.factoryClass;

import replaceNestedBlock.common.Operation;

public class Multiplication implements Operation {

	@Override
	public int apply(int a, int b) {
		return a*b;
	}

	@Override
	public int apply(int a, int b, int c) {
		return a*b*c;
	}

}
