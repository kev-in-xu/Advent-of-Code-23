with open( "day13.txt", "r") as file:
    lines = [line.strip() for line in file]

pt2 = True
map = []
maps = []
for line in lines:
    if line == "":
        maps.append(map)
        map = []
    else:
        map.append([x for x in line])
maps.append(map)

# counts number of different characters btw two strings
def diff(str1, str2):
    count = 0
    for i in range(len(str1)):
        if str1[i] != str2[i]:
            count +=1
    return count

#cleaned
def getRef(map, axis, pt2):
    val = 0
    if axis == 1:
        arr = [[char for char in a] for a in map]
        map = list(zip(*arr))
    
    idx = 1
    prev = map[0]
    for i in range(len(map[1:])):
        if diff(map[i+1],prev) <= 1:
            oneAway = 0

            j = 0
            while j < i+1 and oneAway <= 1:
                if i-j >= 0 and i+1+j < len(map):
                    oneAway += diff(map[i-j],map[i+1+j])
                j += 1
                        
            if (oneAway == 0 and not pt2) or (oneAway == 1 and pt2):
                val += idx * 100 if axis == 0 else idx

        prev = map[i+1]
        idx += 1
    return val

sum = 0
for map in maps:
    sum += getRef(map,0,pt2)
    sum += getRef(map,1,pt2)
print(sum)