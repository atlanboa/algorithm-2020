import java.util.Scanner;
import java.util.Stack;

public class SolutionWs2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = 10;
		int n;
		String formular;
		for (int t = 1; t <= T; t++) {
			n = sc.nextInt();
			formular = sc.next();
			System.out.println("#"+t+" "+calculation(doPostOrder(formular)));

		}
	}

	private static int calculation(String postOrder) {
		char[] srr = postOrder.toCharArray();
		Stack<Integer> stack = new Stack<Integer>();
		for (char ch : srr) {
			if (ch >= '0' && ch <= '9') {
				stack.push(ch - '0');
			} else {
				int temp = stack.pop();
				if (ch == '-') {

					stack.push(stack.pop() - temp);
				} else if (ch == '+') {

					stack.push(stack.pop() + temp);
				} else if (ch == '/') {

					stack.push(stack.pop() / temp);
				} else if (ch == '*') {

					stack.push(stack.pop() * temp);
				}
			}
		}

		return stack.pop();
	}

	private static String doPostOrder(String str) {
		String result = "";
		char[] srr = str.toCharArray();
		Stack<Character> stack = new Stack<Character>();
		for (char ch : srr) {
			if (ch == '(')
				stack.push(ch);
			else if (ch == ')') {
				while (true) {

					if (stack.peek() == '(') {
						stack.pop();
						break;
					} else {
						result += stack.pop();
					}
				}

			} else if (ch >= '0' && ch <= '9') {
				result += ch;
			} else {
				if (icp(ch) > isp(stack.peek()))
					stack.push(ch);
				else {
					while (!stack.isEmpty() && icp(ch) <= isp(stack.peek())) {
						result += stack.pop();
					}

					stack.push(ch);
				}
			}
		}

		return result;
	}

	private static int icp(char charAt) {

		if (charAt == '(')
			return 3;
		else if (charAt == '*' || charAt == '/')
			return 2;
		else if (charAt == '+' || charAt == '-')
			return 1;
		else {
			return 0;
		}

	}

	private static int isp(char peek) {

		if (peek == '(')
			return 0;
		else if (peek == '+' || peek == '-')
			return 1;
		else if (peek == '*' || peek == '/')
			return 2;

		return 0;
	}

}
