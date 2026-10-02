const longestStr = (s) => {
  let max = 0, left = 0;
  const seen = new Map();

  for (let i=0; i<s.length; i++) {
    const c = s[i];

    if (seen.has(c)) {
      left = Math.max(left, seen.get(c) + 1);
    }

    seen.set(c, i);
    max = Math.max(max, i - left + 1);
  }
  
  return max;
}

const validate = (s) => {
  console.log(`max -> ${longestStr(s)}`);
}

validate("abcabcbb");
validate("bbbbb");
validate("pwwkew");
validate("eea");
validate("1R1T7");