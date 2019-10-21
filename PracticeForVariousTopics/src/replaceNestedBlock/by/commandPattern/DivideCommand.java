package replaceNestedBlock.by.commandPattern;

public class DivideCommand implements Command {
	
	private int a;
	private int b;
	
	public DivideCommand(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public Integer execute() {
		if(b == 0) {
			throw new IllegalArgumentException("The divident cannot be ZERO!");
		}
		return a/b;
	}

}
