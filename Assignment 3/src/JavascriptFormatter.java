/**
 * 
 * @author Ralph Huang
 * 	email: ralph.huang@stonybrook.edu
 * 	Stony Brook ID: 110905260
 *
 */
public class JavascriptFormatter {
	private JSStack stack = new JSStack(); //Instantiating a JSStack for input
	private int indentLevel; //The number of tabs that should be on the code input
	public boolean isFor = false; //Checks to see if the code is currently within a for loop

	/**
	 * Returns the input string properly as code
	 * @param input
	 * <dt><b>Preconditions:</b>
	 * <dd><CODE>input</CODE> is not null
	 * <dt><b>Postconditions:</b>
	 * <dd><CODE>input</CODE> is not properly formatted as a code, and also lets the user know if there are any errors with parenthesis, and braces.
	 * @return
	 * The return value is the <CODE>input</CODE> but now changed to be a formatted string.
	 */
	public String format(String input)
	{
		String answer = "";

		//Reading String
		for(int i = 0; i < input.length(); i++)
		{
			char test = input.charAt(i);

			//Testing Parenthesis
			if(test == '(')
			{
				stack.push(BlockType.PAREN);
				answer += "(";
			}

			else if(test == ')')
			{
				if(isFor)
				{
					if(stack.peek() == BlockType.PAREN)
					{
						stack.pop();
						isFor = false;
						answer += ")";
					}
					else
					{
						if(stack.peek() == BlockType.FOR || stack.peek() == BlockType.BRACE)
							answer += "\n//Extra parenthesis here.";
						return answer;
					}
					stack.pop();
				}
				else
				{
					if(stack.peek() == BlockType.FOR || stack.peek() == BlockType.BRACE)
					{
						answer += ")\n//Extra parenthesis here.";
						return answer;
					}
					else {
					answer += ")";
					stack.pop();
					}
				}
			}

			//Testing Braces
			else if(test == '{')
			{
				if(stack.peek() != BlockType.PAREN)
				{
					stack.push(BlockType.BRACE);
					answer += "{";
					answer += "\n";
					indentLevel++;
					for(int j = 0; j < indentLevel; j++)
						answer += "\t";
				}
				else
				{
					answer += "Missing parenthesis here";
					return answer;
				}
			}

			else if(test == '}')
			{
				if(stack.peek() == BlockType.BRACE)
				{
					stack.pop();
					answer += "}\n";
					indentLevel--;
					
					if(input.length() - i > 1 && input.charAt(i+1) != '}')
						indentLevel++;
					else if(input.length() - i > 6)
					{
						 if(input.charAt(i+2) == 'e' && input.charAt(i+3) == 'l' && input.charAt(i+4) == 's'
							        && input.charAt(i+5) == 'e' && input.charAt(i+6) == ' ' && input.charAt(i+7) == '{')
							       indentLevel++;
					}
					for(int j = 0; j < indentLevel; j++)
						answer += "\t";
				}
				else if(stack.peek() == BlockType.PAREN)
				{
					answer += ")\n//Missing closing parenthesis here.";
					return answer;
				}
				else
				{
					answer += "}\n//Missing closing bracket here.";
					return answer;
				}
			}

			//Testing For
			else if(input.length() - i > 3 && test == 'f' && input.charAt(i+1) == 'o' && input.charAt(i+2) == 'r' && input.charAt(i+3) == '(')
			{
				stack.push(BlockType.FOR);
				isFor = true;
				answer += input.charAt(i);
			}

			//Testing semicolon
			else if(test == ';')
			{
				if(isFor)
				{
					answer += ";";
				}
				else
				{
					if(stack.peek() == BlockType.PAREN)
					{
						answer += "\n//Missing closing parenthesis here.";
						return answer;
					}
					answer += ";\n";
					if(input.length() - i > 1)
					{
					if(input.charAt(i+1) == '}')
						indentLevel--;	
					}
					for(int j = 0; j < indentLevel; j++)
						answer += "\t";
				}
			}

			//Testing default characters
			else
			{
				stack.push(input.charAt(i));
				answer += test;
			}
		}

		//finished with entire string, checking if there is anything left in the stack.
		if(stack.peek() == BlockType.BRACE)
		{
			answer += "\n//Missing closing brace.";
			return answer;
		}
		else if(stack.peek() == BlockType.PAREN)
		{
			answer += "\n//Missing closing parenthesis.";
			return answer;
		}
		else if(stack.peek() == BlockType.FOR)
		{
			answer += "\n//Missing parenthesis.";
			return answer;
		}
		return answer;
	}

	/**
	 * default constructor
	 */
	public JavascriptFormatter()
	{


	}

}
