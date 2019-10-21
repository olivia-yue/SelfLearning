package replaceNestedBlock.by.enums;

import replaceNestedBlock.common.Operation;

public enum Operator implements Operation {
	ADD{
		@Override
		public int apply(int a, int b) {
			return a+b;
		}

		@Override
		public int apply(int a, int b, int c) {
			return a+b+c;
		}
	}, 
	DIVEDE{

		@Override
		public int apply(int a, int b) {
			return a/b;
		}

		@Override
		public int apply(int a, int b, int c) {
			return (a+b)/c;
		}
	}, 
	MULTIPLY{

		@Override
		public int apply(int a, int b) {
			return a*b;
		}

		@Override
		public int apply(int a, int b, int c) {
			return a*b*c;
		}
		
	}, 
	SUBTRACT{

		@Override
		public int apply(int a, int b) {
			return a-b;
		}

		@Override
		public int apply(int a, int b, int c) {
			return a-b-c;
		}
		
	}

}
