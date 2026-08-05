from collections import deque

class Solution:
    def remainingMethods(self, n: int, k: int, invocations: List[List[int]]) -> List[int]:
        graph = [[] for _ in range(n)]
        for a, b in invocations:
            graph[a].append(b)
        
        suspicious = set()
        suspicious.add(k)
        queue = deque([k])
        while queue:
            node = queue.popleft()
            for nxt in graph[node]:
                if nxt not in suspicious:
                    suspicious.add(nxt)
                    queue.append(nxt)
        
        for a, b in invocations:
            if b in suspicious and a not in suspicious:
                return list(range(n))
        
        return [i for i in range(n) if i not in suspicious]