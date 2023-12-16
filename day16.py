# Day       Time    Rank  Score       Time    Rank  Score
#  16   00:47:23    2910      0   00:55:00    2573      0

with open( "day16.txt", "r") as file:
    lines = [line.strip() for line in file]

pt2 = True

arr = [[char for char in line] for line in lines]
vis = [[(0,[]) for _ in line] for line in lines]
# 1 means visited, 0 means not
# list will contain directions from which the node has been visited

rows = len(arr)
cols = len(arr[0])

dirs = [(-1,0), (0,1), (1,0), (0,-1)]
# for dir: up = 0, right = 1, down = 2, left = 3

# BFS with queue
def getpaths(queue, arr, vis):

    while queue:
        y,x,dir = queue.pop()

        if y >= rows or y < 0 or x >= cols or x < 0: # if out of bounds
            continue

        pair = vis[y][x]
        if pair[0] == 1 and dir in pair[1]: # if visited from the same direction
            continue

        pt = arr[y][x]
        pair[1].append(dir)
        vis[y][x] = (1, pair[1])
        
        if pt == '.':
            queue.append((y + dirs[dir][0], x + dirs[dir][1], dir)) # go straight
        if pt == '/':
            newdir = (5 - dir) % 4 # maps 0,1,2,3 to 1,0,3,2
            queue.append((y+dirs[newdir][0], x + dirs[newdir][1], newdir))
        if pt == '\\':
            newdir = 3 - dir # maps 0,1,2,3 to 3,2,1,0
            queue.append((y + dirs[newdir][0], x + dirs[newdir][1], newdir))
        if pt == '|':
            if dir == 0 or dir == 2:
                queue.append((y + dirs[dir][0], x + dirs[dir][1], dir)) # go straight
            else:
                queue.append((y-1,x,0))
                queue.append((y+1,x,2))
        if pt == '-':
            if dir == 1 or dir == 3:
                queue.append((y + dirs[dir][0], x + dirs[dir][1], dir)) # go straight
            else:
                queue.append((y,x+1,1))
                queue.append((y,x-1,3))

    # counts number of visited cells
    return sum([[x[0] for x in row].count(1) for row in vis])

def tryStart(y,x,dir,max):
    vis = [[(0,[]) for _ in line] for line in lines]
    queue = [(y,x,dir)]
    count = getpaths(queue, arr, vis)
    if count > max:
        max = count
    return max

if pt2:
    max = 0
    for i in range(cols):
        max = tryStart(0,i,2,max) # top row
        max = tryStart(rows-1,i,0,max) # bot row
    for i in range(rows):
        max = tryStart(i,0,1,max) # left col
        max = tryStart(i,cols-1,3,max) # right col
    print(max)
else:
    print(tryStart(0,0,1,0))