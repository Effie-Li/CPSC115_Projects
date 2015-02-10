from cipher import Cipher, CaesarCipher

class CryptoAnalyzer(object):
    """Analyzes various types of ciphers"""

    dictionary = []

    def __init__(self, type="", dict='crosswords.txt'):
        self.type = type
        self.dict = dict
        self.dictionary = self.input_dictionary(dict)
        
    def __str__(self):
        return self.type + " analyzer"

    # Cracks the secret message if it is
    #  encrypted in this type
    def crack(self, message):
        return self.analyze(message)

    # Default analyzer does nothing
    def analyze(self, message):
        return message

    # Inputs the dictionary
    def input_dictionary(self,dict):
        f = open(dict)
        words = []
        for line in f:
          word = line.strip()
          words.append(word)
        return words

class PlainAnalyzer(CryptoAnalyzer):
    """Analyzes plaintext messages"""

class CaesarAnalyzer(CryptoAnalyzer):
    """Analyzes plaintext messages"""

    # Overriding the default analyzer
    def analyze(self, message):
       for shift in range(26):
           caesar = CaesarCipher(shift)
           decrypt = caesar.decrypt(message)
           answer = raw_input("Y or N, is this the message: " + decrypt + " : " )
           if answer == 'Y' or answer == 'y':
               return decrypt
'''
       for shift in range(26):
           caesar = CaesarCipher(shift)
           decrypt = caesar.decrypt(message)
           words = decrypt.split()
           count = 0
           for word in words:
               if word in self.dictionary:
                   count += 1
           if count >= 3:
               return decrypt
       return "Sorry I can't decrypt: " + message
'''
'''
'''



'''
caesar = CaesarCipher(3)
plain = "Hi, this is a secret message"
secret = caesar.encrypt(plain)
print

analyzer = PlainAnalyzer("Plain")
print "Hi this is", analyzer
print "I am decrypting", plain
print "Here is my result:", analyzer.crack(plain)

analyzer = CaesarAnalyzer("Caesar")
print "Hi this is", analyzer
print "I am decrypting", secret
print "Here is my result:", analyzer.crack(secret)

'''



