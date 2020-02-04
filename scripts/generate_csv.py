input_file = "data.txt"

fp = open(input_file, 'r')


def processTitle(line):
    arr = line.split(' ')
    number = (arr[0])[:-1]
    name = arr[1]
    types = (arr[2])[1:-2]
    types = types.split('/')
    type1 = types[0]
    type2 = ""
    if len(types) is 2:
        type2 = types[1]
    return number, name, type1, type2

while True:
    number, name, type1, type2 = processTitle(fp.readline())
    print "{} = {} : {}  /  {}".format(number, name, type1, type2)
    line = fp.readline()
    while len(line.strip()) is not 0:
        line = fp.readline() 
    if len(line) is 0:
        break
