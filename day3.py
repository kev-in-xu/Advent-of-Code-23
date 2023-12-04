
with open( "day3.txt", "r") as file:
    
    # Making a 2D array representation of input
    ## added buffer rows and columns to prevent ArrayIndexOutOfBounds
    blankrow = []
    for i in range(142):
        blankrow.append('.')
    map = []
    map.append(blankrow)
    for line in file:
        line = line.strip()
        row = ['.']
        for char in line:
            row.append(char)
        row.append('.')
        map.append(row)
    map.append(blankrow)

    # PART 2
    """
    What I need to do for part two?

    iterate through the rows to find gears

    check whether they are adjacent to two exact numbers (how do I do that??)

    left is one, right is one
    need to figure out how to check for two on top or bottom
    (i.e. if there's number above, check if middle spot is empty?)
    """


    # Helper method to parse nearby chars as ints
    def getNeighbor(x, y):
        num = int(map[y][x])

        # check left
        if map[y][x-1].isnumeric():
            num = num + 10 * int(map[y][x-1])
            if map[y][x-2].isnumeric():
                num = num + 100 * int(map[y][x-2])

        # check right
        if map[y][x+1].isnumeric():
            num = num * 10 + int(map[y][x+1])
            if map[y][x+2].isnumeric():
                num = num * 10 + int(map[y][x+2])
        return num


    # Helper method to check adjacent cells
    def countNeighbors(x, y):
        neighborList = []
        count = 0
        
        # check left
        if map[y][x-1].isnumeric():
            count = count + 1
            neighborList.append(getNeighbor(x-1, y))
        
        # check right
        if map[y][x+1].isnumeric():
            count = count + 1
            neighborList.append(getNeighbor(x+1,y))

        # check above
        if map[y-1][x].isnumeric():
            count = count + 1
            neighborList.append(getNeighbor(x,y-1))
        else: # check top corners
            if map[y-1][x-1].isnumeric():
                count = count + 1
                neighborList.append(getNeighbor(x-1,y-1))
            if map[y-1][x+1].isnumeric():
                count = count + 1
                neighborList.append(getNeighbor(x+1,y-1))

        # check bottom
        if map[y+1][x].isnumeric():
            count = count + 1
            neighborList.append(getNeighbor(x,y+1))
        else: # check bottom corners
            if map[y+1][x-1].isnumeric():
                count = count + 1
                neighborList.append(getNeighbor(x-1,y+1))
            if map[y+1][x+1].isnumeric():
                count = count + 1
                neighborList.append(getNeighbor(x+1,y+1))

        if count == 2:
            return neighborList


    # Iterates thorugh map of inputs to check for numbers
    sum = 0
    for y in range(len(map)):   
        x = 0
        for x in range(len(map[y])):
            if map[y][x] == '*':
                pair = countNeighbors(x,y)
                if type(pair) == list:
                    prod = pair[0] * pair[1]
                    sum = sum + prod

    print(sum)
        


# PART 1
"""# Helper method to check adjacent cells
    def check(char):
        if char.isnumeric() or char == '.':
            return False
        return True

    # Iterates thorugh map of inputs to check for numbers
    sum = 0
    for y in range(len(map)):
        x = 0
        while x < len(map[y]):
            char = map[y][x]
            num = 0
            if char.isnumeric():
                num = int(char)
                if map[y][x+1].isnumeric():
                    num = num*10 + int(map[y][x+1])
                    if map[y][x+2].isnumeric():
                        num = num * 10 + int(map[y][x+2])

            part = False
            ## RMBR CHECK EXCEPTIONS FOR FIRST AND LAST ROW, FIRST AND LAST COLUMN
            if num >= 100:
                for i in range(5):
                    if check(map[y-1][x-1+i]) == True:
                        part = True
                        break
                if not part:
                    for i in range(5):
                        if check(map[y+1][x-1+i]) == True:
                            part = True
                            break
                if not part:
                    if check(map[y][x-1]) or check(map[y][x+3]) == True:
                        part = True
                x = x + 2
            elif num >= 10:
                for i in range(4):
                    if check(map[y-1][x-1+i]) == True:
                        part = True
                        break
                if not part:
                    for i in range(4):
                        if check(map[y+1][x-1+i]) == True:
                            part = True
                            break
                if not part:
                    if check(map[y][x-1]) or check(map[y][x+2]) == True:
                        part = True
                x = x + 1
            elif num > 0:
                for i in range(3):
                    if check(map[y-1][x-1+i]) == True:
                        part = True
                        break
                if not part:
                    for i in range(3):
                        if check(map[y+1][x-1+i]) == True:
                            part = True
                            break
                if not part:
                    if check(map[y][x-1]) or check(map[y][x+1]) == True:
                        part = True
            #print(map[y][x], end="")
            x = x + 1

            if part and num != 0:
                sum = sum + num
                print(str(num) + " ", end="")
        #print("")
    print(sum)"""


"""
1. make map
2. iterate row by row
3. if number, check next few chars and parse total number
4. if >10, check 2 digit surroudings, if >100, check 3 digit surroundings
    need to watch out for arrayindexoutofbounds
"""
