'''
File: subcipher.py
Authors: Yuxuan Li & Yuwei Wnag
Lab: Wednesday Lab

Description: This script contains two cipher subclasses
'''

from cipher import Cipher

class SubstitutionCipher(Cipher):
    '''represents a simple substituion cipher with a keyword'''
    
    def __init__(self, keyword = '', type = 'SimpleSubstitution'):
        self.keyword = keyword
        self.type = type
        self.cipher_alphabet = simplesub(self.keyword)

'''
Define a global function that makes the cipher_alphabet with the keyword
    1. Attach the keyword in front of the plain alphabet
    2. Examine each letter starting from the last one.
    3. If the letter is present elsewhere in the string, chop it off.
    4. After the examination, assign whatever is left to the cipher_alphabet.
'''
def simplesub(keyword):
    plain = 'abcdefghijklmnopqrstuvwxyz'
    check = keyword + plain
    i = len(check) - 1
    while i > 0:
        ch = check[i]
        temp = check[:i]
        if ch in temp:
            check = temp + check[i+1:]
        i -= 1
    cipher = check
    return cipher


class MyCipher(Cipher):
    '''represents a nonalphabetic cipher'''
    
    def __init__(self, key = '', type = 'Nonalphabetic'):
        self.key = key
        self.type = type
        self.cipher_alphabet = '_+}#)&`~!@$%^*(-=|{[]\:;?/'

print 'Below examines the global function >'
print 'The function should print: xylophneabcdfhijkmqrstuvwz'
print 'The function prints: ' + simplesub('xylophone')
print

print 'Below examines the substitution cipher class >'
subcipher = SubstitutionCipher('xylophone')
result = subcipher.encrypt('I have weapon')
decrypt = subcipher.decrypt(result)

print 'The class encrypts "i have weapon" as: ' + result
print 'The class encrypts "' + result + '" as: ' + decrypt
print

print 'Below examines the nonalpha cipher class >'
my = MyCipher()
encrypt1 = my.encrypt('I have weapon')
decrypt1 = my.decrypt(encrypt1)
print 'The class encrypts "i have weapon" as: ' + encrypt1
print 'The class encrypts "' + encrypt1 + '" as: ' + decrypt1


# Below is the output of the script
/usr/bin/python -u  "/home/yli2/cpsc115/project1/subcipher.py"
Below examines the global function >
The function should print: xylophneabcdfhijkmqrstuvwz
The function prints: xylophneabcdfgijkmqrstuvwz

Below examines the substitution cipher class >
The class encrypts "i have weapon" as: I extp upxjig
The class encrypts "I extp upxjig" as: I have weapon

Below examines the nonalpha cipher class >
The class encrypts "i have weapon" as: I ~_\) :)_-(*
The class encrypts "I ~_\) :)_-(*" as: I have weapon

