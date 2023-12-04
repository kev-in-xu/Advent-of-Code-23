with open( "day04.txt", "r") as file:
    # PART 2
    """
    set up a list of indices and their counts
    Card = 

    Need to keep track of Card No., copies, points


    Step 1: card 1 gives 4 instances
    Step 2: add +1 number to 
    """
    # Make list of cards
    cards = []
    sum = 0
    for i in range(199):
        card = {"num": 0, "copies": 0, "points": 0}
        card["num"] = i+1
        card["copies"] = 1
        sum = sum + 1
        cards.append(card)

    # add point values to cards based on matching numbers
    for line in file:
        count = 0

        index = int(line[5:8].strip()) - 1
        nums = line[10:39].replace("  ", " ").split(" ")
        winNums = line[42:].strip().replace("  ", " ").split(" ")
        for num in nums:
            if num in winNums:
                count = count+1
        cards[index]["points"] = count

    # use point values to redeem additional copies of cards
    for card in cards:
        copies = card["copies"]
        points = card["points"]
        num = card["num"] # num is already the array index + 1, hence why +1 not needed in line 44
        print(card)
        try:
            for i in range(points):
                sum = sum + copies
                cards[num+i]["copies"] = cards[num+i]["copies"] + copies
        except:
            continue
    
    print(sum)

    """
    ok, so what's really going on here?

    if I have 
    1, 1, 2
    2, 1, 1
    3, 1, 4
    4, 1 ,3

    1, 1, 2
    2, 2, 1
    3, 2, 4
    4, 1 ,3


    copies start at 4

    add 2 to sum
    add 1 to poitns, add 4 to points
    """

    
    
    
    
    
    
    
    
    # PART 1
    """sum = 0
    for line in file:
        count = 0
        points = 0
        index = line[5:8].strip()
        nums = line[10:39].replace("  ", " ").split(" ")
        winNums = line[42:].strip().replace("  ", " ").split(" ")
        
        for num in nums:
            if num in winNums:
                count = count+1
                #print(num)
        print("count" + str(count))
        if count != 0:
            points = 2**(count-1)
            print(points)
            sum = sum + points

    print(sum)"""
