"""
only 12 red cubes, 13 green cubes, and 14 blue cubes

look for numbers >15
then >14
then >13
"""

# part 1
"""with open( "day02.txt", "r") as file:
    sum = 0
    for line in file:
        gameNum = int(line[4:line.find(':')])


        # how to iterate and find all numbers?
        list = line.split(None)
        add = True

        for i in range(2,len(list)):
            word = list[i]
            if word.isnumeric() and int(word) > 12:
                if list[i+1].find("red") != -1:
                    add = False
                    print((word) + list[i+1])
                    print(line)
                    break
                if int(word) > 13:
                    if list[i+1].find("green") != -1:
                        add = False
                        print((word) + list[i+1])
                        print(line)
                        break
                    if int(word) > 14:
                        add = False
                        print((word) + list[i+1])
                        print(line)
                        break


        if (add):
            sum = sum + gameNum
    
    print(sum)"""

# part 2

with open( "day02.txt", "r") as file:
    sum = 0
    for line in file:
        # how to iterate and find all numbers?
        list = line.split(None)
        add = True
        print(len(list) ) #list is always even

        i = len(list)-1
        maxRed = 0
        maxGreen = 0
        maxBlue = 0

        while (i > 1):
            word = list[i]

            if word.find("red") != -1:
                num = int(list[i-1])
                if num > maxRed:
                    maxRed = num

            elif word.find("green") != -1:
                num = int(list[i-1])
                if num > maxGreen:
                    maxGreen = num

            elif word.find("blue") != -1:
                num = int(list[i-1])
                if num > maxBlue:
                    maxBlue = num

            i = i - 2
            
        prod = maxRed * maxGreen * maxBlue
        sum = sum + prod
    
    print(sum)

