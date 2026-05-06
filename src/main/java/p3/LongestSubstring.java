package p3;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongestSubstring {
  private static final Logger log = LoggerFactory.getLogger(LongestSubstring.class);

  public int lengthOfLongestSubstring(String s) {
    int maxLength = 0;
    List<String> subList = new ArrayList<>();

    for (int i = 0; i < s.length(); i++) {
      String cur = s.substring(i, i + 1);

      if (subList.contains(cur)) {
        int index = subList.lastIndexOf(cur);
        maxLength = Math.max(maxLength, subList.size());

        // Reset subList to where the duplicate occurs.
        subList = subList.subList(index + 1, subList.size());
        subList.add(cur);
      } else {
        subList.add(cur);
      }
    }

    maxLength = Math.max(maxLength, subList.size());

    return maxLength;
  }

  public static void main(String[] args) {
    LongestSubstring longestSubstring = new LongestSubstring();

    String s = "abcabcbb";
    log.info("lengthOfLongestSubstring({}) = {}", s, longestSubstring.lengthOfLongestSubstring(s));

    String s1 = "bbbbb";
    log.info("lengthOfLongestSubstring({}) = {}", s1, longestSubstring.lengthOfLongestSubstring(s1));

    String s2 = "pwwkew";
    log.info("lengthOfLongestSubstring({}) = {}", s2, longestSubstring.lengthOfLongestSubstring(s2));

    String s3 = "bpfbhmipx";
    log.info("lengthOfLongestSubstring({}) = {}", s3, longestSubstring.lengthOfLongestSubstring(s3));
  }
}
