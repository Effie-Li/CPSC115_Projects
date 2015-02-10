'''
File: subcipher.py
Authors: Yuxuan Li & Yuwei Wnag
Lab: Wednesday Lab

Description: This script contains one analyzer subclasses
'''

from cryptoanalyzer import Cipher, CaesarCipher, CryptoAnalyzer

class CaesarAnalyzer(CryptoAnalyzer):
    '''Analyzes plaintext messages'''
    
    '''Overriding the default analyzer
        1. It tries every possible shift until it gets an valid output.
        2. For every shift, examine the words in decrypted message. 
            If 3 or more words is contained in the crosswords dictionary,
            return the output.
        3. If not in one shift the did the decrypted message contain 3 or 
            more words in the dictionary, return sorry message.
    '''
    def analyze(self,message):
        for shift in range(26):
            caesar = CaesarCipher(shift)
            decrypt = caesar.decrypt(message)
            words = decrypt.split()
            total = 0
            for word in words:
                if word in self.dictionary:
                    total += 1
            if total >= 3:
                return decrypt
        return "Sorry I can't decrypt: " + message

print
print 'Below examines the CaesarAnalyzer class >'
caesar = CaesarCipher(3)
encrypt = caesar.encrypt('I have the ultimate weapon')
print "It'll try to crack '" + encrypt + "' "
crack = CaesarAnalyzer()
result = crack.analyze(encrypt)
print 'Here is its result: ' + result
print 'It works!'


Below is the output of the above script.
/usr/bin/python -u  "/home/yli2/cpsc115/project1/caesaranalyzer.py"

Below examines the CaesarAnalyzer class >
It'll try to crack 'I kdyh wkh xowlpdwh zhdsrq' 
Here is its result: I have the ultimate weapon
It works!

