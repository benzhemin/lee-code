package p20;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ValidParentheses {

  static Map<Character, Character> BRACKET_MAP = Map.of('(', ')', '{', '}', '[', ']');
  static Set<Character> KEY_SET = BRACKET_MAP.keySet();

  public static boolean isValidParentheses(String input) {
    Deque<Character> stack = new ArrayDeque<Character>();

    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      if (KEY_SET.contains(c)) {
        stack.push(c);
      } else {
        if (stack.isEmpty())
          return false;

        Character popChar = stack.pop();
        Character expectChar = BRACKET_MAP.get(popChar);

        if (expectChar != c)
          return false;
      }
    }

    return stack.isEmpty();
  }

  public static <T> T require(T actual, T expected, String msg) {
    if (!Objects.equals(actual, expected))
      throw new IllegalStateException(
          String.format("%s: expected=%s, actual=%s", msg, expected, actual));
    return actual;
  }

  public static void main(String[] args) {
    // For given test cases

    String s1 = "()";
    require(isValidParentheses(s1), true, "error");

    String s2 = "()[]{}";
    require(isValidParentheses(s2), true, "error");

    String s3 = "(]";
    require(isValidParentheses(s3), false, "error");

    String s4 = "([])";
    require(isValidParentheses(s4), true, "error");

    String s5 = "([)]";
    require(isValidParentheses(s5), false, "error");

    System.out.println("passed");
  }
}
