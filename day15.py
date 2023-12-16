with open( "day15.txt", "r") as file:
    lines = [line.strip() for line in file]

lines = lines[0].split(",")

pt2 = False

arr = [[] for _ in range(256)]

sum = 0

def hash(code):
    num = 0
    for char in code:
        num = ((num + ord(char)) * 17 ) % 256
    return num

def add(arr, code, idx):
    result = hash(code)
    for i in range(len(arr[result])):
        if arr[result][i][0] == code:
            arr[result][i] = (code,idx) # replace
            return
    arr[result].append((code, idx)) # add
    return

def remove(arr, code):
    result = hash(code)
    for i in range(len(arr[result])):
        if arr[result][i][0] == code:
            idx = arr[result][i][1] # gets idx so remove method can be used on tuple
            try:
                arr[result].remove((code, idx))
            finally:
                return
    return

sum = 0
for code in lines:
    if not pt2:
        sum += hash(code)
        continue

    if "=" in code:
        idx = code[-1]
        code = code[:-2]
        add(arr, code, idx)
    elif "-" in code:
        code = code[:-1]
        remove(arr, code)

if pt2:
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            sum += (i+1) * (j+1) * int(arr[i][j][1])
print(sum)