class Solution:
    def solveQueries(self, nums: List[int], queries: List[int]) -> List[int]:
        n = len(nums)
        pos = defaultdict(list) 
        answer = [inf] * n

        for i, num in enumerate(nums):
            positions = pos[num]
            if not positions:
                positions.append(i)
                continue

            first, last = positions[0], positions[-1]
            to_last, to_first = i - last, n - i + first
            answer[i] = min(to_first, to_last)
            answer[last] = min(answer[last], to_last)
            answer[first] = min(answer[first], to_first)

            if len(positions) < 2:
                positions.append(i)
            else:
                positions[1] = i

        return [answer[q] if answer[q] != inf else -1 for q in queries]