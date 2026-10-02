const finTwoSum = (nums, target) => {
  const map = new Map();

  for(let i = 0; i < nums.length; i++) {
    const gap = target - nums[i];
    if (map.has(gap)) {
      return [i, map.get(gap)];
    }
    map.set(nums[i], i);
  }

  return null;
};

const validate = (nums, target) => {
  const res = finTwoSum(nums, target);
  console.log(res.join(', '));
}


validate([2,7,11,15], 9);
validate([3,2,4], 6);
validate([3, 3], 6);

