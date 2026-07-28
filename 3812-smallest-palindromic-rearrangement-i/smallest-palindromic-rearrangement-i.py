class Solution:
    def smallestPalindrome(self, s: str) -> str:
        count = [0] * 26
        for char in s:
            count[ord(char) - ord('a')] += 1
            
        left_half = []
        middle_char = ""
        
        for i in range(26):
            c = chr(ord('a') + i)
            freq = count[i]
            
            left_half.append(c * (freq // 2))
            
            if freq % 2 != 0:
                middle_char = c
                
        left_str = "".join(left_half)
        return left_str + middle_char + left_str[::-1]