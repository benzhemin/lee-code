

const BRACKET_MAP = new Map([
  ["(", ")"],
  ["{", "}"],
  ["[", "]"],
]);

const isValid = (s) => {
  const stack = [];
  
  for (const c of s) {
    if (BRACKET_MAP.has(c)) {
      stack.push(c);
    } else {
      if (stack.length === 0) return false;

      const top = stack.pop();
      if (c !== BRACKET_MAP.get(top)) return false;
    }
  }

  return stack.length === 0;
};

isValid()