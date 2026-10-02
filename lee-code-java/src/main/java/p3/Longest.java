package p3;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
public class Longest {

  public static int findLongest(String s) {
    int left = 0, max = 0;
    Map<Character, Integer> seen = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (seen.containsKey(c)) {
        left = Math.max(left, seen.get(c) + 1);
      }

      seen.put(c, i);
      max = Math.max(max, i - left + 1);
    }
    return max;
  }

  public static void validate(String s) {
    int max = findLongest(s);
    log.info("{}", max);
  }

  public static void main(String[] args) {
    validate("abcabcbb");
    validate("bbbbb");
    validate("pwwkew");
    validate("eea");
    validate("1R1T7");
  }
}
