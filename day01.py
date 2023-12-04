"""
iterate from front
iterate from end
interpret as int
sum

pt 2
"""

# part 1
"""
with open( "day01.txt", "r") as file:
    sum = 0
    for line in file:
        for i in range(len(line)):
            if line[i].isnumeric():
                tens = int(line[i]) * 10
                break
        for i in range(len(line)):
            if line[len(line)-1-i].isnumeric():
                ones = int(line[len(line)-1-i])
                break
            line[i].isnumeric
        
        num = tens + ones
        sum = sum + num
    print(sum)"""



"""
one
two
three
four
five
six
seven
eight
nine

o, t, e, s, f, n

e, o, r, x, n, t
"""
# part 2
with open( "day01.txt", "r") as file:
    sum = 0
    for line in file:
        for i in range(len(line)):
            char = line[i]
            if char.isnumeric():
                tens = int(char) * 10
                break
            if char == 'o':
                if line[i:i+3] == "one":
                    tens = 10
                    break
            elif char == 't':
                if line[i:i+3] == "two":
                    tens = 20
                    break
                if line[i:i+5] == "three":
                    tens = 30
                    break
            elif char == 'f':
                if line[i:i+4] == "four":
                    tens = 40
                    break
                if line[i:i+4] == "five":
                    tens = 50
                    break
            elif char == 's':
                if line[i:i+3] == "six":
                    tens = 60
                    break
                if line[i:i+5] == "seven":
                    tens = 70
                    break
            elif char == 'e':
                if line[i:i+5] == "eight":
                    tens = 80
                    break
            elif char == 'n':
                if line[i:i+4] == "nine":
                    tens = 90
                    break

        for i in range(len(line)):
            char = line[len(line)-1-i]
            if char.isnumeric():
                ones = int(char)
                break
            # e, o, r, x, n, t
            

            elif char == 'e':
                if line[len(line)-i-3:len(line)-i] == "one":
                    ones = 1
                    break
                if line[len(line)-i-5:len(line)-i] == "three":
                    ones = 3
                    break
                if line[len(line)-i-4:len(line)-i] == "five":
                    ones = 5
                    break
                if line[len(line)-i-4:len(line)-i] == "nine":
                    ones = 9
                    break
            elif char == 'o':
                if line[len(line)-i-3:len(line)-i] == "two":
                    ones = 2
                    break
            elif char == 'r':
                if line[len(line)-i-4:len(line)-i] == "four":
                    ones = 4
                    break
            elif char == 'x':
                if line[len(line)-i-3:len(line)-i] == "six":
                    ones = 6
                    break
            elif char == 'n':
                if line[len(line)-i-5:len(line)-i] == "seven":
                    ones = 7
                    break
            elif char == 't':
                if line[len(line)-i-5:len(line)-i] == "eight":
                    ones = 8
                    break
        num = tens + ones
        sum = sum + num
    print(sum)
