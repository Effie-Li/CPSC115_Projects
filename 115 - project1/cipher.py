# File: cipher.py
# Author: cpsc115
# Description: Defines modules needed for implementing various
#  kinds of subsitution ciphersn

class Cipher(object):
    """represents a cipher that encrypts and decrypts"""

    plain_alphabet = 'abcdefghijklmnopqrstuvwxyz'
    cipher_alphabet = 'abcdefghijklmnopqrstuvwxyz'

    # Subclasses will have different types of keys
    # Type is a string like "Caesar"
    def __init__(self, key='', type=''):
        self.key = key
        self.type = type

    # String represenation of the object is just its type
    def __str__(self):
        return self.type +  " cipher"

    #  Returns the encryption of the plaintext str
    def encrypt(self, str):
        result = ''
        for ch in str:
            x = self.plain_alphabet.find(ch)
            if x != -1:
                result += self.cipher_alphabet[x]
            else:
                result += ch
        return result

    #  Returns the decryption of the ciphertext str
    def decrypt(self, str):
        result = ''
        for ch in str:
            x = self.cipher_alphabet.find(ch)
            if x != -1:
                result += self.plain_alphabet[x]
            else:
                result += ch
        return result

class CaesarCipher (Cipher):
    """represents a Caesar cipher with a key in the range 1..25"""

    # Constructs the cipher_alphabet
    def __init__(self, key=3, type="Caesar"):
        self.key = key
        self.type = type
        self.cipher_alphabet = self.plain_alphabet[self.key:] + self.plain_alphabet[0:self.key]

''' Commented out
caesar = CaesarCipher(3, "Caesar")
secret = caesar.encrypt("hello this is a test")
print 'The secret message is ', secret
print 'I decrypted it to ', caesar.decrypt(secret)
print
secret = caesar.encrypt("abcdefghijklmnopqrstuvwxyz")
print 'The secret message is ', secret
print 'I decrypted it to ', caesar.decrypt(secret)
'''

