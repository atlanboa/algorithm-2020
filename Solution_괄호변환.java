import java.util.Stack;

class Solution_괄호변환 {
    public String solution(String p) {
    	String answer =  recursive(p);
        return answer;
    }
    
	private String recursive(String w) {
		
		String u = "" ,  v="", result="";
		if(w.equals("")) {
			return w;
		}else {
			// w를 균형잡힌 문자열 u와 나머지 v로 분리

			int lCnt = 0, rCnt = 0, index =0;
			for(int i=0; i<w.length(); i++) {
				if(w.charAt(i)=='(') lCnt++;
				else if(w.charAt(i)==')') rCnt++;
				
				if(rCnt==lCnt) {
					index = i + 1;
					break;
				}
			}
			
			u = w.substring(0, index);
			v = w.substring(index);
			
			// u가 올바른 괄호 문자열인가?
			if(isCorrect(u)) {
				result = u + recursive(v);
			}else {
				result = "(" + recursive(v) + ")" + reverse(u);
			}

			
			
		}
		
		
		return result;
	}

	private String reverse(String u) {
		String result = "";
		
		if(u.length() >= 2) {
			for(int i=0; i<u.length(); i++) {
				
				if(i==0 || i == (u.length()-1)) {
					continue;
				}
				
				result += u.charAt(i) == '('? ')' : '(';
			}
		}
		
		return result;
	}

	private boolean isCorrect(String u) {
		boolean flag = true;
		Stack<Character> stack = new Stack<>();
		for(char c : u.toCharArray()) {
			if(c == '(') stack.push(c);
			else if(!stack.isEmpty()) stack.pop();
			else if(stack.isEmpty()) {
				flag = false;
				break;
			}
		}
		
		if(!stack.isEmpty()) flag = false;
		
		return flag;
	}
    /*
    1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다. 
	2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다. 
	3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다. 
  		3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다. 
	4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다. 
  	4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다. 
  	4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다. 
  	4-3. ')'를 다시 붙입니다. 
	4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다. 
  	4-5. 생성된 문자열을 반환합니다.
     */


}