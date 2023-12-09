from math import lcm

with open( "day08.txt", "r") as file:
    lines = [line.strip() for line in file]


moves = lines[0]
moves = moves * 100000

class node():
    def __init__(self, data) -> None:
        self.data = data
        self.left = None
        self.right = None
    def __str__(self) -> str:
        return self.data

nodelist = []
namelist = []
childlist = []

for line in lines[2:]:
    nodelist.append(node(str(line[:3])))
    namelist.append(str(line[:3]))
    childlist.append(line[7:-1].split(", "))


for i in range(len(nodelist)):
    left = namelist.index(childlist[i][0])
    right = namelist.index(childlist[i][1])
    nodelist[i].left = nodelist[left]
    nodelist[i].right = nodelist[right]

steps = 0
start = nodelist[namelist.index("AAA")]
current = start

for i in range(len(moves)):
    steps = steps + 1
    if moves[i] == 'L':
        current = current.left
    else:
        current = current.right
    if current.data == "ZZZ":
        break


startnodes = [nodelist[i] for i in range(len(namelist)) if namelist[i][2] == "A"]

print([node.data for node in startnodes])
    
steps = 0
currentnodes = startnodes
reqmoves = [0] * 6
for i in range(len(moves)):
    steps = steps + 1
    for j in range(len(currentnodes)):
        if moves[i] == 'L':
            currentnodes[j] = currentnodes[j].left
            # print(currentnodes[j].left.data)
        else:
            currentnodes[j] = currentnodes[j].right
            # print(currentnodes[j].right.data)

    for k in range(len(currentnodes)):
        # print(node.data)
        if currentnodes[k].data[2] == "Z":
            reqmoves[k] = steps
            print("found XXZ at " + str(steps))
    
    try: 
        reqmoves.index(0)
    except:
        break

temp = 1
for i in range(len(reqmoves)):
    temp = lcm(temp, reqmoves[i])

print(temp)

    


#print([node.right.data for node in nodelist])



