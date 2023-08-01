import sys

def warble(s):
    newS = ""
    toUpper = True

    for i in range(0, len(s)):
        if (s[i] >= 'a' and s[i] <= 'z') or (s[i] >= 'A' and s[i] <= 'Z'):
            if toUpper:
                newS = newS + s[i].upper()
                toUpper = False
            else:
                newS = newS + s[i].lower()
                toUpper = True
        else:
            newS = newS + s[i]
            
    return newS

    


if __name__ == "__main__":
    s = sys.argv[1]

    print(warble(s))
